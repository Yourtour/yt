Ext.define('YourTour.controller.route.RouteScheduleListCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.RouteStore', 'YourTour.model.RouteModel'],
    
    config: {
       refs: {
    	   routeScheduleListView:'#RouteScheduleListView',
       },
       
       control:{
    	   routeScheduleListView:{
    		   destroy:'onRouteScheduleViewDestroy'
    	   },
    	   
    	   schedulePlanList:{
    	   	   itemtap:'onItemTap'
    	   },
    	   
    	   '#RouteScheduleListView #edit':{
    		   tap:'onEditTap'
    	   }
    	   
       },
       
       routes:{
        	'/route/load/:routeId':'showPage'
       },
       
       store:null,
       
       routeId : null
    },
    
    init:function(){
		this.store = Ext.create('YourTour.store.RouteStore', {storeId:'RouteDetailStore'});	    	
    },
    
    onEditTap:function(){
    	this.redirectTo('/route/edit/' + this.routeId);
    },
    
    showPage:function(routeId){
    	this.routeId = routeId;
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteScheduleListView'));
    	
    	var view = this.getRouteScheduleListView();
    	var store = this.store;
    	store.setData('');
    	
    	var showView=function(){
    		var record = store.first();
    		
    		var imageEl = view.down('#imageUrl');
    		imageEl.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "' style='width:100%; max-height:150px'>");
    		
	    	var scheduleList = view.down('#RouteScheduleList');
	    	scheduleList.setStore(record.schedulesStore);
    	};
 	   	
    	store.getProxy().setUrl(YourTour.util.Context.getContext('/routes/' + routeId +'/query'));
 	   	store.load(showView,this);
    },
    
    onRouteScheduleViewDestroy:function(){
    	this.store.setData('');
    }
});