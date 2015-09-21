/**
 * Created by john on 15-9-11.
 */
Ext.define('yt_manager_app.model.resource.Restaurant', {
    extend: 'yt_manager_app.model.resource.Resource',

    fields: [{
        name: 'name', type: 'string'
    }, {
        name: 'deliciouFood', type: 'string'
    }, {
        name: 'foodStandard', type: 'string'
    }, {
        name: 'foodTags', type: 'string'
    }, {
        name: 'networkInfo', type: 'string'
    }]
});