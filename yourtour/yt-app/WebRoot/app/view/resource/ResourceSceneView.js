Ext.define('YourTour.view.resource.ResourceSceneView', {
    extend: 'Ext.Panel',
    requires:['Ext.Panel','Ext.Img', 'YourTour.view.widget.XLabel','YourTour.view.widget.XField'],
    xtype: 'ResourceSceneView',
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
		    	items:[
		    		{
			    		xtype : 'panel',
			    		layout:'hbox',
			    		cls:'row underline raty',
			    		items:[
				    		{
				    			xtype:'xlabel',
				    			flex:1,
				    			html:'推荐指数',
				    			margin:'0 10 0 5'
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
						xtype:'panel',
						layout:'hbox',
						cls:'row underline',
						items:[
							{
								xtype:'label',
								html: '地址',
								cls:'font-medium font-grey',
								margin:'0 10 0 5'
							},
							{  
							    xtype: 'label',  
							    itemId:'address',
							    cls:'font-medium font-grey icon_map',
							    flex:1,
							    margin:'0 5 0 10'
							}
						]
					},
					
			    	{
						xtype:'panel',
						layout:'hbox',
						cls:'row underline',
						items:[
							{
								xtype:'label',
								html: '电话',
								cls:'font-medium font-grey',
								margin:'0 10 0 5'
							},
							{  
							    xtype: 'label',  
							    itemId:'phone',
							    cls:'font-medium font-grey icon_phone',
							    flex:1,
							    margin:'0 5 0 10'
							}
						]
					},
					
					{
						xtype:'panel',
						layout:'hbox',
						cls:'underline',
						items:[
							{
								xtype:'label',
								html: '描述',
								cls:'row font-medium font-grey',
								margin:'0 10 0 5'
							},
							{  
							    xtype: 'label',  
							    itemId:'intro',
							    flex:1,
							    cls:'font-medium font-grey multilineinfo',
							    margin:'9 5 9 10'
							}
						]
					},
					
					{
						xtype:'panel',
						layout:'hbox',
						cls:'underline',
						items:[
							{
								xtype:'label',
								html: '开放',
								cls:'row font-medium font-grey',
								margin:'0 10 0 5'
							},
							{  
							    xtype: 'label',  
							    itemId:'open',
							    cls:'font-medium font-grey multilineinfo',
							    flex:1,
							    margin:'9 5 9 10'
							}
						]
					},
					
					{
						xtype:'panel',
						layout:'hbox',
						cls:'underline row',
						items:[
							{
								xtype:'label',
								html: '价格',
								cls:'row font-medium font-grey',
								margin:'0 10 0 5'
							},
							{  
							    xtype: 'label',  
							    itemId:'price',
							    cls:'font-medium font-grey multilineinfo',
							    flex:1,
							    margin:'9 5 9 10'
							}
						]
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
 	 	   	
 	 	   	var address = me.down('#address');
 	 	   	address.setHtml(record.get('address'));
 	 	   	
 	 	   	var phone = me.down('#phone');
 	 	   	phone.setHtml(record.get('phone'));
 	 	   	
 	 	   	var intro = me.down('#intro');
 	 	   	intro.setHtml(record.get('intro'));
 	 	   	
 	 	   	var openEl = me.down('#open');
 	 	   	var open = record.get('openTime');
 	 	   	if(! (open == '' || open == null)){
 	 	   		openEl.removeCls('row');
 	 	   		openEl.setHtml(open);
    		}	
 	 	   	
 	 	   	var priceEl = me.down('#price');
 	 	   	var price = record.get('price');
 	 	   	if(! (price == '' || price == null)){
 	 	   		priceEl.removeCls('row');
 	 	   		priceEl.setHtml(price);
 	 	   	}	
    	}
    }
});

