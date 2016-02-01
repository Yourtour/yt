Ext.define('YourTour.view.place.PlaceExpertGridItem', {
	extend: 'YourTour.view.widget.grid.component.DataItem',
    xtype: 'PlaceExpertGridItem',
    config: {
        layout:'hbox',
        items: [
			{
				xtype:'ximage',
                itemId:'imageUrl'
			},

            {
                xtype:'panel',
                layout:'vbox',
                items:[
                    {
                        xtype:'xfield',
                        itemId:'nickName'
                    }
                ]
            }
        ]
    }
});

