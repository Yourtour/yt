Ext.define('YourTour.view.home.AlongDetailView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.DataView', 'Ext.Toolbar', 'YourTour.view.home.AlongListItemView', 'YourTour.view.widget.XBlock', 'YourTour.view.common.CommentListItemView', 'YourTour.view.widget.XHeaderBar','YourTour.view.widget.XField','YourTour.view.widget.XLabel'],
    config: {
    	id:'AlongDetailView',
    	layout:'vbox',
    	scrollable: {
    	    direction: 'vertical',
    	    indicators: false	
    	},
        items: [
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'详情'
			}, 
            
			{
				xtype:'xblock',
				layout:'vbox',
				items:[
					{    
		            	xtype:'panel',
		            	layout:'hbox',
		            	cls:'underline',
		            	items:[
			            	{
				   				itemId : 'imageUrl',
								xtype : 'image',
								mode : 'tag',
								flex:1
				    		},
				    		
				    		{
				    			xtype:'panel',
				    			layout:'vbox',
				    			margin:'0 0 0 10',
				    			flex:5,
				    			items:[
			    			       {	
			    			    	   xtype:'label',
			    			    	   cls:'font-large',
			    			    	   itemId:'title'
			    			       },
			    			       {	
			    			    	   xtype:'panel',
			    			    	   layout:'hbox',
			    			    	   margin:'10 0 0 0',
			    			    	   items:[
		    			    	          {
		    			    	        	xtype:'xfield',
		    			    	        	itemId:'nickName'
		    			    	          },
		    			    	          {
							   				itemId : 'genderImageUrl',
											xtype : 'image',
											mode : 'tag'
		    			    	          },
			    			    	   ]
			    			       },
			    			       {
			    			    	   xtype:'panel',
			    			    	   layout:'hbox',
			    			    	   margin:'10 0 0 0',
			    			    	   defaults:{
			    			    		 flex:1  
			    			    	   },
			    			    	   items:[
			    			    	        {
			      			    	        	xtype:'xfield',
			      			    	        	itemId:'publishTime'
			      			    	        },
			      			    	        {
			      			    	        	xtype:'xfield',
			      			    	        	itemId:'intention'
			      			    	        }
			    			    	   ]
			    			       }
				    			]
				    		}
				    	]
		            },
					
		            {
		    			xtype:'panel',
		    			layout:'hbox',
		    			margin:'10 0 0 0',
		    			items:[
							{
								xtype:'xfield',
								html:'日程安排：'
							},
				            {
				            	xtype:'xfield',
				            	itemId:'routeDate'
				            }
				        ]
		            },
		            
		            {
		    			xtype:'panel',
		    			layout:'hbox',
		    			margin:'5 0 0 0',
		    			items:[
							{
								xtype:'xfield',
								html:'线路安排：'
							},
				            {
				            	xtype:'xfield',
				            	itemId:'lineName'
				            }
				        ]
		            },
		            {
		            	xtype:'xfield',
		            	margin:'5 0 0 0',
		            	cls:'multilineinfo',
		            	itemId:'memo'
		            },
		            
		            {
		    			xtype:'panel',
		    			layout:'hbox',
		    			margin:'10 0 0 0',
		    			items:[
							{
								xtype : 'image',
								width:10,
								src:'resources/icons/icon_location_marker.png'
							},   
							{
								xtype:'xfield',
								margin:'0 0 0 5',
								itemId:'address',
								flex:6
							},
							{
								xtype : 'image',
								width:20,
								src:'resources/icons/icon_comment.png'	
							},
				            {
				            	xtype:'xfield',
				            	itemId:'commentNum',
				            	margin:'0 0 0 5',
				            	flex:1
				            },
							{
								xtype : 'image',
								width:15,
								src:'resources/icons/icon_read.png'
							},
							{
				            	xtype:'xfield',
				            	itemId:'readNum',
				            	margin:'0 0 0 5',
				            	pack:'center',
				            	align:'center',
				            	flex:1
				            }
				        ]
		            }
	            ]
    		},
            
            {
            	xtype:'dataview',
            	itemId:'commentList',
            	cls:'space-top',
            	scrollable:false,
		        useComponents: true,
		        flex:1,
		        defaultType: 'CommentListItemView'
    		},
    		
    		{
    			xtype:'toolbar',
    			docked: 'bottom',
    			items: [{
    	            xtype: 'button',
    	            text: '留言',
    	            id: 'btnComment'
    	        },
    	        {
    	            xtype: 'spacer'
    	        },
    	        {
    	            xtype: 'button',
    	            text: '私信',
    	            id: 'btnMessage'
    	        },
    	        {
    	            xtype: 'spacer'
    	        },
    	        {
    	            xtype: 'button',
    	            text: '举报',
    	            id: 'btnReport'
    	        }]
    		}
        ]
    }
});

