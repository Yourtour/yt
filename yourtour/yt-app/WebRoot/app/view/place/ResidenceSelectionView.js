Ext.define('YourTour.view.place.ResidenceSelectionView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar','YourTour.view.place.ResidenceSelectionListDataItem'],
    config: {
        id:'ResidenceSelectionView',
        items: [
            {
                xtype: 'xheaderbar'
            },

            {
                xtype: 'xpagebody',
                layout: 'vbox',
                items: [
                    {
                        xtype: 'xdataview',
                        itemId: 'placeList',
                        flex: 1,
                        defaultType: 'ResidenceSelectionListDataItem'
                    }
                ]
            }
        ]
    }
});

