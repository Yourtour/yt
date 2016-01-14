Ext.define('YourTour.view.route.RouteSchedulePlanView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel', 'YourTour.view.route.RouteSchedulePlanDataItem', 'YourTour.view.widget.XHeaderBar','YourTour.view.widget.XButton'],
    xtype: 'RouteSchedulePlanView',
    config: {
    	id:'RouteSchedulePlanView',
    	layout:'vbox',
    	items:[
    		{    
				xtype: 'xheaderbar',
				title:'行程安排',
				items:[
						{
		                	xtype: "toolbutton", 
		                    ui: "normal", 
		                	text:'讨论',
		                	itemId:'discuss',
		                	align:'right'
		                },
				        {
				    	   xtype: "toolbutton", 
				    	   ui: "normal", 
				    	   text:'设置',
				    	   itemId:'setting',
				    	   align:'right'
				        }
				]
			},
			
			{
    			itemId:'RouteScheduleList',
    			xtype:'dataview',
				flex:1,
				scrollable: {
					direction: 'vertical',
					indicators: false,
					directionLock: true,
					momentumEasing:  {
						momentum: {
							acceleration: 10,
							friction: 0.5
						},
						bounce: {
							acceleration: 0.0001,
							springTension: 0.9999,
						},
						minVelocity: 5
					},
					outOfBoundRestrictFactor: 0
				},
		        useComponents: true,
		        defaultType: 'RouteSchedulePlanDataItem'
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
 			   		{xtype: 'xbutton', baseCls:Ext.baseCSSPrefix + 'button', ui: 'drastic',itemId:'newProvision', text: '添加准备事项' , hidden:true, attr:'Provision'}, 
 			   		{xtype: 'xbutton', baseCls:Ext.baseCSSPrefix + 'button', ui:'drastic', itemId:'insertShcedule', text: '插入日程', hidden:true, attr:'Schedule' },
 			   		{xtype: 'xbutton', baseCls:Ext.baseCSSPrefix + 'button', ui:'drastic', itemId:'newActivity', text: '日程安排', hidden:true, attr:'Schedule' },
 			   ]
 		    }
        ]
    }
});

