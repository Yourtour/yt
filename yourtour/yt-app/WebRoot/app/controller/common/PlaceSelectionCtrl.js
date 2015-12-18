Ext.define('YourTour.controller.common.PlaceSelectionCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.view.common.PlaceListItemView','YourTour.view.widget.XSelecion'],
    config: {
       refs: {
       	   placeType:'#PlaceSelectionView #placeType',
       	   placeList:'#PlaceSelectionView #placeList',
       	   selection:'#PlaceSelectionView #selection',
       	   btnOk:	'#PlaceSelectionView #btnOk',
       },
       
       control	:{
    	   placeType:{
    		   itemtap:'onItemTap4PlaceType'
    	   },
    	   
    	   placeList:{
    		   itemtap:'onItemTap4PlaceList'
    	   },
    	   
    	   btnOk:{
    		   tap:'onTap4BtnOk'
    	   },
    	   
    	   selection:{
    		   itemtap:'onItemTap4Selection'
    	   }
       },
       
       routes:{
        	'/place/selection':'showPage'
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
		Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.common.PlaceSelectionView'));
		
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
    },
    
    /**
     * 
     */
    onItemTap4PlaceList: function(record, e, eOpts){
    	var selection = this.getSelection();
    	
    	var selectionItem = Ext.create('YourTour.view.widget.XSelecion',{selection:selection, model:record});
    	
    	selection.add(selectionItem);
    },
    
    /**
     * 
     */
    onTap4BtnOk:function(){
    	var me = this;
    	var selection = this.getSelection();
    	Ext.ComponentManager.get('MainView').pop();
    	selection.getItems().each(function(item){
    		me.getApplication().getController('route.RouteSchedulePlanCtrl').addPlace(item.model);
    	});
    },
    
    /**
     * 
     */
    onItemTap4Selection:function(item, model){
    	this.getSelection().remove(item);
    }
});
