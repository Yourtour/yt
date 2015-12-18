Ext.define('YourTour.controller.PlaceMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{
    	   placeMainView:'#PlaceMainView'
       },
       
       control:{
    	   
       },
       
       routes:{
        	'/place/:placeId':'showPage'
       },
       
       placeId:null
       
    },
    
    init:function(){
    },
    
    showPage:function(placeId){
    	var me = this;
    	me.placeId = placeId;
    	
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.place.PlaceMainView'));
    }
});
