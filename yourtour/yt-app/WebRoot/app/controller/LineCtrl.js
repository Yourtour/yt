Ext.define('YourTour.controller.LineCtrl', {
    extend: 'Ext.app.Controller',
    requires:['YourTour.store.LineStore','YourTour.view.line.RecommedListItem'],
    config: {
       refs:{
    	   lineCarousel:'#linerecommendview #lineCarousel'
       },
       
       control:{
       	   '#linerecommendview #close':{
       	   	   tap:'backToNewRouteView'
       	   },
       	   
    	   "#routeplan #users":{
    		   itemtap:"onUsersItemTap"
    	   },
    	   
    	   '#routeplan #lineListView':{
    		   itemtap:"onLineItemTap"
    	   }
       },
       
       routes:{
        	'/line/recommend':'showLineRecommendView'
       }
    },
    
    backToNewRouteView:function(){
    	Ext.Viewport.setActiveItem('newrouteview');
    },
    
    showLineRecommendView:function(){
    	Ext.Viewport.setActiveItem('linerecommendview');
    	
    	var lineCarousel = this.getLineCarousel();
    	var store = Ext.create('YourTour.store.LineStore');
        var showView = function(){
        	lineCarousel.removeAll(true,false);
        	
        	store.each(function(record){
        		var item = Ext.create("YourTour.view.line.RecommedListItem",{data:record});
        		lineCarousel.add(item);
        	});
        };
        
        store.load(showView,this);
    }
});
