Ext.define('YourTour.view.along.AlongListDataItem', {
	extend: 'YourTour.view.widget.XDataItem',
    xtype: 'AlongListDataItem',
    requires:['Ext.Label','Ext.Panel'],
    config: {
		layout:'vbox',
    	items:[
			{
				xtype:'panel',
				layout:'hbox',
				cls:'underline',
				padding:'10 10 10 10',
				items:[
					{
						xtype: 'ximage',
						itemId: 'routeImage',
						imageCls: 'img-user-logo-48',
						binding: 'user.imageUrl',
						margin: '0 10 0 0'
					},{
						xtype:'panel',
						layout:'vbox',
						flex:1,
						items:[
							{
								xtype:'xfield',
								padding:'0',
								underline:false,
								dataChange:function(field, record){
									var user = record.userStore.first();
									field.setText(user.get('nickName'));
								}
							},

							{
								xtype:'xfield',
								padding:'0',
								underline:false,
								dataChange:function(field, record){
									var date = new Date(Number(record.get('createdTime')));
									field.setText(Ext.Date.format(date,'Y-m-d H:i:s'));
								}
							}
						]
					}
				]
			},

			{
				xtype:'panel',
				layout:'vbox',
				padding:'0 10',
				items:[
					{
						xtype:'xfield',
						padding:'0',
						underline:false,
						dataChange:function(field, record){
							var route = record.routeStore.first(), places = route.get('toPlaces').split('|'), value='';

							Ext.Array.forEach(places, function(place, index){
								if(index > 0){
									value = value + ',';
								}

								value = value + place.split(',')[1];
							})

							field.setText('线路:<span style="color:blue">(' + value + ')</span><span style="color:blue;margin-left:10px">' + route.get('lineName' ) +'</span>')
						}
					},

					{
						xtype:'xfield',
						padding:'0',
						underline:false,
						dataChange:function(field, record){
							var route = record.routeStore.first(),
								startDate = new Date(Number(route.get('startDate'))),
								endDate = new Date(Number(route.get('endDate')));

							field.setText('日程:<span style="color:blue">' + Ext.Date.format(startDate,'Y-m-d') + '~' +Ext.Date.format(endDate,'Y-m-d') + '</span>')
						}
					},

					{
						xtype:'xmultifield',
						itemId:'memo',
						padding:'5 0 0 0',
						underline:false,
						ellipsis:{
							size:80,
							expandable:false
						}
					},

					{
						xtype:'panel',
						layout:'hbox',
						padding:'0 0 10 0',
						items:[
							{
								xtype:'xfield',
								icon:'icon-position',
								padding:0,
								underline:false,
								flex:1,
								dataChange:function(field, record){
									field.setText('我在' + record.get('address'));
								}
							},
							{
								xtype:'xfield',
								padding:0,
								underline:false,
								margin:'0 10',
								dataChange:function(field, record){
									field.setText('浏览' + record.get('readNum'));
								}
							},

							{
								xtype:'xfield',
								padding:0,
								underline:false,
								dataChange:function(field, record){
									field.setText('评论' + record.get('commentNum'));
								}
							}
						]
					}
				]
			}
    	]
    },

	updateRecord:function(record){
		this.callParent(arguments);
		if(record) {
			var me = this, dataview = me.dataview || me.getDataview(), store = dataview.getStore();
			if (store.indexOf(record) > 0) {
				me.insert(0, {xtype:'xspacer'});
			}
		}
	}
});

