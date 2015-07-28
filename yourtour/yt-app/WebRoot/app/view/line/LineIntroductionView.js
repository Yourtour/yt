Ext.define('YourTour.view.line.LineIntroductionView', {
    extend: 'Ext.Panel',
    requires:['Ext.Panel','Ext.Img','Ext.DataView', 'YourTour.view.widget.XButton','YourTour.view.widget.XField','YourTour.view.widget.XLabel', 'YourTour.view.widget.XToolbar','YourTour.view.line.LineResourceItem'],
    xtype: 'LineIntroductionView',
    config: {
    	itemId:'LineIntroductionView',
    	id:'LineIntroductionView',
      	layout:'vbox',
    	scrollable: {
    	    direction: 'vertical',
    	    indicators: false	
    	},
        items: [
        	{    
				xtype: 'xtoolbar',
				title:'线路介绍',
				itemId:'toolbar',
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
	                	itemId:'consult',
	                	align:'right'
	                },
	                {
	                	xtype: "toolbutton", 
	                    ui: "normal", 
	                	text:'收藏',
	                	itemId:'consult',
	                	align:'right'
	                },
	                {
	                	xtype: "toolbutton", 
	                    ui: "normal", 
	                	text:'分享',
	                	itemId:'consult',
	                	align:'right'
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
		    		}
		    	]
    		},
				    
		    {
    			xtype:'tappanel',
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
					   cls:'row underline bold',
					   html : ''
		    		},
			    
			   		{
			   			xtype:'panel',
			   			layout:'hbox',
			   			cls:'row underline raty',
			   			items:[
				   			{
				   				xtype:'xlabel',
				   				html:'推荐指数:',
				   				flex:1
				   			},
				   			
				   			{
				   				xtype:'image',
				    			src:'resources/images/raty_32.png',
				    			flex:2,
				    			mode:'tag'
				   			},
				   			
				   			{
				   				xtype:'xlabel',
				   				html:'旅游指数:',
				   				flex:1
				   			},
				   			{
				   				xtype:'image',
				    			src:'resources/images/raty_32.png',
				    			flex:2,
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
				   				html:'推荐时间:',
				   				flex:1
				   			},
				   			{
				   				xtype:'xfield',
				   				flex:2,
				   				html:'7、8、9月'
				   			},
				   			{
				   				xtype:'xlabel',
				   				flex:1,
				   				html:'服务达人'
				   			},
				   			{
				   				xtype:'xfield',
				   				flex:2,
				   				html:'10人'
				   			}
			   			]
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
				   		cls:'row'
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
				   		cls:'row'
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
            	itemId:'next',
            	docked:'bottom',
            	text:'行程规划'
            }
		    
        ]
    }
});

