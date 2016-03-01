cordova.define("cordova-plugin-extra.ExtraInfo", function(require, exports, module) { var exec = require('cordova/exec');
 
exports.getExtra = function(success, error) {
    exec(success, error,"ExtraInfo","getExtra", []);
};
});
