Ext.define('YourTour.view.user.ExpertListItem', {
    extend: 'Ext.dataview.component.DataItem',
    xtype: 'expertListItem',
    requires:['Ext.Label', 'Ext.Panel', 'Ext.Img'],
    config: {
    	layout : 'hbox',
    	cls:'expertListItem',
    	padding:'5 10 5 10',
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
			   flex:1,
			   margin:'0 0 0 10',
			   items:[
			   		{	xtype:'panel',
			   			layout:'hbox',
			   			defaults:{
			   				flex:1
			   			},
				   		items:[
				   			{
				   				xtype:'label',
				   				itemId:'nickName',
				   				style:'font-weight:bold; font-size:14px'
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
				    	   		xtype:'label',
				   				itemId:'rank',
				   				style:'font-weight:bold; font-size:14px'
					    	}
				    	]
			   		},
			   		{
		            	xtype:'label',
		            	itemId:'slogan',
		            	margin:'5 0 0 0',
		            	style:'font-size:14px'
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
	 	   
	 	   var nickName = me.down('#nickName');
	 	   nickName.setHtml(record.get('nickName'));
	 	   
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
       	}
    }
});