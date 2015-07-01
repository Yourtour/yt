Ext.define('YourTour.controller.route.Main', {
    extend: 'Ext.app.Controller',
    requires:['YourTour.store.RouteStore','YourTour.store.RouteLocalStore', 'YourTour.model.RouteModel'],
    
    config: {
       refs: {
        	mainView:'mainview',
        	routeCarousel:'#routeCarousel',
       },
       
       control:{
    	   '#routemain #btnRouteNew':{
    		   tap:'newRoute'
    	   }
       },
       
       routes:{
        	'/main/route':'showRoute'
       }
    },
    
    routeStore:Ext.create('YourTour.store.RouteStore'),
    
    newRoute:function(){
    	this.redirectTo("/route/new");
    },
    
    showRoute:function(){
    	var handler = function(){
        	var tpl = new Ext.XTemplate([
                   "<img src='{imageUrl}' style='width:100%'>",
                   "<div class='label'>名称：{name}</div>",
                   "<div class='label'>线路：{lineName}</div>",
                   "<div class='multiline'>随记：{feeling}</div>",
            ]);
        	
        	var panels = [];
        	
        	this.routeStore.each(function(item){
        		var tplHtml = tpl.apply(item.data);
        		panels.push(Ext.create('Ext.Panel',{html:tplHtml, style:'padding:5px',scrollable:{
                    direction:'vertical',
                    directionLock:true
                }}));
        	});
        	
        	this.getRouteCarousel().removeAll(true,false);
        	this.getRouteCarousel().add(panels);
        	this.getRouteCarousel().setActiveItem(0);
    	};
    	
    	this.routeStore.load({params:{userId:'1111','userName':'tony'}, callback:handler,scope:this});
    }
});
