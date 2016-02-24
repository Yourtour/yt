Ext.define('YourTour.util.Context', {
	singleton : true,
	alias:'Context',
	alternateClassName: 'YourTour.Context',
	required:['YourTour.view.widget.XImageSelect'],
    config : {
    	userKey:'user',

		//server:'192.168.1.174:8080'
    	//server:'192.168.2.102:8080'
		server:'120.55.76.201:8080',
		//server:'localhost:8080',

		application:null
    },
    
    constructor : function(config) {
        this.initConfig(config);
    },

	getRemoteServer:function(){
		return this.server || this.getServer();
	},

    getContext:function(url){
    	var s = 'http://' + this.getServer() + '/yt-web/rest';
    	if(url.substr(0,1) != '/'){
    		s += '/';
    	}

    	return s + url;
    },

	getUserId:function(){
		var localStore =  Ext.StoreManager.get('LocalStore');
		localStore.load();

		var index = localStore.find('key', 'user.profile');
		if(index >= 0){
			var userProfile = localStore.getAt(index);
			var profile = Ext.JSON.decode(userProfile.get('value'));
			return profile.id;
		}

		return '';
	},

	/**
	 * 获取图片资源
	 * @param url
	 * @param size
	 * @returns {string}
	 */
    getImageResource:function(url, size){
		if(size){
			var index = url.lastIndexOf('.');
			url = url.substr(0, index) + '_' + size + url.substr(index);
		}

    	var s = 'http://' + this.getServer() + '/yt-web';
    	if(url == null || url == '') return s;

    	if(url.substr(0,1) != '/'){
    		s += '/';
    	}

    	return s + url;
    },

	fillViewFields:function(view, record){
		var elements = Ext.ComponentQuery.query('xfield,xmultifield,xselectfield, ximage,xbutton,xscore,xtextarea,xtextfield, xuserlogo', view);
		var updatable = true;
		Ext.Array.forEach(elements,function(item){
			updatable = true;
			if(item instanceof YourTour.view.widget.XDataView){
				updatable = item.isUpdatable;
			}

			if(updatable)
				item.updateRecord(record);
		});
	},

	/**
	 * 获取页面数据
	 * @param view
	 * @param fields
	 * @returns {{}}
	 */
	getViewFields:function(view, fields){
		var elements = Ext.ComponentQuery.query(fields, view);
		var data = {};
		Ext.Array.forEach(elements,function(item){
			data[item.getItemId()] = item.getValue();
		});

		return data;
	},

	imageCapture:function(success, fail){
		Ext.Viewport.add(Ext.create('YourTour.view.widget.XImageSelect',{success:success, fail : fail}));
	},

	setApplication:function(application){
		this.application = application;
	},

	getApplication:function(){
		return this.application;
	}

});

