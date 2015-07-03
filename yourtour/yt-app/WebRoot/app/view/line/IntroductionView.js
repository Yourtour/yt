Ext.define('YourTour.view.line.IntroductionView', {
    extend: 'Ext.Panel',
    requires:['Ext.Toolbar', 'Ext.Panel','Ext.Carousel','YourTour.view.widget.TitleBar'],
    xtype: 'lineintroview',
    config: {
    	id:'lineintroview',
    	fullscreen: true,
    	layout:'vbox',
    	baseCls:'page',
    	
        items: [
        	{    
				xtype: '_titlebar',
				docked: 'top',
				title: '线路介绍',
				items:[{
					xtype: "image", 
                	itemId:'close',
                	mode:'tag',
                	margin:'0 0 0 5',
                	src:'resources/icons/icon_back.png',
                	align:'left'
				}]			
			},
			
			{
    	   		itemId : 'imageUrl',
	    		xtype : 'image',
	    		mode : 'tag',
	        	src : ''
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
			   		
			   ]
		    },
		    
		    {
		    	xtype:'dataview',
		    	itemId:'scenes'
		    }
        ]
    }
});

