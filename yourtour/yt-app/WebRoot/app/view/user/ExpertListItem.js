Ext.define('YourTour.view.user.ExpertListItem', {
    extend: 'Ext.Panel',
    xtype: 'expertListItem',
    requires:['Ext.Label', 'Ext.Panel', 'Ext.Img'],
    config: {
    	data:undefined,
    	itemId:'expertListItem',
    	layout : 'hbox',
    	cls:'expertListItem',
    	padding:'5 0 5 0',
    	style:'border-bottom:1px solid #EDEDED',
    	items:[
    	    {
    	   		itemId : 'imageUrl',
	    		xtype : 'image',
	    		mode : 'tag'
	    	},
    		
		    {
			   xtype:'panel',
			   layout:'vbox',
			   margin:'0 0 0 10',
			   items:[
			   		{	xtype:'panel',
			   			layout:'hbox',
				   		items:[
				   			{
				   				xtype:'label',
				   				itemId:'nickName',
				   				style:'font-weight:bold; font-size:14px'
						   	},
						   	
					    	{
				    	   		xtype:'label',
				   				itemId:'level',
				   				style:'font-weight:bold; font-size:14px'
					    	}
				    	]
			   		},
			   		{
		            	xtype:'label',
		            	itemId:'info',
		            	html:'达人信息',
		            	style:'font-size:14px'
		            }
			    ]
		    }
    	]
    },
    
    setData: function(record) {
       	var me = this;
       	if(record){
	 	   var imageUrl = me.down('#imageUrl');
	 	   imageUrl.setHtml("<img src='" + record.imageUrl + "' style='width:100%; max-height:250px'>");
	 	   
	 	   var nickName = me.down('#nickName');
	 	   nickName.setHtml(record.nickName);
	 	}
    }
});

