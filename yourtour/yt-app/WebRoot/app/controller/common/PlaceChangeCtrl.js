Ext.define('YourTour.controller.common.PlaceChangeCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.view.common.PlaceListItemView'],
    config: {
       refs: {
       	   placeType:'#PlaceChangeView #placeType',
       	   placeList:'#PlaceChangeView #placeList'
       },
       
       control	:{
    	   placeType:{
    		   itemtap:'onItemTap4PlaceType'
    	   }
       },
       
       routes:{
        	'/place/change':'showPage'
       },
       
       placeStore : null
    },
    
    init: function() {
        this.placeStore = Ext.create('YourTour.store.PlaceStore');
    },
    
    /**
     * 显示页面
     */
    showPage:function(){
		Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.common.PlaceChangeView'));
		
		var store = Ext.create('Ext.data.Store',{
			data:[
			      {code:'China', name:'国内'},
			      {code:'GAT', name:'港澳台'},
			      {code:'Aisa', name:'亚洲'},
			      {code:'Europe', name:'欧洲'},
			      {code:'Africa', name:'非洲'},
			      {code:'America', name:'美洲'},
			      {code:'Ocean', name:'大洋洲'}
			],
			
			fields:[
			      {name:'code', type:'string'},
			      {name:'name', type:'string'}    
			]
		});
		
		this.getPlaceType().setStore(store);
    },
    
    /**
     * 
     */
    onItemTap4PlaceType: function(obj, index, target, record, e, eOpts){
    	var me = this;
    	
    	var placeList = me.getPlaceList();
    	var proxy = this.placeStore.getProxy();
    	proxy.setUrl(YourTour.util.Context.getContext('/place/' +record.get('code') + '/query'));
    	this.placeStore.setProxy(proxy);
    	
    	var success = function(){
    		me.placeStore.getData().each(function(model){
    			placeList.add(Ext.create('YourTour.view.common.PlaceListItemView',{target:placeList, record:model}));
    		});
    	};
    		
    	this.placeStore.load(success);
    }
});
