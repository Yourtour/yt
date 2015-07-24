Ext.define('YourTour.view.route.MainItem', {
    extend: 'Ext.Panel',
    xtype: 'routemainitem',
    requires:['Ext.Label', 'Ext.Img','YourTour.view.widget.MarkedLabel', 'Ext.Toolbar'],
    config: {
    	itemId:'routemainitem',
    	layout:'vbox',
        items: [
			{
    	   		itemId : 'imageUrl',
	    		xtype : 'image',
	    		mode : 'tag',
	    		height:150
	    	},
			{
			   xtype:'markedlabel',
			   pack:'center',
			   align:'center',
			   itemId:'routeName',
			   html : ''
		    },
		    
		    {
			   xtype:'toolbar',
			   itemId:'toolbuttons',
			   padding:5,
			   layout:'hbox',
			   ui:'light',
			   defaults:{
			   		flex:1,
			   		pack:'center',
			   		align:'center'
			   },
			   items:[
			   		{
		              	xtype : 'image',
	    				mode : 'tag',
	    				src:'resources/icons/icon_route.png',
		              	itemId:'btnRoute'
		           	},
		           	{
		              	xtype : 'image',
	    				mode : 'tag',
	    				src:'resources/icons/icon_partner.png',
		              	itemId:'btnMember'  
		            },
		           	{
		              	xtype : 'image',
	    				mode : 'tag',
	    				src:'resources/icons/icon_expense.png',
		              	itemId:'btnCharge'  
		            },
		           	{
		              	xtype : 'image',
	    				mode : 'tag',
	    				src:'resources/icons/icon_diary.png',
		              	itemId:'btnDiary'  
		           	}     
			   ]
		    },
			
			{
            	xtype:'panel',
            	layout:'hbox',
            	padding:5,
            	cls:'textfield',
            	items:[
            	   {
            		   xtype:'image',
            		   src:'resources/icons/icon_mobile.png',
            		   mode : 'tag'
            	   },
            	   
            	   {
            		   xtype:'label',
            		   itemId:'time',
            		   flex:1,
            		   style:'line-height:40px',
            		   margin:'0 0 0 5'
            	   } 
            	]
            },
            
            {
            	xtype:'panel',
            	layout:'hbox',
            	cls:'textfield',
            	padding:'5',
            	items:[
            	   {
            		   xtype:'image',
            		   src:'resources/icons/icon_mobile.png',
            		   mode : 'tag'
            	   },
            	   {
            		   xtype:'label',
            		   itemId:'places',
            		   flex:1,
            		   style:'line-height:40px',
            		   margin:'0 0 0 5'
            	   } 
            	]
            },
			{
            	xtype:'panel',
            	layout:'hbox',
            	cls:'textfield',
            	padding:'5',
            	items:[
            	   {
            		   xtype:'image',
            		   src:'resources/icons/icon_mobile.png',
            		   mode : 'tag'
            	   },
            	   
            	   {
            		   xtype:'label',
            		   itemId:'lineName',
            		   flex:1,
            		   style:'line-height:40px',
            		   margin:'0 0 0 5'
            	   } 
            	]
            },
            {
            	xtype:'panel',
            	style:'background:#fff',
            	flex:1
           	}
        ]
    },
    
    setRecord: function(record) {
        var me = this;
        
        if(record){
 	 	   var imageUrl = me.down('#imageUrl');
 	 	   imageUrl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:150px'>");
 	 	   
 	 	   var name = me.down('#routeName');
 	 	   name.setHtml(record.get('name'));
 	 	   
 	 	   var time = me.down('#time');
 	 	   time.setHtml('2015/12/01~2015/12/10');
	 	   
	 	   var places = me.down('#places');
	 	   places.setHtml('四川成都');
	 	   
 	 	   var line = me.down('#lineName');
 	 	   line.setHtml(record.get('lineName'));
 	 	}
     }   
});

