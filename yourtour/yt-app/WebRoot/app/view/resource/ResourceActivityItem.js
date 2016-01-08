Ext.define('YourTour.view.resource.ResourceActivityItem', {
    extend: 'Ext.Panel',
    xtype: 'ResourceActivityItem',
    requires:['Ext.Label', 'Ext.Img','Ext.Toolbar', 'YourTour.view.widget.MarkedLabel', 'YourTour.view.widget.XLabel', 'YourTour.view.widget.XField'],
    config: {
    	record:null,
    	layout:'vbox',
		style:'background-color:grey',
        items: [
			{
				xtype:'spacer',
				flex:1
			},
			{
				margin:'5 5 10 5',
    	   		itemId : 'image',
	    		xtype : 'image',
	    		mode : 'tag'
	    	},{
				xtype:'panel',
				docked:'bottom',
				bottom:0,
				layout:'vbox',
				padding:'5 5 10 5',
				style: 'background-color:grey;opacity:0.8; width:100%; text-align:center',
				items:[
					{
						xtype:'label',
						itemId:'title',
						cls:'row font-grey font-medium'
					},

					{
						xtype:'label',
						itemId:'memo',
						cls:'multilineinfo font-grey font-medium'
					}
				]
			},
			{
				xtype:'spacer',
				flex:1
			}
        ]
    },
    
    updateRecord: function(record) {
        var me = this;
        me.record = record;
        if(record){
 	 	   	var image = me.down('#image');
			image.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "' style='width:100%'>");

			var title = me.down('#title')
			title.setHtml(record.get('title'));

			var memo = me.down('#memo');
			memo.setHtml(record.get('memo'));
 	 	}
     }   
});

