Ext.define('YourTour.view.member.MemberMainView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar', 'YourTour.view.member.MemberItemView', 'Ext.Img'],
    config: {
	    id:'MemberMainView',
	    layout:'vbox',
        items: [
			{    
				xtype: 'xheaderbar',
				title:'伙伴',
				items:[
					{
					   xtype:'toolbutton',
					   itemId:'management',
					   text:'管理',
					   align:'right'
					},
					{
					   xtype:'toolbutton',
					   itemId:'finished',
					   text:'完成',
					   hidden:true,
					   align:'right'
					}
				]
			},
			
			{
				itemId:'members',
				xtype:'dataview',
				flex:1,
		        useComponents: true,
		        defaultType: 'MemberItemView'
	   		},
			
			{	
        		xtype:'tabpanel',
        		itemId:'menuTab',
        		ui:'dark',
        		docked:'bottom',
        		tabBarPosition:'bottom',
        		tabBar : {
                    defaults: {
                        flex:1
                    }
                },
        	    items:[
					{
						itemId:'btnAdd',
						title:'加人',
					    iconCls:'user',
					    iconAlign:'top'
					},
					
					{
						itemId:'btnMessage', 
					    title:'聊天',
					    iconCls:'home',
					    iconAlign:'top'
					},
					{
					    itemId:'btnPosition',
					    title:'位置',
					    iconCls:'home',
					    iconAlign:'top'
					}
        	    ]
        	}
        ]
    }
});

