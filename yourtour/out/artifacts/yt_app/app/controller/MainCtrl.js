Ext.define('YourTour.controller.MainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
    	refs:{
			launchView:'#LaunchView',

			mainView:'#MainView',
			menuTab:'#MainView #menuTab'
    	},
    	
    	control:{
     	   menuTab:{
     		   activeitemchange:'onActiveItemChange'
     	   },

			'#LaunchView #enter':{
				tap:'doEnter'
			}
     	}
    },

	startup:function(){
		var me = this, index;
		Ext.Viewport.add(Ext.create('YourTour.view.LaunchView'));

		this.store = Ext.create('YourTour.store.LaunchStore', {itemId:'lanuchStore'});
		var success = function(){
			try{
				var localStore =  Ext.StoreManager.get('LocalStore');
				var json = Ext.JSON.encode(this.store.first().raw);
				index = localStore.find('key', 'app.basedata'); //检查是否访问过welcome页
				if(index < 0){
					localStore.add({key:'app.basedata', value:json});
				}else{
					localStore.getAt(index).set('value', json);
				}
				localStore.sync();

				index = localStore.find('key', 'welcome.visited'); //检查是否访问过welcome页
				if(index < 0){ //没有访问过
					var launch = me.getLaunchView();
					launch.setActiveItem(1);
				}else{
					//检查是否登录过
					index = localStore.find('key', 'account.authenticated');
					if(index >= 0){
						me.showMainPage();
					}else{
						me.doEnter();
					}
				}
			}catch(e){
				alert(e.name + ": " + e.message);
			}
		};
		this.store.load(success, this);
	},

	doEnter:function(){
		this.getApplication().store({key:'welcome.visited', value:'1'});

		var controller = this.getApplication().getController('AccountMainCtrl');
		controller.showLoginView();
	},

	/**
	 * 显示APP主页
	 */
    showMainPage:function(){
    	Ext.Viewport.setActiveItem(Ext.create('YourTour.view.MainView'));

		this.showHomeView();
    },
    
    onActiveItemChange:function(tabBar, newTab, oldTab){
    	if(newTab.getItemId() == 'btnHome'){
			this.showHomeView();
    	}else if(newTab.getItemId() == 'btnRoute'){
    		this.showRouteView();
    	}else if(newTab.getItemId() == 'btnPersonal'){
    		this.showUserView();
    	}else if(newTab.getItemId() == 'btnPlace'){
			this.showPlaceView();
		}
    },

	showHomeView:function(){
		var controller = this.getApplication().getController('HomeMainCtrl');
		controller.showMainPage();
	},

	showRouteView:function(){
		var controller = this.getApplication().getController('route.RouteMainCtrl');
		controller.showMainPage();
	},

	showPlaceView:function(){
		var controller = this.getApplication().getController('PlaceMainCtrl');
		controller.showMainPage(6, '黄山');
	},

	showUserView:function(){
		var controller = this.getApplication().getController('UserMainCtrl');
		controller.showMainPage();
	}
});
