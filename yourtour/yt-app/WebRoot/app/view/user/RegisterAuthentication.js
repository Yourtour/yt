Ext.define('YourTour.view.user.RegisterAuthentication', {
    extend: 'Ext.Panel',
    xtype: 'registerauth',
    requires:['Ext.Panel', 'Ext.TitleBar', 'Ext.Img','YourTour.view.widget.XButton'],
    config: {
    	itemId:'registerauth',
    	id:'registerauth',
    	fullscreen: true,
    	layout:'vbox',
    	baseCls:'page',
        items: [
            {
            	xtype:'panel',
            	layout:'hbox',
            	cls:'textfield',
            	margin:'10 0 0 0',
            	items:[
            	   {
            		   xtype:'image', 
            		   src:'resources/icons/icon_mobile.png',
            		   mode : 'tag',
            		   margin:'0 10 0 10'
            	   },
            	   
            	   {
            		   xtype:'label',
            		   html:'|',
            		   margin:'0 10 0 0'
            	   },
            	   {
            		   xtype:'textfield', 
            		   flex:1,
            		   placeHolder:'请输入手机号码(11位数字)',
            		   name: 'mobile'
            	   } 
            	]
            },
            
            {
            	xtype:'panel',
            	layout:'hbox',
            	cls:'textfield',
            	items:[
					{
						   xtype:'image', 
						   src:'resources/icons/icon_code.png',
	                   	   mode : 'tag',
	            		   margin:'0 10 0 10'
					},
					
					{
						   xtype:'label',
	            		   html:'|',
	            		   margin:'0 10 0 0'
					},
					{
						xtype:'panel',
		            	layout:'hbox',
		            	flex:1,
		            	items:[
							{
								   xtype:'passwordfield', 
								   flex:3,
								   placeHolder:'请输入验证码',
								   name: 'userName'
							},
							{
								   xtype:'ytButton', 
								   flex:2,
								   html: '获取验证码'
							}
		            	]
					}
            	]
            }
            ,
            {
            	xtype:'xbutton',
            	id:'btnRegisterAccount',
            	margin:'10 0 0 0',
            	text:'下一步',
            }
        ]
    }
});

