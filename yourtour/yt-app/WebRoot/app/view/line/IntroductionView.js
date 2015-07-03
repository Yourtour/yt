Ext.define('YourTour.view.line.IntroductionView', {
    extend: 'Ext.Panel',
    requires:['Ext.Toolbar', 'Ext.Panel','Ext.Carousel','YourTour.view.widget.TitleBar'],
    xtype: 'lineintroview',
    config: {
    	data:undefined,
    	itemId:'lineintroview',
    	id:'lineintroview',
    	fullscreen: true,
    	layout:'vbox',
    	baseCls:'page',
    	
        items: [
        	{    
				xtype: '_titlebar',
				docked: 'top',
				title: '线路介绍',
				items:[{
					xtype: "image", 
                	itemId:'close',
                	mode:'tag',
                	margin:'0 0 0 5',
                	src:'resources/icons/icon_back.png',
                	align:'left'
				}]			
			},
			
			{
    	   		itemId : 'imageUrl',
	    		xtype : 'image',
	    		mode : 'tag',
	        	src : ''
	    	},
	    	
	    	{
			   xtype:'markedlabel',
			   pack:'center',
			   align:'center',
			   itemId:'name',
			   html : ''
		    },
		    
		    {
			   xtype:'markedlabel',
			   pack:'center',
			   align:'center',
			   itemId:'name',
			   html : ''
		    },
		    
		    {
			   xtype:'panel',
			   margin:'5 0 0 0',
			   style:'background:#fff',
			   layout:'vbox',
			   items:[
			   		{
			   			xtype:'panel',
			   			layout:'hbox',
			   			padding:'5',
			   			defaults:{
			   				flex:1
			   			},
			   			items:[
				   			{
				   				xtype:'label',
				   				html:'推荐指数:',
				   				style:'font-weight:bold; font-size:14px'
				   			},
				   			
				   			{
				   				xtype:'label',
				   				html:'推荐指数:',
				   				style:'font-size:14px'
				   			},
				   			
				   			{
				   				xtype:'label',
				   				html:'旅游指数:',
				   				style:'font-weight:bold; font-size:14px'
				   			},
				   			{
				   				xtype:'label',
				   				html:'推荐指数:',
				   				style:'font-size:14px'
				   			}
			   			]
			   		},
			   		{
			   			xtype:'panel',
			   			layout:'hbox',
			   			padding:'5',
			   			items:[
				   			{
				   				xtype:'label',
				   				html:'最佳时间:',
				   				flex:1,
				   				style:'font-weight:bold; font-size:14px'
				   			},
				   			
				   			{
				   				xtype:'label',
				   				html:'最佳时间:',
				   				flex:4,
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
				   		html:'线路特点'
			   		},	
			   		
			   		{
			   			xtype:'label',
			   			itemId:'feature'
			   		},
			   		
			   		{
			   			xtype:'label',
				   		html:'推荐理由'
			   		},
			   		
			   		{
			   			xtype:'label',
			   			itemId:'reason'
			   		},
			   ]
		    },
		    
		    {
		    	xtype:'dataview',
		    	itemId:'scenes'
		    }
        ]
    },
    
    setData:function(record){
    	console.log(record);
    }
});

