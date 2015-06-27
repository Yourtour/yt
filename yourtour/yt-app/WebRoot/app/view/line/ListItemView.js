Ext.define('YourTour.view.line.ListItemView', {
    extend: 'Ext.Container',
    xtype: 'lineListItemView',
    requires:['Ext.Img'],
    config: {
    	layout : 'vbox',
    	
    	items:[
    	   {
    		   itemId : 'imageUrl',
    		   id:'imageUrl',
    		   xtype : 'image',
    		   mode : 'tag',
    		   src : ''
    	   },
    	   
    	   {
    		   itemId : 'name',
    		   id:'name',
    		   html:'名称'
    	   }
    	]
    },
    
    initialize : function() {
    	this.callParent(arguments);
	},


	beforeInitialize : function() {
		this.imageEl = this.down('#imageUrl');
		this.nameEl = this.down('#name');
	},

	//该方法需要在datatitem调用该方法
	setImageUrl : function(imageUrl) {
		this.imageEl.setSrc(imageUrl);
	},
	
	//该方法需要在datatitem调用该方法
	setName : function(name) {
		this.nameEl.setHtml(name);
	}
});

