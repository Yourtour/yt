Ext.define('YourTour.view.route.RouteActivityEditView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','YourTour.view.widget.XLabel','YourTour.view.route.RouteActivityItemDataItem','YourTour.view.route.RouteActivityItemDataItem','YourTour.view.widget.XTimeField','YourTour.view.widget.XField','YourTour.view.widget.ToggleField','YourTour.view.widget.XHeaderBar'],
    config: {
    	id:'RouteActivityEditView',
    	layout:'vbox',
    	items:[
			{    
				xtype: 'hiddenfield',
				itemId:'id'	,
				value:'0'
			},

			{
				xtype: 'hiddenfield',
				itemId:'resourceId'
			},

			{
				xtype: 'hiddenfield',
				itemId:'resourceType'
			},

    		{    
				xtype: 'xheaderbar',
				title:'行程安排',
				items:[{
                	xtype: "toolbutton", 
                    ui: "normal", 
                	text:'保存',
                	itemId:'btnSave',
                	align:'right'
                }]
			},

			{
				xtype: 'xfield',
				itemId:'resName',
				tappable:true,
				icon:'icon-name',
			},

			{
				xtype:'panel',
				cls:'spacer'
			},

			{
				xtype:'panel',
				layout:'hbox',
				cls:'row underline icon-name',
				padding:'0 0 0 10',
				items:[
					{
						xtype: 'xtextfield',
						itemId:'title',
						cls:'font-medium font-grey nav-arrow',
						flex:1,
						placeHolder:'输入活动名称',
						margin:'0 5 0 30'
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				cls:'row underline icon-time',
				padding:'0 0 0 10',
				items:[
					{
						xtype: 'timepickerfield',
						itemId:'startTime',
						value: new Date(),
						width:100,
						margin:'0 5 0 30'
					},

					{
						xtype:'xlabel',
						html:'至',
						width:40,
						style:'font-weight:bold',
						margin:'0 5 0 5'
					},

					{
						xtype: 'timepickerfield',
						itemId:'endTime',
						width:100,
						value: new Date()
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				cls:'underline icon-memo',
				padding:'0 0 0 10',
				items:[
					{
						xtype: 'xtextarea',
						itemId:'memo',
						height:195,
						clearIcon: true,
						flex:1,
						cls:'font-medium font-grey multilineinfo',
						margin:'0 5 0 30'
					}
				]
			},

			{
				xtype:'panel',
				cls:'spacer'
			},

			{
				xtype:'panel',
				layout:'vbox',
				items:[
					{
						xtype:'xlabel',
						html: '安排',
						itemId:'btnItemAdd',
						tappable:true,
						cls:'row underline font-medium font-grey icon-add',
						padding:'0 0 0 5'
					},
					{
						xtype: 'dataview',
						itemId:'itemList',
						scrollable:null,
						useComponents: true,
						defaultType: 'RouteActivityItemDataItem',
					}
				]
			},

			{
				xtype:'panel',
				cls:'spacer'
			},

			{
				xtype:'panel',
				layout:'vbox',
				items:[
					{
						xtype:'xlabel',
						itemId:'btnServiceAdd',
						html: '服务',
						tappable:true,
						cls:'row underline font-medium font-grey icon-add',
						padding:'0 0 0 5'
					},
					{
						xtype: 'dataview',
						itemId:'serviceList',
						scrollable:null,
						useComponents: true,
						defaultType: 'ExpertServiceListDataItem',
					}
				]
			},
            
            {
            	xtype:'xbutton',
            	id:'btnDelete',
            	docked: 'bottom',
            	text:'删除'
            },
        ]
    },
    
    updateResource:function(resource){
    	this.resource = resource;
    }
});

