Ext.define('YourTour.controller.common.PlaceCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    
    config: {
       refs: {
       	   back:'#placeview #close'
       },
       
       control:{
    	   back:{
    	   		tap:'onBack'
    	   }
       },
       
       routes:{
        	'/place/selection/:source':'showPlaceSelectionView'
       },
       
       source:undefined
    },
    
    showPlaceSelectionView:function(source){
    	this.source = source;
    	this.show('placeview','YourTour.view.common.PlaceView');
    },
    
    onBack:function(){
    	this.show('newrouteview','YourTour.view.route.NewView');
    }
});
