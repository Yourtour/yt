Ext.define('YourTour.view.route.RouteActivityItemDataItem', {
    extend: 'YourTour.view.widget.XDataItem',
    xtype: 'RouteActivityItemDataItem',
    config: {
		layout:'hbox',
    	items:[
			{
				itemId : 'image',
				xtype : 'image',
				mode : 'tag',
				margin:'0 5 0 0',
				imageCls:'img-small'
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
						padding:'0'
					}
				]
			}
		]
    },
    
   	/**
   	 * 
   	 * @param {} record
   	 */
    updateRecord: function(record) {
       var me = this;
       if(record){
		   var image = me.down('#image');
		   image.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "'>");

		   var title = me.down('#title');
    	   title.setText(record.get('title'));

		   var memo = me.down('#memo');
		   memo.setText(Ext.String.ellipsis(record.get('memo'),30,false));
       }
    }   
});

