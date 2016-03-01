Ext.define('YourTour.view.route.RouteSettingView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.form.Panel', 'YourTour.view.widget.XSpacer', 'YourTour.view.widget.XDateField', 'YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.XLabel', 'YourTour.view.widget.XField', 'Ext.field.Text'],
    config: {
    	id:'RouteSettingView',
    	defaults:{
    		labelWidth: '20'
		},
        items: [
            {    
				xtype: 'xheaderbar',
				title: '行程设置'
			},
			
			{  
			    xtype: 'hiddenfield',  
			    itemId:'id',
			    value:'0'
			},

			{
				xtype:'xfield',
				itemId:'name',
				editable:true,
				label:'行程名称',
				align:'right',
				placeHolder:'请输入行程名称'
			},
			{
				xtype:'xfield',
				itemId:'budget',
				label:'行程预算',
				editable:true,
				align:'right',
				placeHolder:'请输入行程预算'
			},

			{
				xtype:'xspacer'
			},

			{
				xtype:'xdatefield',
				itemId:'startDate',
				label:'出发日期',
				align:'right'
			},

			{
				xtype:'xdatefield',
				itemId:'endDate',
				label:'返程日期',
				align:'right'
			},

			{
				xtype:'xfield',
				itemId:'duration',
				fieldCls:'x-field-right',
				label:'日程天数'
			},

			{
				xtype:'xspacer'
			},

			{
				xtype:'xfield',
				itemId:'fromPlace',
				fieldCls:'x-field-right',
				label:'出发地'
			},

			{
				xtype:'xfield',
				itemId:'toPlaces',
				fieldCls:'x-field-right',
				label:'目的地'
			},

			{
				xtype:'xspacer'
			},

			{
				xtype:'xtextfield',
				itemId:'adultNum',
				label:'成人人数'
			},

			{
				xtype:'xtextfield',
				itemId:'childNum',
				label:'儿童人数'
			},

			{
				xtype:'xtextfield',
				itemId:'olderNum',
				label:'老人人数'
			},

			{
				xtype: 'xtoolbar',
				docked: 'bottom',
				itemId: 'toolbar',
				items: [
					{
						xtype:'spacer',
						flex:1
					},
					{
						xtype: 'xbutton',
						itemId: 'btnRefer',
						text: '达人推荐',
						icon:'resources/icons/24/icon_favorite.png'
					},{
						xtype:'xvline',
						flex:1
					} ,
					{
						xtype: 'xbutton',
						itemId: 'btnResevation',
						text: '预约定制',
						icon:'resources/icons/24/icon_booking.png'
					},{
						xtype:'xvline',
						flex:1
					} ,
					{
						xtype: 'xbutton',
						itemId: 'btnCutomized',
						text: '行程定制',
						icon:'resources/icons/24/icon_customize.png'
					},
					{
						xtype:'spacer',
						flex:1
					}
				]
			}
		]
    }
});

