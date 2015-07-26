Ext.define('YourTour.view.route.NewView', {
    extend: 'Ext.form.Panel',
    requires:['Ext.field.DatePicker','Ext.field.Select', 'YourTour.view.widget.SubTitleBar', 'YourTour.view.widget.XToolbar', 'YourTour.view.widget.ToolButton', 'YourTour.view.widget.XLabel', 'YourTour.view.widget.XField', 'Ext.field.Text'],
    xtype: 'newrouteview',
    config: {
    	itemId:'newrouteview',
    	id:'newrouteview',
    	fullscreen: true,
    	layout:'vbox',
    	scrollable:'none',
    	baseCls:'page',
    	defaults:{
    		labelWidth: '20'
		},
        items: [
            {    
				xtype: 'xtoolbar',
				title: '行程规划',
				items:[{
					xtype: "toolbutton", 
	                    ui: "normal", 
	                	text:'新建',
	                	itemId:'new',
	                	align:'right'
				},
				{
					xtype: "toolbutton", 
	                    ui: "normal", 
	                	text:'新建1',
	                	itemId:'new1',
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
				xtype:'panel',
				layout:'hbox',
				margin:'5 0 0 0',
				padding:'0 0 0 10',
				cls:'row',
				items:[
					{
						xtype:'label',
						html: '名称',
						style:'width:50px'
					},
					{  
					    xtype: 'textfield',  
					    itemId:'name',
					    name : 'name',
					    flex:1,
					    clearIcon: true
					}
				]
			},
			
			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 0 0 10',
				cls:'textfield',
				items:[
					{
						xtype:'label',
						html: '出发地',
						style:'width:50px'
					},
					{  
					    xtype: 'textfield',  
					    name : 'place', 
					    itemId:'place',
					    flex:1,
					    readOnly: true,
					    clearIcon: true,
					    cls:'nav_arrow'
					}
				]
			},
			
			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 0 0 10',
				cls:'textfield',
				items:[
					{
						xtype:'label',
						html: '出发日期',
						style:'width:50px'
					},
					{  
					    xtype: 'datepickerfield',  
					    name : 'startDate',
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
				cls:'textfield',
				items:[
					{
						xtype:'label',
						html: '返回日期',
						style:'width:50px'
					},
					{  
					    xtype: 'datepickerfield',  
					    name : 'endDate',
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
            	xtype:'subtitlebar',
            	margin:'5 0 5 0',
            	html:'行程规划',
            	buttons:[
            		{
            			xtype: "image", 
		            	itemId:'add',
		            	mode:'tag',
		            	margin:'5 0 0 0',
		            	src:'resources/icons/icon_plus.png'
            		}
            	]
            },
            {
            	xtype:'ytButton',
            	margin:'10 0 0 0',
            	itemId:'next',
            	text:'下一步'
            }
        ]
    }
});

