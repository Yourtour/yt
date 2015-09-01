Ext.define('YourTour.view.home.AlongItemView', {
    extend: 'Ext.Panel',
    requires:['Ext.Panel', 'YourTour.view.widget.XField','YourTour.view.widget.XPanel'],
    xtype: 'AlongItemView',
    config: {
    	model:null,
      	layout:'vbox',
        items: [
		   	{
   				itemId : 'imageUrl',
				xtype : 'image',
				mode : 'tag'
    		},
    		
    		{
    			xtype:'panel',
    			layout:'hbox',
    			items:[
	    			{
	    				itemId : 'memberImageUrl',
						xtype : 'image',
						mode : 'tag'
	    			},
	    			{
	    				xtype:'panel',
		    			layout:'vbox',
		    			items:[
			    			{
			    				itemId:'title',
			    				xtype:'xfield'
			    			},
			    			{
			    				itemId:'intention',
			    				xtype:'xfield'
			    			},
			    			{
			    				itemId:'deadline',
			    				xtype:'xfield'
			    			},
			    			{
			    				itemId:'alongNum',
			    				xtype:'xfield'
			    			}
		    			]
	    			}
    			]
    		}
        ]
    },
    
    applyModel:function(model){
    	var me = this;
       	
    	console.log(model);
    	
       	if(model){
       	   var imageUrlEl = me.down('#imageUrl');
	 	   imageUrlEl.setHtml("<img src='" + model.get('imageUrl') + "' style='width:100%; max-height:100px'>");
	 	   
	 	   var memberImageUrlEl = me.down('#memberImageUrl');
	 	   memberImageUrlEl.setHtml("<img src='" + model.get('memberImageUrl') + "' style='width:100%; max-height:150px'>");
	 	   
	 	   var titleEl = me.down('#title');
	 	   titleEl.setHtml(model.get('title'));
	 	   
	 	   var intentionEl = me.down('#intention');
	 	   intentionEl.setHtml(model.get('intention'));
	 	   
	 	   var deadlineEl = me.down('#deadline');
	 	   deadlineEl.setHtml(model.get('deadline'));
	 	   
	 	   var alongNumEl = me.down('#alongNum');
	 	   alongNumEl.setHtml(model.get('alongNum'));
	 	}
    }
});

