/**
 * Created by john on 15-9-11.
 */
Ext.define('yt_manager_app.model.resource.Scene', {
    extend: 'yt_manager_app.model.resource.Resource',

    fields: [{
        name: 'intro', type: 'string'
    }, {
        name: 'ticket', type: 'string'
    }, {
        name: 'sceneMap', type: 'string'
    }, {
        name: 'specialScene', type: 'string'
    }, {
        name: 'sceneTraffic', type: 'string'
    }]
});