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
                        icon:'resources/icons/24/icon_header_ok.png',
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
                xtype:'panel',
                itemId:'infoPanel',
                layout:'vbox',
                items:[
                    {
                        xtype:'xspacer'
                    },

                    {
                        xtype:'xfield',
                        itemId:'startDate',
                        label:'起始日期',
                        fieldCls:'x-field-right'
                    },

                    {
                        xtype:'xfield',
                        itemId:'endDate',
                        label:'结束日期',
                        fieldCls:'x-field-right'
                    },

                    {
                        xtype:'xfield',
                        itemId:'duration',
                        label:'合计天数',
                        fieldCls:'x-field-right'
                    }
                ]
            }
        ]
    }
});

