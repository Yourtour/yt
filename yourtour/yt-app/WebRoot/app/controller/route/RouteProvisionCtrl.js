Ext.define('YourTour.controller.route.RouteProvisionCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{
    	   routeProvisionView:'#RouteProvisionView',
    	   
       },
       
       control:{
       	   	'#RouteProvisionView #save':{
       	   		tap:"onSaveTap"	
       	   	},
       
       	   	'#RouteProvisionView #btnDelete':{
       	   		tap:"onDeleteTap"	
       	   	}
       },
       
       routes:{
        	'/route/provision/:routeId/:index/:command':'showPage',
       },
       
       routeId:null,
       index:null,
       command:null,
       
       provision:null
    },
    
    init: function(){
    	
    },
    
    showPage:function(routeId, index, command){
    	this.routeId = routeId;
    	this.index = index;
    	this.command = command;
    	
		Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteProvisionView'));
		
		var me = this;
		if(command == 'edit'){
			var store = me.getApplication().getController('route.RouteScheduleCtrl').store;
	    	var route = store.first();
	    	var scheduleStore = route.schedulesStore;
	    	
	    	this.provision = scheduleStore.getAt(index);
	    	var view = me.getRouteProvisionView();
	    	var title = view.down('#title');
	    	var memo = view.down('#memo');
	    	
	    	title.setValue(this.provision.get('title'));
	    	memo.setValue(this.provision.get('memo'));
		}
    },
    
    onSaveTap:function(){
    	var me = this;
    	var view = this.getRouteProvisionView();
    	var title = view.down('#title');
    	var memo = view.down('#memo');
    	
    	var data = {};
    	if(me.command == 'edit'){
    		data.id = me.provision.get('id');
    	}
    	
    	data.routeId = this.routeId;
    	data.title = title.getValue();
    	data.memo = memo.getValue();
    	
    	Ext.Ajax.request({
    	    url : YourTour.util.Context.getContext('/routes/provision/save'),
    	    method : "POST",
    	    params : Ext.JSON.encode(data),
    	    success : function(response) {
    	    	var respObj = Ext.JSON.decode(response.responseText);
    	    	if(respObj.errorCode != '0'){
    	    		Ext.Msg.alert(resp.errorText);
    	    		return;
    	    	};
    	    	
    	    	//将数据添加到Store
    	    	if(me.command != 'edit'){
	    	    	data.id = respObj.data;
	    	    	var schedule = Ext.create('YourTour.model.RouteScheduleModel',data);
	    	    	var store = me.getApplication().getController('route.RouteScheduleCtrl').store;
	    	    	var route = store.first();
	    	    	var scheduleStore = route.schedulesStore;
	    	    	scheduleStore.insert(me.index,schedule);
	    	    }else{
	    	    	me.provision.set('title',data.title);
	    	    	me.provision.set('memo',data.memo);
	    	    }
    	    	
    	    	Ext.ComponentManager.get('MainView').pop();
    	    },
    	    
    	    failure : function(response) {
    	        var respObj = Ext.JSON.decode(response.responseText);
    	        Ext.Msg.alert("Error", respObj.status.statusMessage);
    	    }
    	});
    },
    
    onDeleteTap:function(){
    	var me = this;
    	Ext.Ajax.request({
    	    url : YourTour.util.Context.getContext('/routes/provision/' + me.provision.get('id') + '/delete'),
    	    method : "GET",
    	    success : function(response) {
    	    	var respObj = Ext.JSON.decode(response.responseText);
    	    	if(respObj.errorCode != '0'){
    	    		Ext.Msg.alert(resp.errorText);
    	    		return;
    	    	};
    	    	
    	    	var store = me.getApplication().getController('route.RouteScheduleCtrl').store;
	    	    var route = store.first();
	    	    var scheduleStore = route.schedulesStore;
	    	    scheduleStore.remove(me.provision);
    	    	
    	    	Ext.ComponentManager.get('MainView').pop();
    	    },
    	    
    	    failure : function(response) {
    	        var respObj = Ext.JSON.decode(response.responseText);
    	        Ext.Msg.alert("Error", respObj.status.statusMessage);
    	    }
    	});
    }
});
