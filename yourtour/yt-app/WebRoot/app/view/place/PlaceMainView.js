Ext.define('YourTour.view.place.PlaceMainView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Carousel', 'YourTour.view.place.PlaceOverviewView','YourTour.view.place.PlaceSceneView','YourTour.view.place.PlaceFoodView','YourTour.view.place.PlaceExpertView'],
    xtype: 'PlaceMainView',
    config: {
    	id:'PlaceMainView',
      	layout:'vbox',
        items: [
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'上海'
			},    
        	{
            	xtype: 'carousel',
            	itemId:'placeCarousel',
            	flex:1,
            	cls:'space-bottom',
            	items:[
            	       {
            	    	   xtype:'PlaceOverviewView'
            	       },
            	       {
            	    	   xtype:'PlaceSceneView'
            	       },
            	       {
            	    	   xtype:'PlaceFoodView'
            	       },
            	       {
            	    	   xtype:'PlaceExpertView'
            	       }
            	]
            }
        ]
    }
});

