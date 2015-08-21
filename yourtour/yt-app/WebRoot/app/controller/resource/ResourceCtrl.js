Ext.define('YourTour.controller.resource.ResourceCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.ResourceStore'],
    config: {
       refs:{
       		sceneResourceDetailView:'SceneResourceDetailView',
       		
       		sceneResource:'#SceneResourceDetailView #sceneresource'
       },
       
       control:{
       	   
       },
       
       routes:{
        	'/resource/detail/:resourceId':'showResourceDetailView'
       },
       
       store:null
    },
    
    init: function(){
    	this.store = Ext.create('YourTour.store.ResourceStore');
    },
    
    showResourceDetailView:function(){
    	this.show('SceneResourceDetailView','YourTour.view.resource.SceneResourceDetailView');
    	
    	var store = this.store;
    	var record = store.getAt(0);
    	
    	if(record){
    		this.getSceneResource().setRecord(record);
    	}
    }
});
