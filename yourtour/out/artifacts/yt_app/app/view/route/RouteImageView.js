Ext.define('YourTour.view.route.RouteImageView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.field.Checkbox', 'YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.ToolButton','YourTour.view.widget.XPanel','YourTour.view.widget.XCheckbox'],
    config: {
	    id:'RouteImageView',
	    layout:'vbox',
        items: [
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'图片设置'
			},{
				xtype:'xpanel',
				itemId:'imageUlr',
				flex:1
			},      
            {
               	xtype: 'panel',
               	docked:'bottom',
               	cls:'row',
               	layout:'hbox',
               	items:[
					{
						xtype : 'xcheckbox',
	    				itemId : 'imageSize',
	    				margin:'6 10 0 10',
	    				label:'全屏',
	    				value:'full'
					},
					{
						xtype:'spacer',
						flex:1
					},
					{
			    	   xtype:'toolbutton',
			    	   itemId:'save',
			    	   text:'保存',
				    }
               	]
            }
        ]
    }
});

