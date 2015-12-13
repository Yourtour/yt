Ext.define('YourTour.controller.ExpertMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{
    	   expertMainView:'#ExpertMainView'
       },
       
       control:{
    	   
       },
       
       routes:{
        	'/expert':'showMainPage',
        	'/expert/apply':'showApplyPage'
       }
    },
    
    init:function(){
    },
    
    showMainPage:function(routeId){
    	var me = this;
    	
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.expert.ExpertMainView'));
    },
    
    showApplyPage:function(routeId){
    	var me = this;
    	
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.expert.ExpertApplyView'));
    }
});
