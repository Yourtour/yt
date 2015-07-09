Ext.define('YourTour.view.resource.SelectionView', {
    extend: 'Ext.Panel',
    xtype: 'resourceselectionview',
    requires:['YourTour.view.widget.TitleBar', 'Ext.field.Select','YourTour.view.widget.ToolButton'],
    
    config: {
    	itemId:'resourceselectionview',
    	id:'resourceselectionview',
    	fullscreen: true,
    	layout:'vbox',
    	baseCls:'page',
        items: [
            {
            	xtype: '_titlebar',
                docked: 'top',
                title: '资源查询',
                items:[{
					xtype: "image", 
                	itemId:'close',
                	mode:'tag',
                	margin:'0 0 0 5',
                	src:'resources/icons/icon_back.png',
                	align:'left'
                }]
            },
            
            {
            	xtype: 'panel',
            	layout:'hbox',
            	defaults:{
            		flex:1
            	},
                items:[{
					xtype:'selectfield',                    
                    itemId:'placeselection',
                    displayField:"text",
                    valueField:"value"
                },{
					xtype:'selectfield',                    
                    itemId:'typeselection',
                    displayField:"text",
                    valueField:"value"
                },{
					xtype:'selectfield',                    
                    itemId:'orderselection',
                    displayField:"text",
                    valueField:"value"
                }]
            }
        ]
    }
});

