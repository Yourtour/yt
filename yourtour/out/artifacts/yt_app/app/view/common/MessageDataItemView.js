Ext.define('YourTour.view.common.MessageDataItemView', {
	extend: 'Ext.dataview.component.DataItem',
    requires:['Ext.Panel', 'YourTour.view.widget.XField','YourTour.view.widget.XPanel'],
    xtype: 'MessageDataItemView',
    config: {
      	layout:'hbox',
      	padding:'10',
      	cls:'x-xmessage',
        items: [
			{
				itemId:'imageLeft',
				xtype:'xuserlogo',
				margin:'0 5 0 0',
				cls:'x-xmedium'
			},

			{
				xtype:'panel',
				flex:1,
				itemId:'contentPanel',
				margin:'0 5',
				layout:'hbox',
				items:[
					{
						xtype:'spacer',
						itemId:'leftSpacer',
						flex:1,
						hidden:true
					},
					{
						itemId:'content',
						xtype:'xmultifield',
						cls:'x-xcontent',
						margin:'0 10',
						fieldCls:'font-normal',
						underline:false
					},{
						xtype:'spacer',
						itemId:'rightSpacer',
						flex:1,
						hidden:true
					}
				]
			},

			{
				itemId:'imageRight',
				xtype:'xuserlogo',
				margin:'0 0 0 5',
				cls:'x-xmedium'
			}
        ]
    },
    
    updateRecord: function(record){
    	var me = this;
       	if(record){
			var user = record.userStore.first();

			var contentPanel = me.down('#contentPanel'),
				content=me.down('#content'),
				left = me.down('#imageLeft'),
				right = me.down('#imageRight'),
				leftSpacer = me.down('#leftSpacer'),
				rightSpacer = me.down('#rightSpacer');

			if (user.get('id') == YourTour.util.Context.getUserId()) {  //本人
				right.setSrc(YourTour.util.Context.getImageResource(user.get('imageUrl')));

				contentPanel.addCls('x-xtriangle-right');
				content.addCls('x-xsend');
				leftSpacer.show();
			} else {
				left.setSrc(YourTour.util.Context.getImageResource(user.get('imageUrl')));

				contentPanel.addCls('x-xtriangle-left');
				content.addCls('x-xreceive');
				rightSpacer.show();
			}

			content.setText(record.get('content'));
	 	}
    }
});

