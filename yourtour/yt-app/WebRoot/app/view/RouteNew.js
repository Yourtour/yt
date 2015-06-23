Ext.define('YourTour.view.RouteNew', {
    extend: 'Ext.form.Panel',
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
			    xtype: 'textfield',  
			    name : 'startDate',  
			    label: '日程',  
			    clearIcon: true,
			    disabled:true,
			    cls:'textfield'
			},
			{  
			    xtype: 'textfield',  
			    name : 'endDate',  
			    clearIcon: true,
			    disabled:true,
			    cls:'textfield'
			},
			{  
			    xtype: 'textfield',  
			    name : 'name',  
			    label: '目的地',  
			    clearIcon: true,
			    disabled:true,
			    cls:'textfield'
			}
        ]
    }
});

