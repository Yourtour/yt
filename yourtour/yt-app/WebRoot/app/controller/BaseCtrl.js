Ext.define('YourTour.controller.BaseCtrl', {
    extend: 'Ext.app.Controller',
    config: {
       refs:{
         mainView:'#MainView'
       }
    },
    
    removeAll:function(){
    	
    },

    getValues:function(formview, fields){
        return YourTour.util.Context.getViewFields(formview, fields);
    },

    alert:function(msg) {
        Ext.Msg.alert(msg);
    }
});
