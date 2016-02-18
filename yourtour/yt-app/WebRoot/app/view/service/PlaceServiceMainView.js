Ext.define('YourTour.view.service.PlaceServiceMainView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Label','Ext.Panel','YourTour.view.service.PlaceServiceListDataItem'],
    config: {
    	id:'PlaceServiceMainView',
		layout: 'card',
    	items:[
			{    
				xtype: 'xheaderbar',
				title:'目的地服务'
			},

			{
				xtype: 'xprocessing'
			},

			{
				xtype: 'xpagebody',
				layout: 'vbox',
				items: [
					{
						xtype:'panel',
						layout:'hbox',
						cls:'row underline',
						items:[
							{
								xtype:'selectfield',
								itemId:'placeSelect',
								usePicker:1,
								flex:1,
								inputCls:'text-align-center',
								options:[
								]
							},

							{
								xtype:'xvline'
							},

							{
								xtype:'selectfield',
								itemId:'serviceSelect',
								usePicker:1,
								flex:1,
								inputCls:'text-align-center',
								options:[
									{text:'行程规划', value:'M'},
									{text:'包车', value:'F'},
									{text:'接送', value:'F'},
									{text:'代购', value:'F'},
									{text:'代收', value:'F'},
									{text:'预约', value:'F'}
								]
							}
						]
					},
					{
						itemId: 'PlaceServiceList',
						xtype: 'xdataview',
						flex: 1,
						defaultType: 'PlaceServiceListDataItem'
					}
				]
			}
    	]
    }
});

