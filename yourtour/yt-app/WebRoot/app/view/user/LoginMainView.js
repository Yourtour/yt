Ext.define('YourTour.view.user.LoginMainView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.user.LoginView','YourTour.view.user.RegisterAccountView','YourTour.view.user.RegisterAuthView','YourTour.view.user.RegisterProfileView'],
    config: {
    	itemId:'LoginMainView',
    	id:'LoginMainView',
    	layout:'card',
        items: [
            {
            	xtype: 'LoginView',
                itemId:'LoginView'	
            },
            {
            	xtype: 'RegisterAuthView',
                itemId:'RegisterAuthView'
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

