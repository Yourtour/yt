/**
 * Created by john on 15-8-31.
 */
Ext.define('yt_manager_app.model.Expert', {
    extend: 'Ext.data.Model',

    fields: [{
        name: 'code', type: 'string'
    }, {
        name: 'name', type: 'string'
    }, {
        name: 'age', type: 'int'
    }]
});