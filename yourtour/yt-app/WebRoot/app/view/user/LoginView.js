Ext.define('YourTour.view.user.LoginView', {
    extend: 'Ext.Panel',
    xtype: 'loginview',
    requires:['Ext.Panel', 'YourTour.view.widget.XTitleBar', 'Ext.field.Password','Ext.field.Text', 'YourTour.view.widget.XButton', 'YourTour.view.widget.ToolButton'],
    config: {
    	itemId:'loginview',
    	id:'loginview',
    	fullscreen: true,
    	layout:'vbox',
    	baseCls:'page',
    	
        items: [
            {
            	xtype: 'xtitlebar',
                docked: 'top',
                title: '会员登录',
                items:[{
                	xtype: "toolbutton", 
                	itemId:'btnRegister',
                	id:'btnRegister',
                	ui: 'mask',
                	text:'注册',
                	align:'right'
                }]	
            },
            {
            	xtype:'panel',
            	layout:'hbox',
            	cls:'textfield',
            	margin:'10 0 0 0',
            	items:[
            	   {
            		   xtype:'image', 
            		   mode : 'tag',
            		   margin:'0 10 0 10',
            		   src:'resources/icons/icon_user.png'
            	   },
            	   
            	   {
            		   xtype:'label',
            		   html:'|',
            		   margin:'0 10 0 0'	   
            	   },
            	   {
            		   xtype:'textfield', 
            		   placeHolder:'请输入用户名(邮箱或者手机号)',
            		   flex:1,
            		   name: 'userName'
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
						   mode : 'tag',
						   margin:'0 10 0 10',
						   src:'resources/icons/icon_password.png'
					},
					
					{
						   xtype:'label', 
	            		   html:'|',
	            		   margin:'0 10 0 0'	
					},
					{
						   xtype:'passwordfield', 
						   placeHolder:'请输入密码(8-16位字母、数字)',
						   flex:1,
						   name: 'password'
					}
            	]
            }
            ,
            {
            	xtype:'xbutton',
            	margin:'10 0 0 0',
            	id:'btnLogin',
            	text:'登录'
            	
            }
        ]
    }
});

