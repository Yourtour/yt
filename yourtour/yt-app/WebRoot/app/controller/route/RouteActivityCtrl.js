Ext.define('YourTour.controller.route.RouteActivityCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.RouteStore', 'YourTour.model.RouteModel'],
    
    config: {
       refs: {
    	   routeActivityEditView:'#RouteActivityEditView',
       },
       
       control:{
       },
       
       routes:{
        	'/route/activity/:routeId/:index/:resourceId':'showPage'
       },
       
       store:null,
       
       routeId : null
    },
    
    init:function(){
    },
    
    onEditTap:function(){
    },
    
    showPage:function(routeId, index, resourceId){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.resource.SelectionListView'));
    }
});