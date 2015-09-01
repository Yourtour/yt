Ext.define('YourTour.view.user.UserListItem', {
    extend: 'YourTour.view.widget.VDataItem',
    xtype: 'UserListItem',
    requires:['YourTour.view.widget.XField','YourTour.view.widget.XLabel', 'Ext.Panel', 'Ext.Img'],
    config: {
    	layout : 'hbox',
    	cls:'verticledataitem underline',
    	padding:'10 5 10 5',
    	items:[
    	    {
    	    	xtype:'panel',
			   	layout:'vbox',
			   	items:[
				   	{
		    	   		itemId : 'imageUrl',
			    		xtype : 'image',
			    		mode : 'tag'
				   	},
				   	
				   	{
				   		margin:'7 0 7 0',
				   		style:'text-align:center',
		    	   		itemId : 'chat',
			    		xtype : 'xlabel',
			    		html:'私信'
				   	}
			   	]
	    	},
    		
		    {
			   xtype:'panel',
			   layout:'vbox',
			   flex:1,
			   margin:'0 0 0 10',
			   items:[
			   		{	xtype:'panel',
			   			layout:'hbox',
			   			margin:'5 0 0 0',
			   			
				   		items:[
				   			{
				   				xtype:'xfield',
				   				itemId:'nickname',
				   				margin:'0 10 0 0'
						   	},
						   	
					    	{
				    	   		xtype : 'image',
	    						mode : 'tag',
				   				itemId:'female',
				   				src:'resources/icons/icon_female.png',
				   				hidden:true
					    	},
					    	
					    	{
				    	   		xtype : 'image',
	    						mode : 'tag',
				   				itemId:'male',
				   				src:'resources/icons/icon_male.png',
				   				hidden:true
					    	},
						   	
					    	{
				    	   		xtype:'xfield',
				    	   		style:'text-align:center',
				   				itemId:'rank',
				   				flex:1
					    	},
					    	
					    	{
				    	   		xtype:'xfield',
				    	   		style:'text-align:center',
				   				itemId:'role',
				   				flex:1
					    	}
				    	]
			   		},
			   		{
		            	xtype:'xfield',
		            	itemId:'slogan',
		            	margin:'5 0 5 0',
		            	cls:'multilineinfo'
		            }
			    ]
		    }
    	]
    },
    
    updateRecord: function(record) {
       	var me = this;
       	
       	if(record){
	 	   var imageUrl = me.down('#imageUrl');
	 	   imageUrl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:250px'>");
	 	   
	 	   var nickname = me.down('#nickname');
	 	   nickname.setHtml(record.get('nickname'));
	 	   
	 	   var slogan = me.down('#slogan');
	 	   slogan.setHtml(record.get('slogan'));
	 	   
	 	   var male = me.down('#male'), female = me.down('#female');
	 	   if(record.get('sex') == 'F'){
	 	   		female.show();
	 	   }else{
	 	   		male.show();
	 	   }
	 	   
	 	   var rank = me.down('#rank');
	 	   rank.setHtml(record.get('rank'));
	 	   
	 	   var role = me.down('#role');
	 	   role.setHtml(record.get('role'));
       	}
    }
});