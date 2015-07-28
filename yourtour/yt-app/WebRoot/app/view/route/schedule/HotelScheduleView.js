Ext.define('YourTour.view.route.schedule.HotelScheduleView', {
    extend: 'Ext.Container',
    requires:['Ext.Panel','YourTour.view.widget.ToggleField','YourTour.view.widget.XToolbar'],
    xtype: 'hotelscheduleview',
    config: {
    	itemId:'hotelscheduleview',
    	id:'hotelscheduleview',
    	fullscreen:true,
    	layout:'vbox',
    	baseCls:'page',
    	scrollable: {
    	    direction: 'vertical',
    	    directionLock: true
    	},
    	items:[
    		{    
				xtype: 'xtoolbar',
				docked: 'top',
				title: '住宿安排',
				items:[
					{
						xtype: "image", 
	                	itemId:'close',
	                	mode:'tag',
	                	margin:'0 0 0 5',
	                	src:'resources/icons/icon_back.png',
	                	align:'left'
					}
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
	    		cls:'textfield raty',
	    		items:[
		    		{
		    			xtype:'label',
		    			flex:1,
		    			html:'推荐指数',
		    			style:'font-weight:bold'
		    			
		    		},
		    		{
		    			xtype:'image',
		    			src:'resources/images/raty_32.png',
		    			flex:2,
		    			mode:'tag'
		    		},
		    		{
		    			xtype:'label',
		    			flex:1,
		    			html:'综合评分',
		    			style:'font-weight:bold'
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
		    	padding:'5',
		    	style:'background:#fff; border-bottom:1px solid #EDEDED;',
		    	items:[
			    	{
		    			xtype:'label',
		    			itemId:'resName',
		    			style:'font-weight:bold; line-height:40px;font-size:16px'
		    		},
			    	{
		    			xtype:'label',
		    			itemId:'address',
		    			style:'line-height:40px;font-size:14px'
		    		},{
		    			xtype:'label',
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
						xtype:'label',
						html: '活动名称'
					},
					{
						xtype:'spacer',
						flex:1
					},
					{  
					    xtype: 'label',  
					    name : 'name',
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
						xtype:'label',
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
    					xtype:'label',
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
					    dateFormat:'Y/m/d',
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

