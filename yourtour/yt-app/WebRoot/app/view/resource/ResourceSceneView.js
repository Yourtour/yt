Ext.define('YourTour.view.resource.ResourceSceneView', {
	extend: 'YourTour.view.resource.ResourceView',
    requires:['Ext.Panel','YourTour.view.widget.XImage','YourTour.view.widget.XDataView','YourTour.view.route.RouteActivityItemDataItem','YourTour.view.widget.XHeaderBar','YourTour.view.widget.XSpacer', 'YourTour.view.widget.XLabel','YourTour.view.widget.XField'],
    config: {
    	items:[
			{
				xtype:'panel',
				layout:'vbox',
				items:[
					{
						itemId : 'image',
						xtype : 'ximage',
						imageCls:'img-medium',
						binding:'imageUrl'
					},

					{
						xtype: 'panel',
						layout: 'hbox',
						cls: 'row underline',
						padding: '0 10 0 10',
						docked: 'bottom',
						bottom: 0,
						style: 'background-color:grey;opacity:0.2; width:100%; text-align:center',
						items: [
							{
								xtype:'spacer',
								flex:1
							},

							{
								xtype: 'image',
								src: 'resources/images/raty_32.png',
								mode: 'tag'
							}
						]
					}
				]
			},
	    	
	    	{
	    		xtype:'panel',
	    		layout:'vbox',
		    	items:[
					{
						xtype: 'xfield',
						itemId:'address',
						icon:'icon-position'
					},

					{
						xtype: 'xfield',
						itemId:'phone',
						icon:'icon-phone'
					},

					{
						xtype: 'xmultifield',
						itemId:'openTime',
						icon:'icon-open'
					},

					{
						xtype: 'xfield',
						itemId:'comment',
						icon:'icon-comment',
						dataChange: function (field, record){
							field.setText('<span style="color:blue">4.5分</span>  <span style="color:blue">866</span>评价');
						}
					},

					{
						xtype:'xspacer'
					},

					{
						xtype: 'xmultifield',
						itemId:'intro',
						icon:'icon-memo',
						ellipsis:{
							size:100,
							expandable:true
						}
					},

					{
						xtype:'xspacer'
					},

					{
						xtype:'panel',
						layout:'vbox',
						items:[
							{
								xtype:'xlabel',
								html: '景点',
								cls:'row underline font-medium font-grey',
								padding:'0 10 0 10'
							},
							{
								xtype: 'xdataview',
								itemId:'itemList',
								scrollable:null,
								binding:'activityItemsStore',
								useComponents: true,
								defaultType: 'RouteActivityItemDataItem'
							}
						]
					}
	    		]
	    	}
        ]
    }
});

