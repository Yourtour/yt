Ext.define('YourTour.view.place.PlaceMainItem', {
	extend: 'Ext.Container',
	xtype:'PlaceMainItem',
    config: {
		layout:'vbox',
		padding:'0 0 30 0',
        items: [
			{
				xtype:'img',
				itemId:'image',
				minHeight:200,
				width:'100%',
				mode:'tag'
			},

			{
				xtype:'xmultifield',
				itemId:'memo',
				underline:false,
				ellipsis:{
					size:200,
					expandable:false
				}
			}
        ]
    },

	updateData:function(data){
		if(data) {
			var me = this,
				image = me.down('#image'),
				memo = me.down('#memo');

			var url = YourTour.util.Context.getImageResource(data.get('imageUrl'));
			image.setSrc(url);

			memo.setText(data.get('memo'));
		}
	}
});

