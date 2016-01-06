Ext.define('YourTour.view.route.ActivityItemDataItem', {
    extend: 'Ext.dataview.component.DataItem',
    xtype: 'ActivityItemDataItem',
    requires:['Ext.Label','Ext.field.Select','Ext.Panel'],
    config: {
    	cls:'underline',
		layout:'hbox',
		padding:'5 5 5 5',
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
				fit:1,
				items:[
					{
						xtype:'label',
						itemId:'title',
						cls:'font-medium font-grey',
					} ,

					{
						xtype:'label',
						cls:'font-medium font-grey multilineinfo',
						flex:1,
						itemId:'memo',
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
		   memo.setHtml(Ext.String.ellipsis(record.get('memo'),40,false));
       }
    }   
});

