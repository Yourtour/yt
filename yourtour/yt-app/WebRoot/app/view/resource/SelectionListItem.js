Ext.define('YourTour.view.resource.SelectionListItem', {
    extend: 'Ext.dataview.component.DataItem',
    xtype: 'SelectionListItem',
    requires:['Ext.Label', 'Ext.Img','YourTour.view.widget.TapLabel'],
    config: {
    	itemId:'SelectionListItem',
    	layout:'vbox',
    	cls:'listitem space-bottom',
    	style:'background:#fff',
    	padding:5,
        items: [
        	{
        		xtype:'panel',
        		layout:'hbox',
        		cls:'horizentalLine',
        		items:[
        			{
		   				xtype:'image',
		   				itemId:'imageUrl',
		    			mode:'tag'
		   			},
		   			
		   			{
		   				xtype:'panel',
		   				layout:'vbox',
		   				flex:1,
		   				margin:'0 0 0 10',
		   				items:[
		   					{
								xtype: "label", 
								itemId:'name',
								cls:'title nav_arrow'
							},
							{
								xtype: "label", 
								itemId:'desc',
								cls:'info'
							}
		   				]
		   			}
        		]
        	},
        	{
        		xtype:'panel',
        		layout:'hbox',
        		cls:'info',
        		items:[
        			{
						xtype: "label", 
						html:'推荐指数'
					},
					{
						xtype:'image',
	   					itemId:'recommendIndex',
	    				mode:'tag',
	    				flex:1,
	    				margin:'0 5 0 0'
					},
        			
					{
						xtype: "label", 
						html:'评价指数'
					},
					{
						xtype:'image',
	   					itemId:'assessmentIndex',
	    				mode:'tag',
	    				flex:1,
	    				margin:'0 5 0 0'
					}
        		]
        	}
        ]
    },
    
    updateRecord: function(record) {
        var me = this;
        
        if(record){
 	 	   var imageUrl = me.down('#imageUrl');
 	 	   imageUrl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:150px'>");
 	 	   
 	 	   var name = me.down('#name');
 	 	   name.setHtml(record.get('name'));
 	 	   
 	 	   var time = me.down('#desc');
 	 	   time.setHtml(record.get('desc'));
	 	   
 	 	   var recommendIndex = me.down('#recommendIndex');
 	 	   recommendIndex.setHtml("<img src='" + record.get('recommendIndex') + "' style='height:15px'>");
 	 	   
 	 	   var assessmentIndex = me.down('#assessmentIndex');
 	 	   assessmentIndex.setHtml("<img src='" + record.get('assessmentIndex') + "' style='height:15px'>");
 	 	}
     }   
});

