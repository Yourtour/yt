Ext.define('YourTour.controller.route.RouteProvisionCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{
    	   routeProvisionView:'#RouteProvisionView',
       },
       
       control:{
       	   '#RouteProvisionView #save':{
       	   	   tap:"onProvisionSave"	
       	   }
       },
       
       routes:{
        	'/route/provision/new':'showPage'
       },
       
       store:null
    },
    
    init: function(){
    	
    },
    
    showPage:function(){
		Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteProvisionView'));
    }
});
