Ext.define('YourTour.controller.route.Plan', {
    extend: 'Ext.app.Controller',
    requires:['YourTour.store.LineStore','YourTour.model.OptionModel','YourTour.model.ScheduleModel'],
    config: {
       refs:{
    	   dvLine:'lineListView',
    	   dvSchedule:'scheduleListView',
    	   panels:'#panels',
    	   btnRecommend:'#btnRecommend',
    	   btnCustomer:'#btnCustomer'
       },
       
       control:{
    	   btnCustomer:{
    		   tap:'onTap4BtnCustomer'
    	   },
    	   
    	   btnRecommend:{
    		   tap:'onTap4BtnRecommend'
    	   },
    	   
    	   "#routeplan #users":{
    		   itemtap:"onUsersItemTap"
    	   },
    	   
    	   '#routeplan #lineListView':{
    		   itemtap:"onLineItemTap"
    	   }
       },
       
       routes:{
        	'/route/plan':'redirectRoutePlanView'
       }
    },
    
    onTap4BtnCustomer:function(){
    	var panel = this.getPanels();
    	panel.setActiveItem(1);
    	
    	var store = Ext.create('YourTour.store.RouteStore');
    	
    	var dvSchedule = this.getDvSchedule();
        store.load(function(){
        	var route = store.getAt(1);
            
        	var places = [];
        	
        	places.push(Ext.create('YourTour.model.OptionModel',{value:'1', text:'上海'}));
        	places.push(Ext.create('YourTour.model.OptionModel',{value:'2', text:'北京'}));
        	
        	var scheduleStore = Ext.create('Ext.data.Store', {model:'YourTour.model.ScheduleModel'});
        	for(var index = 0; index < route.data.period; index++){
            	var schedule = Ext.create('YourTour.model.ScheduleModel',{rowKey:index, date:'第' + index + '天'});
            	schedule.options = places;
            	scheduleStore.add(schedule);
            }
        	scheduleStore.sync();
        	
            dvSchedule.setStore(scheduleStore);
        });
    },
    
    onTap4BtnRecommend:function(){
    	var panel = this.getPanels();
    	panel.setActiveItem(0);
    	
    	var store = Ext.create('YourTour.store.LineStore');
        var showView = function(){
           var dvLine = this.getDvLine();
           dvLine.setStore(store);
        };
        
        store.load(showView,this);
    },
    
    onLineItemTap:function(nestedList, list, index, target,record,e,eOpts){
    },
    
    onUsersItemTap:function(nestedList, list, index, target,record,e,eOpts){
    	
    },
    
    redirectRoutePlanView:function(){
    	Ext.Viewport.setActiveItem('routeplan');
    	this.onTap4BtnRecommend();
    }
});
