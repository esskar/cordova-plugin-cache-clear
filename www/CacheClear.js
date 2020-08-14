//////////////////////////////////////////
// CacheClear.js
// Copyright (C) 2016 Anrip <mail@anrip.com>
//
//////////////////////////////////////////

var exec = require('cordova/exec');

var clearCache = function (success, error) {
    exec(success, error, 'CacheClear', 'task', [false]);
};

var clearAll = function (success, error) {
    exec(success, error, 'CacheClear', 'task', [true]);
};

module.exports.clearCache = clearCache;
module.exports.clearAll = clearAll;
