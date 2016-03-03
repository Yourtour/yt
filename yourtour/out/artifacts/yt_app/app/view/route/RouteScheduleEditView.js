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
	                	xtype: "xbutton",
	                    ui: "normal", 
	                	text:'保存',
	                	itemId:'btnSave',
	                	align:'right'
	                }
		        ]
			},

			{
				xtype: 'hiddenfield',
				itemId:'id'	,
				value:'0'
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
						xtype: 'datepickerfield',
					    itemId:'date',
					    flex:1,
					    clearIcon: true
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
						html: '标题',
						style:'width:50px'
					},
					{  
					    xtype: 'textfield',  
					    itemId:'title',
					    flex:1,
					    clearIcon: true
					}
				]
			},
			
			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 0 0 10',
				cls:'underline',
				height:200,
				items:[
					{
						xtype:'xlabel',
						html: '备注',
						style:'width:50px'
					},
					{  
					    xtype: 'xtextarea',  
					    itemId:'memo',
					    flex:1,
					    height:195,
					    clearIcon: true
					}
				]
			}
        ]
    }
});

