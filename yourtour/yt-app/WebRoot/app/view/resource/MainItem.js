Ext.define('YourTour.view.route.MainItem', {
    extend: 'Ext.Panel',
    xtype: 'routemainitem',
    requires:['Ext.Label', 'Ext.Img','YourTour.view.widget.MarkedLabel', 'Ext.Toolbar'],
    config: {
    	itemId:'routemainitem',
    	fullscreen: true,
    	layout:'vbox',
    	scrollable: {
    	    direction: 'vertical',
    	    directionLock: true
    	},
        items: [
			{
			   xtype:'panel', 
			   itemId:'imageUrl'
			},
			{
			   xtype:'markedlabel',
			   pack:'center',
			   align:'center',
			   itemId:'routeName',
			   html : ''
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
            		   html:'日期',
            		   cls:'subtitle',
            		   margin:'0 0 0 5'
            	   },
            	   {
            		   xtype:'label',
            		   itemId:'time',
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
            		   html:'目的地',
            		   cls:'subtitle',
            		   margin:'0 0 0 5'
            	   },
            	   {
            		   xtype:'label',
            		   itemId:'places',
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
            		   html:'线路',
            		   cls:'subtitle',
            		   margin:'0 0 0 5'
            	   },
            	   {
            		   xtype:'label',
            		   itemId:'lineName',
            		   margin:'0 0 0 5'
            	   } 
            	]
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

