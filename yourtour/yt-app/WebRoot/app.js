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
        'Ext.data.proxy.LocalStorage','Ext.MessageBox', 'YourTour.view.widget.XProcessing', 'Ext.form.Hidden','YourTour.util.Context','YourTour.view.widget.XImageSelect'
    ],
    
    /**
     * APP 本地缓存。
     * this.getApplication().localStorage.setItem('user', this.model.rowKey);
     * this.getApplication().localStorage.getItem('user');
     */
    localStorage : window.localStorage,  
    
    views: [
        'MainView','LaunchView','setting.UserSettingView',
        'common.MessageMainView','common.MessageGroupView','common.FieldEditView','common.FieldEditView',
        'common.CommentMainView','common.TimeSelectionView',

        'home.HomeMainView', 'home.BestListView', 'home.TalentListView', 'SearchMain',
        'route.RouteMainView','route.RouteSettingView','route.RouteImpressionView','route.RouteImageView',
        'route.RouteScheduleListView', 'route.RouteSchedulePlanView','route.RouteScheduleReferenceListView','route.RouteScheduleFormView',
        'route.RouteProvisionView','route.RouteProvisionEditView','route.RouteScheduleEditView','route.RouteScheduleView',
        'route.RouteActivityEditView','route.RoutePlaceEditView','route.RouteCheckinView',
        'route.RouteRecommendListView','route.RouteFormView','route.RouteListView',
        'service.RouteServiceMainView','service.RouteServiceFormView','service.RouteServiceEditView',
        'service.PlaceServiceMainView','service.RouteServiceBookView',
        'user.LoginMainView','user.UserListView','user.UserMainView','user.UserProfileView',
        'resource.ResourceSelectionView','resource.ResourceFormView','resource.ResourceMapView','resource.ResourceActivityItemListView',
        'resource.ResourceActivityItemFormView','resource.ResourceListView',
        'member.MemberMainView','member.MemberView', 'member.MemberAddView','member.MemberPositionView','member.MemberSearchView','member.MemberSelectionView',
        'charge.ChargeMainView','charge.ChargeFormView','charge.ChargeListView','charge.ChargeView','charge.ChargeDivisionView','charge.ChargeDivisionFormView',
        'expert.ExpertMainView','expert.ExpertApplyView','expert.ExpertListView','expert.ExpertServiceEditView',
        'expert.ExpertRecommendListView','expert.ExpertRecommendIntroView','expert.ExpertView','expert.ExpertServiceListView','expert.ExpertServiceFormView',

        'line.LineListView',

        'along.AlongListView','along.AlongFormView','along.AlongFormUserView','along.AlongEditView',

        'community.LiveMainView',
        'place.PlaceMainView','place.PlaceSelectionView'
    ],
    
    controllers: [
        'MainCtrl','SettingMainCtrl','HomeMainCtrl',
        'route.RouteMainCtrl',
        'route.RouteScheduleListCtrl','route.RouteSchedulePlanCtrl',
        'user.AccountMainCtrl',
        'route.ScheduleReferenceCtrl','route.ScheduleDetailCtrl',
        'ResourceMainCtrl','user.UserListCtrl','PlaceSelectionCtrl','CommonMainCtrl',
        'LineMainCtrl','MemberMainCtrl','ChargeMainCtrl','UserMainCtrl', 'ExpertMainCtrl', 'MessageMainCtrl','PlaceMainCtrl','ServiceMainCtrl',
        'AlongMainCtrl'
    ],
    
    models:[
        'LaunchModel','RouteModel','RouteActivityModel', 'LineModel', 'UserModel','OptionModel', 'HomeModel','LiveModel','ChatModel','AlongModel','TalentModel','HomeCarouselModel','CommentModel', 'PlaceModel',
        'CacheModel','ActivityItemModel','RouteServiceModel', 'ExpertModel','ExpertServiceModel','ChargeModel', 'MessageContentModel'
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

        YourTour.util.Context.setApplication(this);
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
    },

    getUserProfile:function(){
        var localStore =  Ext.StoreManager.get('LocalStore');
        localStore.load();

        var index = localStore.find('key', 'user.profile');
        if(index >= 0){
            var userProfile = localStore.getAt(index);
            var profile = Ext.JSON.decode(userProfile.get('value'));
            return profile;
        }

        return '';
    },

    quit:function(){
        var me = this;
        Ext.Msg.confirm("提示", "您确定要退出应用吗?", function(e) {
            if (e == "yes") {
                me.localStorage.clear();

                navigator.app.exitApp();
            }
        }, this);
    },

    getPhoto: function(source, successFn) {
        var me = this;

        navigator.camera.getPicture(
            successFn,
            function() {
            },
            {
                quality: 50,
                destinationType: navigator.camera.DestinationType.FILE_URI,
                sourceType: source
            }
        );
    },

    /**
     *
     * @param options
     */
    callService:function(options){
        try {
            var request = {
                url: YourTour.util.Context.getContext(options.url),
                method: options.method,
                success: function (response) {
                    var respObj = Ext.JSON.decode(response.responseText);
                    if (respObj.errorCode != '0') {
                        Ext.Msg.alert(respObj.errorText);
                        return;
                    }

                    options.success(respObj.data)
                },

                failure: function (response) {
                    var respObj = Ext.JSON.decode(response.responseText);

                    if (options.failure) {
                        options.failure(respObj)
                    } else {
                        Ext.Msg.alert("Error", respObj.status.statusMessage);
                    }
                }
            };
            if (options.params) {
                request.params = Ext.JSON.encode(options.params);
            }

            Ext.Ajax.request(request);
        }catch(e){
            this.toast(e.name + ":" + e.message);
        }
    },

    /**
     *
     * @param options
     */
    query:function(options){
        try {
            var store = Ext.create('YourTour.store.AjaxStore', {model:options.model});
            var proxy = store.getProxy();

            var url = options.url;
            var params = options.params;
            if(params){
                url += '?';

                var first = true;
                params.forEach(function(param){
                    if(first){
                        first = false;
                    }else{
                        url += '&';
                    }
                    url += param.name + '=' + param.value;
                });
            }
            proxy.setUrl(YourTour.util.Context.getContext(url));

            store.load(function(){
                options.success(store);

                var navigationView = Ext.ComponentManager.get('MainView');
                if(navigationView) {
                    var view = navigationView.getActiveItem();

                    if(view instanceof YourTour.view.widget.XPage){
                        view.hideProcessing();
                    }
                }

            })
        }catch(e){
            this.toast(e.name + ":" + e.message);
        }
    },

    /*open:function(options){
        var navigationView = Ext.ComponentManager.get('MainView');
        navigationView.push(Ext.create(options.view));

        var processor = options.processor;
        processor();
    },*/

    alert:function(msg){
        Ext.Msg.alert(msg);
    }
});
