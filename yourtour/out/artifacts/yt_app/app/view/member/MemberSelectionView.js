Ext.define('YourTour.view.member.MemberSelectionView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar', 'YourTour.view.member.MemberItemView', 'Ext.Img'],
    config: {
	    id:'MemberSelectionView',
	    layout:'vbox',
        items: [
			{    
				xtype: 'xheaderbar',
				title:'伙伴'
			},

			{
				xtype:'xdataview',
				itemId:'memberList',
				flex:1,
		        useComponents: true,
		        defaultType: 'MemberItemView'
	   		}
        ]
    }
});

