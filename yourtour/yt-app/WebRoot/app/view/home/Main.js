Ext.define('YourTour.view.home.Main', {
    extend: 'Ext.Panel',
    xtype: 'homemain',
    requires:['Ext.Panel', 'Ext.TitleBar', 'Ext.Toolbar'],
    config: {
    	fullscreen: true,
    	
    	layout:'vbox',
        items: [
            {
            	xtype: 'titlebar',
                docked: 'top',
                title: '中国,上海',
            	items:[{
                	xtype: "button", 
                    ui: "normal", 
                	text:'切换',
                	align:'right'
                }]	
            },
            {
            	xtype:'toolbar',
            	layout: { align: 'stretch' },
            	defaults:{flex:1},
            	items:[
            	   {
					   text:'达人'
				   } ,    
				   {
            		   text:'线路'
            	   } ,
            	   {
            		   text:'景点'
            	   } ,
            	   {
            		   text:'住宿'
            	   }  
            	]
            }
        ]
    }
});

