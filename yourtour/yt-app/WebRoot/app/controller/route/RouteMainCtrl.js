Ext.define('YourTour.controller.route.RouteMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.RouteStore','YourTour.view.route.MainItem'],
    
    config: {
       refs: {
       	    routeMainView:'#RouteMainView',
        	routeCarousel:'#RouteMainView #routeCarousel',
       		newRoute:'#RouteMainView #new',
       		deleteRoute:'#RouteMainView #delete'
       },
       
       control:{
    	   newRoute:{
    		   	tap:'newRoute'
    	   },
    	   
    	   deleteRoute:{
   		   	tap:'deleteRoute'
    	   },
    	   
    	   routeCarousel:{
    		   onRouteTap:'onRouteTap',
    		   onMemberTap:'onMemberTap'
    	   }
       },
       
       routes:{
        	'/main/route':'showPage'
       },
       
       store:null
    },
    
    newRoute:function(){
    	this.redirectTo("/route/new");
    },
    
    deleteRoute:function(){
    	var me = this;
    	var routeCarousel = me.getRouteCarousel();
    	var index = routeCarousel.getActiveIndex();
    	
    	var model = me.store.getAt(index);
    	
    	Ext.Ajax.request({
    		url : YourTour.util.Context.getContext('/routes/' + model.get('id') + '/delete'),
    	    method : "GET",
    	    success : function(response) {
    	    	var respObj = Ext.JSON.decode(response.responseText);
    	    	if(respObj.errorCode != '0'){
    	    		Ext.Msg.alert(resp.errorText);
    	    		return;
    	    	};
    	    	
    	    	Ext.Msg.alert('删除成功。');
    	    	
    	    	me.store.removeAt(index);
    	    	
    	    	routeCarousel.removeAt(index);
    	    }
    	});
    },
    
    showPage:function(){
    	var me = this;
    	
    	YourTour.util.Context.mainview = me.getRouteMainView();
    	
    	var routeCarousel = me.getRouteCarousel();
    	var store = me.store = Ext.create('YourTour.store.RouteStore',{storeId:'RouteMainStore'});	
    	var handler = function(){
    		routeCarousel.removeAll(true, false);
        	store.each(function(item){
        		var routePanel = Ext.create('YourTour.view.route.MainItem',{itemId:item.get('Id'), carousel:routeCarousel, record:item});
        		routeCarousel.add(routePanel);
        	});
        	
        	routeCarousel.setActiveItem(0);
    	};

    	var proxy = store.getProxy();
    	proxy.setUrl(YourTour.util.Context.getContext('/routes/personal/query'));
    	store.load(handler, this);
    },
    
    onRouteTap:function(record){
   		this.redirectTo('/route/load/' + record.get('id'));
    },
    
    onMemberTap:function(record){
    	this.redirectTo('/routes/' + record.get('id') + '/members');
    }
});
