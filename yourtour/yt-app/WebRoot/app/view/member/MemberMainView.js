Ext.define('YourTour.view.member.MemberMainView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar', 'YourTour.view.member.MemberItemView', 'Ext.Img'],
    config: {
	    id:'MemberMainView',
	    layout:'vbox',
        items: [
			{    
				xtype: 'xheaderbar',
				title:'伙伴'
			},
			
			{
	   			xtype:'xgridview',
	   			itemId:'members',
				layut:'hbox',
				cols:2,
				hSpace:5,
				flex:1,
				item:'YourTour.view.member.MemberItemView'
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
					},
					{
						itemId:'btnAdd',
						title:'加人',
					    iconCls:'user',
					    iconAlign:'top'
					}
        	    ]
        	}
        ]
    }
});

