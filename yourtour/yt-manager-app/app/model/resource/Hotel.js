/**
 * Created by john on 15-9-11.
 */
Ext.define('yt_manager_app.model.resource.Hotel', {
    extend: 'yt_manager_app.model.resource.Resource',

    fields: [{
        name: 'name', type: 'string'
    }, {
        name: 'accommodationStandard', type: 'string'
    }, {
        name: 'specialRoom', type: 'string'
    }, {
        name: 'roomEquipment', type: 'string'
    }, {
        name: 'networkInfo', type: 'string'
    }]
});