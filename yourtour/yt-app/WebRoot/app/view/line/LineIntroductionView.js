Ext.define('YourTour.view.line.LineIntroductionView', {
    extend: 'Ext.Panel',
    requires:['Ext.Panel','Ext.Img','Ext.DataView', 'YourTour.view.widget.XToolbar','YourTour.view.widget.MarkedLabel', 'YourTour.view.line.LineResourceItem'],
    xtype: 'LineIntroductionView',
    config: {
    	itemId:'LineIntroductionView',
    	id:'LineIntroductionView',
      	layout:'vbox',
    	baseCls:'page',
    	scrollable: {
    	    direction: 'vertical',
    	    directionLock: true
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
    			style:'background:#fff',
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
    			style:'background:#fff',
    			defaults:{
    				padding:'0 5 0 5'
    			},
	    		items:[
		    		{
					   xtype:'label',
					   pack:'center',
					   align:'center',
					   itemId:'name',
					   cls:'textfield',
					   html : ''
		    		},
			    
			   		{
			   			xtype:'panel',
			   			layout:'hbox',
			   			cls:'textfield raty',
			   			items:[
				   			{
				   				xtype:'label',
				   				html:'推荐指数:',
				   				flex:1,
				   				style:'font-weight:bold;'
				   			},
				   			
				   			{
				   				xtype:'image',
				    			src:'resources/images/raty_32.png',
				    			flex:2,
				    			mode:'tag'
				   			},
				   			
				   			{
				   				xtype:'label',
				   				html:'旅游指数:',
				   				flex:1,
				   				style:'font-weight:bold;'
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
			   			cls:'textfield',
			   			items:[
				   			{
				   				xtype:'label',
				   				html:'推荐时间:',
				   				flex:1,
				   				style:'font-weight:bold; font-size:14px'
				   			},
				   			{
				   				xtype:'label',
				   				flex:2,
				   				html:'7、8、9月',
				   				style:'font-size:14px'
				   			},
				   			{
				   				xtype:'label',
				   				flex:1,
				   				html:'服务达人',
				   				style:'font-weight:bold; font-size:14px'
				   			},
				   			{
				   				xtype:'label',
				   				flex:2,
				   				html:'10人',
				   				style:'font-size:14px'
				   			}
			   			]
			   		}
				]
    	   	},
			    
		    {
		       xtype:'panel',
			   margin:'5 0 0 0',
			   style:'background:#fff',
			   layout:'vbox',
			   items:[
			   		{
			   			xtype:'label',
				   		html:'线路特点',
				   		padding:'5',
				   		cls:'fieldlabel'
			   		},	
			   		
			   		
			   		{
			   			xtype:'label',
			   			itemId:'feature',
			   			cls:'multilineinfo horizentalLine',
			   			padding:'5'
			   		},
			   		
			   		{
			   			xtype:'label',
				   		html:'推荐理由',
				   		cls:'fieldlabel',
				   		padding:'5'
			   		},
			   		
			   		{
			   			xtype:'label',
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
				useComponents:true,
				scrollable:null,
		    	defaultType:'LineResourceItem'
		    }
        ]
    }
});

