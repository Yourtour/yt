Ext.define('YourTour.view.resource.SceneResourceDetailView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','Ext.Img', 'YourTour.view.widget.XToolbar','YourTour.view.widget.XSceneResource'],
    xtype: 'SceneResourceDetailView',
    config: {
    	itemId:'SceneResourceDetailView',
    	id:'SceneResourceDetailView',
    	layout:'vbox',
    	scrollable: {
    	    direction: 'vertical',
    	    indicators: false	
    	},
    	items:[
    		{    
				xtype: 'xtoolbar',
				title: '景点资源',
				itemId:'toolbar',
				items:[
					{
	                	xtype: "toolbutton", 
	                    ui: "normal", 
	                	text:'周边',
	                	itemId:'around',
	                	align:'right'
	                },
	                {
	                	xtype: "toolbutton", 
	                    ui: "normal", 
	                	text:'达人',
	                	itemId:'expert',
	                	align:'right'
	                },
	                {
	                	xtype: "toolbutton", 
	                    ui: "normal", 
	                	text:'线路',
	                	itemId:'line',
	                	align:'right'
	                },
	                {
	                	xtype: "toolbutton", 
	                    ui: "normal", 
	                	text:'游客',
	                	itemId:'travler',
	                	align:'right'
	                }
                ]
			},
			
    		{
	    		xtype : 'xsceneresource',
	    		itemId: 'sceneresource'
	    	}
        ]
    }
});

