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
    baseStore: null,

    /**
     * 用户数据
     */
    userProfile: null,

    views: [
        'MainView', 'WelcomeView', 'UpgradeView', 'ActivityView', 'setting.UserSettingView',

        'common.MessageMainView', 'common.MessageGroupView', 'common.FieldEditView', 'common.FieldEditView',
        'common.CommentMainView', 'common.TimeSelectionView', 'common.ConsultMainView',
        'cart.CartMainView',
        'discovery.DiscoveryMainView',
        'home.HomeMainView', 'home.BestListView', 'home.TalentListView', 'SearchMain',
        'route.RouteMainView', 'route.RoutePlanView', 'route.RouteImpressionView', 'route.RouteImageView',
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
        'DeviceModel', 'VersionModel', 'ActivityModel', 'SimpleModel', 'LaunchModel', 'RouteModel', 'RouteActivityModel', 'LineModel', 'UserModel', 'OptionModel', 'HomeModel', 'LiveModel', 'ChatModel', 'AlongModel', 'TalentModel', 'HomeCarouselModel', 'CommentModel', 'PlaceModel',
        'CacheModel', 'ActivityItemModel', 'RouteServiceModel', 'ExpertModel', 'ExpertServiceModel', 'ChargeModel', 'MessageContentModel'
    ],

    stores: [
        'GenderStore', 'LaunchStore', 'LocalStore', 'SessionStore'],

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

    version: '1.0.0',

    baseStore:'',

    localStorage:null,

    sessionStorage:null,

    launch: function () {
        try {
            var me = this;
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
                    navigator.notification.confirm('您确定要退出应用吗?',
                        function (buttonIndex) {
                            if (buttonIndex == "1") {
                                navigator.app.exitApp();
                            }
                        },
                        '提示',
                        '确定,取消'
                    );
                }
            }, false);

            if (!Ext.os.is.Windows) {
                document.addEventListener("deviceready",
                    function () {
                        App.getVersion(function (version) {
                            me.version = version;
                            me.initialize();
                        });
                    },
                    false);
            } else {
                me.initialize();
            }
        } catch (e) {
            alert(e.name + ":" + e.message);
        }
    },

    initialize: function () {
        var me = this,
            localStorage = me.getLocalStorage(),
            sessionStorage = me.getSessionStorage(),
            accessToken = localStorage.get('access-token'),
            sessionToken = sessionStorage.get('session-token'),
            userToken = me.getUserId();

        Ext.Ajax.on('beforerequest', (function (conn, options, eOpts) {
            options.headers = {
                'Access-Token': accessToken,  //客户端令牌
                'Session-Token': sessionToken,  //客户端令牌
                'User-Token': userToken,  //用户令牌

                'Content-Type': 'application/json'
            };
        }), this);

        YourTour.util.Context.setApplication(this);

        this.initAppContext();
    },

    getBaseStore: function () {
        if (this.baseStore == null) {
            this.initAppContext();
        }

        return this.baseStore;
    },

    /**
     * APP基础数据初始化
     */
    initAppContext: function () {
        var me = this, localStore = this.getLocalStorage(), json;

        localStore.load(function () {
            var index = localStore.find('key', 'app.basedata');
            if (index < 0) { //本地没有缓存
                console.log('Loading base data from remote server.......');
                var store = Ext.create('YourTour.store.LaunchStore', {itemId: 'lanuchStore'});
                var success = function () {
                    try {
                        var json = Ext.JSON.encode(store.first().raw);
                        localStore.add({key: 'app.basedata', value: json});
                        localStore.sync();

                        me.baseStore = Ext.create('YourTour.store.LaunchStore', store.first());
                    } catch (e) {
                        alert(e.name + ": " + e.message);
                    }

                    me.getController('MainCtrl').startup();
                };
                store.load(success, this);
            } else {
                console.log('Loading base data from local.......');
                json = localStore.getAt(index).get('value');
                me.baseStore = Ext.create('YourTour.store.LaunchStore', {data: Ext.JSON.decode(json)});
                me.getController('MainCtrl').startup();
            }
        });
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

    getVersion: function () {
        return this.version;
    },

    getDeviceType: function () {
        if (!Ext.os.is.Windows) {
            return device.platform;
        }else{
            return 'ANDROID';
        }
    },

    getAppType: function () {
        return 'TOURIST';
    },

    getLocalStorage:function(){
        var me = this;

        if(me.localStorage == null) {
            me.localStorage = Ext.StoreManager.get('LocalStore');
            me.localStorage.load();
        }

        return me.localStorage;
    },

    getSessionStorage:function(){
        var me = this;

        if(me.sessionStorage == null) {
            me.sessionStorage = Ext.StoreManager.get('SessionStore');
            me.sessionStorage.load();
        }
        return me.sessionStorage;
    },

    /**
     * 获取用户ID
     * @returns {*}
     */
    getUserId: function () {
        var profile = this.getUserProfile();
        if (profile == null) return null;

        return profile.id;
    },

    /**
     * 获取用户个人信息
     * @returns {*}
     */
    getUserProfile: function () {
        var me = this;
        if (this.userProfile == null) {
            var localStore = this.getLocalStorage();
            var userProfile = localStore.get('user.profile');
            if(userProfile) {
                me.userProfile = Ext.JSON.decode(userProfile);
            }
        }

        return me.userProfile;
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
                mimeString = base64.split(',')[0].split(':')[1].split(';')[0]; // mime类型
                byteString = atob(base64.split(',')[1]); //base64 解码
                arrayBuffer = new ArrayBuffer(byteString.length); //创建缓冲数组
                intArray = new Uint8Array(arrayBuffer); //创建视图
                for (var i = 0; i < byteString.length; i += 1) {
                    intArray[i] = byteString.charCodeAt(i);
                }
                blob = new Blob([intArray], {type: mimeString}); //转成blob

                formdata.append(fileContainer.getItemId(), blob, file.getFileName());
            })
        }

        var xhr = new XMLHttpRequest();
        xhr.open('POST', YourTour.util.Context.getContext(options.url), true);

        xhr.onload = function (event) {
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
            var me = this, url = options.url, localStore = Ext.StoreManager.get('LocalStore');

            var cached = options.cached;
            if (cached) { //从本地缓存获取
                var data = me.getCached(url);
                if (data != null) {
                    var store = Ext.create('Ext.data.Store', {model: options.model, data: data});
                    options.success(store);
                }
            }

            var ajaxStore;
            if (options.config) {
                ajaxStore = Ext.create('YourTour.store.AjaxStore', options.config);
            } else {
                ajaxStore = Ext.create('YourTour.store.AjaxStore', {model: options.model});
            }
            var proxy = ajaxStore.getProxy();

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
            this.debug(YourTour.util.Context.getContext(url));
            proxy.setUrl(YourTour.util.Context.getContext(url));

            ajaxStore.load(function () {
                options.success(ajaxStore, cached ? 'true' : 'false'); //true:表示需要刷新

                if (cached) { //本地缓存
                    //me.StoreCached([{key:url, value:Ext.JSON.encode(ajaxStore.getData())}]);
                }

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
        this.info(msg);
    },

    /**
     * 信息确认窗口
     * @param title
     * @param message
     * @param callback
     */
    confirm: function (title, message, callback) {
        if (!Ext.os.is.Windows) {
            navigator.notification.confirm(message,
                function (buttonIndex) {
                    callback(buttonIndex);
                },
                title,
                '确定,取消'
            );
        } else {
            Ext.MessageBox.confirm(title, message, callback, this);
        }
    },

    debug: function (message) {
        console.log(message);
    },

    /**
     * 信息提示窗口
     * @param o
     */
    info: function (o) {
        var options = {
            title: '提示',
            message: '',
            callback: Ext.emptyFn
        };

        if (Ext.isString(o)) {
            Ext.apply(options, {message: o});
        } else {
            Ext.apply(options, o);
        }


        if (!Ext.os.is.Windows) {
            navigator.notification.alert(options.message, options.title, '确定');
        } else {
            Ext.MessageBox.alert(options.message);
        }
    }
});
