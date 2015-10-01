Ext.define('YourTour.view.personal.PersonalMainView', {
	extend: 'Ext.NavigationView',
    xtype: 'PersonalMainView',
    requires:['Ext.Img', 'Ext.Panel', 'YourTour.view.widget.XToolbar'],
    config: {
    	id:'PersonalMainView',
    	fullscreen: true,
    	
    	layout:'card',
      	navigationBar: {
    		backButton: {
    			iconCls: 'arrow_left',
    			docked: "left"
    		},
    		
    		items:[

            ]
    	},
    	items: [
            {
            	xtype:'panel',
            	title:'个人',
            	items:[
            	  
            	]
            }
        ]
    }
});

