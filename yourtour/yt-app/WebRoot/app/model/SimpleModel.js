Ext.define('YourTour.model.SimpleModel', {
    extend: 'Ext.data.Model',
    config: {
        idProperty:'code',

        fields: [
            {name: 'code', type: 'string'},
            {name: 'name', type: 'string'}
        ]
    }
});
