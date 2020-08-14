Cache Clear
=============

This is a WebView cache plugin for Cordova 6.1.1+ supporting Android (>=4.1) and iOS(>=6.0).
It allows to clear the cordova webview cache.

* cache.clearCache(successCallback, errorCallback)
* cache.clearAll(successCallback, errorCallback)

Installation
======
You may use Cordova CLI as follows:

<pre>
cordova plugin add cordova-plugin-cache-clear
</pre>

Usage
====
```javascript
document.addEventListener('deviceready', function() {
    var success = function(status) {
        alert('Message: ' + status);
    };
    var error = function(status) {
        alert('Error: ' + status);
    };
    window.cache.clearCache(success, error);
    // - or -
    window.cache.clearAll(success, error);
});
```

Android vs. iOS
======

On iOS, CacheClear deletes temporary files (images that have been downloaded by the app).
On Android, CacheClear also deletes all local, persistent data (such as stored files and any data saved to localStorage).
