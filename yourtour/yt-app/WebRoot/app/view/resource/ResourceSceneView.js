Ext.define('YourTour.view.resource.ResourceSceneView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','Ext.Img','YourTour.view.widget.XHeaderBar','YourTour.view.widget.XToolbar', 'YourTour.view.widget.XLabel','YourTour.view.widget.XField'],
    config: {
		id: 'ResourceSceneView',
    	layout:'vbox',
		scrollable:{
			direction: 'vertical',
			indicators: false
		},
    	items:[
			{
				xtype: 'xheaderbar',
				itemId:'headerbar'
			},

			{
				xtype:'panel',
				layout:'vbox',
				height:150,
				items:[
					{
						itemId : 'image',
						xtype : 'image',
						mode : 'tag'
					},

					{
						xtype: 'panel',
						layout: 'hbox',
						cls: 'row underline',
						padding: '0 10 0 10',
						docked: 'bottom',
						bottom: 0,
						style: 'background-color:grey;opacity:0.2; width:100%; text-align:center',
						items: [
							{
								xtype:'spacer',
								flex:1
							},

							{
								xtype: 'image',
								src: 'resources/images/raty_32.png',
								mode: 'tag'
							}
						]
					}
				]
			},
	    	
	    	{
	    		xtype:'panel',
	    		layout:'vbox',
		    	items:[
					{
						xtype:'panel',
						layout:'hbox',
						padding:'0 0 0 10',
						cls:'underline icon_memo',
						items:[
							{
								xtype: 'xlabel',
								itemId:'intro',
								cls:'font-medium font-grey multilineinfo',
								flex:1,
								tappable:true,
								margin:'0 5 0 30'
							}
						]
					},
					{
						xtype:'panel',
						layout:'hbox',
						padding:'0 0 0 10',
						cls:'row underline icon_position',
						items:[
							{
								xtype: 'xlabel',
								itemId:'address',
								cls:'font-medium font-grey nav_arrow',
								flex:1,
								tappable:true,
								margin:'0 5 0 30'
							}
						]
					},

			    	{
						xtype:'panel',
						layout:'hbox',
						cls:'row underline icon_phone',
						items:[
							{
							    xtype: 'label',  
							    itemId:'phone',
							    cls:'font-medium font-grey',
							    flex:1,
							    margin:'0 5 0 10'
							}
						]
					},

					{
						xtype:'panel',
						layout:'hbox',
						padding:'0 0 0 10',
						cls:'underline icon_open',
						items:[
							{
								xtype: 'xlabel',
								itemId:'openTime',
								cls:'font-medium font-grey multilineinfo',
								flex:1,
								margin:'0 5 0 30',
							}
						]
					}
	    		]
	    	},

			{
				xtype: 'toolbar',
				docked: 'bottom',
				itemId:'toolbar',
				items: [
					{
						xtype: 'spacer',
						flex:1
					},{
						xtype: 'button',
						text: '加入日程',
						ui: 'normal',
						iconCls:'action',
						itemId: 'btnAdd'
					},{
						xtype: 'button',
						text: '收藏',
						ui: 'normal',
						iconCls:'favorites',
						itemId: 'btnFavorite'
					},{
						xtype: 'button',
						text: '评论',
						ui: 'normal',
						iconCls:'compose',
						itemId: 'btnComment'
					},{
						xtype: 'button',
						text: '分享',
						ui: 'normal',
						iconCls:'action',
						itemId: 'btnShare'
					}
				]
			}
        ]
    },
    
    setRecord:function(record){
    	var me = this;
    	if(record != null){
    		var image = me.down('#image');
			image.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:150px'>");
 	 	   	
 	 	   	var address = me.down('#address');
 	 	   	address.setHtml(record.get('address'));
 	 	   	
 	 	   	var intro = me.down('#intro');
 	 	   	intro.setHtml(record.get('intro'));
    	}
    }
});

