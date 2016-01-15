Ext.define('YourTour.view.widget.XToolbar', {
    extend: 'Ext.Toolbar',
    xtype: 'xtoolbar',
    config: {
    	items:[]
    },

	add:function(items){
		var newItems = [];
		for(var item in items){
			if(item > 0 && items[item-1].xtype != 'spacer'){
				newItems.push({xtype:'xvline'});
			}

			newItems.push(items[item]);
		}

		this.callParent([newItems]);
	}
});

