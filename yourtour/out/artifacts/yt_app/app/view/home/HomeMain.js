Ext.define('YourTour.view.home.HomeMain', {
    extend: 'YourTour.view.widget.XPage',
    xtype: 'HomeMain',
    requires:['Ext.Panel', 'Ext.Img', 'YourTour.view.widget.XTitleBar','YourTour.view.widget.ToolButton'],
    config: {
    	fullscreen: true,
    	itemId:'HomeMain',
    	id:'HomeMain',
    	layout:'card',
        items: [
            {
            	xtype: 'xtitlebar',
                docked: 'top',
                title: '目的地',
            	items:[]	
            },
            {
            	xtype:"panel",
            	style:"width: 100%; background-image: url(resources/images/home.jpg); background-size: cover;",
            	layout:'vbox',
            	items:[
            		{
	            		xtype: 'panel',
	            		style:'background:#EDEDED !important;',
	            		left:20,
	            		right:20,
	            		bottom:100,
	            		docked:'bottom',
	            		height:100,
	            		padding:'0 20 0 20',
	            		layout:'vbox',
	            		
	            		defaults:{
	            			style:'font-size:13px'
	            		},
	            		items:[
	            			{
	            				xtype: 'textfield',  
							    itemId:'name',
							    placeHolder:'输入目的地',
							    cls:'underline row',
							    clearIcon: true
	            			},
	            			{
	            				xtype:'button',
	            				itemId:'btnSearch',
	            				text: '搜索',
	            				margin:'10 0 0 0'
	            			}
	            		]
	            	}
            	]
    		}
        ]
    }
});

