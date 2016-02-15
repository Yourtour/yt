Ext.define('YourTour.view.common.MessageDataItemView', {
	extend: 'Ext.dataview.component.DataItem',
    requires:['Ext.Panel', 'YourTour.view.widget.XField','YourTour.view.widget.XPanel'],
    xtype: 'MessageDataItemView',
    config: {
      	layout:'hbox',
      	padding:'10 5 10 5',
      	cls:'message underline',
        items: [
			{
				itemId:'imageLeft',
				xtype:'image',
				margin:'0 5 0 0'
			},

			{
				itemId:'content',
				xtype:'xmultifield'
			},

			{
				itemId:'imageRight',
				xtype:'image',
				margin:'0 0 0 5'
			}
        ]
    },
    
    updateRecord: function(record){
    	var me = this;
       	if(record){
			var user = record.userStore.first();
			var left = me.down('#imageLeft'), content=me.down('#content'), right = me.down('#imageRight');
			if (user.get('id') == YourTour.util.Context.getUserId()) {
				left.hide();
				right.setSrc(YourTour.util.Context.getImageResource(user.get('imageUrl')));

				content.addCls('left')
			} else {
				right.hide();
				left.setSrc(YourTour.util.Context.getImageResource(user.get('imageUrl')));

				content.addCls('right')
			}

			content.setText(record.get('content'));
	 	}
    }
});

