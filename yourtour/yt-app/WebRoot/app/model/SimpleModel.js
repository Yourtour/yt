Ext.define('YourTour.model.SimpleModel', {
    extend: 'YourTour.model.BaseModel',
    config: {
        fields: [
            {name: 'code', type: 'string'},
            {name: 'name', type: 'string'}
        ]
    }
});
