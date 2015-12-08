Ext.define('YourTour.view.member.MemberDataItem', {
	extend: 'Ext.dataview.component.DataItem',
    xtype:'MemberDataItem',
    config: {
	    layout:'hbox',
	    padding:5,
	    height:75,
	    cls:'underline',
        items: [
			{
				xtype:'image',
				itemId:'imageUrl',
				mode:'tag'
			}, 
			
			{
				xtype:'panel',
				layout:'vbox',
				items:[
				       {
				    	   xtype:'label',
				    	   itemId:'nickname',
				    	   html:'afadsfads'	   
				       }
				]
			}
        ]
    },
    
    updateRecord: function(record) {
        var me = this;
        
        if(record){
        	var imageUrl = me.down('#imageUrl');
  	 	   	imageUrl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:150px'>");
  	 	   
  	 	   	var nickname = me.down('#nickname');
  	 	   	nickname.setHtml(record.get('nickname'));
        }
    }   
});

