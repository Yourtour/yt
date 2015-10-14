Ext.define('YourTour.view.line.LineRecommendItem', {
    extend: 'YourTour.view.widget.VDataItem',
    xtype: 'LineRecommendItem',
    requires:['Ext.Panel', 'Ext.Img', 'YourTour.view.widget.XField','YourTour.view.widget.XLabel'],
    config: {
    	items:[
    		{
    			xtype:'panel',
    			layout:'vbox',
    			height:150,
    			items:[
		    		{
		   				itemId : 'imageUrl',
						xtype : 'image',
						mode : 'tag'
		    		},
		    		
		    		{
		    	   		itemId : 'name',
			    		xtype : 'label',
			    		cls:'h2',
			    		style:'background:grey;opacity:0.5; color:#fff; font-size:14px; font-weight:bold; width:100%; height:40px; line-height:40px; text-align:center',
			    		docked:'bottom',
			    		bottom:0
			    	}
		    	]
    		},
    		
    		{
    			xtype:'panel',
    			layut:'vbox',
    			padding:5,
    			itemId:'lineInfo',
	    		items:[
			   		{
			   			xtype:'panel',
			   			layout:'hbox',
			   			cls:'row underline raty',
			   			items:[
			   				{
				   				xtype:'xlabel',
				   				html:'推荐指数:',
				   				flex:1
				   			},
				   			{
				   				xtype:'image',
				    			src:'resources/images/raty_32.png',
				    			flex:1,
				    			mode:'tag'
				   			},
				   			
				   			{
				   				xtype:'xlabel',
				   				html:'评价指数:',
				   				flex:1
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
			   			cls:'row',
			   			items:[
				   			{
				   				xtype:'xlabel',
				   				html:'推荐月份:',
				   				flex:1
				   			},
				   			{
				   				xtype:'xfield',
				   				flex:1,
				   				html:'7、8、9月'
				   			},
				   			{
				   				xtype:'xlabel',
				   				flex:1,
				   				html:'推荐天数:'
				   			},
				   			{
				   				xtype:'xfield',
				   				flex:1, 
				   				html:'10天'
				   			}
			   			]
			   		}
				]
    	   }
    	]
    },
    
    updateRecord: function(record) {
       var me = this;
       
       if(record){
       	   var imageUrlEl = me.down('#imageUrl');
	 	   imageUrlEl.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:150px'>");
	 	   
	 	   var nameEl = me.down('#name');
	 	   nameEl.setHtml(record.get('name'));
	 	}
    }   
});

