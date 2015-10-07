Ext.define('YourTour.controller.common.PlaceChangeCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    
    config: {
       refs: {
       	   oversea: '#PlaceChangeView #oversea',
       	   domestic:'#PlaceChangeView #domestic',
       	   placeType:'#PlaceChangeView #placeType'
       },
       
       routes:{
        	'/place/change':'showPage'
       },
       
       source:undefined
    },
    
    showPage:function(){
		Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.common.PlaceChangeView'));
    }
});
