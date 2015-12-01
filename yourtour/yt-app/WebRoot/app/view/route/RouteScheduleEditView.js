Ext.define('YourTour.view.route.RouteScheduleEditView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel', 'YourTour.view.widget.XHeaderBar'],
    config: {
    	id:'RouteScheduleEditView',
    	layout:'vbox',
		scrollable: {
    	    direction: 'vertical',
    	    indicators: false	
    	},    	
    	items:[
    		{    
				xtype: 'xheaderbar',
				title:'日程编辑',
				items:[
					{
	                	xtype: "toolbutton", 
	                    ui: "normal", 
	                	text:'保存',
	                	itemId:'save',
	                	align:'right'
	                }
		        ]
			},
			
			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 0 0 10',
				cls:'row underline',
				items:[
					{
						xtype:'xlabel',
						html: '日期',
						style:'width:50px'
					},
					{  
					    xtype: 'xlabel',  
					    itemId:'title',
					    flex:1,
					    clearIcon: true,
					}
				]
			},
			
			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 0 0 10',
				cls:'row underline',
				items:[
					{
						xtype:'xlabel',
						html: '备注',
						style:'width:50px'
					},
					{  
					    xtype: 'textfield',  
					    itemId:'memo',
					    flex:1,
					    clearIcon: true
					}
				]
			},
        ]
    }
});

