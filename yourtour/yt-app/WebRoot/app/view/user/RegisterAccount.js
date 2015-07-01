Ext.define('YourTour.view.user.RegisterAccount', {
    extend: 'Ext.Panel',
    xtype: 'registeraccount',
    requires:['Ext.Panel', 'Ext.Img','YourTour.view.widget.Button'],
    config: {
    	itemId:'registeraccount',
    	id:'registeraccount',
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
            		   src:'resources/icons/icon_user.png',
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
            		   placeHolder:'请输入用户名(邮箱)',
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
						   src:'resources/icons/icon_password.png',
						   mode : 'tag',
	            		   margin:'0 10 0 10'
					},
					
					{
						   xtype:'label',
	            		   html:'|',
	            		   margin:'0 10 0 0'
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
            	cls:'textfield',
            	items:[
					{
						   xtype:'image', 
						   src:'resources/icons/icon_password.png',
						   mode : 'tag',
	            		   margin:'0 10 0 10'
					},
					
					{
						   xtype:'label',
	            		   html:'|',
	            		   margin:'0 10 0 0'
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
            	xtype:'ytButton',
            	id:'btnRegisterProfile',
            	margin:'10 0 0 0',
            	text:'下一步'
            }
        ]
    }
});

