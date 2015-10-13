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
    
    /**
     * 显示页面
     */
    showPage:function(){
		Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.common.PlaceSelectionView'));
		
		var store = Ext.create('Ext.data.Store',{
			data:[
			      {code:'1', name:'国内'},
			      {code:'2', name:'港澳台'},
			      {code:'3', name:'亚洲'},
			      {code:'4', name:'欧洲'},
			      {code:'5', name:'非洲'},
			      {code:'6', name:'美洲'},
			      {code:'7', name:'大洋洲'}
			],
			
			fields:[
			      {name:'code', type:'string'},
			      {name:'name', type:'string'}    
			]
		});
		
		this.getPlaceType().setStore(store);
		
		this.placeStore = Ext.create('YourTour.store.PlaceStore');
    	this.placeStore.load();
    },
    
    /**
     * 
     */
    onItemTap4PlaceType: function(obj, index, target, record, e, eOpts){
    	var placeList = this.getPlaceList();
    	this.placeStore.getData().each(function(model){
    		placeList.add(Ext.create('YourTour.view.common.PlaceListItemView',{target:placeList, record:model}));
    	});
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
    	Ext.ComponentManager.get('MainView').pop();
    },
    
    /**
     * 
     */
    onItemTap4Selection:function(item, model){
    	this.getSelection().remove(item);
    }
});
