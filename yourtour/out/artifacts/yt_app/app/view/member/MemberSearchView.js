Ext.define('YourTour.view.member.MemberSearchView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar','Ext.field.Search','YourTour.view.member.MemberSearchDataItem'],
    config: {
	    id:'MemberSearchView',
	    layout:'vbox',
        items: [
			{    
				xtype: 'xheaderbar',
				title:'',
				items:[
					{
						xtype: 'searchfield',
						itemId: 'query',
						flex:1,
						placeHolder:'昵称/手机号',
						align:'right'
					},
					{
						xtype: "xbutton",
						icon:'resources/icons/icon_header_search.png',
						itemId:'btnSearch',
						align:'right'
					}
				]
			},

			{
    			itemId:'memberSearchList',
    			xtype:'xdataview',
    			flex:1,
		        useComponents: true,
		        defaultType: 'MemberSearchDataItem'
    		}
        ]
    }
});

