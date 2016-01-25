Ext.define('YourTour.view.common.TimeSelectionView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar','YourTour.view.widget.XCalendar','YourTour.view.widget.XField','YourTour.view.widget.XSpacer'],
    config: {
        id:'TimeSelectionView',
        items: [
            {
                xtype: 'xheaderbar',
                title:'行程日期安排',
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
            },

            {
                xtype:'xspacer'
            },

            {
                xtype:'panel',
                layout:'vbox',
                items:[
                    {
                        xtype:'xfield',
                        itemId:'startDate',
                        label:'出发日期',
                        fieldCls:'x-field-right'
                    },

                    {
                        xtype:'xfield',
                        itemId:'endDate',
                        label:'返程日期',
                        fieldCls:'x-field-right'
                    },

                    {
                        xtype:'xfield',
                        itemId:'duration',
                        label:'行程天数',
                        fieldCls:'x-field-right'
                    }
                ]
            }
        ]
    }
});

