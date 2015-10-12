Ext.define('YourTour.view.widget.XHeaderBar', {
    extend: 'Ext.TitleBar',
    xtype: 'xheaderbar',
    requires:['YourTour.view.widget.XBack', 'YourTour.view.widget.XPlainButton'],
    config: {
    	docked: 'top'
    },
    
    constructor: function(config) {
    	config = config || {};
    	
        if (!config.items) {
             config.items = [];
        }
        
        var found = false;
        Ext.each(config.items, function(item){
        	if(item['itemId'] == 'back'){
        		found = true;
        	}
        });
        
        var backButton = config['backButton'] == undefined || config['backButton']?true:false;
        if(! found && backButton){
	        config.items.push({
	            xtype: 'xplainbutton',
	            itemId:'back',
	 		   	text:'返回',
	            ui: 'back'
	        });
        }
        
    	this.callParent([config]);
    },
    
    initialize : function(){
    	this.callParent(arguments);
    	
    	var me = this;
    	var back = me.down('#back');
    	if(back != null){
    		back.element.on({
				scope : me,
				tap : function(e, t) {
					Ext.ComponentManager.get('MainView').pop();
				}
			});
    	}
    },
    
    /**
     * @private
     */
    applyBackButton: function(backButton) {
    	this.backButton = backButton;
    }
});
