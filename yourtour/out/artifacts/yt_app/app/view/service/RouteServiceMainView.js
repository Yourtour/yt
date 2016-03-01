Ext.define('YourTour.view.service.RouteServiceMainView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Label','Ext.Panel','YourTour.view.service.RouteServiceListDataItem'],
    config: {
    	id:'RouteServiceMainView',
		layout: 'card',

    	items:[
			{    
				xtype: 'xheaderbar',
				title:'行程服务',
				items: [
					{
						xtype: 'xbutton',
						itemId: 'btnAdd',
						align: 'right',
						icon:'resources/icons/24/icon_header_add.png'
					}
				]
			},

			{
				xtype: 'xprocessing'
			},

			{
				xtype: 'xpagebody',
				layout: 'vbox',
				items: [
					{
						itemId: 'RouteServiceList',
						xtype: 'xdataview',
						flex: 1,
						defaultType: 'RouteServiceListDataItem'
					}
				]
			}
    	]
    }
});

