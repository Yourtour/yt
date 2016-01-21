Ext.define('YourTour.view.widget.XHeaderBar', {
    extend: 'Ext.Container',
    xtype: 'xheaderbar',
    requires:['YourTour.view.widget.XBack', 'YourTour.view.widget.XPlainButton'],
    config: {
		itemId:'headerbar',
    	docked: 'top',
		layout:'hbox',
		padding:'0 0 0 10',
		baseCls:'x-xheaderbar'
    },

    constructor: function(config) {
    	config = config || {};

        if (!config.items) {
             config.items = [];
        }

		console.log(config);

		var title = config['title'];

		var left = {xtype:'label', itemId:'back'};
		var middle = {xtype:'label', style:'text-align:center'};
		var right = {xtype:'panel', layout:'hbox', items:[{xtype:'spacer', flex:1}]};

		if(config['backButton'] == false){
			left.flex=1;
			middle.flex=1;
			right.flex=1;

			middle.cls = 'x-xmiddle x-xtitle';
			middle.html = title
		}else{
			left.cls = 'x-xleft icon-back x-xtitle';

			left.html = title;
			left.flex=1;

			right.hidden=true;
		}

		var items = config.items;
		if(items){
			Ext.Array.forEach(items,function(item){
				item.margin = '7 0 7 0';
				right.items.push(item);
			})
		}

		config.items = [],
		config.items.push(left, middle, right);
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
    }
});

