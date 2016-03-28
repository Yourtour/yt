Ext.define('YourTour.view.place.PlaceMainView', {
	extend: 'Ext.Container',
    xtype:'PlaceMainView',
	config: {
    	id:'PlaceMainView',
		layout: 'card',
        items: [
			{
				xtype:'xheaderbar2',
				items:[
					{
						xtype: 'xbutton',
						itemId: 'placeSelection',
						align: 'right',
						icon:'resources/icons/24/icon_places.png'
					}
				]
			},

			{
				xtype:'carousel',
				itemId:'places',
				indicator:false,
			}
        ]
    }
});

