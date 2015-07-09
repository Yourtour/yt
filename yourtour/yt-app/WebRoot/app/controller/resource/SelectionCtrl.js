Ext.define('YourTour.controller.resource.SelectionCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{
       		placeselection:'#resourceselectionview #placeselection',
       		typeselection:'#resourceselectionview #typeselection',
       		orderselection:'#resourceselectionview #orderselection'
       },
       
       control:{
       	   
       },
       
       routes:{
        	'/schedule/resource/selection':'showResourceSelectionView'
       }
    },
    
    showResourceSelectionView:function(){
    	this.show('resourceselectionview','YourTour.view.resource.SelectionView');
    	
    	var values = [];
    	values.add({text:'上海', value:'SH'});
    	values.add({text:'北京', value:'BJ'});
    	values.add({text:'重庆', value:'CQ'});
    	var typeStore = Ext.create('Ext.data.Store', {model:'YourTour.model.OptionModel', data:values});
    }
});
