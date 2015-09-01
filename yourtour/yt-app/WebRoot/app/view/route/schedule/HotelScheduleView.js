Ext.define('YourTour.view.route.schedule.HotelScheduleView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','YourTour.view.widget.XLabel','YourTour.view.widget.XField','YourTour.view.widget.ToggleField','YourTour.view.widget.XToolbar'],
    xtype: 'HotelScheduleView',
    config: {
    	itemId:'HotelScheduleView',
    	id:'HotelScheduleView',
    	layout:'vbox',
    	scrollable: {
    	    direction: 'vertical'
    	},
    	items:[
    		{    
				xtype: 'xtoolbar',
				itemId:'toolbar',
				title: '住宿安排',
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

