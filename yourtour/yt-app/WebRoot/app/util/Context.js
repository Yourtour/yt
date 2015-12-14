Ext.define('YourTour.util.Context', {
	singleton : true,
	alias:'Context',
	
    config : {
    	userKey:'user',
    	
    	server:'172.16.1.191'
    },
    
    constructor : function(config) {
        this.initConfig(config);
    },
    
    getContext:function(url){
    	var s = 'http://' + this.getServer() + ':8080/yt-web/rest';
    	if(url.substr(0,1) != '/'){
    		s += '/';
    	}
    	
    	return s + url;
    },
    
    getImageResource:function(url){
    	var s = 'http://' + this.getServer() + ':8080/yt-app';
    	if(url == null || url == '') return s;
    	
    	if(url.substr(0,1) != '/'){
    		s += '/';
    	}
    	
    	return s + url;
    }
    
    
});

