/**
 * Created by john on 15-9-11.
 */
Ext.define('yt_manager_app.model.route.Line', {
    extend: 'Ext.data.Model',

    fields: [{
        name: 'id', type: 'int', defaultValue: -1
    }, {
        name: 'name', type: 'string'
    }, {
        name: 'imageUrl', type: 'string'
    }, {
        name: 'intro', type: 'string'
    }, {
        name: 'feature', type: 'string'
    }, {
        name: 'reason', type: 'string'
    }, {
        name: 'recommendIndex', type: 'number'
    }, {
        name: 'commentIndex', type: 'number'
    }, {
        name: 'place', type: 'string'
    }, {
        name: 'placeId', type: 'int'
    }, {
        name: 'arriveNum', type: 'int'
    }, {
        name: 'tags', type: 'string'
    }, {
        name: 'scenes', type: 'string'
    }, {
        name: 'hotels', type: 'string'
    }, {
        name: 'restaurants', type: 'string'
    }, {
        name: 'commentScore', type: 'number'
    }, {
        name: 'commentNum', type: 'int'
    }, {
        name: 'thumbupNum', type: 'int'
    }, {
        name: 'favoriteNum', type: 'int'
    }, {
        name: 'shareNum', type: 'int'
    }, {
        name: 'status', type: 'string', defaultValue: 'VALIDATED'
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