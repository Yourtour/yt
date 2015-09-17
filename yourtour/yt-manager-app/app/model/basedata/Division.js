/**
 * Created by john on 15-9-16.
 */
Ext.define('yt_manager_app.model.basedata.Division', {
    extend: 'Ext.data.Model',

    fields: [{
        name: 'code', type: 'string'
    }, {
        name: 'shorter', type: 'string'
    }, {
        name: 'text', type: 'string'
    }, {
        name: 'memo', type: 'string'
    }]
});