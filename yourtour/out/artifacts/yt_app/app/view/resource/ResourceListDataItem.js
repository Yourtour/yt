Ext.define('YourTour.view.resource.ResourceListDataItem', {
    extend: 'YourTour.view.widget.XDataItem',
    xtype: 'ResourceListDataItem',
    requires:['Ext.Img','YourTour.view.widget.XLabel','YourTour.view.widget.XField'],
    config: {
		layout:'vbox',
		cls:'x-xspacer',
        items: [
			{
				xtype: 'panel',
				layout: 'hbox',
				cls: 'row nav-arrow underline',
				padding: '0 10 0 10',
				items:[
					{
						xtype: "xfield",
						itemId:'name',
						underline:false
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				padding:'10',
				items:[
					{
						xtype:'ximage',
						itemId:'image',
						imageCls:'img-small',
						margin:'0 5 0 0'
					},

					{
						xtype:'panel',
						layout:'vbox',
						flex:1,
						items:[
							{
								xtype:'xmultifield',
								itemId:'feature',
								padding:0,
								underline:false,
								ellipsis:{
									size:50,
									expandable:false
								}
							}
						]
					}
				]
			},

			{
				xtype: "xfield",
				itemId:'address',
				icon:'icon-position',
				padding: '0 10 10 10',
			}
        ]
    },

	updateRecord:function(record){
		var me = this;

		me.callParent(arguments);

		if(record){
			var type = record.get('type'), name = me.down('#name');
			if(type == 'SCENE'){
				name.addCls('x-xschedule-scene');
			}else if(type == 'FOOD') {
				name.addCls('x-xschedule-food');
			}else if(type == 'HOTEL') {
				name.addCls('x-xschedule-hotel');
			}else if(type == 'TRAFFIC') {
				name.addCls('x-xschedule-traffic');
			}
		}
	}
});

