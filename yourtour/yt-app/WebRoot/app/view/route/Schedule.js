Ext.define('YourTour.view.route.Schedule', {
    extend: 'Ext.form.Panel',
    requires:['Ext.field.DatePicker','Ext.field.Select', 'Ext.field.Text'],
    xtype: 'routenew',
    config: {
    	itemId:'routenew',
    	id:'routenew',
    	fullscreen: true,
    	layout:'vbox',
    	scrollable:'none',
    	
    	defaults:{
    		labelWidth: '20'
		},
        items: [
            {    
				xtype: 'titlebar',
				docked: 'top',
				title: '行程设置',
				items:[{
					itemId:'close',
					id:'close',
					xtype: "button", 
				    ui: "normal", 
					text:'关闭',
					iconCls:'delete',
					iconAlign: 'left',
					align:'left'
				},
				{
					itemId:'next',
					id:'next',
					xtype: "button", 
				    ui: "normal", 
					text:'下一步',
					align:'right'
				}]			
			},
			
			{  
			    xtype: 'hiddenfield',  
			    itemId:'step',
			    name : 'step',
			    value:'1'
			},
			
			{  
			    xtype: 'textfield',  
			    itemId:'name',
			    name : 'name',  
			    label: '名称',  
			    clearIcon: true,
			    cls:'textfield'
			},
			{  
			    xtype: 'datepickerfield',  
			    name : 'startDate',  
			    label: '出发日期',  
			    value:new Date(),
			    dateFormat:'Y/m/d',
			    clearIcon: true,
			    cls:'textfield nav_arrow'
			},
			{  
			    xtype: 'datepickerfield',  
			    name : 'endDate',
			    label: '返回日期', 
			    value:new Date(),
			    dateFormat:'Y/m/d',
			    clearIcon: true,
			    cls:'textfield nav_arrow'
			},
			{  
			    xtype: 'textfield',  
			    name : 'place', 
			    itemId:'place',
			    id:'place',
			    label: '目的地',
			    readOnly: true,
			    value:'wwww',
			    clearIcon: true,
			    cls:'textfield nav_arrow'
			}
        ]
    }
});

