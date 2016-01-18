Ext.define('YourTour.view.resource.ResourceFormView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','Ext.Img',  'YourTour.view.widget.XHeaderBar','YourTour.view.widget.XToolbar','YourTour.view.resource.ResourceSceneView'],
    config: {
    	id:'ResourceFormView',
		itemId:'ResourceFormView',
    	layout:'vbox',
    	items:[
    		{    
    			xtype: 'xheaderbar',
    			itemId:'headerbar'
    		},		
			
			{
				xtype: 'xtoolbar',
				docked: 'bottom',
				itemId:'toolbar',
				cls:'toolbar',
				items: [
					{
						xtype:'xspacer',
						flex:1
					},
					{
						xtype: 'button',
						text: '加入日程',
						ui: 'normal',
						iconCls:'action',
						itemId: 'btnResourceAddTap'
					},{
						xtype: 'button',
						text: '收藏',
						ui: 'normal',
						iconCls:'favorites',
						itemId: 'btnFavorite'
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

