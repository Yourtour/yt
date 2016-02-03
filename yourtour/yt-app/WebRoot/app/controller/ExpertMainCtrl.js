Ext.define('YourTour.controller.ExpertMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.view.expert.ExpertViewIntroItem'],
    config: {
       refs:{
    	   expertMainView:'#ExpertMainView',
           expertRouteList:'#ExpertMainView #routeList',

           expertApplyView:'#ExpertApplyView',
           expertCarousel:'#ExpertApplyView #expertCarousel',
           expertServiceList:'#ExpertApplyView #ServiceList',

           expertServiceEditView:'#ExpertServiceEditView',

           expertRecommendListView:'#ExpertRecommendListView',
           expertRecommendList:' #ExpertRecommendListView #expertList',
           expertRecommendIntroView:'#ExpertRecommendIntroView',

           expertView:'#ExpertView'
       },
       
       control:{
           '#ExpertMainView #btnApply':{
               tap:'onExpertApplyTap'
           },

    	   '#ExpertApplyView #btnResource':{
               tap:'onResourceItemTap'
           },

           '#ExpertApplyView #btnService':{
               tap:'onServiceItemTap'
           },

           '#ExpertApplyView #btnSubmit':{
               tap:'onExpertSubmitTap'
           },

           '#ExpertApplyView #expertCarousel':{
               activeitemchange:'onActiveItemChange'
           },

           '#ExpertApplyView #btnAdd':{
               tap:'onServiceAddTap'
           },

           expertServiceList:{
               itemtap:'onServiceItemtap'
           },

           '#ExpertServiceEditView #btnSave':{
               tap:'onServiceSaveTap'
           },

           '#ExpertServiceEditView #btnDelete':{
               tap:'onServiceDeleteTap'
           },

           expertRecommendList:{

           }
       },
       
       routes:{
        	'/expert':'showMainPage',
        	'/expert/:userId':'showIntroPage',
        	'/experts/:placeId':'showListPage'
       }
    },
    
    init:function(){
    },

    showMainPage:function(){
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.expert.ExpertMainView'));

        var me = this;
        var view = me.getExpertMainView();

        var profile = this.getApplication().getUserProfile();
        if(profile != undefined){
            if(profile.expert != '1'){
                var promptPanel = view.down('#promptPanel');
                promptPanel.show();
            }else{
                var expertRouteList = me.getExpertRouteList();

                expertRouteList.show();

                var store = Ext.create('YourTour.store.AjaxStore', {model:'YourTour.model.RouteModel'});
                store.getProxy().setUrl(YourTour.util.Context.getContext('/expert/routes/' + me.getApplication().getUserId()));
                store.load(function(){
                    expertRouteList.setStore(store);
                })
            }
        }
    },

    /**
     * 显示推荐达人页面
     * @param duration
     * @param places
     */
    showRecommendPage:function(duration, places){
        duration = 5;
        var ids = '', names = '';
        var pArray = places.split('|');
        for(var index = 0; index < pArray.length; index++){
            if(index > 0){
                ids = ids + ',';
                names = names + ',';
            }

            var array = pArray[index].split(',');
            ids = ids + array[0];
            names = names + array[1];
        }

        var me = this;

        var options = {
            model:'YourTour.model.ExpertModel',
            url:'/expert/' + ids + '/' + duration,
            success:function(store){
                Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.expert.ExpertRecommendListView'));
                var view = me.getExpertRecommendListView();

                var headerbar = view.down('#headerbar');
                headerbar.setTitle(names);

                store.each(function(expert){
                    var item = Ext.create('YourTour.view.expert.ExpertRecommendDataItem',{record:expert});
                    item.on('tap', function(record){
                        me.showRecommendIntroPage(record);
                    });
                    view.add(item);
                });
            }
        };
        me.getApplication().query(options);
    },

    showRecommendIntroPage:function(record){
        var me = this;

        var options = {
            model:'YourTour.model.ExpertModel',
            url:'/expert/' + record.get('id'),
            success:function(store){
                Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.expert.ExpertRecommendIntroView'));
                var view = me.getExpertRecommendIntroView();

                var record = store.first();

                var image = view.down('#image');
                image.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "' style='width:64px; height:64px'>");

                var nickName = view.down('#nickName');
                nickName.setHtml(record.get('nickName'));

                var identity = view.down('#identity');
                identity.setText(record.get('identity'));

                var age = view.down('#age');
                age.setText(record.get('age'));

                var idAuthenticate = view.down('#idAuthenticate');
                idAuthenticate.addCls(record.get('idAuthenticate') == 1?'icon-checked':'icon-unchecked');

                var snsAuthenticate = view.down('#snsAuthenticate');
                snsAuthenticate.addCls(record.get('snsAuthenticate') == 1?'icon-checked':'icon-unchecked');

                var mobileAuthenticate = view.down('#mobileAuthenticate');
                mobileAuthenticate.addCls(record.get('mobileAuthenticate') == 1?'icon-checked':'icon-unchecked');

                var memo = view.down('#memo');
                memo.setText(record.get('memo'));

                var routeStore = record.routesStore;
                var routes = view.down('#routes');
                routeStore.each(function(route){
                    var item = Ext.create('YourTour.view.route.RouteRecommendDataItem',{record:route});
                    item.on('tap', function(record){
                        var controller = me.getApplication().getController('RouteSchedulePlanCtrl');
                        contoller.showRecommendRouteInfo(record);
                    });
                    routes.add(item);
                });
            }
        };
        me.getApplication().query(options);
    },

    onExpertApplyTap:function(){
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.expert.ExpertApplyView'));

        var page = this.getExpertApplyView();
        var btnResource = page.down('#btnResource');
        btnResource.addCls('active');

        this.getExpertCarousel().setActiveItem(0);
    },

    onResourceItemTap:function(){
        this.showTab(0);

        this.getExpertCarousel().setActiveItem(0);
    },

    onExpertSubmitTap:function(){
        var me = this;

        var view = this.getExpertApplyView();
        var data = {};

        var realName = view.down('#realName').getValue();
        var certType = view.down('#certType').getValue();
        var certNo =  view.down('#certNo').getValue();
        var tags = view.down('#tags').getValue();
        data.realName= realName;
        data.certType = certType;
        data.certNo = certNo;
        data.tags = tags;

        Ext.Ajax.request({
            url : YourTour.util.Context.getContext('/expert/application/save'),
            method : "POST",
            params : Ext.JSON.encode(data),
            success : function(response) {
                var respObj = Ext.JSON.decode(response.responseText);
                if(respObj.errorCode != '0'){
                    Ext.Msg.alert(respObj.errorText);
                    return;
                };
            },
            failure : function(response) {
                var respObj = Ext.JSON.decode(response.responseText);
                Ext.Msg.alert("Error", respObj.status.statusMessage);
            }
        });
    },

    onServiceItemTap:function(switchCarousel){
        var me = this;

        me.showTab(1);
        me.getExpertCarousel().setActiveItem(1);

        var store = Ext.create('YourTour.store.AjaxStore', {model:'YourTour.model.ServiceModel'});
        store.getProxy().setUrl(YourTour.util.Context.getContext('/expert/services/' + me.getApplication().getUserId()));
        store.load(function(){
            me.getExpertServiceList().setStore(store);
        })
    },

    onActiveItemChange:function(carousel, value, oldValue, eOpts){
        if(oldValue.getItemId() == 'overviewPanel'){
            this.showTab(0);
        }else{
            this.showTab(1);
        }
    },

    showTab:function(index){
        var page = this.getExpertApplyView();
        var btnService = page.down('#btnService');
        var btnResource = page.down('#btnResource');

        if(index == 0){
            btnService.addCls('active');
            btnResource.removeCls('active');
        }else{
            btnService.removeCls('active');
            btnResource.addCls('active');
        }
    },

    onServiceAddTap:function(){
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.expert.ExpertServiceEditView'));
    },

    onServiceSaveTap:function(){
        var me = this;
        var view = this.getExpertServiceEditView();
        var data = {};

        var id = view.down('#id').getValue();
        var title = view.down('#title').getValue();
        var memo =  view.down('#memo').getValue();
        data.id= id;
        data.title = title;
        data.memo = memo;

        Ext.Ajax.request({
            url : YourTour.util.Context.getContext('/expert/service/save'),
            method : "POST",
            params : Ext.JSON.encode(data),
            success : function(response) {
                var respObj = Ext.JSON.decode(response.responseText);
                if(respObj.errorCode != '0'){
                    Ext.Msg.alert(respObj.errorText);
                    return;
                };

                data.id = respObj.data;

                me.getExpertServiceList().getStore().add(Ext.create('YourTour.model.ServiceModel', data));

                Ext.ComponentManager.get('MainView').pop();
            },
            failure : function(response) {
                var respObj = Ext.JSON.decode(response.responseText);
                Ext.Msg.alert("Error", respObj.status.statusMessage);
            }
        });
    },

    onServiceItemtap:function(dataview, index, item, record,e){
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.expert.ExpertServiceEditView'));

        var view = this.getExpertServiceEditView();

        var idEl = view.down('#id');
        var titleEl = view.down('#title');
        var memoEl = view.down('#memo');

        idEl.setValue(record.get('id'));
        titleEl.setValue(record.get('title'));
        memoEl.setValue(record.get('memo'));

        var toolbar = view.down('#toolbar');
        toolbar.show();
    },

    onServiceDeleteTap:function(){
        var me = this;
        var view = this.getExpertServiceEditView();

        Ext.Msg.confirm("提示", "您确定要删除当前服务吗?", function(e) {
            if (e == "yes") {
                var id = view.down('#id').getValue();

                Ext.Ajax.request({
                    url : YourTour.util.Context.getContext('/expert/service/' + id + '/delete'),
                    method : "GET",
                    success : function(response) {
                        var respObj = Ext.JSON.decode(response.responseText);
                        if(respObj.errorCode != '0'){
                            Ext.Msg.alert(respObj.errorText);
                            return;
                        };

                        var store = me.getExpertServiceList().getStore();
                        var index = store.find('id', id);
                        store.removeAt(index);

                        Ext.ComponentManager.get('MainView').pop();
                    },
                    failure : function(response) {
                        var respObj = Ext.JSON.decode(response.responseText);
                        Ext.Msg.alert("Error", respObj.status.statusMessage);
                    }
                });
            }
        }, this);
    },

    showExpertInfo:function(userId, record){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.expert.ExpertView'));
        var me = this, expertview = me.getExpertView(), expertCarousel = expertview.down('#expertCarousel');

        var options = {
            model: 'YourTour.model.ExpertModel',
            url: '/expert/' + userId,
            success: function (store) {
                var expert = store.first();
                expertview.setData(expert);

                var introItem = Ext.create('YourTour.view.expert.ExpertViewIntroItem', {record:expert});
                expertCarousel.add(introItem)
            }
        };
        me.getApplication().query(options);
    },
    
    showExpertList:function(placeId, store){
    	var me = this;
    	
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.expert.ExpertListView'));
    }
});
