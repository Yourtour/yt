/**
 * 启动活动页面，显示启动时从后台服务获取的启动数据
 */
Ext.define('YourTour.view.ActivityView', {
    extend: 'Ext.Container',
    config: {
    	layout:'card',
    	id:'ActivityView',
        items: [
            {
                xtype:'img',
                itemId:'img'
            },

            {
                xtype:'button',
                itemId:'btnSkip',
                text: '跳过',
                hidden:true,
                top:20,
                right:20
            }
        ]
    }
});

