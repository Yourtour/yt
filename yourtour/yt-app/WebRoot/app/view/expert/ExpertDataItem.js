Ext.define('YourTour.view.expert.ExpertDataItem', {
    extend: 'Ext.dataview.component.DataItem',
    xtype: 'ExpertDataItem',
    requires:['Ext.Label','Ext.Panel'],
    config: {
		layout:'vbox',
    	items:[
			{
				xtype : 'image',
				mode : 'tag',
				itemId:'image'
			},

			{
				xtype:'label',
				itemId:'name',
				padding:'0 5 0 5',
				cls:'row underline font-medium font-grey'
			},

			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 5 0 5',
				cls:'row underline',
				items:[
					{
						xtype:'label',
						itemId:'date',
						cls:'font-medium font-grey'
					}
				]
			},

			{
				xtype:'panel',
				cls:'spacer'
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
			var imageEl = me.down('#image');
			imageEl.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "' style='width:100%'>");

			var nameEl = me.down('#name');
			nameEl.setHtml(record.get('name'));

			var dateEl = me.down('#date');
			dateEl.setHtml(record.get('startDate') + ' - ' + record.get('endDate'));
		}
    }
});

