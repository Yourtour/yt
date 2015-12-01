Ext.define('YourTour.view.resource.ResourceDetailView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','Ext.Img',  'YourTour.view.widget.XHeaderBar','YourTour.view.widget.XToolbar','YourTour.view.resource.SceneResourceView'],
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
				title:'行程安排',
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
	    		xtype : 'panel',
	    		flex:1,
	    		itemId: 'detailview'
	    	},
	    	
	    	{
            	xtype:'xbutton',
            	id:'btnAdd',
            	docked:'bottom',
            	text:'加入日程'
            },
        ]
    }
});

