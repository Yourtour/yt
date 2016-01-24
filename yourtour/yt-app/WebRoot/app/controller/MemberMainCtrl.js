Ext.define('YourTour.controller.MemberMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{
    	   menuTab:'#MemberMainView #menuTab',
    	   memberList:'#MemberMainView #members',
    	   managementBtn:'#MemberMainView #management',
    	   finishedBtn:'#MemberMainView #finished',
    	   memberSearchList:'#MemberSearchView #memberSearchList'
       },
       
       control:{
    	   menuTab:{
     		   activeitemchange:'onActiveItemChange'
     	   },
     	   
     	  memberSearchList:{
			  itemtap:'onSearchListItemtap'
     	  },
     	  
     	  '#MemberMainView #management':{
     		 tap:'onManagementTap'
     	  },
     	  
     	 '#MemberMainView #finished':{
    		  tap:'onFinishTap'
    	  },
    	  
     	  '#MemberAddView #barscanner':{
     		  tap:'onBarScanner'
     	  },
     	  
     	 '#MemberAddView #btnSearch':{
    		  tap:'onSearch'
    	  },

		 '#MemberSearchView #btnAddAsExpert':{
			 tap:'onAddAsExpertTap'
		  },

		   '#MemberSearchView #btnAddAsMember':{
			   tap:'onAddAsMemberTap'
		   },

		   '#MemberSearchView #btnAddAsLeader':{
			   tap:'onAddAsLeaderTap'
		   },
    	  memberList:{
    		  itemdelete:'onItemDelete'
    	  }
       },
       
       routes:{
        	'/routes/:routeId/members':'showPage'
       },
       
       routeId:null,
       
       searchStore : null,
       
       memberStore : null,

	   selectedMember:null
    },
    
    init:function(){
    	this.searchStore = Ext.create('YourTour.store.UserStore');
    	
    	this.memberStore = Ext.create('YourTour.store.RouteMemberStore'); 
    },
    
    showPage:function(routeId){
    	var me = this;
    	
    	me.routeId = routeId;
    	
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.member.MemberMainView'));
    	
    	var store = me.memberStore;
    	store.setData('');
    	store.getProxy().setUrl(YourTour.util.Context.getContext('/route/members/' + me.routeId + '/query'));
    	store.load(function(){
    		me.getMemberList().setStore(store);
    	});
    },
    
    onActiveItemChange:function(tabBar, newTab, oldTab){
    	if(newTab.getItemId() == 'btnMessage'){
    		this.showMessagePage();
    	}else if(newTab.getItemId() == 'btnPosition'){
    		this.showPositionPage();
    	}else if(newTab.getItemId() == 'btnAdd'){
    		this.showAddPage();
    	}
    },
    
    showAddPage:function(){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.member.MemberAddView'));
    },
    
    showMessagePage:function(){
    	var sessionId = '1111';
    	this.redirectTo('/message/session/' + sessionId);
    },
    
    /**
     * 
     */
    showPositionPage:function(){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.member.MemberPositionView'));
    	
    	var map = new BMap.Map('map');//指向map的容器
        map.centerAndZoom(new BMap.Point(121.491, 31.233), 11);
        window.setTimeout(function(){  
        		map.panTo(new BMap.Point(116.409, 39.918));
        	}, 
        2000);
    },
    
    /**
     * 
     */
    onBarScanner:function(){
    	cordova.plugins.barcodeScanner.scan(  
	      function (result) {  
	          alert("We got a barcode\n" +  
	                "Result: " + result.text + "\n" +  
	                "Format: " + result.format + "\n" +  
	                "Cancelled: " + result.cancelled);  
	      },   
	      function (error) {  
	          alert("Scanning failed: " + error);  
	      }  
    	);  
    },
    
    /**
     * 
     */
    onSearch:function(){
    	var me = this;
    	
    	var store = me.searchStore;
    	store.setData('');
    	store.getProxy().setUrl(YourTour.util.Context.getContext('/users/query'));
    	
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.member.MemberSearchView'));
    	var memberList = me.getMemberSearchList();
    	
    	var onLoaded=function(){
    		memberList.setStore(store);
    	};
 	   	store.load(onLoaded,this);
    },

	onSearchListItemtap:function(dataview, index, item, record,e){
		this.selectedMember = record;
	},

	onAddAsMemberTap:function(){
		this.onMemberAdd('MEMBER');
	},

	onAddAsLeaderTap:function(){
		this.onMemberAdd('LEADER');
	},

	onAddAsExpertTap:function(){
		this.onMemberAdd('EXPERT');
	},

    /**
     * 
     */
    onMemberAdd:function(role){
    	var data = {};

		var record = this.selectedMember;

    	data.routeId = this.routeId;
    	data.userId = record.get('id');
    	data.role = role;

    	Ext.Ajax.request({
    	    url : YourTour.util.Context.getContext('route/members/save'),
    	    method : "POST",
    	    params : Ext.JSON.encode(data),
    	    success : function(response) {
    	    	var respObj = Ext.JSON.decode(response.responseText);
    	    	if(respObj.errorCode != '0'){
    	    		Ext.Msg.alert(resp.errorText);
    	    		return;
    	    	};
    	    	
    	    	Ext.ComponentManager.get('MainView').pop();
    	    },
    	    
    	    failure : function(response) {
    	        var respObj = Ext.JSON.decode(response.responseText);
    	        Ext.Msg.alert("Error", respObj.status.statusMessage);
    	    }
    	});
    },
    
    onManagementTap:function(){
    	var list = this.getMemberList();
    	var len = list.getStore().getData().length ;
    	var item, imageDel, imageRole;
    	for(var index = 0; index < len; index++){
    		item = list.getItemAt(index);
    		
    		imageDel = item.down('#imageDel');
    		imageDel.show();
    		
    		imageRole = item.down('#imageRole');
    		imageRole.hide();
    	}
    	
    	this.getManagementBtn().hide();
    	this.getFinishedBtn().show();
    },
    
    onFinishTap:function(){
    	var list = this.getMemberList();
    	var len = list.getStore().getData().length ;
    	var item, imageDel, imageRole;
    	for(var index = 0; index < len; index++){
    		item = list.getItemAt(index);
    		
    		imageDel = item.down('#imageDel');
    		imageDel.hide();
    		
    		imageRole = item.down('#imageRole');
    		imageRole.show();
    	}
    	
    	this.getManagementBtn().show();
    	this.getFinishedBtn().hide();
    },
    
    onItemDelete:function(dataview, record){
    	var list = this.getMemberList();
    	var store = list.getStore();
    	var index = store.indexOf(record);
    	
    	var data = {};
    	data.routeId = this.routeId;
    	data.userId = record.get('id');
    	
    	Ext.Ajax.request({
    	    url : YourTour.util.Context.getContext('route/members/' + this.routeId + '/' + record.get('id') + '/delete'),
    	    method : "GET",
    	    success : function(response) {
    	    	var respObj = Ext.JSON.decode(response.responseText);
    	    	if(respObj.errorCode != '0'){
    	    		Ext.Msg.alert(respObj.errorText);
    	    		return;
    	    	};
    	    	
    	    	store.removeAt(index);
    	    },
    	    
    	    failure : function(response) {
    	        var respObj = Ext.JSON.decode(response.responseText);
    	        Ext.Msg.alert("Error", respObj.status.statusMessage);
    	    }
    	});
    }
});
