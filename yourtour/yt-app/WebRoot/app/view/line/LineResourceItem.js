Ext.define('YourTour.view.line.LineResourceItem', {
    extend: 'Ext.dataview.component.DataItem',
    xtype: 'LineResourceItem',
    requires:['Ext.Label', 'Ext.Img', 'YourTour.view.widget.XField','YourTour.view.widget.XLabel'],
    config: {
    	layout : 'vbox',
    	cls:'space-bottom',
    	padding:5,
    	items:[
    	    {
    	   		itemId : 'name',
	    		xtype : 'xlabel',
	    		cls:'row nav-arrow font-medium horizentalLine'
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
            	xtype:'xfield',
            	itemId:'intro',
            	cls:'multilineinfo'
            }
    	]
    },
    
    updateRecord: function(record) {
       var me = this;
       
       this.record = record;
       if(record){
	 	   var name = me.down('#name');
	 	   name.setHtml(record.get('name'));
	 	   
	 	   var imageUrls = record.get('imageUrls');
	 	   var urls = imageUrls.split(';');
	 	   for (var i=1; i <= urls.length; i++){
	 	   		var img = me.down('#imageUrl' + i);
	 	   		img.show();
	 	   		img.setHtml("<img src='" + urls[i-1] + "' style='width:100%; max-height:50px'>");
	 	   }
	 	   
	 	   var intro = me.down('#intro');
	 	   intro.setHtml(record.get('intro'));
       }
    }   
});