Ext.define('YourTour.view.home.BestItemView', {
    extend: 'YourTour.view.widget.grid.XGridItem',
    requires:['Ext.Panel', 'YourTour.view.widget.XPanel','YourTour.view.widget.XField'],
    xtype: 'BestItemView',
    config: {
    	padding:5,
        items: [
			{
				itemId : 'imageUrl',
				xtype : 'image',
				mode : 'tag'
			},    
		    {
			   xtype:'xfield',
			   itemId:'name'
    		}
        ]
    },
    
    applyModel:function(model){
    	var me = this;
       	if(model){
       	   var imageUrlEl = me.down('#imageUrl');
	 	   imageUrlEl.setHtml("<img src='" + model.get('imageUrl') + "' style='width:100%; height:100%'>");
	 	   
	 	   var nicknameEl = me.down('#name');
	 	   nicknameEl.setHtml(model.get('name'));
	 	}
    }
});

