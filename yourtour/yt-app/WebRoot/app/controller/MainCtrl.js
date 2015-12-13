Ext.define('YourTour.controller.MainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
    	refs:{
    		menuTab:'#MainView #menuTab',
    	},
    	
    	control:{
     	   menuTab:{
     		   activeitemchange:'onActiveItemChange'
     	   }
     	},
    	
    	routes:{
       		'/mainpage':'showPage'
    	}
    },
    
    showPage:function(){
    	Ext.Viewport.setActiveItem(Ext.create('YourTour.view.MainView'));
    	
    	this.doForward('/main/home');
    },
    
    onActiveItemChange:function(tabBar, newTab, oldTab){
    	if(newTab.getItemId() == 'btnHome'){
    		this.doForward('/main/home');
    	}else if(newTab.getItemId() == 'btnRoute'){
    		this.doForward('/main/route');
    	}else if(newTab.getItemId() == 'btnPersonal'){
    		this.doForward('/main/user');
    	}
    },
    
    doForward:function(target){
    	this.redirectTo(target);
    }
});
