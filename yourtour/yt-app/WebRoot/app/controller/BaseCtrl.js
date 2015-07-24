Ext.define('YourTour.controller.BaseCtrl', {
    extend: 'Ext.app.Controller',
    config: {
       refs:{
    	 pageContainer:'#mainview #pageContainer'  
       }
    },
    
    show:function(viewId, viewName, cfg){
    	if(cfg == undefined){
    		cfg = {};
    	}
    	
    	var pageContainer = this.getPageContainer();
    	if(pageContainer == undefined) return;
    	
    	var view = pageContainer.getItems(viewId);
    	if(view.items.length == 0){
    		view = Ext.create(viewName,cfg);
	    	pageContainer.add(view);
    	}
    	
    	pageContainer.setActiveItem(viewId);
    },
    
    contains:function(viewId){
    	var pageContainer = this.getPageContainer();
    	if(pageContainer == undefined) return false;
    	
    	var view = pageContainer.getItems(viewId);
    	return view.length > 0;
    }
});
