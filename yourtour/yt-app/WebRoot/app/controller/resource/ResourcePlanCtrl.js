Ext.define('YourTour.controller.resource.ResourcePlanCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.ResourceStore'],
    config: {
       refs:{
       		sceneResourcePlanView:'SceneResourcePlanView',
       		
       		sceneResource:'#SceneResourcePlanView #sceneresource'
       },
       
       control:{
       	   '#SceneResourcePlanView #toolbar':{
       	   	   	tap:'onBackTap'
       	   }
       },
       
       routes:{
        	'/resource/plan/:resourceId':'showResourcePlanView'
       },
       
       store:null
    },
    
    init: function(){
    	this.store = Ext.create('YourTour.store.ResourceStore');
    },
    
    onBackTap:function(){
    	this.redirectTo('/schedule/resource/selection');
    },
    
    showResourcePlanView:function(resourceId){
    	this.show('SceneResourcePlanView','YourTour.view.resource.SceneResourcePlanView');
    	
    	var store = this.store;
    	var record = store.getAt(0);
    	
    	if(record){
    		this.getSceneResource().setRecord(record);
    	}
    }
});
