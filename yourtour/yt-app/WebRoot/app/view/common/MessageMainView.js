Ext.define('YourTour.view.common.MessageMainView', {
	extend: 'YourTour.view.widget.XPage',
	requires:['Ext.Panel','YourTour.view.common.MessageDataItemView', 'YourTour.view.widget.XTextArea'],
	xtype:'MessageMainView',
    config: {
    	id:'MessageMainView',
    	layout:'card',
    	items:[
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'目的地',
				items:[
			       {
			    	   xtype:'toolbutton',
			    	   itemId:'group',
			    	   text:'成员',
			    	   align:'right'
			       }
				]
			},
			
			{
    			xtype:'xdataview',
				itemId:'MessageList',
		        defaultType: 'MessageDataItemView'
    		},
			
			{    
				xtype: 'xpanel',
				itemId:'messagetool',
				height:50,
				layout:'hbox',
				docked:'bottom',
				items:[
					{
						xtype : 'image',
						mode : 'tag',
						src:'resources/icons/48/icon_voice.png',
					  	itemId:'voice'  
					},
					
					{
						xtype:'xtextarea',
						itemId: 'content',
						flex:1
					},
					
					{  
						xtype : 'xlabel',
						html:'Send',
	              		itemId:'btnSend'
					}
				]
			} 
    	]
    }
});