Ext.define('YourTour.view.route.RouteSettingView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.form.Panel', 'YourTour.view.widget.XSpacer', 'YourTour.view.widget.ToolButton', 'Ext.field.DatePicker','YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.XLabel', 'YourTour.view.widget.XField', 'Ext.field.Text'],
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
				xtype:'xtextfield',
				itemId:'name',
				label:'行程名称',
			},

			{
				xtype:'xspacer'
			},

			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 0 0 10',
				cls:'row underline',
				items:[
					{
						xtype:'xlabel',
						html: '出发日期',
						style:'width:60px'
					},
					{  
					    xtype: 'datepickerfield',  
					    itemId:'startDate',
					    flex:1,
					    value:new Date(),
					    dateFormat:'Y/m/d',
					    clearIcon: true,
					    cls:'nav-arrow'
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 0 0 10',
				cls:'row underline',
				items:[
					{
						xtype:'xlabel',
						html: '返程日期',
						style:'width:60px'
					},
					{
						xtype: 'datepickerfield',
						itemId:'endDate',
						flex:1,
						value:new Date(),
						dateFormat:'Y/m/d',
						clearIcon: true,
						cls:'nav-arrow'
					}
				]
			},

			{
				xtype:'xspacer'
			},

			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 10 0 10',
				cls:'row underline',
				items:[
					{
						xtype:'xlabel',
						html: '出发地',
						style:'width:50px'
					},
					{
						xtype: 'xfield',
						itemId:'fromPlace',
						style:'text-align:right',
						flex:1
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 10 0 10',
				cls:'row underline',
				items:[
					{
						xtype:'xlabel',
						html: '目的地',
						style:'width:50px'
					},
					{
						xtype: 'xfield',
						itemId:'toPlaces',
						style:'text-align:right',
						flex:1
					}
				]
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
				cls: 'toolbar',
				items: [
					{
						xtype:'spacer',
						flex:1
					},
					{
						xtype: 'button',
						text: '智能匹配',
						ui: 'normal',
						iconCls: 'action',
						itemId: 'btnRefer',
						flex:2
					},{
						xtype:'xvline'
					} ,
					{
						xtype: 'button',
						text: '行程定制',
						ui: 'normal',
						iconCls: 'favorites',
						itemId: 'btnCutomized',
						flex:2
					},
					{
						xtype:'spacer',
						flex:1
					},
				]
			}
		]
    }
});

