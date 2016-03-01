Ext.define('YourTour.view.home.ChatItemView', {
	extend: 'Ext.dataview.component.DataItem',
    requires:['Ext.Panel', 'YourTour.view.widget.XPanel'],
    xtype: 'ChatItemView',
    config: {
      	layout:'hbox',
   		padding:'0 5 0 5',
   		cls:'horizentalLine row',
        items: [
			{
				xtype:'image',
				itemId:'logoUrl',
				mode:'tag'
			},    
		    {
			   xtype:'xfield',
			   margin:'0 0 0 5',
			   itemId:'content'
    		}
        ]
    },
    
    updateRecord: function(record) {
    	var me = this;
    	
    	if(record){
    		var imageUrl = me.down('#logoUrl');
  	 	   	imageUrl.setHtml("<img src='" + record.get('logoUrl') + "' style='width:100%; max-height:150px'>");
  	 	   	
  	 	   	var content = me.down('#content');
  	 	 	content.setHtml(record.get('content'));
    	}
    }
});

