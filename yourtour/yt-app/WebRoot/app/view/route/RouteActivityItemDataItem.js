Ext.define('YourTour.view.route.RouteActivityItemDataItem', {
    extend: 'Ext.dataview.component.DataItem',
    xtype: 'RouteActivityItemDataItem',
    requires:['Ext.Label','Ext.field.Select','Ext.Panel'],
    config: {
		layout:'hbox',
		padding:'10 5 10 5',
		cls:'underline',
    	items:[
			{
				itemId : 'image',
				xtype : 'image',
				mode : 'tag',
				margin:'0 10 0 0'
			},

			{
				xtype:'panel',
				layout:'vbox',
				flex:1,
				items:[
					{
						xtype:'label',
						itemId:'title',
						cls:'font-medium font-grey',
					} ,

					{
						xtype:'label',
						itemId:'memo',
						cls:'font-medium font-grey multilineinfo'
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
		   image.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "' style='height:75px'>");

		   var title = me.down('#title');
    	   title.setHtml(record.get('title'));

		   var memo = me.down('#memo');
		   memo.setHtml(Ext.String.ellipsis(record.get('memo'),30,false));
       }
    }   
});

