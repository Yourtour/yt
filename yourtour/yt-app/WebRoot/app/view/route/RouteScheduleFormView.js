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
				xtype: 'xfield',
				itemId:'resName',
				tappable:true,
				icon:'icon-name',
			},

			{
				xtype: 'xfield',
				itemId:'address',
				tappable:true,
				icon:'icon-position',
			},

			{
				xtype: 'xfield',
				itemId:'phone',
				tappable:true,
				icon:'icon-phone',
			},

			{
				xtype:'panel',
				cls:'spacer'
			},

			{
				xtype: 'xfield',
				itemId:'time',
				icon:'icon-time',
			},

			{
				xtype: 'xmultifield',
				itemId:'memo',
				size:140,
				icon:'icon-memo'
			},

			{
				xtype:'panel',
				cls:'spacer'
			},

			{
				xtype:'label',
				html: '安排',
				cls:'row underline font-medium font-grey',
				padding:'0 0 0 10'
			},
			{
				xtype: 'dataview',
				itemId:'items',
				scrollable:null,
				useComponents: true,
				defaultType: 'RouteActivityItemDataItem',
			},

			{
				xtype:'panel',
				cls:'spacer'
			},

			{
				xtype:'label',
				html: '服务',
				cls:'row underline font-medium font-grey',
				padding:'0 0 0 10'
			},
			{
				xtype: 'dataview',
				itemId:'services',
				scrollable:null,
				useComponents: true,
				defaultType: 'ExpertServiceListDataItem',
			},


            {
                xtype: 'xtoolbar',
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
	                    xtype: 'button',
	                    text: '签到',
	                    ui: 'normal',
	                    icon:'resources/icons/icon_checkin.png',
	                    itemId: 'btnCheckin'
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

