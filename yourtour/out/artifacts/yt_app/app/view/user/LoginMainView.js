Ext.define('YourTour.view.user.LoginMainView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.user.LoginView','YourTour.view.user.RegisterAccountView','YourTour.view.user.RegisterProfileView'],
    config: {
    	id:'LoginMainView',
    	layout:'card',
        items: [
            {
            	xtype: 'LoginView',
                itemId:'LoginView'	
            },
            {
            	xtype: 'RegisterAccountView',
                itemId:'RegisterAccountView'	
            },
            {
            	xtype: 'RegisterProfileView',
                itemId:'RegisterProfileView'	
            }            
        ]
    }
});

