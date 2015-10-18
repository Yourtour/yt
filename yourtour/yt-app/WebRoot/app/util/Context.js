Ext.define('YourTour.util.Context', {
	singleton : true,
	alias:'Context',
	
    config : {
    },
    
    constructor : function(config) {
        this.initConfig(config);
    },
    
    getContext:function(url){
    	var s = 'http://localhost:8080/yt-web/rest';
    	if(url.substr(0,1) != '/'){
    		s += '/';
    	}
    	
    	return s + url;
    },
    
    getUserId:function(){
    	return '3333';
    }
});

