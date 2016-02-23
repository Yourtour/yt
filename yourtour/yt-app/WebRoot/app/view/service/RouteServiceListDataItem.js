Ext.define('YourTour.view.service.RouteServiceListDataItem', {
	extend: 'YourTour.view.widget.XDataItem',
    xtype: 'RouteServiceListDataItem',
	requires:['YourTour.view.widget.XUserLogo'],
    config: {
      	layout:'vbox',
		cls:'x-xspacer',
        items: [
			{
				xtype: 'xfield',
				itemId:'title',
				labelCls:'',
				padding:'10 20 10 10',
				indicator:'nav-arrow',
				dataChange:function(field, record){
					var expertService = record.expertServiceStore.first();
					field.setText('<span style="width:30%">' + expertService.get('title') + '</span><span style="position:absolute;right:100px;">' + record.get('fromDateStr') + '~' + record.get('endDateStr') + '</span><span  style="position:absolute; right:20px">' + '未完成' + '</span>');
				}
			},

			{
				xtype: 'xmultifield',
				itemId: 'memo',
				underline: false,
				padding: '10',
				binding:'expertService.memo',
				ellipsis: {
					size: 60,
					expandable: false
				}
			},

			{
				xtype: 'panel',
				layout: 'hbox',
				padding: '0 10 10 10',
				items: [
					{
						xtype: 'xuserlogo',
						margin: '0 10 0 0',
						binding:'expertService.user.imageUrl',
						cls: 'x-xmedium'
					},

					{
						xtype:'xfield',
						underline:false,
						padding: '0 10 10 0',
						dataChange:function(field, record){
							var expertService = record.expertServiceStore.first(), user = expertService.userStore.first();

							field.setText(user.get('nickName'));
						}
					}
				]
			}
        ]
    }
});

