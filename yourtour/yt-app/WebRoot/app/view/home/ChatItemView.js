Ext.define('YourTour.view.home.ChatItemView', {
    extend: 'Ext.Panel',
    requires:['Ext.Panel', 'YourTour.view.widget.XPanel'],
    xtype: 'LiveItemView',
    config: {
    	model:null,
    	
    	itemId:'LiveItemView',
    	id:'LiveItemView',
      	layout:'card',
    	defaults:{
    		padding:'0 5 0 5'
    	},
        items: [
		    {
			   xtype:'xlabel',
			   itemId:'content'
    		}
        ]
    },
    
    applyModel:function(model){
    	var me = this;
    	
		var content = me.down('#content');
		content.setHtml(model.get('content'));
    }
});

