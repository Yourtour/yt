Ext.define('YourTour.controller.ServiceMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{
    	   serviceDetailView:'#ServiceDetailView'
       },
       
       control:{
    	   
       },
       
       routes:{
        	'/service/:serviceId':'showFormPage',
       }
    },
    
    init: function(){
    },
    
    showFormPage:function(serviceId){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.common.ServiceFormView'));
    	
    }
});
