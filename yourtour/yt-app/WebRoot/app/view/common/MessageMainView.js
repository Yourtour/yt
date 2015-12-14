Ext.define('YourTour.view.common.MessageMainView', {
	extend: 'YourTour.view.common.MessageView',
	requires:['Ext.Panel'],
    config: {
    	id:'MessageMainView',
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