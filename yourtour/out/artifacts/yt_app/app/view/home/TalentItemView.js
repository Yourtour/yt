Ext.define('YourTour.view.home.TalentItemView', {
    extend: 'Ext.Panel',
    requires:['Ext.Panel', 'YourTour.view.widget.XField','YourTour.view.widget.XPanel'],
    xtype: 'TalentItemView',
    config: {
    	model:null,
      	layout:'hbox',
      	padding:5,
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
								margin:'0 0 0 5',
								mode : 'tag'
							}
						]
					},
					
					{
						itemId:'tag1',
						margin:'5 0 0 0',
						xtype:'xfield'
					},
					
					{
						itemId:'tag2',
						margin:'5 0 0 0',
						xtype:'xfield'
					}
    			]
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

