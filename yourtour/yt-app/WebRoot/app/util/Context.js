Ext.define('YourTour.util.Context', {
	singleton : true,
	alias:'Context',
	
    config : {
    	userKey:'user'
    },
    
    constructor : function(config) {
        this.initConfig(config);
    },
    
    getContext:function(url){
    	var s = 'http://192.168.2.102:8080/yt-web/rest';
    	if(url.substr(0,1) != '/'){
    		s += '/';
    	}
    	
    	return s + url;
    },
    
    getUserId:function(){
    	return '3333';
    }
});

