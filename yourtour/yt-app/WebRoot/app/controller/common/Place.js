Ext.define('YourTour.controller.common.Place', {
    extend: 'Ext.app.Controller',
    
    config: {
       refs: {
       },
       
       control:{
    	   
       },
       
       routes:{
        	'/place/selection':'showPlaceSelectionView'
       }
    },
    
    showPlaceSelectionView:function(){
    	Ext.Viewport.setActiveItem('placeselection');
    }
});
