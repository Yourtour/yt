Ext.define('YourTour.view.widget.XHeaderBar', {
    extend: 'Ext.TitleBar',
    xtype: 'xheaderbar',
    requires:['YourTour.view.widget.XBack'],
    config: {
    	docked: 'top',
    	items:[
			{
			   xtype:'xback',
			   itemId:'back'
			}
    	]
    },
    
    initialize : function(){
    	this.callParent(arguments);
    	
    	var me = this;
    	var close = me.down('#back');
    	if(close != null){
	    	close.element.on({
				scope : me,
				tap : function(e, t) {
					Ext.ComponentManager.get('MainView').pop();
				}
			});
    	}
    },
});

