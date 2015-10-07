Ext.define('YourTour.view.route.schedule.FoodScheduleView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','YourTour.view.widget.XLabel','YourTour.view.widget.XField','YourTour.view.widget.ToggleField','YourTour.view.widget.XHeaderBar'],
    xtype:'FoodScheduleView',
    config: {
    	itemId:'FoodScheduleView',
    	id:'FoodScheduleView',
    	layout:'vbox',
    	scrollable: {
    	    direction: 'vertical'
    	},
    	items:[
    		{    
				xtype: 'xheaderbar',
				title: '餐饮安排',
				items:[
                ]
			},
			
    		{
    	   		itemId : 'imageUrl',
	    		xtype : 'image',
	    		mode : 'tag'
	    	},
	    	
	    	{
		    	xtype:'panel',
		    	layout:'hbox',
		    	padding:'0 5 0 5',
		    	cls:'row underline bold font-large',
		    	items:[
			    	{
					   xtype:'xlabel',
					   pack:'center',
					   align:'center',
					   itemId:'resName',
					   html : ''
		    		}
	    		]
	    	},
	    	
	    	{
	    		xtype : 'panel',
	    		layout:'hbox',
	    		padding:'0 5 0 5',
	    		cls:'row raty underline',
	    		items:[
		    		{
		    			xtype:'xlabel',
		    			flex:1,
		    			html:'推荐指数'
		    			
		    		},
		    		{
		    			xtype:'image',
		    			src:'resources/images/raty_32.png',
		    			flex:2,
		    			mode:'tag'
		    		},
		    		{
		    			xtype:'xlabel',
		    			flex:1,
		    			html:'综合评分'
		    		},
		    		{
		    			xtype:'image',
		    			flex:2,
		    			mode:'tag',
		    			src:'resources/images/raty_32.png'
		    		}
	    		]
	    	},
	    	
	    	{
		    	xtype:'panel',
		    	layout:'hbox',
		    	padding:'0 5 0 5',
		    	cls:'row underline',
		    	items:[
			    	{
		    			xtype:'xlabel',
		    			html:'酒店地址'
		    		},{
		    			xtype:'xfield',
		    			itemId:'address',
		    			flex:1,
		    			margin:'0 0 0 10'
		    		}
	    		]
	    	},
	    	
	    	{
		    	xtype:'panel',
		    	layout:'hbox',
		    	padding:'0 5 0 5',
		    	cls:'row',
		    	items:[
			    	{
		    			xtype:'xlabel',
		    			html:'酒店电话'
		    		},{
		    			xtype:'xfield',
		    			itemId:'phone',
		    			flex:1,
		    			margin:'0 0 0 10'
		    		}
	    		]
	    	},
    		
	    	{
            	xtype:'subtitlebar',
            	margin:'5 0 5 0',
            	html:'概述'
            },
            
    		{
				xtype:'panel',
				layout:'hbox',
				padding:'0 5 0 5',
				cls:'row underline',
				items:[
					{
						xtype:'xlabel',
						html: '活动名称'
					},
					{
						xtype:'spacer',
						flex:1
					},
					{  
					    xtype: 'xlabel',  
					    itemId:'name',
					    clearIcon: true,
					    cls:'nav_arrow',
					    inputCls:'align-right'
					}
				]
			},
			
			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 5 0 5',
				cls:'textfield',
				items:[
					{
						xtype:'xlabel',
						html: '活动描述'
					},
					{
						xtype:'spacer',
						flex:1
					},
					{  
					    xtype: 'label',  
					    name : 'desc',
					    itemId:'desc',
					    clearIcon: true,
					    cls:'nav_arrow',
					    inputCls:'align-right'
					}
				]
			},
			
    		{
            	xtype:'subtitlebar',
            	margin:'5 0 5 0',
            	html:'安排'
            },
    		
    		{
				xtype:'panel',
				layout:'hbox',
				padding:'0 5 0 5',
				cls:'row underline',
				items:[
					{
						xtype:'xlabel',
						html: '开始时间'
					},
					{
						xtype:'spacer',
						flex:1
					},
					{  
					    xtype: 'datepickerfield',  
					    name : 'startDate',
					    itemId:'startDate',
					    value:new Date(),
					    dateFormat:'Y/m/d',
					    clearIcon: true,
					    cls:'nav_arrow',
					    inputCls:'align-right'
					}
				]
			},
			
			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 5 0 5',
				cls:'row underline',
				items:[
					{
						xtype:'xlabel',
						html: '结束时间'
					},
					{
						xtype:'spacer',
						flex:1
					},
					{  
					    xtype: 'datepickerfield',  
					    name : 'endDate',
					    itemId:'endDate',
					    value:new Date(),
					    dateFormat:'Y/m/d',
					    clearIcon: true,
					    cls:'nav_arrow',
					    inputCls:'align-right'
					}
				]
			},
			
			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 5 0 5',
				cls:'row underline',
				items:[
					{
						xtype:'xlabel',
						html: '持续时间'
					},
					{
						xtype:'spacer',
						flex:1
					},
					{  
					    xtype: 'datepickerfield',  
					    name : 'endDate',
					    itemId:'endDate',
					    value:new Date(),
					    dateFormat:'Y-m-d',
					    clearIcon: true,
					    cls:'nav_arrow',
					    inputCls:'align-right'
					}
				]
			},
			
            {
            	xtype:'subtitlebar',
            	margin:'5 0 5 0',
            	html:'备注'
            }
        ]
    }
});

