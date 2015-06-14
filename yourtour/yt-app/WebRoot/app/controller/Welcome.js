Ext.define('YourTour.controller.Welcome', {
    extend: 'Ext.app.Controller',
    config: {
        refs: {
        	enter:'#enter'
        },
        
        control: {
        	enter:{
              tap:'enter'
            }
        }
    },
    
    enter:function(){
    	this.getApplication().localStorage.setItem("welcome.visited", "1");
    	
    }
});
