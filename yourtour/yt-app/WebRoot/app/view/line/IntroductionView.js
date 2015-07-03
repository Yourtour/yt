Ext.define('YourTour.view.line.IntroductionView', {
    extend: 'Ext.Panel',
    requires:['Ext.Toolbar', 'Ext.Panel','Ext.Carousel','YourTour.view.widget.TitleBar','YourTour.view.line.LineResourceItem'],
    xtype: 'lineintroview',
    config: {
    	itemId:'lineintroview',
    	id:'lineintroview',
    	fullscreen: true,
    	layout:'vbox',
    	baseCls:'page',
    	scrollable: {
    	    direction: 'vertical',
    	    directionLock: true
    	},
        items: [
        	{    
				xtype: '_titlebar',
				docked: 'top',
				title: '线路介绍',
				items:[{
					xtype: "image", 
                	itemId:'close',
                	mode:'tag',
                	margin:'0 0 0 5',
                	src:'resources/icons/icon_back.png',
                	align:'left'
				}]			
			},
			
			{
    	   		itemId : 'imageUrl',
	    		xtype : 'image',
	    		mode : 'tag',
	        	src : ''
	    	},
				    
		    {
			   xtype:'markedlabel',
			   pack:'center',
			   align:'center',
			   itemId:'name',
			   html : ''
		    },
				    
		    {
			   xtype:'panel',
			   margin:'5 0 0 0',
			   style:'background:#fff',
			   layout:'vbox',
			   items:[
			   		{
			   			xtype:'panel',
			   			layout:'hbox',
			   			padding:'5',
			   			defaults:{
			   				flex:1
			   			},
			   			items:[
				   			{
				   				xtype:'label',
				   				html:'推荐指数:',
				   				style:'font-weight:bold; font-size:14px'
				   			},
				   			
				   			{
				   				xtype:'label',
				   				html:'推荐指数:',
				   				style:'font-size:14px'
				   			},
				   			
				   			{
				   				xtype:'label',
				   				html:'旅游指数:',
				   				style:'font-weight:bold; font-size:14px'
				   			},
				   			{
				   				xtype:'label',
				   				html:'推荐指数:',
				   				style:'font-size:14px'
				   			}
			   			]
			   		},
			   		{
			   			xtype:'panel',
			   			layout:'hbox',
			   			padding:'5',
			   			items:[
				   			{
				   				xtype:'label',
				   				html:'最佳时间:',
				   				flex:1,
				   				style:'font-weight:bold; font-size:14px'
				   			},
				   			
				   			{
				   				xtype:'label',
				   				html:'最佳时间:',
				   				flex:4,
				   				style:'font-size:14px'
				   			}
			   			]
			   		}
			   ]
	    	},
			    
		    /*{
		       xtype:'panel',
			   margin:'5 0 0 0',
			   style:'background:#fff',
			   layout:'vbox',
			   items:[
			   		{
			   			xtype:'label',
				   		html:'线路特点',
				   		style:'font-weight:bold; font-size:14px',
				   		padding:'5'
			   		},	
			   		
			   		{
			   			xtype:'label',
			   			itemId:'feature',
			   			style:'font-size:14px;line-height:20px',
			   			padding:'5'
			   		},
			   		
			   		{
			   			xtype:'label',
				   		html:'推荐理由',
				   		style:'font-weight:bold; font-size:14px',
				   		padding:'5'
			   		},
			   		
			   		{
			   			xtype:'label',
			   			itemId:'reason',
			   			style:'font-size:14px;line-height:20px',
			   			padding:'5'
			   		}
			   ]
		    },*/
			    
		    {
		    	xtype:'dataview',
		    	itemId:'scenes',
		    	flex:1,
		    	margin:'5 0 0 0',
		    	useComponents:true,
		    	defaultType:'lineresourceitem'
		    }
        ]
    }
});

