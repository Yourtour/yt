Ext.define('YourTour.view.home.AlongItemView', {
    extend: 'Ext.Panel',
    requires:['Ext.Panel', 'YourTour.view.widget.XField','YourTour.view.widget.XPanel'],
    xtype: 'AlongItemView',
    config: {
    	model:null,
    	padding:5,
      	layout:'vbox',
        items: [
		   	{
   				itemId : 'imageUrl',
				xtype : 'image',
				mode : 'tag'
    		},
    		
    		{
				itemId:'title',
				xtype:'xlabel',
				margin:'5 0 0 0 '
			},
			{
				itemId:'intention',
				xtype:'xfield',
				margin:'5 0 0 0 '
			},
			{
				itemId:'deadline',
				xtype:'xfield',
				margin:'5 0 0 0 '
			},
			{
				itemId:'alongNum',
				xtype:'xfield',
				margin:'5 0 0 0 '
			}
        ]
    },
    
    applyModel:function(model){
    	var me = this;
       	if(model){
       	   var imageUrlEl = me.down('#imageUrl');
	 	   imageUrlEl.setHtml("<img src='" + model.get('imageUrl') + "' style='width:100%; max-height:100px'>");
	 	   
	 	   var titleEl = me.down('#title');
	 	   titleEl.setHtml(model.get('title'));
	 	   
	 	   var intentionEl = me.down('#intention');
	 	   intentionEl.setHtml('结伴目的：' + model.get('intention'));
	 	   
	 	   var deadlineEl = me.down('#deadline');
	 	   deadlineEl.setHtml('截止时间：' + model.get('deadline'));
	 	   
	 	   var alongNumEl = me.down('#alongNum');
	 	   alongNumEl.setHtml('结伴人数：' + model.get('alongNum'));
	 	}
    }
});

