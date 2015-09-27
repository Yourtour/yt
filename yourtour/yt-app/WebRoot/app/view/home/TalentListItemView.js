Ext.define('YourTour.view.home.TalentListItemView', {
    extend: 'Ext.Panel',
    requires:['Ext.Panel', 'YourTour.view.widget.XField','YourTour.view.widget.XPanel'],
    xtype: 'TalentListItemView',
    config: {
    	model:null,
      	layout:'hbox',
      	padding:5,
      	cls:'space-bottom',
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
    
    updateRecord: function(record){
    	var me = this;
       	if(record){
       	   var imageUrlEl = me.down('#imageUrl');
	 	   imageUrlEl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:48px; max-height:48px'>");
	 	   
	 	   var nicknameEl = me.down('#nickname');
	 	   nicknameEl.setHtml(record.get('nickname'));
	 	   
	 	   var sexImgUrlEl = me.down('#sexImgUrl');
	 	   sexImgUrlEl.setHtml("<img src='resources/icons/" + (record.get('sex')=='M'?"icon_male.png":"icon_female.png") + "'>");
	 	   
	 	   var tag1El = me.down('#tag1');
	 	   tag1El.setHtml(record.get('tag1'));
	 	   
	 	   var tag2El = me.down('#tag2');
	 	   tag2El.setHtml(record.get('tag2'));
	 	}
    }
});

