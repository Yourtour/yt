Ext.define('YourTour.view.line.LineIntroductionView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','Ext.Img','Ext.DataView', 'YourTour.view.widget.XPanel', 'YourTour.view.widget.XButton','YourTour.view.widget.XField','YourTour.view.widget.XLabel', ,'YourTour.view.widget.XHeaderBar', 'YourTour.view.line.LineResourceItem'],
    xtype: 'LineIntroductionView',
    config: {
    	id:'LineIntroductionView',
      	layout:'vbox',
    	scrollable: {
    	    direction: 'vertical',
    	    indicators: false	
    	},
        items: [
        	{    
				xtype: 'xheaderbar',
				title:'线路介绍',
				items:[
					{
						itemId:'more',
						xtype:'image',
						mode : 'tag',
						align:'right',
						src:'resources/icons/icon_more.png'
					}       
				]
			},
			
			{
    			xtype:'panel',
    			layout:'fit',
    			height:150,
    			items:[
		    		{
		   				itemId : 'imageUrl',
						xtype : 'image',
						mode : 'tag'
		    		},
		    		
		    		{
						xtype:'panel',
						layout:'hbox',
						itemId:'buttons',
						style:'background:grey;opacity:0.5; color:#fff; font-size:14px; font-weight:bold; width:100%; height:45px; line-height:40px; text-align:center',
						left:0,
						top:0,
						docked:'top',
						defaults:{
							flex:1
						},
						items:[
							{
			                	xtype: "toolbutton", 
			                    ui: "normal", 
			                	text:'咨询',
			                	itemId:'consult',
			                	align:'right'
			                },
			                
			                {
			                	xtype: "toolbutton", 
			                    ui: "normal", 
			                	text:'评论',
			                	itemId:'comment',
			                	align:'right'
			                }
						]			
					},
		    	]
    		},
				    
		    {
    			xtype:'panel',
    			layut:'vbox',
    			itemId:'lineInfo',
    			defaults:{
    				padding:'0 5 0 5'
    			},
	    		items:[
		    		{
					   xtype:'xlabel',
					   pack:'center',
					   align:'center',
					   itemId:'name',
					   cls:'row underline bold font-large',
					   html : ''
		    		},
			    
			   		{
			   			xtype:'panel',
			   			layout:'hbox',
			   			cls:'row underline raty',
			   			items:[
				   			{
				   				xtype:'xlabel',
				   				margin:'0 10 0 0',
				   				html:'推荐指数:'
				   			},
				   			
				   			{
				   				xtype:'image',
				    			src:'resources/images/raty_32.png',
				    			flex:1,
				    			mode:'tag'
				   			},
				   			
				   			{
				   				xtype:'xlabel',
				   				margin:'0 10 0 0',
				   				html:'评价指数:'
				   			},
				   			{
				   				xtype:'image',
				    			src:'resources/images/raty_32.png',
				    			flex:1,
				    			mode:'tag'
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
				   				margin:'0 10 0 0',
				   				html:'推荐月份:'
				   			},
				   			{
				   				xtype:'xfield',
				   				flex:1,
				   				html:'7、8、9月'
				   			},
				   			{
				   				xtype:'xlabel',
				   				flex:1
				   			},
				   			{
				   				xtype:'xlabel',
				   				flex:1
				   			}
			   			]
			   		}
				]
    	   	},
			
    	   	{
			   xtype:'xpanel',
			   itemId:'users',
			   tappable:true,
			   cls:'row underline',
			   layout:'hbox',
    		   padding:'0 5 0 5',
			   items:[
			   		{
		   				xtype:'xlabel',
		   				margin:'0 10 0 0',
		   				html:'线路达人:'
		   			},
		   			{
		   				xtype:'xfield',
		   				itemId:'talent',
		   				flex:1,
		   				html:'10人'
		   			},
		   			{
		   				xtype:'xlabel',
		   				margin:'0 10 0 0',
		   				html:'线路驴友:'
		   			},
		   			{
		   				xtype:'xfield',
		   				itemId:'tourism',
		   				flex:1,
		   				html:'10人'
		   			}
			   ]
    		},
		    		
		    {
		       xtype:'panel',
			   margin:'5 0 0 0',
			   layout:'vbox',
			   items:[
			   		{
			   			xtype:'xlabel',
				   		html:'线路特点',
				   		padding:'5',
				   		cls:'row font-medium'
			   		},	
			   		
			   		
			   		{
			   			xtype:'xfield',
			   			itemId:'feature',
			   			cls:'multilineinfo underline',
			   			padding:'5'
			   		},
			   		
			   		{
			   			xtype:'xlabel',
				   		html:'推荐理由',
				   		padding:'5',
				   		cls:'row font-medium'
			   		},
			   		
			   		{
			   			xtype:'xfield',
			   			itemId:'reason',
			   			cls:'multilineinfo',
			   			padding:'5'
			   		}
			   ]
		    },
		    
		    {
		    	xtype:'dataview',
		    	itemId:'resources',
		    	cls:'space-top',
				scrollable:null,
				flex:1,
		    	useComponents:true,
		    	defaultType:'LineResourceItem'
		    },
		    
		    {
            	xtype:'xbutton',
            	itemId:'btnPlan',
            	docked:'bottom',
            	text:'行程规划'
            }
		    
        ]
    }
});

