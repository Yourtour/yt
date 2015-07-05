Ext.define('YourTour.view.line.RecommedListItem', {
    extend: 'Ext.dataview.component.DataItem',
    xtype: 'lineListItem',
    requires:['YourTour.view.widget.SubTitleBar','Ext.Panel', 'Ext.Img'],
    config: {
    	layout : 'vbox',
    	cls:'lineListItem',
    	margin:'10 0 0 0',
    	style:'background:#fff',
    	items:[
    	   {
    	   		itemId : 'imageUrl',
	    		xtype : 'image',
	    		mode : 'tag'
	    	},
    		
	    	{
			   xtype:'markedlabel',
			   pack:'center',
			   align:'center',
			   itemId:'name',
			   html : ''
		    },
		    
		    {
			   xtype:'panel',
			   layout:'vbox',
			   items:[
			   		{
			   			xtype:'panel',
			   			layout:'hbox',
			   			padding:'5',
			   			defaults:{
			   				flex:1
			   			},
			   			items:[
				   			{
				   				xtype:'label',
				   				html:'推荐指数:',
				   				style:'font-weight:bold; font-size:14px'
				   			},
				   			
				   			{
				   				xtype:'label',
				   				html:'推荐指数:',
				   				style:'font-size:14px'
				   			},
				   			
				   			{
				   				xtype:'label',
				   				html:'旅游指数:',
				   				style:'font-weight:bold; font-size:14px'
				   			},
				   			{
				   				xtype:'label',
				   				html:'推荐指数:',
				   				style:'font-size:14px'
				   			}
			   			]
			   		},
			   		{
			   			xtype:'panel',
			   			layout:'hbox',
			   			padding:'5',
			   			defaults:{
			   				flex:1
			   			},
			   			items:[
				   			{
				   				xtype:'label',
				   				html:'最佳时间:',
				   				style:'font-weight:bold; font-size:14px'
				   			},
				   			{
				   				xtype:'label',
				   				html:'最佳时间:',
				   				style:'font-size:14px'
				   			},
				   			{
				   				xtype:'label',
				   				style:'font-weight:bold; font-size:14px'
				   			},
				   			{
				   				xtype:'label',
				   				style:'font-size:14px'
				   			}
			   			]
			   		}
			   ]
		    }
    	]
    },
    
    updateRecord: function(record) {
       var me = this;
       
       if(record){
       	   var imageUrlEl = me.down('#imageUrl');
	 	   imageUrlEl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:150px'>");
	 	   
	 	   var nameEl = me.down('#name');
	 	   nameEl.setHtml(record.get('name'));
	 	}
    }   
});

