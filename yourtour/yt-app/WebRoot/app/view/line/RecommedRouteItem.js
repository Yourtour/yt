Ext.define('YourTour.view.line.RecommedRouteItem', {
    extend: 'Ext.dataview.component.DataItem',
    xtype: 'routeListItem',
    requires:['Ext.Panel'],
    config: {
    	layout : 'vbox',
    	margin:'5 0 5 0',
    	style:'background:#fff',
    	defaults:{
    		padding:'0 5 0 5'
    	},
    	items:[
    	   {
   				xtype:'label',
				itemId:'name',
				cls:'seperator nav_arrow',
   				style:'font-size:16px; height:40px; line-height:40px'
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
		   				style:'font-size:14px; color:grey'
		   			},
		   			
		   			{
		   				xtype:'image',
		    			src:'resources/images/raty_32.png',
		    			flex:2,
		    			mode:'tag'
		   			},
		   			
		   			{
		   				xtype:'label',
		   				html:'评价指数:',
		   				flex:1,
		   				style:'font-size:14px; color:grey'
		   			},
		   			{
		   				xtype:'image',
		    			src:'resources/images/raty_32.png',
		    			flex:2,
		    			mode:'tag'
		   			}
	   			]
	   		},
	   		
    	   {	xtype:'panel',
	    		layout:'hbox',
	    		margin:'5 0 0 0',
	    		defaults:{
	    			flex:1
	    		},
	    	    items:[
		    	    {
		    	   		itemId : 'imageUrl1',
			    		xtype : 'image',
			    		mode : 'tag',
			        	hidden:true
			    	},
			    	{
		    	   		itemId : 'imageUrl2',
			    		xtype : 'image',
			    		mode : 'tag',
			    		margin:'0 0 0 5',
			        	hidden:true
			    	},
			    	{
		    	   		itemId : 'imageUrl3',
			    		xtype : 'image',
			    		mode : 'tag',
			        	margin:'0 5 0 5',
			        	hidden:true
			    	},
			    	{
		    	   		itemId : 'imageUrl4',
			    		xtype : 'image',
			    		mode : 'tag',
			        	hidden:true
			    	}
		    	]
	    	},
    	   	
	       {
	       		xtype:'label',
				itemId:'feature',
   				style:'line-height:20px; font-size:14px; color:grey'		
		   }
    	]
    },
    
    updateRecord: function(record) {
       var me = this;
       
       if(record){
       	   var imageUrls = record.get('imageUrls');
	 	   var urls = imageUrls.split(';');
	 	   for (var i=1; i <= urls.length; i++){
	 	   		var img = me.down('#imageUrl' + i);
	 	   		img.show();
	 	   		img.setHtml("<img src='" + urls[i-1] + "' style='width:100%; max-height:50px'>");
	 	   }
	 	   
	 	   var nameEl = me.down('#name');
	 	   nameEl.setHtml(record.get('name'));
	 	   
	 	   var featureEl = me.down('#feature');
	 	   featureEl.setHtml(record.get('feature'));
	 	}
    }   
});

