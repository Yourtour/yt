Ext.define('YourTour.view.home.PlaceMainView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Carousel', 'Ext.Panel','Ext.Img','Ext.DataView', 'YourTour.view.widget.XPanel', 'YourTour.view.widget.XButton','YourTour.view.widget.XField','YourTour.view.widget.XLabel', 'YourTour.view.widget.XToolbar','YourTour.view.line.LineResourceItem'],
    xtype: 'PlaceMainView',
    config: {
    	itemId:'PlaceMainView',
    	id:'PlaceMainView',
      	layout:'vbox',
    	scrollable: {
    	    direction: 'vertical',
    	    indicators: false	
    	},
    	defaults:{
    		padding:'0 5 0 5'
    	},
        items: [
        	{    
				xtype: 'xtoolbar',
				title:'',
				itemId:'toolbar',
				items:[
					{
	                	xtype: "toolbutton", 
	                    ui: "normal", 
	                	text:'咨询',
	                	itemId:'consult',
	                	align:'right'
	                }
				]			
			},
			
			{
            	xtype: 'carousel',
            	itemId:'placeCarousel',
            	height:100,
            	cls:'space-bottom'
            },
    	   	
    	   	{
    			xtype:'xpanel',
    			layout:'hbox',
    			cls:'underline',
	    		items:[
		    		{
					   xtype:'xlabel',
					   style:'height:60px; line-height:60px',
					   html : '直播室'
		    		},
					{
		    			xtype:'xpanel',
    					layout:'vbox',
    					margin:'0 0 0 30',
    					defaults:{
    						style:'height:30px; line-height:30px'
    					},
    					items:[
    						{
    							xtype:'xlabel',
    							itemId:'liveTitle',
					   			html : '目前有3000人参与行程直播互动'
    						},
    						{
    							xtype: 'carousel',
				            	itemId:'liveContent',
				            	indicator:false,
            					direction:'verticle',
				            	height:30
    						}
    					]
		    		}   		
				]
    	   	},
    	   	
		    {
    			xtype:'xpanel',
    			layout:'hbox',
    			cls:'underline',
	    		items:[
		    		{
					   xtype:'xlabel',
					   style:'height:60px; line-height:60px',
					   html : '聊天室'
		    		},
					{
		    			xtype:'xpanel',
    					layout:'vbox',
    					margin:'0 0 0 30',
    					defaults:{
    						style:'height:30px; line-height:30px'
    					},
    					items:[
    						{
    							xtype:'xlabel',
    							itemId:'chatTitle',
					   			html : '目前有3000人参与目的地聊天'
    						},
    						{
    							xtype: 'carousel',
				            	itemId:'chatContent',
				            	indicator:false,
            					direction:'verticle',
				            	height:30
    						}
    					]
		    		}   		
				]
    	   	},
			
    	   	{
    			xtype:'xpanel',
    			layut:'vbox',
    			cls:'space-top',
	    		items:[
		    		{
					   xtype:'xlabel',
					   cls:'row nav_arrow',
					   html : '达人'
		    		},
					   		
			   		{
			   			xtype:'xpanel',
    					layout:'hbox',
    					itemId:'talents',
    					defaults:{
    						flex:1
    					},
    					
    					items:[
    					]
			   		}
				]
    	   	},
    	   	
    	   	{
    			xtype:'xpanel',
    			layut:'vbox',
    			cls:'space-top',
	    		items:[
		    		{
					   xtype:'xlabel',
					   cls:'row nav_arrow',
					   html : '热门游'
		    		},
					   		
			   		{
			   			xtype:'xpanel',
    					layut:'hbox',
    					defaults:{
    						flex:1
    					}
			   		}
				]
    	   	},
    	   	
    		{
    			xtype:'xpanel',
    			layut:'vbox',
    			cls:'space-top',
	    		items:[
		    		{
					   xtype:'xlabel',
					   cls:'row nav_arrow',
					   html : '结伴游'
		    		},
					   		
			   		{
			   			xtype:'xpanel',
    					layout:'hbox',
    					itemId:'alongs',
    					defaults:{
    						flex:1,
    						padding:5
    					},
    					items:[
    					]
			   		}
				]
    	   	}	   	
        ]
    }
});

