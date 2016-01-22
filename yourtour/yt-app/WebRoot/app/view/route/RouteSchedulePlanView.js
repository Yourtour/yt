Ext.define('YourTour.view.route.RouteSchedulePlanView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel', 'YourTour.view.route.RouteSchedulePlanDataItem', 'YourTour.view.widget.XHeaderBar','YourTour.view.widget.XButton'],
    config: {
    	id:'RouteSchedulePlanView',
    	layout:'hbox',
    	items:[
    		{    
				xtype: 'xheaderbar',
				items:[
						{
		                	xtype: "xbutton",
		                    ui: "normal",
							icon:'resources/icons/icon_header_discuss.png',
		                	itemId:'discuss',
		                	align:'right'
		                },
				        {
				    	   xtype: "xbutton",
				    	   ui: "normal",
							icon:'resources/icons/icon_header_set.png',
				    	   itemId:'setting',
				    	   align:'right'
				        }
				]
			},

			{
				xtype:'dataview',
				itemId:'scheduleList',
				flex:2,
				style:'background-color:silver',
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

				itemTpl:new Ext.XTemplate('<tpl if="this.isEnabled(type) == true">','<div class="font-medium underline" style="height:45px; padding:3px 0px 3px 10px"><div class="font-bold">{title}</div><div class="font-grey">{startTime}</div></div>','</tpl>',{
					isEnabled:function(type){
						return type == 'Provision' || type == 'Schedule';
					}
				})
			},

			{
				xtype:'dataview',
				itemId:'ScheduleItemList',
				flex:3,
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
				itemTpl:new Ext.XTemplate('<tpl if="this.isEnabled(type, hidden) == true">','<div class="row font-grey font-medium underline" style="padding-left:10px">{title}</div>','</tpl>',{
					isEnabled:function(type, hidden){
						if(type == 'Provision' || type == 'Schedule'){
							return false;
						}else{
							return ! hidden;
						}
					}
				})
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
    },
});

