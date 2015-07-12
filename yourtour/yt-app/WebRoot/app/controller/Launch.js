Ext.define('YourTour.controller.Launch', {
    extend: 'Ext.app.Controller',
    config: {
        refs: {
        	launch:'#launch'
        },
        
        control: {
        	'#launch #enter':{
        		tap:'showMainView'
        	}
        }
    },
    
    launch:function(){
    	Ext.fly('appLoadingIndicator').destroy();
        
        Ext.Viewport.add(Ext.create('YourTour.view.Launch'));
        
        var launch = this.getLaunch();
        Ext.defer(function () { 
        	launch.setActiveItem(1);
        },2000);
    },
    
    showMainView:function(){
    	this.redirectTo('homepage');
    }
});
