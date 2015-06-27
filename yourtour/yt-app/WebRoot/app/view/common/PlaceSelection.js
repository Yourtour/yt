Ext.define('YourTour.view.common.PlaceSelection', {
    extend: 'Ext.Panel',
    xtype: 'placeselection',
    config: {
    	itemId:'placeselection',
    	id:'placeselection',
    	fullscreen: true,
    	layout:'vbox',
    	
        items: [
            {    
				xtype: 'titlebar',
				docked: 'top',
				title: '目的地',
				items:[{
					itemId:'close',
					id:'close',
					xtype: "button", 
				    ui: "normal", 
					text:'返回',
					iconCls:'back_arrow',
					iconAlign: 'left',
					align:'left'
				}]			
			}
        ]
    }
});

