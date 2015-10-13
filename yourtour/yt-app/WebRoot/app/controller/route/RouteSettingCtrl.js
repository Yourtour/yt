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
        	'/route/new':'showPage'
       },
       
       store:Ext.create('YourTour.store.RouteStore'),
    },
    
    showPage:function(){
		Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteSettingView'));
    },
    
    addDestinationPlace:function(){
    	this.redirectTo('/place/selection');
    },
    
    addStartPlace:function(){
    	this.redirectTo('/place/selection');
    },
    
    OnNextClick:function(){
    	this.redirectTo('/line/recommend');
    },
    
    addPlace:function(place){
    	this.store.add(place);
    }
});
