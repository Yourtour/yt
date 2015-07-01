Ext.define('YourTour.controller.Main', {
    extend: 'Ext.app.Controller',
    config: {
       refs:{
    	 mainView:'mainview'  
       },
       
       control:{
    	   mainView:{
    		   activeitemchange:'activeitemchange'
    	   }
        }
    },
    
    activeitemchange:function(tabBar, newTab, oldTab){
    	var index = tabBar.indexOf(newTab);
    	if(index == 2){
    		this.redirectTo('/main/route');
    	}
    }
});
