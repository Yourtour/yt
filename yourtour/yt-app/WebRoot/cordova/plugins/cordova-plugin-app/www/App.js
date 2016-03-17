var exec = require('cordova/exec');

exports.getVersion = function(success, error) {
    exec(success, error,"App","getVersion", []);
};

exports.install = function(file, success, error) {
    exec(success, error,"App","install", [file]);
};