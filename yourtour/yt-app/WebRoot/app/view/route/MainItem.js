Ext.define('YourTour.view.route.MainItem', {
    extend: 'Ext.Panel',
    xtype: 'routemainitem',
    requires:['Ext.Label', 'Ext.Img','Ext.Toolbar', 'YourTour.view.widget.MarkedLabel', 'YourTour.view.widget.XLabel', 'YourTour.view.widget.XField'],
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
		            }   
			   ]
		    },
			
		    {
		    	xtype:'panel',
		    	padding:'10 10 10 10',
		    	items:[
				    {
		    		   	xtype:'xlabel',
		    		   	cls:'font-bold font-large',
						html:'旅行线路:'
		    		},
		            
					{
		    		   xtype:'xfield',
		    		   itemId:'lineName',
		    		   cls:'multilineinfo',
		    		   margin:'5 0 0 0'
		            },
		            
		            {
		    		   	xtype:'xlabel',
		    		   	cls:'font-bold font-large',
						html:'旅行时间:',
						margin:'10 0 0 0'
		    		},
		            
					{
		    		   xtype:'xfield',
		    		   itemId:'time',
		    		   margin:'5 0 0 0'
		            },
		            
		            {
		    		   	xtype:'xlabel',
		    		   	cls:'font-bold font-large',
						html:'旅行印象:',
						margin:'10 0 0 0'
		    		},
		            
					{
		    		   xtype:'xfield',
		    		   itemId:'impression',
		    		   cls:'multilineinfo',
		    		   margin:'5 0 0 0',
		    		   minHeight:20,
		    		   maxHeight:40
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
 	 	   time.setHtml(record.get('startTime') +'-' + record.get('endTime') + '(' + record.get('period')+')');
 	 	   
 	 	   var lineName = me.down('#lineName');
 	 	   lineName.setHtml(record.get('lineName'));
 	 	   
 	 	   var impression = me.down('#impression');
 	 	   impression.setHtml(Ext.String.ellipsis(record.get('impression'),70,false));
 	 	}
     }   
});

