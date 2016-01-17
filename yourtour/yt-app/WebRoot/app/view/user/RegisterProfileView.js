Ext.define('YourTour.view.user.RegisterProfileView', {
	extend: 'Ext.form.Panel',
    xtype: 'RegisterProfileView',
    requires:['Ext.Panel','Ext.field.Radio','Ext.field.File', 'YourTour.view.widget.XLabel', 'YourTour.view.widget.XSpacer', 'YourTour.view.widget.XTitleBar', 'Ext.field.Password','YourTour.view.widget.XTextField', 'YourTour.view.widget.XButton', 'YourTour.view.widget.ToolButton'],
    config: {
    	id:'RegisterProfileView',
    	layout:'vbox',
        items: [
        	{
            	xtype: 'xtitlebar',
                docked: 'top',
                title: '用户注册',
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
            	xtype:'xspacer'
            },
            
            {
            	xtype:'panel',
            	layout:'hbox',
            	cls:'row underline',
            	style:'background:white',
            	padding:'0 10 0 10',
            	items:[
            	   {
            		   xtype:'image', 
            		   src:'resources/icons/icon_nickname.png',
            		   mode : 'tag'
            	   },
            	   {
            		   xtype:'label',
            		   html:'|',
            		   margin:'0 10 0 10'
            	   },
            	   {
            		   xtype:'xtextfield', 
            		   flex:1,
            		   placeHolder:'请输入昵称',
            		   name: 'nickName'
            	   }
            	]
            },
            
            {
            	xtype:'panel',
            	layout:'hbox',
            	cls:'row underline',
            	style:'background:white',
            	padding:'0 10 0 10',
            	items:[
					{
						   xtype:'image', 
						   src:'resources/icons/icon_nickname.png',
						   mode : 'tag'
					},
					{
						   xtype:'label',
						   html:'|',
						   margin:'0 10 0 10'
					},    
					{
            		   xtype:'selectfield',                     
                       displayField:"text",
                       valueField:"value",
                       placeHolder:'请选择性别',
                       options:[
							{text: '男',  value: 'M'},
							{text: '女', value: 'F'}
                       ]
					}
				]
            },
            
            {
            	xtype:'panel',
            	layout:'hbox',
            	cls:'textfield',
            	padding:'0 10 0 10',
            	style:'height:75px',
            	items:[
					{
            		   xtype:'image', 
            		   src:'resources/icons/icon_portrait.png',
            		   itemId:'portrait',
            		   mode : 'tag'
            	    },
					
            	    {
					   xtype:'label',
					   html:'|',
					   margin:'0 10 0 10'
					},   
					
					{
            	       xtype:'image', 
             		   src:'resources/icons/icon_portrait_demo.png',
             		   itemId:'portrait',
             		   mode : 'tag',
             		   maxHeight:50,
					},
					
					{
						xtype: 'filefield',
						name: 'file',
						hidden:true,
						accept: 'image',
						multiple: true
					}
            	]
            },
            
            {
            	xtype:'xspacer'
            },
            
            {
            	xtype:'panel',
            	layout:'hbox',
            	margin:'10 0 0 0',
            	items:[{
					xtype:'panel',
					layout:'hbox',
					cls:'textfield',
					style:'width:100%',
					items:[
					   {
						   xtype:'label',
						   html:'-',
						   fit:1
					   },
					   {
						   xtype:'label',
						   html:'标签',
						   fit:1
					   },
					   {
						   xtype:'label',
						   html:'-',
						   fit:1
					   }
					]
            	}]
            },
            
            {
            	xtype:'xspacer'
            },
            
            {
            	xtype:'xbutton',
            	id:'btnRegisterDone',
				cls: 'x-button-primary',
            	text:'注册'
            },
            
            {
            	xtype: 'panel',
                docked: 'bottom',
                itemId:'portraitOptions',
                layout:'vbox',
            	items:[{
                	xtype: "xlabel", 
                	itemId:'btnPhoto',
                	cls:'row underline',
                	tappable:true,
                	html:'照片'
                },
                {
                	xtype: "xlabel", 
                	itemId:'btnCamera',
                	cls:'row',
                	tappable:true,
                	html:'照相'
                }]	
            },
        ]
    }
});

