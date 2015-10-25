Ext.define('YourTour.view.user.LoginView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','YourTour.view.widget.HSpacer', 'YourTour.view.widget.XTitleBar', 'Ext.field.Password','YourTour.view.widget.XTextField', 'YourTour.view.widget.XButton', 'YourTour.view.widget.ToolButton'],
    xtype:'LoginView',
    config: {
    	id:'LoginView',
    	layout:'vbox',
    	defaults:{
    		padding:'0 10 0 10'
    	},
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
            	xtype:'hspacer'
            },
            {
            	xtype:'panel',
            	layout:'hbox',
            	cls:'row underline',
            	items:[
            	   {
            		   xtype:'image', 
            		   mode : 'tag',
            		   src:'resources/icons/icon_user.png'
            	   },
            	   
            	   {
            		   xtype:'xlabel',
            		   html:'|',
            		   margin:'0 10 0 10'
            	   },
            	   {
            		   xtype:'xtextfield', 
            		   placeHolder:'请输入用户名(邮箱或者手机号)',
            		   flex:1,
            		   name: 'userName'
            	   }
            	]
            },
            
            {
            	xtype:'panel',
            	layout:'hbox',
            	cls:'row underline',
            	items:[
					{
						   xtype:'image', 
						   mode : 'tag',
						   src:'resources/icons/icon_password.png'
					},
					
					{
						   xtype:'label', 
	            		   html:'|',
	            		   margin:'0 10 0 10'	
					},
					{
						   xtype:'passwordfield', 
						   placeHolder:'请输入密码(8-16位字母、数字)',
						   inputCls:'borderless',
						   flex:1,
						   name: 'password'
					}
            	]
            },
            
            {
            	xtype:'hspacer'
            },
            
            {
            	xtype:'xbutton',
            	id:'btnLogin',
            	text:'登录'
            },
            
            {
				xtype:'label',
				cls:'row',
				html: '忘记密码?',
				style:'text-align:right',
				padding:'0 10 0 0'
			}
        ]
    }
});

