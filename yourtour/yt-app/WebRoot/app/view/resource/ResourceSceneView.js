Ext.define('YourTour.view.resource.ResourceSceneView', {
	extend: 'YourTour.view.resource.ResourceView',
    requires:['Ext.Panel','YourTour.view.widget.XImage','YourTour.view.widget.XDataView','YourTour.view.route.RouteActivityItemDataItem','YourTour.view.widget.XHeaderBar','YourTour.view.widget.XSpacer', 'YourTour.view.widget.XLabel','YourTour.view.widget.XField'],
    config: {
    	items:[
			{
				itemId : 'image',
				xtype : 'ximage',
				imageCls:'img-medium',
				binding:'imageUrl'
			},

			{
				xtype:'xspacer'
			},

			{
				xtype:'xpanel',
				layout:'hbox',
				itemId:'commentPanel',
				padding:'0 10 0 0',
				cls:'row nav-arrow',
				items:[
					{
						xtype:'xlabel',
						html:'游徒点评',
						margin:'0 10 0 0'
					},
					{
						xtype: 'image',
						src: 'resources/images/raty_32.png',
						mode: 'tag'
					},

					{
						xtype: 'xfield',
						itemId:'comment',
						margin:'0 0 0 10',
						underline:false,
						dataChange: function (field, record){
							field.setText('<span style="color:blue">4.5分</span>  <span style="color:blue">866</span>评价');
						}
					}
				]
			},

			{
				xtype:'xspacer'
			},
	    	
			{
				xtype: 'xfield',
				itemId:'address',
				indicator:'nav-arrow',
				label:'景点地址'
			},

			{
				xtype: 'xfield',
				itemId:'phone',
				label:'景点电话'
			},

			{
				xtype: 'xmultifield',
				itemId:'openTime',
				label:'开放时间'
			},

			{
				xtype: 'xmultifield',
				itemId:'intro',
				label:'景点介绍',
				ellipsis:{
					size:100,
					expandable:true
				}
			},

			{
				xtype: 'xmultifield',
				itemId:'price',
				label:'门票信息'
			},

			{
				xtype: 'xmultifield',
				itemId:'traffic',
				label:'交通信息'
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
						html: '景点帖示',
						cls:'row underline font-medium',
						padding:'0 10 0 10'
					},
					{
						xtype: 'xmultifield',
						itemId:'tips'
					}
				]
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
						html: '景点攻略',
						cls:'row underline font-medium',
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
						html: '附近热门',
						cls:'nav-arrow row underline font-medium',
						padding:'0 10 0 10'
					},
					{
						xtype: 'xdataview',
						itemId:'recommendList',
						scrollable:null,
						useComponents: true
					}
				]
			}
        ]
    }
});