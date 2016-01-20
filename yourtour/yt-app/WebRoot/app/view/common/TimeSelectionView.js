Ext.define('YourTour.view.common.TimeSelectionView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar','YourTour.view.widget.XCalendar'],
    config: {
        id:'TimeSelectionView',
        padding:10,
        items: [
            {
                xtype: 'xheaderbar',
                title:'想什么时候去'
            },

            {
                xtype: 'xcalendar',
                itemId: 'calendar',
                flex:1
            },

            {
                xtype: 'spacer',
                height:25
            }
        ]
    }
});

