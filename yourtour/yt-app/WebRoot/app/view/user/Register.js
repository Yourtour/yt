Ext.define('YourTour.view.user.Register', {
    extend: 'Ext.Panel',
    xtype: 'registerview',
    requires:['Ext.Panel', 'YourTour.view.widget.TitleBar', 'YourTour.view.user.RegisterAuthentication','Ext.field.Text', 'YourTour.view.user.RegisterAccount','YourTour.view.user.RegisterProfile'],
    config: {
    	itemId:'registerview',
    	id:'registerview',
    	fullscreen: true,
    	layout:'vbox',
        items: [
            {
            	xtype: '_titlebar',
                docked: 'top',
                title: '会员注册',
            	items:[{
                	xtype: "image", 
                	itemId:'back',
                	id:'back',
                	mode:'tag',
                	margin:'0 0 0 5',
                	src:'resources/icons/icon_back.png',
                	align:'left'
                }]	
            },
            
            {
            	xtype:'panel',
            	layout:'card',
            	id:'register',
            	style:'width:100%; height:100%',
            	items:[
            	   {
            		   xtype:'registerauth'
            	   },
            	   
            	   {
            		   xtype:'registeraccount'
            	   },
            	   {
            		   xtype:'registerprofile'
            	   } 
            	]
            }
        ]
    }
});

