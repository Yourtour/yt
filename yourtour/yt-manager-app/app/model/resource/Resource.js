/**
 * Created by john on 15-9-11.
 */
Ext.define('yt_manager_app.model.resource.Resource', {
    extend: 'Ext.data.Model',

    // TODO 字段未修改

    fields: [{
        name: 'id', type: 'int', defaultValue: -1
    }, {
        name: 'code', type: 'string'
    }, {
        name: 'name', type: 'string'
    }, {
        name: 'imageUrl', type: 'string'
    }, {
        name: 'openTime', type: 'string'
    }, {
        name: 'closeTime', type: 'string'
    }, {
        name: 'trafficIntro', type: 'string'
    }, {
        name: 'payment', type: 'string'
    }, {
        name: 'star', type: 'int', defaultValue: 3
    }, {
        name: 'member', type: 'boolean'
    }, {
        name: 'phone', type: 'string'
    }, {
        name: 'address', type: 'string'
    }, {
        name: 'website', type: 'string'
    }, {
        name: 'position', type: 'string'
    }, {
        name: 'postCode', type: 'string'
    }, {
        name: 'arriveNum', type: 'int'
    }, {
        name: 'commentScore', type: 'number'
    }, {
        name: 'commentNum', type: 'int'
    }, {
        name: 'favoriteNum', type: 'int'
    }, {
        name: 'shareNum', type: 'int'
    }, {
        name: 'bookingMemo', type: 'string'
    }, {
        name: 'tips', type: 'string'
    }, {
        name: 'place', type: 'string'
    }, {
        name: 'placeId', type: 'int'
    }, {
        name: 'createdUserId', type: 'string'
    }, {
        name: 'createdTime', type: 'int'
    }, {
        name: 'updatedUserId', type: 'string'
    }, {
        name: 'updatedTime', type: 'int'
    }, {
        name: 'status', type: 'string', defaultValue: 'VALIDATED'
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