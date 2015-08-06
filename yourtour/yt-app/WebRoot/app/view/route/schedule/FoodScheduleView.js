Ext.define('YourTour.view.route.schedule.FoodScheduleView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','YourTour.view.widget.XLabel','YourTour.view.widget.XField','YourTour.view.widget.ToggleField','YourTour.view.widget.XToolbar'],
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
				xtype: 'xtoolbar',
				itemId:'toolbar',					
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
	    		xtype : 'panel',
	    		layout:'hbox',
	    		padding:'0 5 0 5',
	    		cls:'row raty',
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
		    	layout:'vbox',
		    	padding:'0 5 0 5',
		    	items:[
			    	{
		    			xtype:'xfield',
		    			itemId:'resName'
		    		},
			    	{
		    			xtype:'xlabel',
		    			itemId:'address',
		    			style:'line-height:40px;font-size:14px'
		    		},{
		    			xtype:'xlabel',
		    			itemId:'phone',
		    			style:'line-height:40px;font-size:14px'
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
				cls:'textfield',
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
    			cls:'underline',
    			layout:'hbox',
    			pack: 'center',
    			padding:'0 5 0 5',
    			items:[
    				{
    					xtype:'label',
    					html:'活动性质',
    					style:'line-height:40px'
    				},
    				{
						xtype:'spacer',
						flex:1
					},
    				{
    					xtype:'yttogglefield',
    					texts:['可选','必须']
    				}
    			]
    		},
    		
    		{
    			xtype:'panel',
    			cls:'underline',
    			layout:'hbox',
    			padding:'0 5 0 5',
    			items:[
    				{
    					xtype:'xlabel',
    					html:'活动方式',
    					style:'line-height:40px'
    				},
    				{
						xtype:'spacer',
						flex:1
					},
    				{
    					xtype:'yttogglefield',
    					texts:['个人','集体']
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
						xtype:'label',
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
				cls:'textfield',
				items:[
					{
						xtype:'label',
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
				cls:'textfield',
				items:[
					{
						xtype:'label',
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
            	html:'事项'
            }
        ]
    }
});

