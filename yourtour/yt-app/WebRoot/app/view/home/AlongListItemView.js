Ext.define('YourTour.view.home.AlongListItemView', {
	extend: 'Ext.dataview.component.DataItem',
    requires:['Ext.Panel', 'YourTour.view.widget.XPanel','YourTour.view.widget.XField','YourTour.view.widget.XLabel'],
    xtype: 'AlongListItemView',
    config: {
    	padding:5,
      	layout:'vbox',
      	cls:'space-bottom',
        items: [
            {    
            	xtype:'panel',
            	layout:'hbox',
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
	    			    	   margin:'5 0 0 0',
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
    			margin:'5 0 0 0',
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
            },
        ]
    },
    
    updateRecord: function(record){
    	var me = this;
       	if(record){
       	   var imageUrlEl = me.down('#imageUrl');
 	 	   imageUrlEl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:100px'>");
 	 	   
 	 	   var titleEl = me.down('#title');
 	 	   titleEl.setHtml(record.get('title'));
 	 	   
 	 	   var nickNameEl = me.down('#nickName');
 	 	   nickNameEl.setHtml(record.get('nickName'));
	 	   
 	 	   var publishTimeEl = me.down('#publishTime');
 	 	   publishTimeEl.setHtml(record.get('publishTime'));
 	 	   
 	 	   var intentionEl = me.down('#intention');
 	 	   intentionEl.setHtml(record.get('intention'));
 	 	   
 	 	   var routeDateEl = me.down('#routeDate');
 	 	   routeDateEl.setHtml(record.get('startDate') + '到' + record.get('endDate'));
 	 	   
 	 	   var lineNameEl = me.down('#lineName');
 	 	   lineNameEl.setHtml(record.get('lineName'));
 	 	   
 	 	   var memoEl = me.down('#memo');
 	 	   memoEl.setHtml(record.get('memo'));
 	 	   
 	 	   var addressEl = me.down('#address');
	 	   addressEl.setHtml(record.get('address'));
	 	   
	 	   var readNumEl = me.down('#readNum');
	 	   readNumEl.setHtml(record.get('readNum'));
	 	   
	 	   var commentNumEl = me.down('#commentNum');
	 	  commentNumEl.setHtml(record.get('commentNum'));
	 	}
    }
});

