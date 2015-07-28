Ext.define('YourTour.view.resource.SceneResourceDetailView', {
    extend: 'Ext.Container',
    requires:['Ext.Panel','YourTour.view.widget.ToggleField','YourTour.view.widget.XToolbar'],
    xtype: 'SceneResourceDetailView',
    config: {
    	itemId:'SceneResourceDetailView',
    	id:'SceneResourceDetailView',
    	fullscreen:true,
    	layout:'vbox',
    	baseCls:'page',
    	scrollable: {
    	    direction: 'vertical',
    	    directionLock: true
    	},
    	items:[
    		{    
				xtype: 'xtoolbar',
				docked: 'top',
				title: '景点资源',
				items:[
					{
						xtype: "image", 
	                	itemId:'close',
	                	mode:'tag',
	                	margin:'0 0 0 5',
	                	src:'resources/icons/icon_back.png',
	                	align:'left'
					}
                ]
			},
			
    		{
    	   		itemId : 'imageUrl',
	    		xtype : 'image',
	    		mode : 'tag'
	    	},
	    	
	    	{
	    		xtype : 'panel',
	    		layout:'hbox',
	    		padding:'0 5 0 5',
	    		cls:'textfield raty',
	    		items:[
		    		{
		    			xtype:'label',
		    			flex:1,
		    			html:'推荐指数',
		    			style:'font-weight:bold'
		    			
		    		},
		    		{
		    			xtype:'image',
		    			src:'resources/images/raty_32.png',
		    			flex:2,
		    			mode:'tag'
		    		},
		    		{
		    			xtype:'label',
		    			flex:1,
		    			html:'综合评分',
		    			style:'font-weight:bold'
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
    			xtype:'label',
    			itemId:'name',
    			cls:'textfield',
    			padding:'0 5 0 5',
    			style:'font-weight:bold;'
    		},
	    	
	    	{
		    	xtype:'panel',
		    	layout:'hbox',
		    	padding:'5',
		    	cls:'textfield',
		    	items:[
			    	{
		    			xtype:'label',
		    			html:'地址:'
		    		},
			    	{
		    			xtype:'label',
		    			itemId:'address',
		    			margin:'0 0 0 5'
		    		}
	    		]
	    	},
	    	
	    	{
		    	xtype:'panel',
		    	layout:'hbox',
		    	padding:'5',
		    	cls:'textfield',
		    	items:[
			    	{
		    			xtype:'label',
		    			html:'电话:'
		    		},
			    	{
		    			xtype:'label',
		    			itemId:'phone',
		    			margin:'0 0 0 5'
		    		}
	    		]
	    	},
	    	
	    	
    		{
				xtype:'panel',
				layout:'vbox',
				padding:'0 5 0 5',
				style:'background:#fff',
				margin:'5 0 0 0',
				items:[
					{
						xtype:'label',
						cls:'textfield',
						html: '景点介绍',
						style:'font-weight:bold;'
					},
					{  
					    xtype: 'label',  
					    itemId:'intro',
					    style:'font-size:14px;line-height:20px; color:grey'
					}
				]
			},
			
			{
				xtype:'label',
				cls:'textfield',
				style:'font-weight:bold;',
				padding:'5',
				margin:'5 0 0 0',
				html: '开放时间'
			},
			
			{  
			    xtype: 'label',  
			    itemId:'open',
			    padding:'5 5 5 5',
			    cls:'underline',
			    style:'background:#fff;font-size:14px;line-height:20px; color:grey'
			},
			
			{
				xtype:'label',
				cls:'textfield',
				padding:'5',
				style:'font-weight:bold;',
				html: '价格信息'
			},
			
			{  
			    xtype: 'label',  
			    itemId:'price',
			    padding:'5 5 5 5',
			    style:'background:#fff; font-size:14px;line-height:20px; color:grey'
			}
        ]
    }
});

