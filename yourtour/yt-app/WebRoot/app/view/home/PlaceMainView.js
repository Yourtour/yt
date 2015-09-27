Ext.define('YourTour.view.home.PlaceMainView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Carousel', 'Ext.Panel','Ext.Img','Ext.DataView', 'YourTour.view.widget.XPanel', 'YourTour.view.widget.XButton','YourTour.view.widget.XField','YourTour.view.widget.XLabel', 'YourTour.view.widget.XToolbar','YourTour.view.line.LineResourceItem','YourTour.view.widget.XGridView'],
    xtype: 'PlaceMainView',
    config: {
    	itemId:'PlaceMainView',
    	id:'PlaceMainView',
      	layout:'vbox',
    	scrollable: {
    	    direction: 'vertical',
    	    indicators: false	
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
						text:'收藏',
						itemId:'favorite',
						align:'right'
					},   
					{
	                	xtype: "toolbutton", 
	                    ui: "normal", 
	                	text:'点评',
	                	itemId:'consult',
	                	align:'right'
	                }
				]			
			},
			
			{
            	xtype: 'carousel',
            	itemId:'placeCarousel',
            	height:200,
            	cls:'space-bottom'
            },
    	   	
            {
        		xtype:'toolbar',
        		id:'navigationBar',
        		defaults:{
        			flex:1,
        			height:'80px',
        			pack:'center',
        			padding:'5 0 0 0',
        			style:'color:grey'
        		},
        		baseCls:'navigationBar',
        		
        		ui:'light',
	            items:[
	            	{
	            		itemId:'btnHome', 
		                text:'精选',
		                iconCls:'home',
		                iconAlign:'top'
		            },
		            {
		                itemId:'btnRoute',
		                text:'游玩',
		                iconCls:'home',
		                iconAlign:'top'
		            },
		            {
		            	itemId:'btnPersonal',
		                text:'美食',
		                iconCls:'user',
		                iconAlign:'top'
		            },
		            {
		            	itemId:'btnPersonal',
		                text:'住宿',
		                iconCls:'user',
		                iconAlign:'top'
		            }
	            ]
        	},
    	   	
		    {
    			xtype:'xpanel',
    			layout:'vbox',
    			cls:'underline space-top',
    			padding:'0 5 0 5',
    			defaults:{
    				cls:'font-medium'
    			},
	    		items:[
		    		{
		    		   xtype:'xlabel',
					   cls:'row nav_arrow font-large underline',
					   html : '聊天室'
		    		},
		    		
		    		{
		    			itemId:'chatList',
		    			xtype:'dataview',
		    			scrollable:null,
				        useComponents: true,
				        defaultType: 'ChatItemView'
		    		}		
				]
    	   	},
			
    	   	{
    			xtype:'xpanel',
    			layut:'vbox',
    			cls:'space-top',
    			padding:'0 5 0 5',
	    		items:[
		    		{
					   xtype:'xlabel',
					   itemId:'moreTalent',
					   cls:'row nav_arrow font-large underline',
					   html : '当地达人',
					   tappable :true
		    		},
					
		    		{
			   			xtype:'xgridview',
			   			itemId:'talents',
    					layut:'hbox',
    					cols:2,
    					hSpace:5,
    					item:'YourTour.view.home.TalentItemView'
			   		}
				]
    	   	},
    	   	
    	   	{
    			xtype:'xpanel',
    			layut:'vbox',
    			cls:'space-top',
    			padding:'0 5 0 5',
	    		items:[
		    		{
					   xtype:'xlabel',
					   itemId:'moreBest',
					   cls:'row nav_arrow font-large underline',
					   html : '当季游',
					   tappable :true
		    		},
					   		
			   		{
			   			xtype:'xgridview',
			   			itemId:'bestView',
    					layut:'hbox',
    					cols:2,
    					item:'YourTour.view.home.BestItemView'
			   		}
				]
    	   	},
    	   	
    		{
    			xtype:'xpanel',
    			layut:'vbox',
    			cls:'space-top',
    			padding:'0 5 0 5',
	    		items:[
		    		{
					   xtype:'xlabel',
					   cls:'row nav_arrow font-large underline',
					   html : '结伴游'
		    		},
					 
		    		{
			   			xtype:'xgridview',
			   			itemId:'alongs',
    					layut:'hbox',
    					cols:2,
    					item:'YourTour.view.home.AlongItemView'
			   		}
				]
    	   	}	   	
        ]
    }
});

