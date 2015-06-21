Ext.define('YourTour.view.RouteNew', {
    extend: 'Ext.form.Panel',
    xtype: 'routenew',
    config: {
    	itemId:'routenew',
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
				}]			
			},
			
			{  
			    xtype: 'textfield',  
			    name : 'name',  
			    label: '名称',  
			    clearIcon: true,
			    cls:'textfield'
			},
			{  
			    xtype: 'textfield',  
			    name : 'name',  
			    label: '日程',  
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

