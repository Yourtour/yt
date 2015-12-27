Ext.define('YourTour.view.expert.ExpertServiceDataItem', {
    extend: 'Ext.dataview.component.DataItem',
    xtype: 'ExpertServiceDataItem',
    requires:['Ext.Label','Ext.Panel'],
    config: {
		layout:'vbox',
		defaults:{
			padding:'0 5 0 5'
		},
    	items:[
			{
				xtype:'label',
				itemId:'title',
				cls:'row underline font-medium font-bold'
			},

			{
				xtype:'label',
				itemId:'memo',
				cls:'multilineinfo font-medium font-grey'
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
			var titleEl = me.down('#title');
			titleEl.setHtml(record.get('title'));

			var memoEl = me.down('#memo');
			memoEl.setHtml(record.get('memo'));
		}
    }
});

