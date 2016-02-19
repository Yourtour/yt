Ext.define('YourTour.view.place.PlaceExpertItem', {
	extend: 'Ext.Container',
    xtype: 'PlaceExpertItem',
    requires:['YourTour.view.widget.grid.XGridView','YourTour.view.place.PlaceExpertGridItem'],
    config: {
        layout:'vbox',
      	items:[
            {
                xtype: 'xspacer'
            },
            {
                xtype: 'xlabel',
                itemId:'placeMoreExperts',
                cls: 'underline x-xlabel-normal font-medium',
                indicator:'nav-arrow',
                html: '游徒达人'
            },

            {
                xtype: 'xgridview',
                itemId:'placeExpertList',
                cols:2,
                height:140,
                size:4,
                useComponents: true,
                scrollable:'none',
                defaultType: 'PlaceExpertGridItem'
            }
        ]
    }
});

