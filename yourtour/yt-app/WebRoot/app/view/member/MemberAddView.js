Ext.define('YourTour.view.member.MemberAddView', {
	extend: 'YourTour.view.widget.XPage',
    xtype: 'MemberAddView',
    requires:['YourTour.view.widget.XHeaderBar','Ext.field.Search'],
    config: {
	    id:'MemberAddView',
	    layout:'vbox',
        items: [
			{    
				xtype: 'xheaderbar',
				title:'添加伙伴'
			},
			
			{
				xtype:'panel',
				margin:'10 0 0 0',
				cls:'row underline',
				layout:'hbox',
				style:'background-color:white',
				items:[
					{  
					    xtype: 'searchfield',  
					    itemId:'key',
					    placeHolder:'昵称/手机号',
					    flex:1,
					},
					
					{  
						xtype : 'image',
	    				mode : 'tag',
	    				margin:'5 0 0 0',
	    				src:'resources/icons/icon_search.png',
		              	itemId:'btnSearch'  
					},
				]
			},
			
			{  
			    xtype: 'xlabel',  
			    itemId:'barscanner',
			    html:'扫一扫',
			    margin:'20 0 0 0',
				padding:'0 0 0 10',
				cls:'row underline',
				style:'background-color:white',
				tappable:true
			},
			{  
			    xtype: 'spacer',  
			    flex:1
			}
        ]
    }
});

