Ext.define('YourTour.controller.MemberCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{
    	   menuTab:'#MemberMainView #menuTab',
    	   
    	   memberSearchList:'#MemberSearchView #memberSearchList',
       },
       
       control:{
    	   menuTab:{
     		   activeitemchange:'onActiveItemChange'
     	   },
     	   
     	  '#MemberAddView #barscanner':{
     		  tap:'onBarScanner'
     	  },
     	  
     	 '#MemberAddView #btnSearch':{
    		  tap:'onSearch'
    	  }
       },
       
       routes:{
        	'/routes/:routeId/members':'showPage'
       },
       
       routeId:null,
       
       searchStore : null
    },
    
    init:function(){
    	this.searchStore = Ext.create('YourTour.store.UserStore');
    },
    
    showPage:function(routeId){
    	this.routeId = routeId;
    	
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.member.MemberMainView'));
    },
    
    onActiveItemChange:function(tabBar, newTab, oldTab){
    	if(newTab.getItemId() == 'btnMessage'){
    		
    	}else if(newTab.getItemId() == 'btnPosition'){
    		this.showPositionPage();
    	}else if(newTab.getItemId() == 'btnAdd'){
    		this.showAddPage();
    	}
    },
    
    showAddPage:function(){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.member.MemberAddView'));
    },
    
    showPositionPage:function(){
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.member.MemberPositionView'));
    	
    	var map = new BMap.Map('map');//指向map的容器
        map.centerAndZoom(new BMap.Point(121.491, 31.233), 11);
        window.setTimeout(function(){  
        		map.panTo(new BMap.Point(116.409, 39.918));
        	}, 
        2000);
    },
    
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
    }
});
