Ext.define('YourTour.view.route.MainItem', {
    extend: 'Ext.Panel',
    xtype: 'routemainitem',
    requires:['Ext.Carousel', 'YourTour.view.widget.TitleBar', 'YourTour.view.widget.ToolButton', 'Ext.field.Hidden'],
    
    config: {
    	itemId:'routemainitem',
    	fullscreen: true,
    	layout:'vbox',
    	
        items: [
			{
			   xtype:'image', 
			   id:'imgUrl',
			   mode : 'tag',
			}
        ]
    },
    
    updateRecord: function(record) {
    	console.log('updateRecord');
        var me = this;
        
        if(record){
 	 	   var imageUrl = me.down('#imageUrl');
 	 	   imageUrl.setSrc(record.get('imageUrl'));
 	 	}
     }   
});

