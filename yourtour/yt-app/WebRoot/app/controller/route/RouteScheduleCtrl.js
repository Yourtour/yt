Ext.define('YourTour.controller.route.RouteScheduleCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{
    	   scheduleEditView:'#RouteScheduleEditView',
    	   
       },
       
       control:{
       	   	'#RouteScheduleEditView #save':{
       	   		tap:"onSaveTap"	
       	   	},
       
       	   	'#RouteScheduleEditView #btnDelete':{
       	   		tap:"onDeleteTap"	
       	   	}
       },
       
       routes:{
        	'/route/schedule/:routeId/:index/:command':'showPage',
       },
       
       routeId:null,
       index:null,
       command:null,
       
       schedule:null
    },
    
    init: function(){
    	
    },
    
    showPage:function(routeId, index, command){
    	this.routeId = routeId;
    	this.index = index;
    	this.command = command;
    	
		Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteScheduleEditView'));
		
		var me = this;
		if(command == 'edit'){
			var store = me.getApplication().getController('route.RouteScheduleListCtrl').store;
	    	var route = store.first();
	    	var scheduleStore = route.schedulesStore;
	    	
	    	this.schedule = scheduleStore.getAt(index);
	    	
	    	var view = me.getScheduleEditView();
	    	var title = view.down('#title');
	    	var memo = view.down('#memo');
	    	
	    	title.setHtml(this.schedule.get('title'));
	    	memo.setValue(this.schedule.get('memo'));
		}
    },
    
    /**
     * 
     */
    onSaveTap:function(){
    	var me = this;
    	var view = this.getScheduleEditView();
    	var memo = view.down('#memo');
    	
    	var data = {};
    	if(me.command == 'edit'){
    		data.id = me.schedule.get('id');
    	}
    	
    	data.memo = memo.getValue();
    	
    	try{
	    	Ext.Ajax.request({
	    		url : YourTour.util.Context.getContext('/routes/' + this.routeId + '/schedule/save'),
	    	    method : "POST",
	    	    data:Ext.JSON.encode(data),
	    	    success : function(response) {
	    	    	var respObj = Ext.JSON.decode(response.responseText);
	    	    	if(respObj.errorCode != '0'){
	    	    		Ext.Msg.alert(resp.errorText);
	    	    		return;
	    	    	};
	    	    	
	    	    	//将数据添加到Store
	    	    	if(me.command != 'edit'){
		    	    	data.id = respObj.data;
		    	    	data.type='ProvisionItem';
		    	    	var schedule = Ext.create('YourTour.model.RouteScheduleModel',data);
		    	    	var store = me.getApplication().getController('route.RouteScheduleCtrl').store;
		    	    	var route = store.first();
		    	    	var scheduleStore = route.schedulesStore;
		    	    	scheduleStore.insert(me.index,schedule);
		    	    }else{
		    	    	me.schedule.set('memo',data.memo);
		    	    }
	    	    	
	    	    	Ext.ComponentManager.get('MainView').pop();
	    	    }
	    	});
    	}catch(e){
    		alert('错误' + e.message + '发生在' +   e.lineNumber + '行');
    	}
    },
    
    /**
     * 
     */
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
