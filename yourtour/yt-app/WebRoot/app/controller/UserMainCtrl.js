Ext.define('YourTour.controller.UserMainCtrl', {
    extend: 'Ext.app.Controller',
    config: {
       refs:{
    	   userMainView:'#UserMainView',
       },
       
       control:{
    	   '#UserMainView #expert':{
    		   tap:'onExpertTap'
    	   }
       },
       
       routes:{
    	   '/main/user':'showPage'
       }
    },
    
    init:function(){
    },
    
    showPage:function(){
    	var me = this;
    	
    	YourTour.util.Context.mainview = me.getUserMainView();		
    },
    
    onExpertTap:function(){
    	this.redirectTo('/expert/apply');
    }
});
