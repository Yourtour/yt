Ext.define('YourTour.view.route.RouteSchedulePlanView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel', 'YourTour.view.route.RouteScheduleDataView', 'YourTour.view.route.RouteScheduleDataListItem', 'YourTour.view.widget.XHeaderBar'],
    xtype: 'RouteSchedulePlanView',
    config: {
    	id:'RouteSchedulePlanView',
    	layout:'vbox',
		scrollable: {
    	    direction: 'vertical',
    	    indicators: false	
    	},    	
    	items:[
    		{    
				xtype: 'xheaderbar',
				title:'行程安排'
			},
			
			{
    			itemId:'RouteScheduleList',
    			xtype:'RouteScheduleDataView',
    			readonly:false,
    			scrollable:null,
    			
		    	/**
		         * Tell the dataview to use components for each item
		         */
		        useComponents: true,
		        
		        /**
		         * Set the default item for this component list to be the {@link Example.view.KittensListItem}
		         * class.
		         */
		        defaultType: 'RouteScheduleDataListItem'
    		},
    		
    		{
 			   xtype:'toolbar',
 			   itemId:'toolbar',
 			   docked: 'bottom',
 			   hidden:true,
 			   defaults:{
 				   flex:1
 			   },
 			   items:[
 			   		{itemId:'newProvision', text: '添加准备事项' , hidden:true}, 
 			   		
 			   		{itemId:'insertProvisiion', text: '插入准备事项' , hidden:true},
 			   		
 			   		{itemId:'insertShcedule', text: '插入行程', hidden:true },
 			   		{itemId:'newActivity', text: '添加日程安排', hidden:true },

 			   		{itemId:'insertActivity', text: '插入日程安排', hidden:true },
 			   ]
 		    }
        ]
    }
});

