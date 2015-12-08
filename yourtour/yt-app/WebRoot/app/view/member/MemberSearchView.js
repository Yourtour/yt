Ext.define('YourTour.view.member.MemberSearchView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar','Ext.field.Search','YourTour.view.member.MemberDataItem'],
    config: {
	    id:'MemberSearchView',
	    layout:'vbox',
        items: [
			{    
				xtype: 'xheaderbar',
				title:'会员清单'
			},
			
			{
				xtype:'panel',
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
	    				margin:'5 5 0 5',
	    				src:'resources/icons/icon_search.png',
		              	itemId:'btnSearch'  
					},
				]
			},
			
			{
    			itemId:'memberSearchList',
    			xtype:'dataview',
    			flex:1,
		        useComponents: true,
		        defaultType: 'MemberDataItem'
    		}
        ]
    }
});

