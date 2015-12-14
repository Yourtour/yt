/*
    This file is generated and updated by Sencha Cmd. You can edit this file as
    needed for your application, but these edits will have to be merged by
    Sencha Cmd when it performs code generation tasks such as generating new
    models, controllers or views and when running "sencha app upgrade".

    Ideally changes to this file would be limited and most work would be done
    in other places (such as Controllers). If Sencha Cmd cannot merge your
    changes and its generated code, it will produce a "merge conflict" that you
    will need to resolve manually.
*/

Ext.application({
    name: 'YourTour',

    requires: [
        'Ext.MessageBox', 'YourTour.view.widget.XTextArea', 'Ext.form.Hidden','YourTour.util.Context','YourTour.view.home.BestItemView','YourTour.view.common.PlaceGridItemView'
    ],
    
    /**
     * APP 本地缓存。
     * this.getApplication().localStorage.setItem('user', this.model.rowKey);
     * this.getApplication().localStorage.getItem('user');
     */
    localStorage : window.localStorage,  
    
    views: [
        'MainView','Launch','common.PlaceChangeView','common.PlaceSelectionView',
        'home.HomeMainView', 'home.BestListView', 'home.AlongListView', 'home.AlongDetailView', 'home.TalentListView', 'SearchMain',
        'route.RouteMainView','route.RouteSettingView','route.RouteImpressionView','route.RouteDiscussView',
        'route.RouteScheduleListView', 'route.RouteSchedulePlanView','route.RouteScheduleReferenceView','route.schedule.SceneScheduleView','route.schedule.HotelScheduleView','route.schedule.FoodScheduleView',
        'route.RouteProvisionView','route.RouteProvisionEditView','route.RouteScheduleEditView','route.RouteScheduleView',
        'route.RouteActivityEditView',
        'user.LoginMainView','user.UserListView','user.UserMainView',
        'line.LineRecommendView','line.LineIntroductionView',
        'resource.ResourceSelectionView','resource.ResourceDetailView',
        'member.MemberMainView','member.MemberAddView','member.MemberPositionView','member.MemberSearchView',
        'expert.ExpertMainView','expert.ExpertApplyView','expert.ExpertIntroView','expert.ExpertListView'
    ],
    
    controllers: [
        'Launch', 'MainCtrl', 'common.PlaceChangeCtrl','common.PlaceSelectionCtrl',
        'home.HomeMainCtrl', 'home.BestMainCtrl','home.TalentMainCtrl','home.AlongMainCtrl',
        'route.RouteMainCtrl','route.RouteEditCtrl',
        'route.RouteScheduleListCtrl','route.RouteSchedulePlanCtrl',
        'line.LineRecommendCtrl','line.LineIntroductionCtrl','user.AccountMainCtrl',
        'route.ScheduleReferenceCtrl','route.ScheduleDetailCtrl',
        'resource.ResourceCtrl','user.UserListCtrl',
        'MemberMainCtrl','UserMainCtrl', 'ExpertMainCtrl'
    ],
    
    models:[
        'LaunchModel','RouteModel','RouteActivityModel', 'LineModel', 'UserModel','OptionModel', 'HomeModel','LiveModel','ChatModel','AlongModel','TalentModel','HomeCarouselModel','CommentModel', 'PlaceModel',
        'CacheModel'
    ],
    
    stores:[
        'LaunchStore','RouteStore','RouteMemberStore', 'LineStore','ResourcePlayStore','ResourceFoodStore','UserStore', 'HomeStore', 'BestListStore','TalentListStore', 'AlongListStore','CommentStore', 'PlaceStore','LocalStore'
    ],
        
    icon: {
        '57': 'resources/icons/Icon.png',
        '72': 'resources/icons/Icon~ipad.png',
        '114': 'resources/icons/Icon@2x.png',
        '144': 'resources/icons/Icon~ipad@2x.png'
    },
    
    isIconPrecomposed: true,
    
    startupImage: {
        '320x460': 'resources/startup/launch.jpg',
        '640x920': 'resources/startup/launch.png',
        '768x1004': 'resources/startup/launch.png',
        '748x1024': 'resources/startup/launch.png',
        '1536x2008': 'resources/startup/launch.png',
        '1496x2048': 'resources/startup/launch.png'
    },

    launch: function() {
    	try{
    		document.addEventListener("deviceready", this.onDeviceReady, false);
    	}catch(e){
    		alert(e.name + ":" + e.message);
    	}   
    	
    	//设置AJAX请求公用信息
    	Ext.Ajax.failure = function(response) {
	    	alert('failure=' + response.responseText);
	    	
	        var respObj = Ext.JSON.decode(response.responseText);
	        Ext.Msg.alert("Error", respObj.status.statusMessage);
	    };
	    
    	Ext.Ajax.on('beforerequest', (function(conn, options, eOpts) {
    		var localStore =  Ext.StoreManager.get('LocalStore');
        	localStore.load();
        	
        	var userToken = '';
        	var index = localStore.find('key', 'user.profile'); 
        	if(index >= 0){
        		var userProfile = localStore.getAt(index);
        		var profile = Ext.JSON.decode(userProfile.get('value'));
        		userToken = profile.id;
        	}
    		
    	    options.headers = {
    	    	'User-Token':userToken,
    	    	'Content-Type':'application/json'
    	    };
    	}), this);
    },
    
    onDeviceReady:function() {
    	try{
    		document.addEventListener("backbutton", function(){
    	        var canPop = false;
    	        var id = Ext.Viewport.getActiveItem().id;
    	        
    	        if (id.indexOf("MainView") != -1) {
    	          var mainview = Ext.Viewport.getActiveItem();
    	          var length = mainview.getItems().length;
    	          if (length > 2) {
    	            canPop = true;
    	          }
    	        }
    	        
    	        if (canPop) {
    	        	Ext.ComponentManager.get('MainView').pop();
    	        } else {
    	        	Ext.Msg.confirm("提示", "您确定要退出应用吗?", function(e) {
    	        		if (e == "yes") {
    	        			navigator.app.exitApp();
    	        		}
    	        	}, this);
    	        }
    		}, false);
    	}catch(e){
    		alert(e.name + ":" + e.message);
    	}
    },
    
    onUpdated: function() {
        Ext.Msg.confirm(
            "Application Update",
            "This application has just successfully been updated to the latest version. Reload now?",
            function(buttonId) {
                if (buttonId === 'yes') {
                    window.location.reload();
                }
            }
        );
    },
    
    getUserId:function(){
    	var localStore =  Ext.StoreManager.get('LocalStore');
    	localStore.load();
    	
    	var index = localStore.find('key', 'user.profile'); 
    	if(index >= 0){
    		var userProfile = localStore.getAt(index);
    		var profile = Ext.JSON.decode(userProfile.get('value'));
    		return profile.id;
    	}
    	
    	return '';
    }
});
