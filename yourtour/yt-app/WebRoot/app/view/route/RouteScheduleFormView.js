Ext.define('YourTour.view.route.RouteScheduleFormView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','YourTour.view.widget.XLabel','YourTour.view.widget.XImage', 'YourTour.view.widget.XDataView','YourTour.view.widget.XVerticalLine', 'YourTour.view.route.RouteActivityItemDataItem','YourTour.view.common.ExpertServiceListDataItem','YourTour.view.widget.XHeaderBar'],
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
				xtype : 'ximage',
				imageCls:'img-medium',
				binding:'resource.imageUrl'
			},

			{
				xtype:'panel',
				cls:'spacer'
			},
			{
				xtype: 'xfield',
				itemId:'resName',
				tappable:true,
				icon:'icon-name',
				binding:'resource.name'
			},

			{
				xtype: 'xfield',
				itemId:'address',
				tappable:true,
				icon:'icon-position',
				binding:'resource.address'
			},

			{
				xtype: 'xfield',
				itemId:'phone',
				tappable:true,
				icon:'icon-phone',
				binding:'resource.phone'
			},

			{
				xtype:'panel',
				cls:'spacer'
			},

			{
				xtype: 'xfield',
				itemId:'time',
				icon:'icon-time',
				dataChange:function(field, record){
					field.setText(record.get('startTime') +'-' + record.get('startTime'));
				}
			},

			{
				xtype: 'xmultifield',
				itemId:'memo',
				icon:'icon-memo',
				ellipsis:{
					size:100,
					expandable:true
				}
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
				xtype: 'xdataview',
				itemId:'items',
				scrollable:null,
				useComponents: true,
				defaultType: 'RouteActivityItemDataItem',
				binding:'itemsStore'
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
				xtype: 'xdataview',
				itemId:'services',
				scrollable:null,
				useComponents: true,
				defaultType: 'ExpertServiceListDataItem',
				binding:'servicesStore'
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

