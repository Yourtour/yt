Ext.define('YourTour.view.widget.XHeaderBar', {
    extend: 'Ext.TitleBar',
    xtype: 'xheaderbar',
    requires:['YourTour.view.widget.XBack', 'YourTour.view.widget.XPlainButton'],
    config: {
		itemId:'headerbar',
    	docked: 'top',
		cls:'headerbar',
    	style:'background-color:#3CB371',
		opacity:0,
    	defaults:{
    		style:'color:white'
    	}
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
	            xtype: 'button',
	            itemId:'back',
	            iconCls:'arrow_left',
	            ui: 'normal'
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

	updateOpacity:function(opacity){
		this.setOpacity(opacity);
	},

	setOpacity:function(opacity){
		this.opacity = opacity;
	},

    /**
     * @private
     */
    applyBackButton: function(backButton) {
    	this.backButton = backButton;
    }
});

