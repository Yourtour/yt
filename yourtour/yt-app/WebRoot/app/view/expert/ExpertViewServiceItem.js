Ext.define('YourTour.view.expert.ExpertViewServiceItem', {
    extend: 'Ext.Container',
    xtype:'ExpertViewServiceItem',
    requires:['YourTour.view.expert.ExpertServiceDataItem'],
    config: {
        layout: 'vbox',
        title:'达人服务',
        items: [
            {
                xtype: 'xdataview',
                itemId: 'serviceList',
                binding:'services',
                defaultType: 'ExpertServiceDataItem'
            }
        ]
    }
});

