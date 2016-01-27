Ext.define('YourTour.controller.route.RouteMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    requires:['YourTour.store.RouteStore','YourTour.view.route.RouteMainItem'],
    
    config: {
       refs: {
       	    routeMainView:'#RouteMainView',
        	routeCarousel:'#RouteMainView #routeCarousel',
       		newRoute:'#RouteMainView #new',
       		deleteRoute:'#RouteMainView #delete',
       		
       		routeImpressionView:'#RouteImpressionView',
       			
       		routeImageView:'#RouteImageView'
       },
       
       control:{
    	   newRoute:{
    		   	tap:'onRouteNew'
    	   },
    	   
    	   deleteRoute:{
   		   		tap:'onRouteDelete'
    	   },
    	   
    	   routeCarousel:{
			   activeitemchange: 'onActiveItemChange',
			   tap:'onCarouselItemTap'
    	   },
    	   
    	   '#RouteImpressionView #save':{
    		   tap:'onImpressionSave'
    	   },
    	   
    	   '#RouteImageView #save':{
    		   tap:'onImageSave'
    	   },

		   '#RouteMainView #btnRoute':{
			   tap:'onRouteTap'
		   },

		   '#RouteMainView #btnMember':{
			   tap:'onRouteMemberTap'
		   },

		   '#RouteMainView #btnCharge':{
			   tap:'onRouteChargeTap'
		   },

		   '#RouteMainView #impression':{
			   tap:'onImpressionEdit'
		   }
       },
       
       routes:{
        	'/main/route':'showPage'
       },
       
       store:null
    },
    
    showPage:function(){
    	var me = this;
    	
    	/*YourTour.util.Context.mainview = me.getRouteMainView();*/

    	var routeCarousel = me.getRouteCarousel();
    	var store = me.store = Ext.create('YourTour.store.RouteStore',{storeId:'RouteMainStore'});	
    	var handler = function(){
			if(routeCarousel) {
				routeCarousel.removeAll(true, false);

				store.each(function (item) {
					var routePanel = Ext.create('YourTour.view.route.RouteMainItem', {
						carousel: routeCarousel,
						record: item
					});
					routeCarousel.add(routePanel);
				});

				routeCarousel.setActiveItem(0);
			}
    	};

    	var proxy = store.getProxy();
    	proxy.setUrl(YourTour.util.Context.getContext('/routes/personal/query'));
    	store.load(handler, this);
    },
    
    onRouteNew:function(){
		var controller = this.getApplication().getController('route.RouteSchedulePlanCtrl')
		controller.createNewRoute();
    },
    
    /**
     * 
     */
    onRouteDelete:function(){
    	var me = this;
    	var routeCarousel = me.getRouteCarousel();
    	var index = routeCarousel.getActiveIndex();
    	
    	var model = me.store.getAt(index);
    	
    	Ext.Ajax.request({
			confirm:'是否要删除当前行程?',
    		url : YourTour.util.Context.getContext('/routes/' + model.get('id') + '/delete'),
    	    method : "GET",
    	    success : function(response) {
    	    	var respObj = Ext.JSON.decode(response.responseText);
    	    	if(respObj.errorCode != '0'){
    	    		Ext.Msg.alert(resp.errorText);
    	    		return;
    	    	};
    	    	
    	    	Ext.Msg.alert('删除成功。');
    	    	me.store.removeAt(index);
    	    	routeCarousel.removeAt(index);
    	    }
    	});
    },
    
    onRouteTap:function(record){
		var me = this;
		var routeCarousel = me.getRouteCarousel();
		var index = routeCarousel.getActiveIndex();
		var record = me.store.getAt(index);

		this.redirectTo('/route/load/' + record.get('id'));
    },
    
    onRouteMemberTap:function(record){
		var me = this;
		var routeCarousel = me.getRouteCarousel();
		var index = routeCarousel.getActiveIndex();
		var record = me.store.getAt(index);

		this.redirectTo('/routes/' + record.get('id') + '/members');
    },

	onRouteChargeTap:function(record){
		var me = this, routeCarousel = me.getRouteCarousel();

		var index = routeCarousel.getActiveIndex();
		var record = me.store.getAt(index);

		var controller = me.getApplication().getController('ChargeMainCtrl');
		controller.showPage(record.get('id'));
	},

	onActiveItemChange: function (carousel, value, oldValue, eOpts) {
		var index = carousel.getActiveIndex();
		var record = this.store.getAt(index);

		var view = this.getRouteMainView();
		view.setData(record);
	},

	onCarouselItemTap:function(){
		var index = this.getRouteCarousel().getActiveIndex();
		var route = this.store.getAt(index);

    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteImageView'));
    	
    	this.getPhoto(navigator.camera.PictureSourceType.PHOTOLIBRARY);
    },
    
    getPhoto: function(source) {
        var me = this;

        navigator.camera.getPicture(me.success, me.failure, {
            quality: 50,
            destinationType: navigator.camera.DestinationType.FILE_URI,
            sourceType: source 
        });
    },
    
    success: function(image_uri) {
    	alert(image_uri);
    	
    	var view = this.getRegisterProfileView();
        var img = view.down('#portrait');
        img.setSrc(image_uri);
    },

    failure: function(message) {
    },
    
    /**
     * 
     */
    onImpressionEdit:function(route){
    	var me = this;
    	Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.route.RouteImpressionView'));
    	
    	var impressionView = me.getRouteImpressionView();
    	var impression = impressionView.down('#impression');
    	
    	impression.setValue(route.get('impression'));
    },
    
    /**
     * 
     */
    onImpressionSave:function(){
    	var me = this;
    	
    	var data = {};
    	data.attr = 'impression';
    	
    	var impressionView = me.getRouteImpressionView();
    	var impression = impressionView.down('#impression');
    	data.attrValue = impression.getValue();
    	
    	var routeCarousel = me.getRouteCarousel();
    	var index = routeCarousel.getActiveIndex();
    	var model = me.store.getAt(index);
    	data.routeId = model.get('id');
    	
    	var userId =  me.getApplication().getUserId();
    	data.userId = userId;
    	
    	Ext.Ajax.request({
    		url : YourTour.util.Context.getContext('/route/members/setting/save'),
    	    method : "POST",
    	    data:Ext.JSON.encode(data),
    	    success : function(response) {
    	    	var respObj = Ext.JSON.decode(response.responseText);
    	    	if(respObj.errorCode != '0'){
    	    		Ext.Msg.alert(respObj.errorText);
    	    		return;
    	    	};
    	    	
    	    	Ext.ComponentManager.get('MainView').pop();
    	    }
    	});
    },
    
    onImageSave:function(){
    	var routeImageView = this.getRouteImageView();
    	
    	var imageSize = routeImageView.down('#imageSize');
    },

	onShareTap:function(){
		window.plugins.socialsharing.share(null, null, 'http://www.baidu.com/img/bdlogo.gif', null);
	}
});
