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
    	
    	var userMainView = this.getUserMainView();
    	
    	var userLogo = userMainView.down('#userLogo');
    	userLogo.setHtml("<img src='" + YourTour.util.Context.getImageResource('/resources/images/user_logo_64.png') + "' style='width:64px; height:64px'>");
    },
    
    onExpertTap:function(){
    	this.redirectTo('/expert/apply');
    }
});
