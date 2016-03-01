Ext.define('YourTour.view.home.BestListItemView', {
	extend: 'Ext.dataview.component.DataItem',
    requires:['Ext.Panel', 'Ext.Img', 'YourTour.view.widget.XField','YourTour.view.widget.XLabel'],
    xtype: 'BestListItemView',
    config: {
    	layout:'vbox',
    	cls:'space-bottom',
        items: [
		   	{
   				itemId : 'imageUrl',
				xtype : 'image',
				mode : 'tag'
    		},
    		
    		{
    			xtype:'panel',
    			layout:'vbox',
    			padding:'0 5 5 5',
    			defaults:{
    	    		margin:'5 0 0 0'
    	    	},
    			items:[
					{
						itemId:'name',
						xtype:'xlabel'
					},
					
					{
						xtype:'panel',
						layout:'hbox',
						items:[
							{
				   				xtype:'xlabel',
				   				margin:'0 10 0 0',
				   				html:'推荐指数:'
				   			},
				   			
				   			{
				   				xtype:'image',
				    			src:'resources/images/raty_32.png',
				    			flex:1,
				    			mode:'tag'
				   			},
				   			
				   			{
				   				xtype:'xlabel',
				   				margin:'0 10 0 0',
				   				html:'评价指数:'
				   			},
				   			{
				   				xtype:'image',
				    			src:'resources/images/raty_32.png',
				    			flex:1,
				    			mode:'tag'
				   			}
						]
					},
					
					{
						xtype:'panel',
						layout:'hbox',
						items:[
							{
								xtype:'xfield',
								html:'综合排名：'
							},
							{
								itemId:'rank',
								xtype:'xfield'
							}
						]
					},
					
					{
						xtype:'panel',
						layout:'hbox',
						items:[
							{
								xtype : 'image',
								width:20,
								src:'resources/icons/icon_thumbup.png'	
							},
							{
								xtype:'xfield',
								itemId:'thumbupNum',
								margin:'0 0 0 5',
								flex:1
							},
							
							{
								xtype : 'image',
								width:20,
								src:'resources/icons/icon_button_favorite.png'
							},
					        {
					        	xtype:'xfield',
					        	itemId:'favoriteNum',
					        	margin:'0 0 0 5',
					        	flex:1
					        },
					        
					        {
								xtype : 'image',
								width:20,
								src:'resources/icons/icon_share.png'	
							},
					        {
					        	xtype:'xfield',
					        	itemId:'shareNum',
					        	margin:'0 0 0 5',
					        	flex:1
					        },
					        
					        {
								xtype : 'image',
								width:20,
								src:'resources/icons/icon_comment.png'	
							},
					        {
					        	xtype:'xfield',
					        	itemId:'commentNum',
					        	margin:'0 0 0 5',
					        	flex:1
					        }
						]
					}
    			]
    		}
        ]
    },
    
    updateRecord: function(record){
    	var me = this;
       	if(record){
       	   var imageUrlEl = me.down('#imageUrl');
	 	   imageUrlEl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:75'>");
	 	   
	 	   var nicknameEl = me.down('#name');
	 	   nicknameEl.setHtml(record.get('name'));
	 	   
	 	   var rankEl = me.down('#rank');
	 	   rankEl.setHtml(record.get('rank'));
	 	   
	 	   var thumbupEl = me.down('#thumbupNum');
	 	   thumbupEl.setHtml(record.get('thumbupNum'));
	 	   
	 	   var favoriteNum = me.down('#favoriteNum');
	 	   favoriteNum.setHtml(record.get('favoriteNum'));
	 	   
	 	   var shareNumEl = me.down('#shareNum');
	 	   shareNumEl.setHtml(record.get('shareNum'));
	 	   
	 	   var commentNumEl = me.down('#commentNum');
	 	   commentNumEl.setHtml(record.get('commentNum'));
	 	}
    }
});

