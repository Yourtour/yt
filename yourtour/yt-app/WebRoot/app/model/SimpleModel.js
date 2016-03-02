Ext.define('YourTour.model.SimpleModel', {
    extend: 'YourTour.model.BaseModel',
    config: {
        fields: [
            {name: 'id', type: 'string'},
            {name: 'code', type: 'string'},
            {name: 'name', type: 'string'}
        ]
    }
});
