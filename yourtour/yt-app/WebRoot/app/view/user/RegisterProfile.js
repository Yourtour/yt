Ext.define('YourTour.view.user.RegisterProfile', {
    extend: 'Ext.Panel',
    xtype: 'registerprofile',
    requires:['Ext.Panel', 'Ext.TitleBar', 'Ext.field.Radio', 'Ext.Img','YourTour.view.widget.XButton'],
    config: {
    	itemId:'registerprofile',
    	id:'registerprofile',
    	fullscreen: true,
    	baseCls:'page',
    	layout:'vbox',
        items: [
            {
            	xtype:'panel',
            	layout:'hbox',
            	cls:'textfield',
            	margin:'10 0 0 0',
            	items:[
            	   {
            		   xtype:'image', 
            		   src:'resources/icons/icon_nickname.png',
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
            		   placeHolder:'请输入昵称',
            		   name: 'nickName'
            	   },
            	   
            	   {
                       xtype: 'radiofield',
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
                   },
            	]
            },
            
            {
            	xtype:'panel',
            	layout:'hbox',
            	cls:'textfield',
            	style:'height:100px',
            	items:[
					{
            		   xtype:'image', 
            		   src:'resources/icons/icon_portrait.png',
            		   mode : 'tag',
            		   margin:'0 10 0 10'
            	    },
					
					{
            	       xtype:'image', 
             		   src:'resources/icons/icon_portrait_demo.png',
             		   mode : 'tag',
             		   margin:'0 10 0 10'
					},
            	]
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
            	xtype:'xbutton',
            	margin:'10 0 0 0',
            	id:'btnRegisterDone',
            	text:'注册',
            }
        ]
    }
});

