Ext.define('YourTour.controller.common.Place', {
    extend: 'Ext.app.Controller',
    
    config: {
       refs: {
       	   back:'#placeselection #close'
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
    	
    	Ext.Viewport.setActiveItem('placeselection');
    },
    
    onBack:function(){
    	
    	Ext.Viewport.setActiveItem('routenew');
    }
});
