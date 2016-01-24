Ext.define('YourTour.view.common.TimeSelectionView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar','YourTour.view.widget.XCalendar'],
    config: {
        id:'TimeSelectionView',
        padding:10,
        items: [
            {
                xtype: 'xheaderbar',
                title:'想什么时候去',
                items:[
                    {
                        xtype: "xbutton",
                        itemId:'btnNext',
                        icon:'resources/icons/icon_header_next.png',
                        align:'right'
                    }
                ]
            },

            {
                xtype: 'xcalendar',
                itemId: 'calendar',
                flex:1
            }
        ]
    }
});

