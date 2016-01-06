Ext.define('YourTour.view.route.RouteActivityEditView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','YourTour.view.widget.XLabel','YourTour.view.widget.XTimeField','YourTour.view.widget.XField','YourTour.view.widget.ToggleField','YourTour.view.widget.XHeaderBar'],
    config: {
    	id:'RouteActivityEditView',
    	layout:'vbox',
    	scrollable: {
    	    direction: 'vertical'
    	},
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
				xtype:'panel',
				layout:'hbox',
				cls:'row underline icon_name',
				padding:'0 0 0 10',
				items:[
					{
						xtype: 'xlabel',
						itemId:'resName',
						tappable:true,
						cls:'font-medium font-grey nav_arrow',
						flex:1,
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
				layout:'hbox',
				cls:'row underline icon_name',
				padding:'0 0 0 10',
				items:[
					{
						xtype: 'xtextfield',
						itemId:'title',
						cls:'font-medium font-grey nav_arrow',
						flex:1,
						placeHolder:'输入活动名称',
						margin:'0 5 0 30'
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				cls:'row underline icon_time',
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
				padding:'0 5 0 5',
				cls:'underline',
				height:200,
				items:[
					{
						xtype:'xlabel',
						html: '活动概述',
						margin:'0 10 0 0'
					},   
					{  
					    xtype: 'xtextarea',  
					    itemId:'memo',
					    flex:1,
					    height:195,
					    clearIcon: true
					}
				]
			},
			
            {
            	xtype:'subtitlebar',
            	margin:'5 0 5 0',
            	html:'事项'
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

