Ext.define('YourTour.view.common.CommentListItemView', {
	extend: 'Ext.dataview.component.DataItem',
    requires:['Ext.Panel', 'YourTour.view.widget.XField','YourTour.view.widget.XPanel'],
    xtype: 'CommentListItemView',
    config: {
      	layout:'hbox',
      	padding:'10 5 10 5',
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
						xtype:'panel',
						layout:'hbox',
						items:[
							{
								itemId:'nickname',
								xtype:'xlabel'
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
						itemId:'comment',
						cls:'multilineinfo',
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
	 	   
	 	   var commentEl = me.down('#comment');
	 	   commentEl.setHtml(record.get('comment'));
	 	}
    }
});

