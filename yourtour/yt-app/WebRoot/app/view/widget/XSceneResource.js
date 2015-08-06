Ext.define('YourTour.view.widget.XSceneResource', {
    extend: 'Ext.Panel',
    requires:['Ext.Panel','Ext.Img', 'YourTour.view.widget.XLabel','YourTour.view.widget.XField'],
    xtype: 'xsceneresource',
    config: {
    	record:null,

    	layout:'vbox',
    	items:[
    		{
    	   		itemId : 'imageUrl',
	    		xtype : 'image',
	    		mode : 'tag'
	    	},
	    	
	    	{
	    		xtype:'panel',
	    		layout:'vbox',
	    		defaults:{
	    			padding:'0 5 0 5'
	    		},
		    	items:[
		    		{
			    		xtype : 'panel',
			    		layout:'hbox',
			    		cls:'row underline raty',
			    		items:[
				    		{
				    			xtype:'xlabel',
				    			flex:1,
				    			html:'推荐指数'
				    			
				    		},
				    		{
				    			xtype:'image',
				    			src:'resources/images/raty_32.png',
				    			flex:2,
				    			mode:'tag'
				    		},
				    		{
				    			xtype:'xlabel',
				    			flex:1,
				    			html:'评价指数'
				    		},
				    		{
				    			xtype:'image',
				    			flex:2,
				    			mode:'tag',
				    			src:'resources/images/raty_32.png'
				    		}
			    		]
			    	},
		    	
			    	{
		    			xtype:'xlabel',
		    			itemId:'name',
		    			cls:'row underline',
		    			style:'font-weight:bold;'
		    		},
		    	
			    	{
				    	xtype:'panel',
				    	layout:'hbox',
				    	cls:'row underline',
				    	items:[
					    	{
				    			xtype:'xlabel',
				    			html:'地址:'
				    		},
					    	{
				    			xtype:'xfield',
				    			itemId:'address',
				    			margin:'0 0 0 5'
				    		}
			    		]
			    	},
		    	
			    	{
				    	xtype:'panel',
				    	layout:'hbox',
				    	cls:'row underline',
				    	items:[
					    	{
				    			xtype:'xlabel',
				    			html:'电话:'
				    		},
					    	{
				    			xtype:'xfield',
				    			itemId:'phone',
				    			margin:'0 0 0 5'
				    		}
			    		]
			    	},
		    	
		    	
    		
					{
						xtype:'xlabel',
						cls:'row underline',
						html: '景点介绍'
					},
					{  
					    xtype: 'xfield',  
					    itemId:'intro',
					    cls:'multilineinfo'
					},
				
					{
						xtype:'xlabel',
						cls:'row underline',
						html: '开放时间'
					},
					
					{  
					    xtype: 'xfield',  
					    itemId:'open',
					    cls:'multilineinfo underline'
					},
					
					{
						xtype:'xlabel',
						cls:'row underline',
						html: '价格信息'
					},
					
					{  
					    xtype: 'xfield',  
					    itemId:'price',
					    cls:'multilineinfo underline'
					}
	    		]
	    	}
        ]
    },
    
    setRecord:function(record){
    	var me = this;
    	if(record != null){
    		var imageUrl = me.down('#imageUrl');
 	 	   	imageUrl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:150px'>");
 	 	   	
 	 	   	var name = me.down('#name');
 	 	   	name.setHtml(record.get('name'));
 	 	   	
 	 	   	var address = me.down('#address');
 	 	   	address.setHtml(record.get('address'));
 	 	   	
 	 	   	var phone = me.down('#phone');
 	 	   	phone.setHtml(record.get('phone'));
 	 	   	
 	 	   	var intro = me.down('#intro');
 	 	   	intro.setHtml(record.get('intro'));
 	 	   	
 	 	   	var open = me.down('#open');
 	 	   	open.setHtml(record.get('open'));
 	 	   	
 	 	   	var price = me.down('#price');
 	 	   	price.setHtml(record.get('price'));
    	}
    }
});

