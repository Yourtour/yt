Ext.define('YourTour.controller.RouteMain', {
    extend: 'Ext.app.Controller',
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
        	'/main/route':'showRouteView'
       }
    },
    
    newRoute:function(){
    	this.redirectTo("/route/new");
    },
    
    showRouteView:function(){
    	var store = Ext.create('YourTour.store.RouteMain');
    	
    	var handler = function(){
        	var tpl = new Ext.XTemplate([
                   "<img src='{imgUrl}' style='width:100%'>",
                   "<div class='label'>名称：{name}</div>",
                   "<div class='label'>线路：{lineName}</div>",
                   "<div class='multiline'>随记：{feeling}</div>",
            ]);
        	
        	var panels = [];
        	
        	store.each(function(item){
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
    	
    	store.load(handler,this);
    }
});
