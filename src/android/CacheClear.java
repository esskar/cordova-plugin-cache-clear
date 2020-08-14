package com.anrip.cordova;

import org.json.JSONException;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;

import android.annotation.TargetApi;
import android.app.Activity;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebStorage;

@TargetApi(19)
public class CacheClear extends CordovaPlugin {

    private static final String LOG_TAG = "CacheClear";
    private static final String MESSAGE_TASK = "Cordova Android CacheClear() called.";
    private static final String MESSAGE_ERROR = "Error while clearing webview cache.";

    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        Log.v(LOG_TAG, MESSAGE_TASK);

        if (action.equals("task")) {
            task(callbackContext, args.getBoolean(0));
            return true;
        }

        return false;
    }

    public void task(CallbackContext callbackContext, boolean clearAll) {
        final CacheClear self = this;
        final CallbackContext callback = callbackContext;

        cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                try {
                    if (clearAll) {
                        // Clear all the Application Cache, Web SQL Database and the HTML5 Web Storage
                        WebStorage.getInstance().deleteAllData();

                        // Clear all the cookies
                        CookieManager.getInstance().removeAllCookies(null);
                        CookieManager.getInstance().flush();

                        self.webView.clearCache(true);
                        // self.webView.clearFormData();
                        self.webView.clearHistory();
                        // self.webView.clearSslPreferences();
                    } else {
                        // clear the cache
                        self.webView.clearCache(true);
                    }

                    // send success result to cordova
                    PluginResult result = new PluginResult(PluginResult.Status.OK);
                    result.setKeepCallback(false);
                    callback.sendPluginResult(result);
                } catch (Exception e) {
                    Log.e(LOG_TAG, MESSAGE_ERROR);
                    // return error answer to cordova
                    PluginResult result = new PluginResult(PluginResult.Status.ERROR, MESSAGE_ERROR);
                    result.setKeepCallback(false);
                    callback.sendPluginResult(result);
                }
            }
        });
    }

}
