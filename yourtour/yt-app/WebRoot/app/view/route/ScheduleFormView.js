Ext.define('YourTour.view.route.ScheduleFormView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','YourTour.view.widget.XLabel','YourTour.view.route.ActivityItemDataItem','YourTour.view.widget.XVerticalLine','YourTour.view.common.ServiceDataItem','YourTour.view.widget.XHeaderBar'],
    config: {
    	id:'ScheduleFormView',
    	layout:'vbox',
		scrollable:{
			direction: 'vertical',
			indicators: false
		},
    	items:[
    		{    
				xtype: 'xheaderbar',
				itemId:'headerbar'
			},
			
			{
				itemId : 'image',
				xtype : 'image',
				mode : 'tag'
			},

			{
				xtype:'panel',
				layout:'hbox',
				cls:'row underline icon_name',
				padding:'0 0 0 10',
				items:[
					{
						xtype: 'xlabel',
						itemId:'resName',
						tappable:true,
						cls:'font-medium font-grey nav_arrow',
						flex:1,
						margin:'0 5 0 30'
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 0 0 10',
				cls:'row underline icon_position',
				items:[
					{
						xtype: 'xlabel',
						itemId:'address',
						cls:'font-medium font-grey nav_arrow',
						flex:1,
						tappable:true,
						margin:'0 5 0 30'
					}
				]
			},
					
			{
				xtype:'panel',
				layout:'hbox',
				cls:'row underline icon_phone',
				padding:'0 0 0 10',
				items:[
					{
						xtype: 'label',
						itemId:'phone',
						cls:'font-medium font-grey nav_arrow',
						flex:1,
						margin:'0 5 0 30'
					}
				]
			},

			{
				xtype:'panel',
				cls:'spacer'
			},
					
			{
				xtype:'panel',
				layout:'hbox',
				cls:'row underline icon_time',
				padding:'0 0 0 10',
				items:[
					{
						xtype: 'label',
						itemId:'time',
						cls:'font-medium font-grey',
						margin:'0 5 0 30'
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				items:[
					{
						xtype:'label',
						html: '描述',
						cls:'row font-medium font-grey',
						padding:'0 10 0 5'
					},
					{
						xtype: 'label',
						itemId:'memo',
						flex:1,
						cls:'font-medium font-grey multilineinfo',
						margin:'9 5 9 10'
					}
				]
			},

			{
				xtype:'panel',
				cls:'spacer'
			},

			{
				xtype:'panel',
				layout:'vbox',
				items:[
					{
						xtype:'label',
						html: '安排',
						cls:'row underline font-medium font-grey',
						padding:'0 0 0 5'
					},
					{
						xtype: 'dataview',
						itemId:'items',
						scrollable:null,
						useComponents: true,
						defaultType: 'ActivityItemDataItem',
					}
				]
			},

			{
				xtype:'panel',
				cls:'spacer'
			},

			{
				xtype:'panel',
				layout:'vbox',
				items:[
					{
						xtype:'label',
						html: '服务',
						cls:'row underline font-medium font-grey',
						padding:'0 0 0 5'
					},
					{
						xtype: 'dataview',
						itemId:'services',
						scrollable:null,
						useComponents: true,
						defaultType: 'ServiceDataItem',
					}
				]
			},

            {
                xtype: 'toolbar',
                docked: 'bottom',
				cls:'toolbar',
                items: [
	                {
	                    xtype: 'spacer',
	                    flex:1
	                },

					{
						xtype: 'button',
						text: '留言',
						ui: 'normal',
						icon:'resources/icons/icon_message.png',
						itemId: 'btnMessage'
					},
					{
						xtype:'xvline'
					},
					{
	                    xtype: 'button',
	                    text: '签到',
	                    ui: 'normal',
	                    icon:'resources/icons/icon_checkin.png',
	                    itemId: 'btnCheckin'
	                },{
						xtype:'xvline'
					},{
	                    xtype: 'button',
	                    text: '随记',
	                    ui: 'normal',
						icon:'resources/icons/icon_note.png',
	                    itemId: 'btnNotes'
	                }
	            ]
            }
        ]
    }
});

