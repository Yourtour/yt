Ext.define('YourTour.view.home.CarouselItemView', {
    extend: 'Ext.Panel',
    requires:['Ext.Panel', 'YourTour.view.widget.XField','YourTour.view.widget.XPanel'],
    xtype: 'CarouselItemView',
    config: {
    	model:null,
      	layout:'vbox',
        items: [
		   	{
   				itemId : 'imageUrl',
				xtype : 'image',
				mode : 'tag'
    		}
        ]
    },
    
    applyModel:function(model){
    	var me = this;
       	if(model){
       	   var imageUrlEl = me.down('#imageUrl');
	 	   imageUrlEl.setHtml("<img src='" + model.get('imageUrl') + "' style='width:100%; max-height:200px'>");
	 	}
    }
});

