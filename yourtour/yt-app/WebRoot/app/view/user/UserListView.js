Ext.define('YourTour.view.user.UserListView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','Ext.Img','Ext.DataView', 'YourTour.view.widget.XPanel', 'YourTour.view.widget.XToolbar','YourTour.view.user.UserListItem'],
    xtype: 'UserListView',
    config: {
    	itemId:'UserListView',
    	id:'UserListView',
      	layout:'vbox',
        items: [
        	{    
				xtype: 'xtoolbar',
				title:'达人驴友',
				itemId:'toolbar',
				items:[
				]			
			},
			
		    {
		    	xtype:'dataview',
		    	itemId:'userList',
				flex:1,
		    	useComponents:true,
		    	defaultType:'UserListItem'
		    }
        ]
    }
});

