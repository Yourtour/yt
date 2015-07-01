Ext.define('YourTour.util.Context', {
	singleton : true,
	alias:'Context',
	requires:['YourTour.model.RouteModel'],
	
    config : {
    	
    },
    
    constructor : function(config) {
        this.initConfig(config);
    },
    
    getContext:function(url){
    	var s;
    	if(url.substr(0,1) == '/'){
    		s = 'http://172.13.0.168:8080/yt-web' + url;
    	}else{
    		s = 'http://172.13.0.168:8080/yt-web/' + url;
    	}
    	
    	return s;
    },
    
    getUserId:function(){
    	return '3333';
    }
});
