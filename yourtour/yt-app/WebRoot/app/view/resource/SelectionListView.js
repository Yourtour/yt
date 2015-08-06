Ext.define('YourTour.view.resource.SelectionListView', {
    extend: 'YourTour.view.widget.XPage',
    xtype: 'SelectionListView',
    requires:['YourTour.view.widget.XToolbar', 'Ext.field.Select','YourTour.view.widget.ToolButton','YourTour.view.resource.SelectionListItem'],
    
    config: {
    	itemId:'SelectionListView',
    	id:'SelectionListView',
    	layout:'vbox',
        items: [
            {
            	xtype: 'xtoolbar',
                itemId:'toolbar',
                title: '资源查询',
                items:[]
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

