Ext.define('YourTour.controller.LineCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.LineStore','YourTour.view.line.RecommedListItem'],
    config: {
       refs:{
    	   lineCarousel:'#linerecommendview #lineCarousel',
    	   lineIntro:'#lineintroview'
       },
       
       control:{
       	   lineCarousel:{
       	   		tap:'showLineIntro'	
       	   },
       	   
       	   /**
       	    * 线路推荐列表返回按钮事件定义
       	    * @type 
       	    */
       	   '#linerecommendview #close':{
       	   	   tap:'backToNewRouteView'
       	   },
       	   
       	   /**
       	    * 线路推荐列表返回按钮事件定义
       	    * @type 
       	    */
       	   '#lineintroview #close':{
       	   	   tap:'backToLineRecommendView'
       	   },
       	   
       	   /**
       	    * 线路推荐列表详情按钮事件定义
       	    * @type 
       	    */
       	   '#linerecommendview #intro':{
       	   	   tap:'showLineIntro'
       	   },
       	   
       	   
    	   "#linerecommendview #expertListItem":{
    		   itemtap:"onUsersItemTap"
    	   },
    	   
    	   '#routeplan #lineListView':{
    		   itemtap:"onLineItemTap"
    	   }
       },
       
       routes:{
        	'/line/recommend':'showLineRecommendView'
       },
       
       store : undefined
    },
    
    init: function(){
    	this.store = Ext.create('YourTour.store.LineStore');	
    },
    
    /**
     * 显示线路详细信息
     */
    showLineIntro:function(){
    	this.show('lineintroview','YourTour.view.line.IntroductionView');
	
    	var index = this.getLineCarousel().getActiveIndex();
    	var record = this.store.getAt(index);
    	
    	
    	if(record){
    	   	var imageUrl = this.getLineIntro().down('#imageUrl');
 	 	   	imageUrl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:150px'>");
 	 	   	
 	 	   	var name = this.getLineIntro().down('#name');
 	 	   	name.setHtml(record.get('name'));
 	 	   	
 	 	   	var feature = this.getLineIntro().down('#feature');
 	 	   	feature.setHtml(record.get('feature'));
 	 	   	
 	 	   	var reason = this.getLineIntro().down('#reason');
 	 	   	reason.setHtml(record.get('reason'));
    	}
    },
    
    
    backToLineRecommendView:function(){
    	this.show('linerecommendview','YourTour.view.line.RecommendView');
    },
    
    backToNewRouteView:function(){
    	this.show('newrouteview','YourTour.view.route.NewView');
    },
    
    onUsersItemTap:function(){
    	console.log('onUsersItemTap');	
    },
    
    showLineRecommendView:function(){
    	this.show('linerecommendview','YourTour.view.line.RecommendView');
    	
    	var store = this.store;
    	
    	var lineCarousel = this.getLineCarousel();
        var showView = function(){
        	lineCarousel.removeAll(true,false);
        	
        	store.each(function(record){
        		var item = Ext.create("YourTour.view.line.RecommedListItem",{data:record});
        		lineCarousel.add(item);
        	});
        };
        
        store.load(showView,this);
    }
});
