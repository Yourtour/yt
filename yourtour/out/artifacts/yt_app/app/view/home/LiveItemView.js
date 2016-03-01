Ext.define('YourTour.view.home.LiveItemView', {
    extend: 'Ext.Panel',
    requires:['Ext.Panel', 'YourTour.view.widget.XPanel'],
    xtype: 'LiveItemView',
    config: {
    	model:null,
      	layout:'card',
    	defaults:{
    		padding:'0 5 0 5'
    	},
        items: [
		    {
			   xtype:'xlabel',
			   itemId:'lineName'
    		}
        ]
    },
    
    applyModel:function(model){
    	var me = this;
    	
		var lineName = me.down('#lineName');
		lineName.setHtml(model.get('lineName'));
    }
});

