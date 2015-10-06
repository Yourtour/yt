Ext.define('YourTour.controller.home.AlongMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
       refs:{
    	   alongList:'#AlongListView #alongList',
    	   alongDetailView:'#AlongDetailView',
    	   commentList:'#AlongDetailView #commentList'	   
       },
       
       control:{
    	   alongList:{
    		   itemtap:'onItemTap'
    	   },
    	   
    	   commentList:{
    		   refresh:'onRefresh'
    	   }
       },
       
       routes:{
       		'/home/along/list':'showListView'
       },
      
       store : null,
    },
    
    init: function(){
    	this.store = Ext.create('YourTour.store.AlongListStore'); 
    },
    
    showListView:function(){
		Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.home.AlongListView'));
		
		var me = this;
		var store = this.store;
		var success = function(){
			me.getAlongList().setStore(store);
		};
		
		store.load(success, this);
    },
    
    onItemTap:function(dataview, index, item, record,e){
		Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.home.AlongDetailView'));
		
		if(record){
			var alongDetailView = this.getAlongDetailView();
			
			var imageUrlEl = alongDetailView.down('#imageUrl');
	 	   	imageUrlEl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:100px'>");
	 	   
	 	   	var titleEl = alongDetailView.down('#title');
	 	   	titleEl.setHtml(record.get('title'));
	 	   
	 	   	var nickNameEl = alongDetailView.down('#nickName');
	 	   	nickNameEl.setHtml(record.get('nickName'));
	 	   
	 	   	var publishTimeEl = alongDetailView.down('#publishTime');
	 	   	publishTimeEl.setHtml(record.get('publishTime'));
	 	   
	 	   	var intentionEl = alongDetailView.down('#intention');
	 	   	intentionEl.setHtml(record.get('intention'));
	 	   
	 	   	var routeDateEl = alongDetailView.down('#routeDate');
	 	   	routeDateEl.setHtml(record.get('startDate') + '到' + record.get('endDate'));
	 	   
	 	   	var lineNameEl = alongDetailView.down('#lineName');
	 	   	lineNameEl.setHtml(record.get('lineName'));
	 	   
	 	   	var memoEl = alongDetailView.down('#memo');
	 	   	memoEl.setHtml(record.get('memo'));
	 	   
	 	   	var addressEl = alongDetailView.down('#address');
	 	   	addressEl.setHtml(record.get('address'));
	 	   
	 	   	var readNumEl = alongDetailView.down('#readNum');
	 	   	readNumEl.setHtml(record.get('readNum'));
	 	   
	 	   	var commentNumEl = alongDetailView.down('#commentNum');
	 	   	commentNumEl.setHtml(record.get('commentNum'));
	 	   	
	 	   	var commentEl = alongDetailView.down('#commentList');
	 	   	commentEl.setStore(record.commentsStore);
		}
    },
    
    onRefresh:function(){
    	console.log(this.getAlongDetailView().down('#commentList').getViewItems());
    }
});
