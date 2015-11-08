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
        	try{
	        	var localStore =  Ext.StoreManager.get('LocalStore');
	        	localStore.load();
	        	
	        	var index = localStore.find('key', 'welcome.visited'); //检查是否访问过welcome页
	        	if(index < 0){
	        		var launch = me.getLaunch();
	            	launch.setActiveItem(1);
	        	}else{
	        		//检查是否登录过
	        		index = localStore.find('key', 'account.authenticated');
	        		if(index >= 0){
	        			me.redirectTo('/mainpage');
	        		}else{
	        			me.redirectTo('/login');
	        		}
	        	}
        	}catch(e){
        		 alert(e.name + ": " + e.message);
        	}
        };
        
        this.store.load(success, this);
    },
    
    doEnter:function(){
    	var localStore =  Ext.StoreManager.get('LocalStore');
    	localStore.add({key:'welcome.visited', value:'1'});
    	localStore.sync();
    	
    	this.redirectTo('/login');
    }
});
