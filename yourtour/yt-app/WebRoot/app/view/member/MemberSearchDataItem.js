Ext.define('YourTour.view.member.MemberSearchDataItem', {
	extend: 'Ext.dataview.component.DataItem',
    xtype:'MemberSearchDataItem',
    config: {
	    layout:'hbox',
	    cls:'underline',
		padding:10,
        items: [
			{
				xtype:'ximage',
				itemId:'imageUrl'
			},

			{
				xtype:'panel',
				layout:'vbox',
				margin:'0 0 0 10',
				flex:1,
				items:[
			       {
			    	   xtype:'xlabel',
					   cls:'font-medium',
			    	   itemId:'nickName'
			       }
				]
			}
        ]
    },
    
    updateRecord: function(record) {
        var me = this;
        
        if(record){
        	var imageUrl = me.down('#imageUrl');
  	 	   	imageUrl.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "' style='width:40px; height:40px'>");
  	 	   
  	 	   	var nickName = me.down('#nickName');
  	 	   	nickName.setHtml(record.get('nickName'));
        }
    }   
});

