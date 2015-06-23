Ext.define('YourTour.util.Context', {
	singleton : true,
	alias:'Context',
    config : {
    },
    
    constructor : function(config) {
        this.initConfig(config);
    },
    
    getContext:function(url){
    	if(url.substr(0,1) == '/'){
    		return 'http://localhost:8080/yt-web' + url;
    	}else{
    		return 'http://localhost:8080/yt-web/' + url;
    	}
    },
    
    getUserId:function(){
    	return '3333';
    }
});

