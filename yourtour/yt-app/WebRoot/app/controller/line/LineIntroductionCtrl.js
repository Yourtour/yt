Ext.define('YourTour.controller.line.LineIntroductionCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.LineStore'],
    config: {
       refs:{
       	   lineIntro:'#LineIntroductionView',
       	   
       	   resources:'#LineIntroductionView #resources'
       },
       
       control:{
       	   'lineresourceitem[itemId=lineresourceitem]':{
       	   	   	onTap:'showResource'
       	   },
       	   
       	   /**
       	    * 线路推荐列表返回按钮事件定义
       	    * @type 
       	    */
       	   '#LineIntroductionView #toolbar':{
       	   	   	back:'backToLineRecommendView'
       	   },
       	   
       	   resources:{
       	   		itemtap:'onResourceInfoTap'
       	   }
       },
       
       routes:{
        	'/line/introduction/:index':'showIntroduction'
       },
       
       store:null
    },
    
    init: function(){
    	this.store = Ext.create('YourTour.store.LineStore');
    },
    
    onResourceInfoTap:function(dataView, index, target, record, e, eOpts){
    	this.redirectTo('/resource/detail/' + record.get('rowKey'));	
    },
    
    showResource:function(record){
    	console.log(record);
    },
    
    backToLineRecommendView:function(){
    	this.show('LineRecommendView','YourTour.view.line.LineRecommendView');	
    },
    
    /**
     * 显示线路详细信息
     */
    showIntroduction:function(index){
    	var store = this.store;
    	var record = store.getAt(index);
    	this.show('LineIntroductionView','YourTour.view.line.LineIntroductionView');
	
    	var lineIntroView = this.getLineIntro();
    	if(record){
    	   	var imageUrl = lineIntroView.down('#imageUrl');
 	 	   	imageUrl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:150px'>");
 	 	   	
 	 	   	var name = lineIntroView.down('#name');
 	 	   	name.setHtml(record.get('name'));
 	 	   	
 	 	   	var feature = lineIntroView.down('#feature');
 	 	   	feature.setHtml(record.get('feature'));
 	 	   	
 	 	   	var reason = lineIntroView.down('#reason');
 	 	   	reason.setHtml(record.get('reason'));
			
 	 	   	var resources = lineIntroView.down('#resources');
 	 	   	var resModels = [];
 	 	   	record.resources().each(function(resource){
 	 	   		resModels.push(resource.data);
 	 	   	});
 	 	   	
 	 	   	var resStore = Ext.create('Ext.data.Store',{model:'YourTour.model.ResourceModel', data:resModels});
 	 	   	resources.setStore(resStore);
 	 	   	
 	 	   	var items = resources.getViewItems();
 	 	   	resources.setHeight(Ext.get(items[0].getId()).getHeight() * items.length);
    	}
    }
});