Ext.define('YourTour.controller.route.RouteSettingCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.RouteStore', 'YourTour.model.RouteModel'],
    config: {
       refs:{
       },
       
       control:{
       	   '#RouteSettingView #add':{
       	   	   tap:"addDestinationPlace"	
       	   },
       	   
    	   "#RouteSettingView #close":{
    		   tap:"OnCloseClick"
    	   },
    	   
    	   '#RouteSettingView #place':{
    		   focus:"addStartPlace"
    	   },
    	   
    	   '#RouteSettingView #next':{
    		   tap:'OnNextClick'
    	   }
       },
       
       routes:{
        	'/route/new':'showNewRouteView'
       }
    },
    
    routeStore:Ext.create('YourTour.store.RouteStore'),
    
    addDestinationPlace:function(){
    	console.log('addDestinationPlace');
    	
    	this.redirectTo('/place/selection/1');
    },
    
    OnCloseClick:function(){
    	this.show('routemainview','YourTour.view.route.MainView');
    },
    
    addStartPlace:function(){
    	this.redirectTo('/place/selection/2');
    },
    
    OnNextClick:function(){
    	this.redirectTo('/line/recommend');
    },
    
    showNewRouteView:function(){
    	this.show("RouteSettingView","YourTour.view.route.RouteSettingView");
    }
});
