Ext.define('YourTour.view.resource.ResourceDetailView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','Ext.Img',  'YourTour.view.widget.XHeaderBar','YourTour.view.widget.XToolbar','YourTour.view.resource.ResourceSceneView'],
    config: {
    	id:'ResourceDetailView',
    	layout:'vbox',
    	scrollable: {
    	    direction: 'vertical',
    	    indicators: false	
    	},
    	items:[
    		{    
    			xtype: 'xheaderbar',
    			itemId:'headerbar'
    		},		
			
    		{
	    		xtype : 'panel',
	    		flex:1,
	    		itemId: 'detailview'
	    	},
	    	
	    	{
                xtype: 'toolbar',
                docked: 'bottom',
                itemId:'toolbar',
                items: [
	                {
	                    xtype: 'spacer',
	                    flex:1
	                },{
	                    xtype: 'button',
	                    text: '加入日程',
	                    ui: 'normal',
	                    iconCls:'action',
	                    itemId: 'btnAdd'
	                },{
	                    xtype: 'button',
	                    text: '收藏',
	                    ui: 'normal',
	                    iconCls:'favorites',
	                    itemId: 'btnFavorite'
	                },{
	                    xtype: 'button',
	                    text: '评论',
	                    ui: 'normal',
	                    iconCls:'compose',
	                    itemId: 'btnComment'
	                },{
	                    xtype: 'button',
	                    text: '分享',
	                    ui: 'normal',
	                    iconCls:'action',
	                    itemId: 'btnShare'
	                }
	            ]
            }
        ]
    }
});

