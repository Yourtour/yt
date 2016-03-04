Ext.define('YourTour.store.GenderStore', {
	extend: 'Ext.data.Store',
    requires: [
       'YourTour.model.SimpleModel'
    ],
    data:[
        {name: '男', code: 'MALE'},
        {name: '女', code: 'FEMALE'},
        {name: '未知', code: 'NA'}
    ]
});
