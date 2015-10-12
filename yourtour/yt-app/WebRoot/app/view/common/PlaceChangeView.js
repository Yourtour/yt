Ext.define('YourTour.view.common.PlaceChangeView', {
	extend: 'YourTour.view.widget.XPage',
	requires:['Ext.Panel', 'Ext.DataView','YourTour.view.widget.XHeaderBar','YourTour.view.common.PlaceTypeListItemView', 'YourTour.view.common.PlaceListItemView'],
    xtype:'PlaceChangeView',
    config: {
    	id:'PlaceChangeView',
    	layout:'fit',
    	items:[
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'目的地',
				items:[
					
				]
			}, 
			
			{
				xtype:'panel',
				layout:'hbox',
				items:[
					{
						xtype:'dataview',
						itemId:'placeType',
						style:'background:silver',
						useComponents: true,
				        defaultType: 'PlaceTypeListItemView',
			        	scrollable:false,
					    flex:2
					},
					
					{
						xtype:'panel',
						itemId:'placeList',
					    flex:7,
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