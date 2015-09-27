Ext.define('YourTour.view.home.BestListItemView', {
	extend: 'Ext.dataview.component.DataItem',
    requires:['Ext.Panel', 'YourTour.view.widget.XPanel','YourTour.view.widget.XField','YourTour.view.widget.XLabel'],
    xtype: 'BestListItemView',
    config: {
    	layout:'hbox',
    	padding:5,
      	cls:'underline',
        items: [
		   	{
   				itemId : 'imageUrl',
				xtype : 'image',
				mode : 'tag'
    		},
    		
    		{
    			xtype:'panel',
    			layout:'vbox',
    			margin:'0 0 0 5',
    			items:[
					{
						itemId:'name',
						xtype:'xlabel'
					}
    			]
    		}
        ]
    },
    
    updateRecord: function(record){
    	var me = this;
       	if(record){
       	   var imageUrlEl = me.down('#imageUrl');
	 	   imageUrlEl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100; height:50'>");
	 	   
	 	   var nicknameEl = me.down('#name');
	 	   nicknameEl.setHtml(record.get('name'));
	 	}
    }
});

