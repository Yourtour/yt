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
        'Ext.data.proxy.LocalStorage', 'Ext.MessageBox', 'YourTour.view.widget.XProcessing', 'Ext.form.Hidden', 'YourTour.util.Context', 'YourTour.view.widget.XImageSelect'
    ],

    /**
     * APP 本地缓存。
     * this.getApplication().localStorage.setItem('user', this.model.rowKey);
     * this.getApplication().localStorage.getItem('user');
     */
    localStorage: window.localStorage,

    /**
     * 系统基础数据
     */
    baseStore:null,

    /**
     * 用户数据
     */
    userProfile:null,

    views: [
        'MainView', 'LaunchView', 'setting.UserSettingView',
        'common.MessageMainView', 'common.MessageGroupView', 'common.FieldEditView', 'common.FieldEditView',
        'common.CommentMainView', 'common.TimeSelectionView', 'common.ConsultMainView',

        'home.HomeMainView', 'home.BestListView', 'home.TalentListView', 'SearchMain',
        'route.RouteMainView', 'route.RouteSettingView', 'route.RouteImpressionView', 'route.RouteImageView',
        'route.RouteScheduleListView', 'route.RouteSchedulePlanView', 'route.RouteScheduleReferenceListView', 'route.RouteScheduleFormView',
        'route.RouteProvisionView', 'route.RouteProvisionEditView', 'route.RouteScheduleEditView', 'route.RouteScheduleView',
        'route.RouteActivityEditView', 'route.RoutePlaceEditView', 'route.RouteCheckinView',
        'route.RouteRecommendListView', 'route.RouteFormView', 'route.RouteListView', 'route.RouteReservationPlanView',
        'service.RouteServiceMainView', 'service.RouteServiceFormView', 'service.RouteServiceEditView',
        'service.PlaceServiceMainView', 'service.RouteServiceBookView',
        'user.LoginMainView', 'user.UserListView', 'user.UserMainView', 'user.UserProfileView',
        'resource.ResourceSelectionView', 'resource.ResourceFormView', 'resource.ResourceMapView', 'resource.ResourceActivityItemListView',
        'resource.ResourceActivityItemFormView', 'resource.ResourceListView',
        'member.MemberMainView', 'member.MemberView', 'member.MemberAddView', 'member.MemberPositionView', 'member.MemberSearchView', 'member.MemberSelectionView',
        'charge.ChargeMainView', 'charge.ChargeFormView', 'charge.ChargeListView', 'charge.ChargeView', 'charge.ChargeDivisionView', 'charge.ChargeDivisionFormView',
        'expert.ExpertMainView', 'expert.ExpertApplyView', 'expert.ExpertListView', 'expert.ExpertServiceEditView',
        'expert.ExpertRecommendListView', 'expert.ExpertRecommendIntroView', 'expert.ExpertView', 'expert.ExpertServiceListView', 'expert.ExpertServiceFormView',

        'line.LineListView',

        'along.AlongListView', 'along.AlongFormView', 'along.AlongFormUserView', 'along.AlongEditView',

        'community.LiveMainView',
        'place.PlaceMainView', 'place.PlaceSelectionView'
    ],

    controllers: [
        'MainCtrl', 'SettingMainCtrl', 'HomeMainCtrl',
        'route.RouteMainCtrl',
        'route.RouteScheduleListCtrl', 'route.RouteSchedulePlanCtrl',
        'AccountMainCtrl',
        'route.ScheduleReferenceCtrl', 'route.ScheduleDetailCtrl',
        'ResourceMainCtrl', 'user.UserListCtrl', 'PlaceSelectionCtrl', 'CommonMainCtrl',
        'LineMainCtrl', 'MemberMainCtrl', 'ChargeMainCtrl', 'UserMainCtrl', 'ExpertMainCtrl', 'MessageMainCtrl', 'PlaceMainCtrl', 'ServiceMainCtrl',
        'AlongMainCtrl'
    ],

    models: [
        'SimpleModel', 'LaunchModel', 'RouteModel', 'RouteActivityModel', 'LineModel', 'UserModel', 'OptionModel', 'HomeModel', 'LiveModel', 'ChatModel', 'AlongModel', 'TalentModel', 'HomeCarouselModel', 'CommentModel', 'PlaceModel',
        'CacheModel', 'ActivityItemModel', 'RouteServiceModel', 'ExpertModel', 'ExpertServiceModel', 'ChargeModel', 'MessageContentModel'
    ],

    stores: [
        'LaunchStore', 'RouteStore', 'RouteMemberStore', 'LineStore', 'ResourcePlayStore', 'ResourceFoodStore', 'UserStore', 'HomeStore', 'BestListStore', 'TalentListStore', 'AlongListStore', 'CommentStore', 'PlaceStore', 'LocalStore'
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

    launch: function () {
        try {
            document.addEventListener("deviceready", this.onDeviceReady, false);
        } catch (e) {
            alert(e.name + ":" + e.message);
        }

        this.initialize();


    },

    initialize: function () {
        var me = this;
        Ext.Ajax.on('beforerequest', (function (conn, options, eOpts) {
            var userToken = me.getUserId();
            options.headers = {
                'User-Token': userToken,
                'Content-Type': 'application/json'
            };
        }), this);

        YourTour.util.Context.setApplication(this);

        this.initAppContext();
    },

    getBaseStore:function(){
        if(this.baseStore == null){
            this.initAppContext();
        }

        return this.baseStore;
    },

    /**
     * APP基础数据初始化
     */
    initAppContext:function(){
        console.log('Initializing App Context.......');
        var me = this, localStore =  Ext.StoreManager.get('LocalStore'), json;

        localStore.load(function(){
            var index = localStore.find('key', 'app.basedata');
            if(index < 0){ //本地没有缓存
                console.log('Loading base data from remote server.......');
                var store = Ext.create('YourTour.store.LaunchStore', {itemId:'lanuchStore'});
                var success = function(){
                    try{
                        var json = Ext.JSON.encode(store.first().raw);
                        localStore.add({key:'app.basedata', value:json});
                        localStore.sync();

                        me.baseStore = Ext.create('YourTour.store.LaunchStore', store.first());
                    }catch(e){
                        alert(e.name + ": " + e.message);
                    }

                    me.getController('MainCtrl').startup();
                };
                store.load(success, this);
            }else{
                console.log('Loading base data from local.......');
                json = localStore.getAt(index).get('value');
                me.baseStore = Ext.create('YourTour.store.LaunchStore', {data:Ext.JSON.decode(json)});

                me.getController('MainCtrl').startup();
            }
        });
    },

    onDeviceReady: function () {
        try {
            document.addEventListener("backbutton", function () {
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
                    Ext.Msg.confirm("提示", "您确定要退出应用吗?", function (e) {
                        if (e == "yes") {
                            navigator.app.exitApp();
                        }
                    }, this);
                }
            }, false);
        } catch (e) {
            alert(e.name + ":" + e.message);
        }
    },

    onUpdated: function () {
        Ext.Msg.confirm(
            "Application Update",
            "This application has just successfully been updated to the latest version. Reload now?",
            function (buttonId) {
                if (buttonId === 'yes') {
                    window.location.reload();
                }
            }
        );
    },

    store: function (values) {
        var me = this, localStore = Ext.StoreManager.get('LocalStore');
        localStore.load();

        var v = [];
        if (Ext.isArray(values)) {
            v = values;
        } else {
            v.push(values);
        }

        var found = false;
        Ext.Array.forEach(v, function (value) {
            if(value.key == 'user.profile'){
                me.userProfile = null;
            }

            found = false;
            localStore.each(function(item){
                if(item.get('key') == value.key){
                    found = true;
                    item.set('value', value.value);
                }
            });

            if(! found) {
                localStore.add(v);
            }
        });

        localStore.sync();
    },

    /**
     * 获取用户ID
     * @returns {*}
     */
    getUserId: function () {
        var profile = this.getUserProfile();
        if(profile == null) return null;

        return profile.id;
    },

    /**
     * 获取用户个人信息
     * @returns {*}
     */
    getUserProfile: function () {
        var me = this;
        if(this.userProfile == null){
            var localStore = Ext.StoreManager.get('LocalStore');
            localStore.load();
            var index = localStore.find('key', 'user.profile');
            if (index >= 0) {
                var userProfile = localStore.getAt(index);
                me.userProfile = Ext.JSON.decode(userProfile.get('value'));
            }
        }

        return me.userProfile;
    },

    /**
     * 退出应用
     */
    quit: function () {
        var me = this;
        Ext.Msg.confirm("提示", "您确定要退出应用吗?", function (e) {
            if (e == "yes") {
                me.localStorage.clear();

                navigator.app.exitApp();
            }
        }, this);
    },

    /**
     * 调用远程服务(非查询类的)
     * @param options
     */
    callService: function (options) {
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
                    Ext.Msg.alert("Error", '系统异常，请稍后再试！');
                }
            };
            if (options.params) {
                request.params = Ext.JSON.encode(options.params);
            }

            Ext.Ajax.request(request);
        } catch (e) {
            this.toast(e.name + ":" + e.message);
        }
    },

    /**
     * 附件上传
     * @param options
     */
    uploadService: function (o) {
        var formdata = new FormData();

        var options = {params: {}, files: [], url: ''};
        Ext.apply(options, o);
        var params = options.params;
        if (params) {
            for (var param in params) {
                formdata.append(param, params[param]);
            }
        }

        var fileContainer = options.fileContainer;
        if (fileContainer) {
            var files = fileContainer.getImages();
            var base64, mimeString, byteString, arrayBuffer, intArray, blob;
            Ext.Array.forEach(files, function (file) {
                base64 = file.getAsBase64();
                mimeString =  base64.split(',')[0].split(':')[1].split(';')[0]; // mime类型
                byteString = atob(base64.split(',')[1]); //base64 解码
                arrayBuffer = new ArrayBuffer(byteString.length); //创建缓冲数组
                intArray = new Uint8Array(arrayBuffer); //创建视图
                for (var i = 0; i < byteString.length; i += 1) {
                    intArray[i] = byteString.charCodeAt(i);
                }
                blob = new Blob([intArray], { type:  mimeString }); //转成blob

                formdata.append(fileContainer.getItemId(), blob, file.getFileName());
            })
        }

        var xhr = new XMLHttpRequest();
        xhr.open('POST', YourTour.util.Context.getContext(options.url), true);

        xhr.onload = function(event) {
            if (xhr.status === 200) {
                var respObj = Ext.JSON.decode(event.target.responseText);
                if (respObj.errorCode != '0') {
                    Ext.Msg.alert(respObj.errorText);
                    return;
                }

                options.success(respObj.data);
            } else {
                Ext.Msg.alert("Error", '系统异常，请稍后再试！');
            }
        };

        xhr.send(formdata);
    },


    /**
     * 调用远程查询类服务
     * @param options
     */
    query: function (options) {
        try {
            var store;
            if(options.config){
                store = Ext.create('YourTour.store.AjaxStore', options.config);
            }else{
                store = Ext.create('YourTour.store.AjaxStore', {model: options.model});
            }
            var proxy = store.getProxy();

            var url = options.url;
            var params = options.params;
            if (params) {
                url += '?';

                var first = true;
                params.forEach(function (param) {
                    if (first) {
                        first = false;
                    } else {
                        url += '&';
                    }
                    url += param.name + '=' + param.value;
                });
            }
            proxy.setUrl(YourTour.util.Context.getContext(url));

            store.load(function () {
                options.success(store);

                var navigationView = Ext.ComponentManager.get('MainView');
                if (navigationView) {
                    var view = navigationView.getActiveItem();

                    if (view instanceof YourTour.view.widget.XPage) {
                        view.hideProcessing();
                    }
                }

            })
        } catch (e) {
            this.toast(e.name + ":" + e.message);
        }
    },

    toast: function (msg) {
        Ext.Msg.alert(msg);
    }
});
