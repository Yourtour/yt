Ext.define('YourTour.controller.Launch', {
    extend: 'Ext.app.Controller',
    config: {
        refs: {
        	launch:'#launch'
        },
        
        control: {
        	'#launch #enter':{
        		tap:'doEnter'
        	}
        },
        
        store : null
    },
    
    launch:function(){
    	Ext.fly('appLoadingIndicator').destroy();
        Ext.Viewport.add(Ext.create('YourTour.view.Launch'));
        
        this.store = Ext.create('YourTour.store.LaunchStore', {itemId:'lanuchStore'});
        
        var me = this;
        
        var success = function(){
        	var launch = me.getLaunch();
        	launch.setActiveItem(1);
        };
        
        this.store.load(success, this);
    },
    
    doEnter:function(){
    	this.redirectTo('/login');
    }
});
