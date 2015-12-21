Ext.define('YourTour.view.common.ServiceFormView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Label','Ext.Panel'],
    config: {
    	id:'ServiceDetailView',
    	layout:'fit',
    	items:[
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'服务',
				items:[
					
				]
			},
			
			{
				xtype:'panel',
				layout:'vbox',
				items:[
					{
						xtype:'panel',
						layout:'hbox',
						cls:'row underline',
						items:[
							{
								xtype:'label',
								html: '名称',
								cls:'font-medium font-grey',
								margin:'0 10 0 5'
							},
							{  
							    xtype: 'label',  
							    itemId:'time',
							    cls:'font-medium font-grey',
							    margin:'0 5 0 10'
							}
						]
					},
					
					{
						xtype:'panel',
						layout:'hbox',
						items:[
							{
								xtype:'label',
								html: '描述',
								cls:'row font-medium font-grey',
								margin:'0 10 0 5'
							},
							{  
							    xtype: 'label',  
							    itemId:'memo',
							    flex:1,
							    cls:'font-medium font-grey multilineinfo',
							    margin:'9 5 9 10'
							}
						]
					},
				]
			},
			
			{
                xtype: 'toolbar',
                docked: 'bottom',
                items: [
	                {
	                    xtype: 'spacer',
	                    flex:1
	                },{
	                    xtype: 'button',
	                    text: '点评',
	                    ui: 'normal',
	                    iconCls:'compose',
	                    itemId: 'comments'
	                }
	            ]
            }
    	]
    }
});

