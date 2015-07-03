Ext.define('YourTour.controller.LineCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.LineStore','YourTour.view.line.RecommedListItem'],
    config: {
       refs:{
    	   lineCarousel:'#linerecommendview #lineCarousel'
       },
       
       control:{
       	   lineCarousel:{
       	   		tap:'showLineIntro'	
       	   },
       	   
       	   '#linerecommendview #close':{
       	   	   tap:'backToNewRouteView'
       	   },
       	   
    	   "#linerecommendview #expertListItem":{
    		   itemtap:"onUsersItemTap"
    	   },
    	   
    	   '#routeplan #lineListView':{
    		   itemtap:"onLineItemTap"
    	   },
       },
       
       routes:{
        	'/line/recommend':'showLineRecommendView'
       }
    },
    
    showLineIntro:function(){
    	this.redirectTo('/line/introduction');
    },
    
    backToNewRouteView:function(){
    	this.show('newrouteview','YourTour.view.route.NewView');
    },
    
    onUsersItemTap:function(){
    	console.log('onUsersItemTap');	
    },
    
    showLineRecommendView:function(){
    	this.show('linerecommendview','YourTour.view.line.RecommendView');
    	
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
