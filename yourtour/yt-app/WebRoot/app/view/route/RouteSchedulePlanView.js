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
				cls:'scheduleList',
				flex:1,
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
							springTension: 0.9999
						},
						minVelocity: 5
					},
					outOfBoundRestrictFactor: 0
				},

				itemTpl:new Ext.XTemplate('<tpl if="this.isEnabled(type) == true">','<div class="underline" style="height:45px; padding:3px 0px 3px 10px"><div class="font-medium font-bold"  style="margin-top:2px">{title}</div><div class="font-small font-grey" style="margin-top:5px">{startTime}</div></div>','</tpl>',{
					isEnabled:function(type){
						return type == 'Provision' || type == 'Schedule';
					}
				})
			},

			{
				xtype:'panel',
				layout:'vbox',
				flex:3,
				items:[
					{
						xtype:'dataview',
						itemId:'scheduleItemList',
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
									springTension: 0.9999
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
						xtype: 'xtoolbar',
						docked: 'bottom',
						itemId: 'toolbar',
						items: [
							{xtype:'spacer', flex:1},
							{
								xtype: 'xbutton',
								itemId: 'btnAdd',
								text: '添加',
								iconAlign:'top',
								icon: 'resources/icons/icon_button_add.png'
							},
							{
								xtype: 'xbutton',
								itemId: 'btnDelete',
								text: '删除',
								hidden:true,
								margin:'0 20 0 0',
								iconAlign:'top',
								icon: 'resources/icons/icon_button_delete.png'
							},

							{
								xtype: 'xbutton',
								itemId: 'btnCancel',
								text: '取消',
								hidden:true,
								margin:'0 0 0 10',
								iconAlign:'top',
								icon: 'resources/icons/icon_button_cancel.png'
							},
							{xtype:'spacer', flex:1}
						]
					}
				]
			}
        ]
    },
});

