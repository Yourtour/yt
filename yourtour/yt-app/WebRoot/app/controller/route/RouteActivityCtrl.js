Ext.define('YourTour.controller.route.RouteActivityCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.RouteStore', 'YourTour.model.RouteModel'],
    
    config: {
       refs: {
    	   routeScheduleListView:'#RouteScheduleListView',
       },
       
       control:{
    	   schedulePlanList:{
    	   	   itemtap:'onItemTap'
    	   },
    	   
    	   '#RouteScheduleListView #edit':{
    		   tap:'onEditTap'
    	   }
    	   
       },
       
       routes:{
        	//'/route/load/:routeId':'showPage'
       },
       
       store:null,
       
       routeId : null
    },
    
    init:function(){
    },
    
    onEditTap:function(){
    },
    
    showPage:function(routeId){
    	
    }
    
});