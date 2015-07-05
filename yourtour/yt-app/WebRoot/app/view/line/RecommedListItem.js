Ext.define('YourTour.view.line.RecommedListItem', {
    extend: 'Ext.Panel',
    xtype: 'lineListItem',
    requires:['YourTour.view.widget.SubTitleBar','Ext.Panel', 'Ext.Img', 'Ext.DataView','YourTour.view.user.ExpertListItem'],
    config: {
    	data:undefined,
    	fullscreen: true,
    	layout : 'vbox',
    	cls:'lineListItem',
		scrollable: {
    	    direction: 'vertical',
    	    directionLock: true
    	},
    	
    	items:[
    	   {
    	   		itemId : 'imageUrl',
	    		xtype : 'image',
	    		mode : 'tag',
	        	src : ''
	    	},
    		
	    	{
			   xtype:'markedlabel',
			   pack:'center',
			   align:'center',
			   itemId:'name',
			   html : ''
		    },
		    
		    {
			   xtype:'panel',
			   margin:'5 0 0 0',
			   style:'background:#fff',
			   layout:'vbox',
			   items:[
			   		{
			   			xtype:'panel',
			   			layout:'hbox',
			   			padding:'5',
			   			defaults:{
			   				flex:1
			   			},
			   			items:[
				   			{
				   				xtype:'label',
				   				html:'推荐指数:',
				   				style:'font-weight:bold; font-size:14px'
				   			},
				   			
				   			{
				   				xtype:'label',
				   				html:'推荐指数:',
				   				style:'font-size:14px'
				   			},
				   			
				   			{
				   				xtype:'label',
				   				html:'旅游指数:',
				   				style:'font-weight:bold; font-size:14px'
				   			},
				   			{
				   				xtype:'label',
				   				html:'推荐指数:',
				   				style:'font-size:14px'
				   			}
			   			]
			   		},
			   		{
			   			xtype:'panel',
			   			layout:'hbox',
			   			padding:'5',
			   			defaults:{
			   				flex:1
			   			},
			   			items:[
				   			{
				   				xtype:'label',
				   				html:'最佳时间:',
				   				style:'font-weight:bold; font-size:14px'
				   			},
				   			{
				   				xtype:'label',
				   				html:'最佳时间:',
				   				style:'font-size:14px'
				   			},
				   			{
				   				xtype:'label',
				   				style:'font-weight:bold; font-size:14px'
				   			},
				   			{
				   				xtype:'label',
				   				style:'font-size:14px'
				   			}
			   			]
			   		}
			   ]
		    },
		    
		    {
            	xtype:'subtitlebar',
            	margin:'5 0 5 0',
            	html:'达人信息'
            },
            
    	    {
    		   itemId : 'users',
    		   xtype:'panel',
    		   style:'background:#fff',
    		   items:[
    		   ]
    	    }
    	]
    },
    
    setData: function(record) {
       var me = this;
       
       if(record){

       	   var imageUrl = me.down('#imageUrl');
	 	   imageUrl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:150px'>");
	 	   
	 	   var name = me.down('#name');
	 	   name.setHtml(record.get('name'));
	 	   
	 	   var users = me.down('#users');
	 	   record.users().each(function(user){
	 	   	  var item = Ext.create('YourTour.view.user.ExpertListItem',{data:user.data});	
	 	   	  users.add(item);
	 	   });
	 	}
    }   
});

