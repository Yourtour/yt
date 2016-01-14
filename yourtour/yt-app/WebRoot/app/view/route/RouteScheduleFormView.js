Ext.define('YourTour.view.route.RouteScheduleFormView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','YourTour.view.widget.XLabel','YourTour.view.widget.XMultiLabel', 'YourTour.view.route.RouteActivityItemDataItem','YourTour.view.widget.XVerticalLine','YourTour.view.common.ExpertServiceListDataItem','YourTour.view.widget.XHeaderBar'],
    config: {
    	id:'RouteScheduleFormView',
    	layout:'vbox',
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
				cls:'row underline icon-name',
				padding:'0 0 0 10',
				items:[
					{
						xtype: 'xlabel',
						itemId:'resName',
						tappable:true,
						cls:'font-medium font-grey nav-arrow',
						flex:1,
						margin:'0 5 0 30'
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 0 0 10',
				cls:'row underline icon-position',
				items:[
					{
						xtype: 'xlabel',
						itemId:'address',
						cls:'font-medium font-grey nav-arrow',
						flex:1,
						tappable:true,
						margin:'0 5 0 30'
					}
				]
			},
					
			{
				xtype:'panel',
				layout:'hbox',
				cls:'row underline icon-phone',
				padding:'0 0 0 10',
				items:[
					{
						xtype: 'label',
						itemId:'phone',
						cls:'font-medium font-grey nav-arrow',
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
				cls:'row underline icon-time',
				padding:'0 0 0 10',
				items:[
					{
						xtype: 'label',
						itemId:'time',
						cls:'font-medium font-grey',
						margin:'0 5 0 30',
						flex:1
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				cls:'underline icon-memo',
				padding:'0 0 0 10',
				items:[
					{
						xtype: 'xmultilabel',
						itemId:'memo',
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
						defaultType: 'RouteActivityItemDataItem',
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
						defaultType: 'ExpertServiceListDataItem',
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

