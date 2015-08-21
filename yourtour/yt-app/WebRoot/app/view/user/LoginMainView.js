Ext.define('YourTour.view.user.LoginMainView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.user.LoginView','YourTour.view.user.RegisterAccountView','YourTour.view.user.RegisterAuthView','YourTour.view.user.RegisterProfileView'],
    config: {
    	itemId:'LoginMainView',
    	id:'LoginMainView',
    	layout:'card',
        items: [
            {
            	xtype: 'xloginview',
                itemId:'LoginView'	
            },
            {
            	xtype: 'xregisterauth',
                itemId:'RegisterAuthView'
            },
            {
            	xtype: 'xregisteraccount',
                itemId:'RegisterAccountView'	
            },
            {
            	xtype: 'xregisterprofile',
                itemId:'RegisterProfileView'	
            }            
        ]
    }
});

