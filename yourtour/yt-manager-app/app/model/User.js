/**
 * Created by john on 15-8-30.
 */
Ext.define('yt_manager_app.model.User', {
    extend: 'Ext.data.Model',

    fields: [{
        name: 'id', type: 'int', defaultValue: -1
    }, {
        name: 'userName', type: 'string'
    }, {
        name: 'password', type: 'string'
    }, {
        name: 'realName', type: 'string'
    }, {
        name: 'nickName', type: 'string'
    }, {
        name: 'gender', type: 'string', defaultValue: 'NA'
    }, {
        name: 'birthday', type: 'string'
    }, {
        name: 'imageUrl', type: 'string'
    }, {
        name: 'character', type: 'string'
    }, {
        name: 'mobileNo', type: 'string'
    }, {
        name: 'email', type: 'string'
    }, {
        name: 'residence', type: 'string'
    }, {
        name: 'nativePlace', type: 'string'
    }, {
        name: 'constellation', type: 'string'
    }, {
        name: 'role', type: 'string', defaultValue: 'MEMBER'
    }, {
        name: 'rank', type: 'int', defaultValue: 5
    }, {
        name: 'status', type: 'string', defaultValue: 'DRAFT'
    }, {
        name: 'slogan', type: 'string'
    }, {
        name: 'createdUserId', type: 'string'
    }, {
        name: 'createdTime', type: 'int'
    }, {
        name: 'updatedUserId', type: 'string'
    }, {
        name: 'updatedTime', type: 'int'
    }, {
        name: 'rowKey', type: 'string'
    }, {
        name: 'roleName', calculate: function (data) {
            var code = data.role;
            if (code == 'EXPERT') {
                return '达人';
            } else if (code == 'HOST') {
                return '地主';
            } else {
                return '一般会员';
            }
        }
    }, {
        name: 'createdTimeStr', calculate: function (data) {
            var time = data.createdTime,
                date = new Date();
            date.setTime(time);
            return Ext.Date.format(date, 'Y/m/d H:i:s');
        }
    }, {
        name: 'updatedTimeStr', calculate: function (data) {
            if (data.updatedUserId == null || data.updatedUserId == '') {
                return '';
            }
            var time = data.updatedTime,
                date = new Date();
            date.setTime(time);
            return Ext.Date.format(date, 'Y/m/d H:i:s');
        }
    }],
    idProperty: 'id'
});