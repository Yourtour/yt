Ext.define('YourTour.view.line.RecommedListItem', {
    extend: 'Ext.Panel',
    xtype: 'lineListItem',
    requires:['YourTour.view.widget.SubTitleBar','Ext.Panel', 'Ext.Img'],
    config: {
    	data:undefined,
    	
    	layout : 'vbox',
    	cls:'lineListItem',
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
			   			items:[
				   			{
				   				xtype:'label',
				   				html:'最佳时间:',
				   				flex:1,
				   				style:'font-weight:bold; font-size:14px'
				   			},
				   			
				   			{
				   				xtype:'label',
				   				html:'最佳时间:',
				   				flex:4,
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
    		   xtype:'dataview',
    		   style:'height:85px',
    		   scrollable:false,
    		   itemTpl:'<div class="clsExpertInfo"><table height="100%" width="100%"><tr><td width="40px"><img src="{imageUrl}"></td><td>{nickName}</td></tr></table></div>'
    	    }
    	]
    },
    
    setData: function(record) {
       var me = this;
       
       if(record){
	 	   var imageUrl = me.down('#imageUrl');
	 	   imageUrl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:250px'>");
	 	   
	 	   var name = me.down('#name');
	 	   name.setHtml(record.get('name'));
	 	   
	 	   var users = me.down('#users');
	 	   var arrayUser = [];
	 	   record.users().each(function(user){
	 	   	  arrayUser.push(user.data);
	 	   });
	 	   
	 	   var store = Ext.create('Ext.data.Store',{data:arrayUser, model:'YourTour.model.UserModel'});
	 	   users.setStore(store);  
	 	}
    }   
});

