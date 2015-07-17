Ext.define('YourTour.controller.resource.ResourceCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.ResourceStore'],
    config: {
       refs:{
       		sceneResourceDetailView:'SceneResourceDetailView'
       },
       
       control:{
       	   
       },
       
       routes:{
        	'/resource/detail/:resourceId':'showResourceDetailView'
       },
       
       store:null
    },
    
    init: function(){
    	this.store = Ext.create('YourTour.store.ResourceStore');
    },
    
    showResourceDetailView:function(){
    	this.show('SceneResourceDetailView','YourTour.view.resource.SceneResourceDetailView');
    	
    	var store = this.store;
    	var record = store.getAt(0);
    	
    	var sceneResourceDetailView = this.getSceneResourceDetailView();
    	console.log(record);
    	if(record){
    	   	var imageUrl = sceneResourceDetailView.down('#imageUrl');
 	 	   	imageUrl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:150px'>");
 	 	   	
 	 	   	var name = sceneResourceDetailView.down('#name');
 	 	   	name.setHtml(record.get('name'));
 	 	   	
 	 	   	var address = sceneResourceDetailView.down('#address');
 	 	   	address.setHtml(record.get('address'));
 	 	   	
 	 	   	var phone = sceneResourceDetailView.down('#phone');
 	 	   	phone.setHtml(record.get('phone'));
 	 	   	
 	 	   	var intro = sceneResourceDetailView.down('#intro');
 	 	   	intro.setHtml(record.get('intro'));
 	 	   	
 	 	   	var open = sceneResourceDetailView.down('#open');
 	 	   	open.setHtml(record.get('open'));
 	 	   	
 	 	   	var price = sceneResourceDetailView.down('#price');
 	 	   	price.setHtml(record.get('price'));
    	}
    }
});
