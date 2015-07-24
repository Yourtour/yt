Ext.define('YourTour.controller.resource.SelectionCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{
       		placeselection:'#SelectionListView #placeselection',
       		typeselection:'#SelectionListView #typeselection',
       		orderselection:'#SelectionListView #orderselection',
       		
       		selectionList:'#SelectionListView #selectionList'
       },
       
       control:{
       	   
       },
       
       routes:{
        	'/schedule/resource/selection':'showResourceSelectionView'
       },
       
       store:null
    },
    
    init: function(){
    	this.store = Ext.create('YourTour.store.ResourceStore');
    },
    
    showResourceSelectionView:function(){
    	this.show('SelectionListView','YourTour.view.resource.SelectionListView');
    	
    	/*var values = [];
    	values.add({text:'上海', value:'SH'});
    	values.add({text:'北京', value:'BJ'});
    	values.add({text:'重庆', value:'CQ'});
    	var typeStore = Ext.create('Ext.data.Store', {model:'YourTour.model.OptionModel', data:values});*/
    	
    	this.getSelectionList().setStore(this.store);
    }
});
