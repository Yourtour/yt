Ext.define('YourTour.view.member.MemberAddView', {
	extend: 'YourTour.view.widget.XPage',
    xtype: 'MemberAddView',
    requires:['YourTour.view.widget.XHeaderBar','YourTour.view.widget.XSearchField'],
    config: {
	    id:'MemberAddView',
	    layout:'vbox',
        items: [
			{    
				xtype: 'xheaderbar',
				title:'添加伙伴'
			},

			{
				xtype: 'xsearchfield',
				itemId:'searchfield',
				html:'昵称/手机号',
				cls: 'underline',
				padding:'5 10 5 10'
			},

			{  
			    xtype: 'xlabel',  
			    itemId:'barscanner',
			    html:'扫一扫',
				padding:'0 0 0 40',
				cls:'row underline font-grey icon-bar'
			},
			{  
			    xtype: 'spacer',  
			    flex:1
			}
        ]
    }
});

