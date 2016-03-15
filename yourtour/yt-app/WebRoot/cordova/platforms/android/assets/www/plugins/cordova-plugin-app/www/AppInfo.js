cordova.define("cordova-plugin-app.AppInfo", function(require, exports, module) { var exec = require('cordova/exec');

exports.getVersion = function(success, error) {
    exec(success, error,"AppInfo","getVersion", []);
};
});
