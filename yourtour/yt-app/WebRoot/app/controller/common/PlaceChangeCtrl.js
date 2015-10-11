Ext.define('YourTour.controller.common.PlaceChangeCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    
    config: {
       refs: {
       	   placeType:'#PlaceChangeView #placeType',
       	   placeList:'#PlaceChangeView #placeList'
       },
       
       control	:{
    	   placeType:{
    		   itemtap:'onItemTap4PlaceType'
    	   },
    	   
    	   placeList:{
    		   itemtap:'onItemTap4PlaceList'
    	   }
       },
       
       routes:{
        	'/place/change':'showPage'
       },
       
       placeStore : null
    },
    
    init:function(){
    },
    
    showPage:function(){
		Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.common.PlaceChangeView'));
		
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
    
    onItemTap4PlaceType: function(obj, index, target, record, e, eOpts){
    	this.getPlaceList().setStore(this.placeStore);
    },
    
    onItemTap4PlaceList: function(obj, index, target, record, e, eOpts){
    	console.log(target);
    }
});
