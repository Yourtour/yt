Ext.define('YourTour.view.route.ScheduleFormView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','YourTour.view.widget.XLabel','YourTour.view.common.ServiceDataItem','YourTour.view.widget.XHeaderBar'],
    config: {
    	id:'ScheduleFormView',
    	layout:'vbox',
    	items:[
    		{    
				xtype: 'xheaderbar',
				itemId:'headerbar'
			},
			
			{
				xtype:'panel',
				layout:'vbox',
				flex:1,
				scrollable: {
	        	    direction: 'vertical',
	        	    indicators: false	
	        	},
				items:[
		    		{
		    	   		itemId : 'image',
			    		xtype : 'image',
			    		mode : 'tag'
			    	},
			    	
			    	{
			    		xtype : 'panel',
			    		layout:'hbox',
			    		padding:'0 5 0 5',
			    		cls:'row underline raty',
			    		items:[
				    		{
				    			xtype:'label',
				    			flex:1,
				    			cls:'font-medium font-grey',
				    			html:'推荐指数'
				    			
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
				    			cls:'font-medium font-grey',
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
						cls:'row underline',
						items:[
							{
								xtype:'xlabel',
								html: '景点',
								cls:'font-medium font-grey',
								margin:'0 10 0 5'
							},
							{  
							    xtype: 'xlabel',  
							    itemId:'resName',
							    tappable:true,
							    cls:'font-medium font-grey nav_arrow',
							    flex:1,
							    margin:'0 5 0 10'
							}
						]
					},
					
			    	{
						xtype:'panel',
						layout:'hbox',
						cls:'row underline',
						items:[
							{
								xtype:'label',
								html: '地址',
								cls:'font-medium font-grey',
								margin:'0 10 0 5'
							},
							{  
							    xtype: 'xlabel',  
							    itemId:'address',
							    cls:'font-medium font-grey icon_map',
							    flex:1,
							    tappable:true,
							    margin:'0 5 0 10'
							}
						]
					},
					
			    	{
						xtype:'panel',
						layout:'hbox',
						cls:'row underline',
						items:[
							{
								xtype:'label',
								html: '电话',
								cls:'font-medium font-grey',
								margin:'0 10 0 5'
							},
							{  
							    xtype: 'label',  
							    itemId:'phone',
							    cls:'font-medium font-grey icon_phone',
							    flex:1,
							    margin:'0 5 0 10'
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
						cls:'row underline',
						items:[
							{
								xtype:'label',
								html: '时间',
								cls:'font-medium font-grey',
								margin:'0 10 0 5'
							},
							{  
							    xtype: 'label',  
							    itemId:'time',
							    cls:'font-medium font-grey',
							    margin:'0 5 0 10'
							}
						]
					},
					
					{
						xtype:'panel',
						layout:'hbox',
						items:[
							{
								xtype:'label',
								html: '描述',
								cls:'row font-medium font-grey',
								margin:'0 10 0 5'
							},
							{  
							    xtype: 'label',  
							    itemId:'memo',
							    flex:1,
							    cls:'font-medium font-grey multilineinfo',
							    margin:'9 5 9 10'
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
						items:[
							{
								xtype:'label',
								html: '服务',
								cls:'row font-medium font-grey',
								margin:'0 10 0 5'
							},
							{  
							    xtype: 'dataview',  
							    itemId:'services',
							    flex:1,
							    scrollable:null,
						        useComponents: true,
						        defaultType: 'ServiceDataItem',
						        margin:'0 5 0 10'
							}
						]
					}
				]
			},
            
            {
                xtype: 'toolbar',
                docked: 'bottom',
                items: [
	                {
	                    xtype: 'spacer',
	                    flex:1
	                },{
	                    xtype: 'button',
	                    text: '联系达人',
	                    ui: 'normal',
	                    iconCls:'action',
	                    itemId: 'backToPlaces'
	                },{
	                    xtype: 'button',
	                    text: '签到',
	                    ui: 'normal',
	                    iconCls:'locate',
	                    itemId: 'checkin'
	                },{
	                    xtype: 'button',
	                    text: '随记',
	                    ui: 'normal',
	                    iconCls:'compose',
	                    itemId: 'notes'
	                }
	            ]
            }
        ]
    }
});

