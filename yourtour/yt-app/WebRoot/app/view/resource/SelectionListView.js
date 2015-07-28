Ext.define('YourTour.view.resource.SelectionListView', {
    extend: 'Ext.Panel',
    xtype: 'SelectionListView',
    requires:['YourTour.view.widget.XToolbar', 'Ext.field.Select','YourTour.view.widget.ToolButton','YourTour.view.resource.SelectionListItem'],
    
    config: {
    	itemId:'SelectionListView',
    	id:'SelectionListView',
    	fullscreen: true,
    	layout:'vbox',
    	baseCls:'page',
        items: [
            {
            	xtype: 'xtoolbar',
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
            	cls:'textfield',
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
            },
            
            {
    			itemId:'selectionList',
    			xtype:'dataview',
    			flex:1,
    			cls:'bg',
		        useComponents: true,
		        defaultType: 'SelectionListItem'
    		}
        ]
    }
});

