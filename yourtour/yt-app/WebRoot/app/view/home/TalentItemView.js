Ext.define('YourTour.view.home.TalentItemView', {
    extend: 'Ext.Panel',
    requires:['Ext.Panel', 'YourTour.view.widget.XField','YourTour.view.widget.XPanel'],
    xtype: 'TalentItemView',
    config: {
    	model:null,
      	layout:'vbox',
        items: [
		   	{
   				itemId : 'imageUrl',
				xtype : 'image',
				mode : 'tag'
    		},
    		
    		{
    			xtype:'panel',
    			layout:'hbox',
    			items:[
	    			{
	    				itemId:'nickname',
	    				xtype:'xfield'
	    			},
	    			{
	    				itemId : 'sexImgUrl',
						xtype : 'image',
						mode : 'tag'
	    			}
    			]
    		},
    		
    		{
				itemId:'tag1',
				xtype:'xfield'
			},
			
			{
				itemId:'tag2',
				xtype:'xfield'
			}
        ]
    },
    
    applyModel:function(model){
    	var me = this;
       	if(model){
       	   var imageUrlEl = me.down('#imageUrl');
	 	   imageUrlEl.setHtml("<img src='" + model.get('imageUrl') + "' style='width:48px; max-height:48px'>");
	 	   
	 	   var nicknameEl = me.down('#nickname');
	 	   nicknameEl.setHtml(model.get('nickname'));
	 	   
	 	   var sexImgUrlEl = me.down('#sexImgUrl');
	 	   sexImgUrlEl.setHtml("<img src='resources/icons/" + (model.get('sex')=='M'?"icon_male.png":"icon_female.png") + "'>");
	 	   
	 	   var tag1El = me.down('#tag1');
	 	   tag1El.setHtml(model.get('tag1'));
	 	   
	 	   var tag2El = me.down('#tag2');
	 	   tag2El.setHtml(model.get('tag2'));
	 	}
    }
});

