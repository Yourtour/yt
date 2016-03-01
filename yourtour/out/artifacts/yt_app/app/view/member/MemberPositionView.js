Ext.define('YourTour.view.member.MemberPositionView', {
	extend: 'YourTour.view.widget.XPage',
    xtype: 'MemberPositionView',
    requires:['YourTour.view.widget.XHeaderBar', 'YourTour.view.member.MemberItemView', 'Ext.Img'],
    config: {
	    id:'MemberPositionView',
	    layout:'vbox',
        items: [
			{    
				xtype: 'xheaderbar',
				title:'位置'
			},
			
			{
                xtype:'panel',
                id:'map',
                styleHtmlContent: true,  
                scrollable: false,  
                flex:1
            }
        ]
    }
});

