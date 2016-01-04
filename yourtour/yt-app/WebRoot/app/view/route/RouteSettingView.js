Ext.define('YourTour.view.route.RouteSettingView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.form.Panel', 'YourTour.view.widget.HSpacer', 'YourTour.view.widget.XKVField','YourTour.view.widget.ToolButton', 'Ext.field.DatePicker','YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.XLabel', 'YourTour.view.widget.XField', 'Ext.field.Text'],
    xtype: 'RouteSettingView',
    config: {
    	id:'RouteSettingView',
    	layout:'vbox',
    	defaults:{
    		labelWidth: '20'
		},
        items: [
            {    
				xtype: 'xheaderbar',
				title: '行程设置',
				items:[
					{
					   xtype:'toolbutton',
					   itemId:'next',
					   text:'下一步',
					   align:'right'
					}
				]
			},
			
			{  
			    xtype: 'hiddenfield',  
			    itemId:'id',
			    value:'0'
			},

			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 0 0 10',
				cls:'row underline',
				items:[
					{
						xtype:'xlabel',
						html: '名称',
						style:'width:50px'
					},
					{  
					    xtype: 'textfield',  
					    itemId:'name',
					    flex:1,
					    clearIcon: true
					}
				]
			},

			{
				xtype:'hspacer'
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
					    cls:'nav_arrow'
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
						cls:'nav_arrow'
					}
				]
			},

			{
				xtype:'hspacer'
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
						xtype: 'xkvfield',
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
						xtype: 'xkvfield',
						itemId:'toPlaces',
						style:'text-align:right',
						flex:1
					}
				]
			},

			{
				xtype:'hspacer'
			},

			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 0 0 10',
				cls:'row underline',
				items:[
					{
						xtype:'xlabel',
						html: '成人人数',
						style:'width:60px'
					},
					{
						xtype: 'textfield',
						itemId:'adultNum',
						flex:1,
						clearIcon: true,
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
						html: '儿童人数',
						style:'width:60px'
					},
					{
						xtype: 'textfield',
						itemId:'childNum',
						flex:1,
						clearIcon: true,
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
						html: '老人人数',
						style:'width:60px'
					},
					{
						xtype: 'textfield',
						itemId:'olderNum',
						flex:1,
						clearIcon: true,
					}
				]
			},
		]
    }
});

