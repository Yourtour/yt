Ext.define('YourTour.controller.line.IntroductionCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.LineStore'],
    config: {
       refs:{
       	   lines:'#linerecommendview #lines',
    	   lineIntro:'#lineintroview'
       },
       
       control:{
       	   'lineresourceitem[itemId=lineresourceitem]':{
       	   	   onTap:'showResource'
       	   },
       	   
       	   /**
       	    * 线路推荐列表返回按钮事件定义
       	    * @type 
       	    */
       	   '#lineintroview #close':{
       	   	   tap:'backToLineRecommendView'
       	   }
       },
       
       routes:{
        	'/line/introduction/:index':'showIntroduction'
       }
    },
    
    init: function(){
    },
    
    showResource:function(record){
    	console.log(record);
    },
    
    /**
     * 显示线路详细信息
     */
    showIntroduction:function(index){
    	var store = this.getLines().getStore();
    	var record = store.getAt(index);
    	
    	this.show('lineintroview','YourTour.view.line.IntroductionView');
	
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
			
 	 	   	var scenes = this.getLineIntro().down('#scenes');
 	 	   	var resources = [];
 	 	   	record.resources().each(function(resource){
 	 	   		resources.push(resource.data);
 	 	   	});
 	 	   	
 	 	   	var sceneStore = Ext.create('Ext.data.Store',{model:'YourTour.model.ResourceModel', data:resources});
 	 	   	scenes.setStore(sceneStore);
 	 	   	
 	 	   	var scenes = lineIntroView.down('#scenes');
 	 	   	var items = scenes.getViewItems();
 	 	   	scenes.setHeight(Ext.get(items[0].getId()).getHeight() * items.length);
    	}
    }
});
