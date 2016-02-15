Ext.define('YourTour.view.service.RouteServiceListDataItem', {
	extend: 'YourTour.view.widget.XDataItem',
    xtype: 'RouteServiceListDataItem',
    config: {
      	layout:'hbox',
		padding:10,
        items: [
			{
				itemId : 'image',
				xtype : 'ximage',
				margin:'0 5 0 0',
				imageCls:'img-small',
			},

			{
				xtype:'panel',
				layout:'vbox',
				flex:1,
				items:[
					{
						xtype:'xfield',
						itemId:'title',
						underline:false,
						fieldCls:'font-bold font-normal',
						padding:'0'
					} ,

					{
						xtype:'xmultifield',
						itemId:'memo',
						underline:false,
						padding:'0',
						ellipsis:{
							size:40,
							expandable:false
						}
					}
				]
			}
        ]
    },

	updateRecord:function(record){
		var me = this;

		if(record){
			var expertService = record.expertServiceStore.first();

			var image = me.down('#image');
			image.setSrc(expertService.get('imageUrl'));

			var title = me.down('#title');
			title.setText(expertService.get('title'));

			var memo = me.down('#memo');
			memo.setText(record.get('memo'));
		}
	}
});

