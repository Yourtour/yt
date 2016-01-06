Ext.define('YourTour.view.resource.ResourceDetailView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','Ext.Img',  'YourTour.view.widget.XHeaderBar','YourTour.view.widget.XToolbar','YourTour.view.resource.ResourceSceneView'],
    config: {
    	id:'ResourceDetailView',
    	layout:'vbox',
    	scrollable: {
    	    direction: 'vertical',
    	    indicators: false	
    	},
    	items:[
    		{    
    			xtype: 'xheaderbar',
    			itemId:'headerbar'
    		},		
			
    		{
	    		xtype : 'panel',
	    		flex:1,
	    		itemId: 'detailview'
	    	},
	    	

        ]
    }
});

