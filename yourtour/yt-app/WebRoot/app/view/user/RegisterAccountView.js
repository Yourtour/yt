Ext.define('YourTour.view.user.RegisterAccountView', {
	extend: 'Ext.form.Panel',
    requires:['Ext.Panel','YourTour.view.widget.HSpacer', 'YourTour.view.widget.XTitleBar', 'Ext.field.Password','YourTour.view.widget.XTextField', 'YourTour.view.widget.XButton', 'YourTour.view.widget.ToolButton'],
    xtype: 'RegisterAccountView',
    config: {
    	id:'RegisterAccountView',
    	layout:'vbox',
    	defaults:{
    		padding:'0 10 0 10'
    	},
        items: [
        	{
            	xtype: 'xtitlebar',
                docked: 'top',
                title: '账号注册',
            	items:[{
                	xtype: "image", 
                	itemId:'back',
                	id:'back',
                	mode:'tag',
                	margin:'0 0 0 5',
                	src:'resources/icons/icon_back.png',
                	align:'left'
                }]	
            },
            
            {
            	xtype:'hspacer'
            },
            
            {
            	xtype:'panel',
            	layout:'hbox',
            	cls:'row underline',
            	style:'background:white',
            	items:[
            	   {
            		   xtype:'image', 
            		   src:'resources/icons/icon_user.png',
            		   mode : 'tag'
            	   },
            	   
            	   {
            		   xtype:'label',
            		   html:'|',
            		   margin:'0 10 0 10'
            	   },
            	   {
            		   xtype:'textfield', 
            		   flex:1,
            		   placeHolder:'请输入用户名(邮箱)',
            		   name: 'userName'
            	   } 
            	]
            },
            
            {
            	xtype:'panel',
            	layout:'hbox',
            	cls:'row underline',
            	style:'background:white',
            	items:[
					{
						   xtype:'image', 
						   src:'resources/icons/icon_password.png',
						   mode : 'tag'
					},
					
					{
						   xtype:'xlabel',
	            		   html:'|',
	            		   margin:'0 10 0 10'
					},
					{
						xtype:'passwordfield',
		            	flex:1,
		            	placeHolder:'请输入密码(8-16位字母、数字)',
		            	name: 'password'
					}
            	]
            },{
            	xtype:'panel',
            	layout:'hbox',
            	cls:'row underline',
            	style:'background:white',
            	items:[
					{
						   xtype:'image', 
						   src:'resources/icons/icon_password.png',
						   mode : 'tag'
					},
					
					{
						   xtype:'xlabel',
	            		   html:'|',
	            		   margin:'0 10 0 10'
					},
					{
						xtype:'passwordfield',
		            	flex:1,
		            	placeHolder:'请输入验证密码(8-16位字母、数字)',
		            	name: 'confirmPassword'
					}
            	]
            },
            
            {
            	xtype:'hspacer'
            },
            
            {
            	xtype:'xbutton',
            	id:'btnNext',
            	text:'下一步'
            }
        ]
    }
});

