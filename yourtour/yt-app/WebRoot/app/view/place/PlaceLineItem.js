Ext.define('YourTour.view.place.PlaceLineItem', {
	extend: 'Ext.Container',
    xtype: 'PlaceLineItem',
    requires:['YourTour.view.widget.grid.XGridView','YourTour.view.widget.grid.XDataView','YourTour.view.place.PlaceExpertGridItem'],
    config: {
        layout:'vbox',
      	items:[
            {
                xtype: 'xspacer'
            },
            {
                xtype: 'xlabel',
                itemId:'placeMorelines',
                cls: 'underline x-xlabel-normal',
                indicator:'nav-arrow',
                html: '旅行线路'
            },

            {
                xtype: 'xdataview',
                itemId: 'placeLineList',
                defaultType: 'RouteScheduleListDataItem'
            }
        ]
    }
});

