Ext.define('YourTour.view.route.RouteScheduleReferenceListView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','Ext.Toolbar'],
    config: {
    	id:'RouteScheduleReferenceListView',
    	layout:'vbox',
    	items:[
	    	{
    			itemId:'routeActivitiesList',
    			xtype:'ActivityDataView',
    			readonly:false,
    			scrollable:null,
		        useComponents: true,
		        defaultType: 'ActivityDataListItem'
    		}
        ]
    }
});

