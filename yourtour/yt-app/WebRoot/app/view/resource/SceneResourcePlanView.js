Ext.define('YourTour.view.resource.SceneResourcePlanView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','Ext.Img', 'YourTour.view.widget.XToolbar','YourTour.view.widget.XSceneResource'],
    xtype: 'SceneResourcePlanView',
    config: {
    	itemId:'SceneResourcePlanView',
    	id:'SceneResourcePlanView',
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
	                	text:'评论',
	                	itemId:'comment',
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

