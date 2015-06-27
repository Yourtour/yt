Ext.define('YourTour.controller.route.Plan', {
    extend: 'Ext.app.Controller',
    requires:['YourTour.store.LineStore'],
    config: {
       refs:{
    	   dvLine:'lineListView'
       },
       
       control:{
       },
       
       routes:{
        	'/route/plan':'showRoutePlanView'
       }
    },
    
    showRoutePlanView:function(){
    	var store = Ext.create('YourTour.store.LineStore');
        var showView = function(){
           Ext.Viewport.setActiveItem("routeplan");
           var dvLine = this.getDvLine();
           dvLine.setStore(store);
        };
        
        store.load(showView,this);
    }
});
