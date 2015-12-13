Ext.define('YourTour.view.user.UserMainView', {
    extend: 'YourTour.view.widget.XPage',
    requires:[ 'YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.XLabel'],
    xtype:'UserMainView',
    config: {
    	id:'UserMainView',
    	layout:'vbox',
    	cls:'page',
    	items: [
            {
            	xtype: 'xheaderbar',
				itemId:'headerbar',
				backButton:false,
				title:'个人中心',	
            },
            
        	{
   				itemId : 'imageUrl',
				xtype : 'image',
				height:200,
				width:'100%',
				src:'resources/images/personal_view_image.jpg',
    		},
    		
    		{
    			xtype:'panel',
    			layout:'vbox',
    			margin:'10 0 0 0',
    			items:[
		    		{
		    			 xtype:'xlabel',
		    			 itemId:'expert',
		    			 padding:'0 10 0 10',
		    			 cls:'row nav_arrow font-large underline',
		    			 html : '我是达人',
		    			 tappable :true
		    		},
		    	]
    		},
    		{
    			xtype:'panel',
    			layout:'vbox',
    			margin:'10 0 0 0',
    			items:[
					{
						xtype:'xlabel',
						itemId:'order',
						cls:'row nav_arrow font-large underline',
						html : '我的消息',
						padding:'0 10 0 10',
						tappable :true
					},
					
					{
						xtype:'xlabel',
						itemId:'order',
						cls:'row nav_arrow font-large underline',
						html : '我的点评',
						padding:'0 10 0 10',
						tappable :true
					},
					
					{
						xtype:'xlabel',
						itemId:'order',
						cls:'row nav_arrow font-large underline',
						html : '我的收藏',
						padding:'0 10 0 10',
						tappable :true
					},
    			]
    		},
    		
    		{
    			xtype:'panel',
    			layout:'vbox',
    			margin:'10 0 0 0',
    			items:[
		    		{
		    			 xtype:'xlabel',
		    			 itemId:'equity',
		    			 padding:'0 10 0 10',
		    			 cls:'row nav_arrow font-large underline',
		    			 html : '我的权益',
		    			 tappable :true
		    		},
		    		
		    		{
			   			xtype:'xlabel',
			   			itemId:'order',
			   			cls:'row nav_arrow font-large underline',
			   			html : '我的订单',
			   			padding:'0 10 0 10',
			   			tappable :true
		    		},
		    		
		    		{
			   			xtype:'xlabel',
			   			itemId:'footprint',
			   			cls:'row nav_arrow font-large underline',
			   			html : '我的足迹',
			   			padding:'0 10 0 10',
			   			tappable :true
		    		},
		    		
		    		{
			   			xtype:'xlabel',
			   			itemId:'charge',
			   			cls:'row nav_arrow font-large underline',
			   			html : '我的费用',
			   			padding:'0 10 0 10',
			   			tappable :true
		    		}
		    	]
    		}
        ]
    }
});

