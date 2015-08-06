Ext.define('YourTour.view.user.RegisterProfileView', {
    extend: 'YourTour.view.widget.XPage',
    xtype: 'xregisterprofile',
    requires:['Ext.Panel','Ext.field.Radio','YourTour.view.widget.XLabel', 'YourTour.view.widget.HSpacer', 'YourTour.view.widget.XTitleBar', 'Ext.field.Password','YourTour.view.widget.XTextField', 'YourTour.view.widget.XButton', 'YourTour.view.widget.ToolButton'],
    config: {
    	itemId:'RegisterProfileView',
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
            	xtype:'hspacer'
            },
            
            {
            	xtype:'panel',
            	layout:'hbox',
            	cls:'row underline',
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
            	   },
            	   
            	   {
                       xtype: 'xlabel',
                       name : 'gender',
                       value: 'M',
                       label: '男',
                       margin:'0 10 0 10',
                       checked: true
                   },
                   {
                       xtype: 'radiofield',
                       name : 'gender',
                       value: 'F',
                       margin:'0 10 0 10',
                       label: '女'
                   }
            	]
            },
            
            {
            	xtype:'panel',
            	layout:'hbox',
            	cls:'textfield',
            	padding:'0 10 0 10',
            	style:'height:100px',
            	items:[
					{
            		   xtype:'image', 
            		   src:'resources/icons/icon_portrait.png',
            		   mode : 'tag',
            		   margin:'0 10 0 0'
            	    },
					
					{
            	       xtype:'image', 
             		   src:'resources/icons/icon_portrait_demo.png',
             		   mode : 'tag',
             		   maxHeight:50
					}
            	]
            },
            
            {
            	xtype:'hspacer'
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
				xtype:'panel',
				layout: 'vbox',
				style:'height:100px;background:white',
				defaults: {
					flex: 1,
					width: '100%',
					padding: 10,
					defaults: {
						flex: 1,
						height: '100%',
						margin: 10
					}   
				},
				items:[{
					xtype: 'panel',
					layout: 'hbox',
					items:[
						{xtype:'label',html:'海滨主题'},
						{xtype:'label',html:'登山主题'},
						{xtype:'label',html:'滑雪主题'}
					]
				},{
					xtype: 'panel',
					layout: 'hbox',
					items:[
						{xtype:'label',html:'亲子主题'},
						{xtype:'label',html:'古镇主题'},
						{xtype:'label',html:'蜜月主题'}
					]
				}]
            },
            
            {
            	xtype:'hspacer'
            },
            
            {
            	xtype:'xbutton',
            	id:'btnRegisterDone',
            	text:'注册'
            }
        ]
    }
});

