Ext.define('YourTour.view.line.RecommedListItem', {
    extend: 'Ext.Panel',
    xtype: 'lineListItem',
    requires:['YourTour.view.widget.SubTitleBar','Ext.Spacer', 'Ext.Panel', 'Ext.Img','Ext.DataView','YourTour.view.line.RecommedRouteItem','YourTour.view.widget.TapPanel'],
    config: {
    	model:null,
    	itemId:'lineListItem',
    	layout : 'vbox',
    	baseCls:'lineListItem page',
    	scrollable: {
    	    direction: 'vertical',
    	    directionLock: true
    	},
    	
    	items:[
    		{
    			xtype:'panel',
    			style:'background:#fff',
    			layout:'fit',
    			height:150,
    			items:[
		    		{
		   				itemId : 'imageUrl',
						xtype : 'image',
						mode : 'tag',
						margin:5
		    		}
		    	]
    		},
    		
    		{
    			xtype:'tappanel',
    			layut:'vbox',
    			itemId:'lineInfo',
    			style:'background:#fff',
    			defaults:{
    				padding:'0 5 0 5'
    			},
	    		items:[
		    		{
					   xtype:'label',
					   pack:'center',
					   align:'center',
					   itemId:'name',
					   cls:'textfield nav_arrow',
					   html : ''
		    		},
			    
			   		{
			   			xtype:'panel',
			   			layout:'hbox',
			   			cls:'textfield raty',
			   			items:[
				   			{
				   				xtype:'label',
				   				html:'推荐指数:',
				   				flex:1,
				   				style:'font-weight:bold;'
				   			},
				   			
				   			{
				   				xtype:'image',
				    			src:'resources/images/raty_32.png',
				    			flex:2,
				    			mode:'tag'
				   			},
				   			
				   			{
				   				xtype:'label',
				   				html:'旅游指数:',
				   				flex:1,
				   				style:'font-weight:bold;'
				   			},
				   			{
				   				xtype:'image',
				    			src:'resources/images/raty_32.png',
				    			flex:2,
				    			mode:'tag'
				   			}
			   			]
			   		},
					   		
			   		{
			   			xtype:'panel',
			   			layout:'hbox',
			   			cls:'textfield',
			   			items:[
				   			{
				   				xtype:'label',
				   				html:'推荐时间:',
				   				flex:1,
				   				style:'font-weight:bold; font-size:14px'
				   			},
				   			{
				   				xtype:'label',
				   				flex:2,
				   				html:'7、8、9月',
				   				style:'font-size:14px'
				   			},
				   			{
				   				xtype:'label',
				   				flex:1,
				   				html:'服务达人',
				   				style:'font-weight:bold; font-size:14px'
				   			},
				   			{
				   				xtype:'label',
				   				flex:2,
				   				html:'10人',
				   				style:'font-size:14px'
				   			}
			   			]
			   		}
				]
    	   },
    	   
		   {
            	xtype:'subtitlebar',
            	html:'推荐行程',
            	padding:'0 5 0 5',
            	margin:'5 0 5 0'
           },
           
		   {
		    	xtype:'dataview',
		    	itemId:'routes',
				useComponents:true,
				scrollable:null,
		    	defaultType:'routeListItem'
		   }
    	]
    },
    
    setModel: function(model) {
       var me = this;
       
       if(model){
       	   var imageUrlEl = me.down('#imageUrl');
	 	   imageUrlEl.setHtml("<img src='" + model.get('imageUrl') + "' style='width:100%; max-height:150px'>");
	 	   
	 	   var nameEl = me.down('#name');
	 	   nameEl.setHtml(model.get('name'));
	 	   
	 	   var routesEl = me.down('#routes');
	 	   var routes = [];
	 	   model.routes().each(function(route){
				routes.push(route.data);
	 	   });
	 	   var store = Ext.create("Ext.data.Store",{model:'YourTour.model.RouteModel', data:routes});
	 	   routesEl.setStore(store);
	 	}
    }   
});

