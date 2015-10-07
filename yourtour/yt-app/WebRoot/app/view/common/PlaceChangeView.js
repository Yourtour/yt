Ext.define('YourTour.view.common.PlaceChangeView', {
	extend: 'YourTour.view.widget.XPage',
	requires:['Ext.Panel', 'Ext.NestedList','YourTour.view.widget.XHeaderBar'],
    xtype:'PlaceChangeView',
    config: {
    	id:'PlaceChangeView',
    	layout:'fit',
    	items:[
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'目的地'
			}, 
			
			{
				xtype:'panel',
				layout:'hbox',
				items:[
					{
						xtype:'nestedlist',
						itemId:'placeList',
					    useComponents: true,
					    defaultType: 'AlongListItemView',
						scrollable: {
						    direction: 'vertical',
						    indicators: false	
						}  	
					},
					
					{
						xtype:'dataview',
						itemId:'alongList',
					    useComponents: true,
					    defaultType: 'AlongListItemView',
						scrollable: {
						    direction: 'vertical',
						    indicators: false	
						}  	
					}
				]
			}
    	]
    }
});