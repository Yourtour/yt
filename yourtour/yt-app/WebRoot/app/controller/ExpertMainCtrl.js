Ext.define('YourTour.controller.ExpertMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{
    	   expertMainView:'#ExpertMainView',
           expertRouteList:'#ExpertMainView #routeList',

           expertApplyView:'#ExpertApplyView',
           expertCarousel:'#ExpertApplyView #expertCarousel',
           expertServiceList:'#ExpertApplyView #ServiceList',

           expertServiceEditView:'#ExpertServiceEditView'
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

        var store = Ext.create('YourTour.store.AjaxStore', {model:'YourTour.model.ExpertServiceModel'});
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

                me.getExpertServiceList().getStore().add(Ext.create('YourTour.model.ExpertServiceModel', data));

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

    showIntroPage:function(userId){
    	var me = this;
    	
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.expert.ExpertIntroView'));
    },
    
    showListPage:function(placeId){
    	var me = this;
    	
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.expert.ExpertListView'));
    }
});
