Ext.define('YourTour.view.line.ListItem', {
    extend: 'Ext.dataview.component.DataItem',
    xtype: 'lineListItem',
    config: {
    	layout : 'vbox',
    	cls:'clsLineItem',
    	items:[
    	   {
    		   xtype:'panel',
    		   layout:'hbox',
    		   cls:'clsLineInfo',
	    	   items:[{
	    		   itemId : 'imageUrl',
	    		   xtype : 'image',
	    		   mode : 'tag',
	    		   flex:1,
	    		   src : ''
	    	   },
	    	   
	    	   {
	    		   itemId : 'name',
	    		   html:'名称',
	    		   flex:2
	    	   }]
    	   },
    	   
    	   {
    		   itemId : 'users',
    		   xtype:'dataview',
    		   style:'height:85px',
    		   scrollable:false,
    		   itemTpl:'<div class="clsExpertInfo"><table height="100%" width="100%"><tr><td width="40px"><img src="{imageUrl}"></td><td>{nickName}</td></tr></table></div>'
    	   },
    	]
    },
    
    updateRecord: function(record) {
       var me = this;
       
       if(record){
	 	   var imageUrl = me.down('#imageUrl');
	 	   imageUrl.setSrc(record.get('imageUrl'));
	 	   
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

