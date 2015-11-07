Ext.define('YourTour.store.LocalStore', {
    extend: 'Ext.data.Store',
    
    fields: ['key', 'value'],
    
    proxy: {
        type: 'localstorage',
        id  : 'YTKey'
    }
});
