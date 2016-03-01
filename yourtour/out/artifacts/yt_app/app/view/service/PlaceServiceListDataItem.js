Ext.define('YourTour.view.service.PlaceServiceListDataItem', {
	extend: 'YourTour.view.widget.XDataItem',
    xtype: 'PlaceServiceListDataItem',
    config: {
      	layout:'hbox',
		padding:10,
        items: [
			{
				itemId : 'image',
				xtype : 'ximage',
				margin:'0 5 0 0',
				imageCls:'img-small'
			},

			{
				xtype:'panel',
				layout:'vbox',
				flex:1,
				items:[
					{
						xtype:'panel',
						layout:'hbox',
						items:[
							{
								xtype:'xfield',
								itemId:'title',
								underline:false,
								flex:1,
								fieldCls:'font-bold font-normal',
								padding:'0'
							},

							{
								xtype:'xscore',
								itemId:'score',
								star:true,
								margin:'0 0 0 10'
							}
						]
					},

					{
						xtype:'xfield',
						itemId:'fee',
						underline:false,
						padding:'0'
					},
				]
			}
        ]
    },

	updateRecord:function(record){
		var me = this;

		if(record){
			var image = me.down('#image');
			image.setSrc(record.get('imageUrl'));

			var title = me.down('#title');
			title.setText(record.get('title'));

			var fee = me.down('#fee'), value = record.get('fee');
			fee.setText(fee=='0'?'免费':'收费');

			var score = me.down('#score');
			score.setHtml(record.get('commentScore'))
		}
	}
});

