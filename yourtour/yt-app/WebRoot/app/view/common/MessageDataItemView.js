Ext.define('YourTour.view.common.MessageDataItemView', {
	extend: 'Ext.dataview.component.DataItem',
    requires:['Ext.Panel', 'YourTour.view.widget.XField','YourTour.view.widget.XPanel'],
    xtype: 'MessageDataItemView',
    config: {
      	layout:'hbox',
      	padding:'10 5 10 5',
      	cls:'message',
        items: [
			{
				itemId:'imageLeft',
				xtype:'xuserlogo',
				margin:'0 10 0 0',
				cls:'x-xmedium'
			},

			{
				itemId:'content',
				xtype:'xmultifield',
				flex:1,
				underline:false
			},

			{
				itemId:'imageRight',
				xtype:'xuserlogo',
				margin:'0 0 0 10',
				cls:'x-xmedium'
			}
        ]
    },
    
    updateRecord: function(record){
    	var me = this;
       	if(record){
			console.log(record);
			var user = record.userStore.first();

			var left = me.down('#imageLeft'), content=me.down('#content'), right = me.down('#imageRight');
			if (user.get('id') == YourTour.util.Context.getUserId()) {
				right.setSrc(YourTour.util.Context.getImageResource(user.get('imageUrl')));

				content.addCls('left')
			} else {
				left.setSrc(YourTour.util.Context.getImageResource(user.get('imageUrl')));

				content.addCls('right')
			}

			content.setText(record.get('content'));
	 	}
    }
});

