Ext.define('YourTour.controller.LineIntroCtrl', {
    extend: 'Ext.app.Controller',
    requires:['YourTour.store.LineStore','YourTour.view.line.LineRecommendItem'],
    config: {
       refs:{
       },
       
       control:{
       		
    	   '#routeplan #lineListView':{
    		   itemtap:"onLineItemTap"
    	   }
       },
       
       routes:{
        	'/line/introduction':'showIntroductionView'
       }
    },
    
    showIntroductionView:function(){
    	Ext.Viewport.setActiveItem('lineintroview');
    }
});
