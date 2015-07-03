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
    		console.log('creating view for viewId=' + viewId +', viewname=' + viewName);
    		view = Ext.create(viewName,cfg);
	    	pageContainer.add(view);
    	}else{
    		console.log('view for viewId=' + viewId +' existed');
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
