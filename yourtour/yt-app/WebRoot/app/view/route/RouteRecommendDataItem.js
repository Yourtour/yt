Ext.define('YourTour.view.route.RouteRecommendDataItem', {
    extend: 'YourTour.view.widget.VDataItem',
    xtype: 'RouteRecommendDataItem',
    requires:['Ext.Panel', 'Ext.Img', 'YourTour.view.widget.XIcon', 'YourTour.view.widget.XField','YourTour.view.widget.XLabel','YourTour.view.widget.HSpacer'],
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
						itemId : 'expertImage',
						xtype : 'image',
						mode : 'tag',
						top:0,
						left:5,
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
								itemId: 'lineName',
								xtype: 'label',
								style:'color:#FFFFFF;font-size:14px; font-weight:bold;'
							},

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
				xtype:'xfield',
				itemId:'feature',
				cls:'row underline font-medium font-grey',
				html:'徒步旅行，适合徒步爱好者。'
			},

			{
				xtype:'panel',
				layout:'hbox',
				cls:'row underline',
				items:[
					{
						xtype:'xfield',
						itemId:'duration',
						html:'10天'
					},

					{
						xtype:'xfield',
						itemId:'expense',
						margin:'0 0 0 10',
						html:'¥ 450元/人/天'
					},
				]
			},

			{
				xtype:'hspacer'
			}
    	]
    },
    
    updateRecord: function(record) {
       var me = this;
       if(record){
       	   var imageUrlEl = me.down('#imageUrl');
	 	   imageUrlEl.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "' style='width:100%; max-height:150px'>");
	 	   
	 	   var lineName = me.down('#lineName');
		   lineName.setHtml(record.get('lineName'));

		   var expert = record.userStore.first();
		   var expertImage = me.down('#expertImage');
		   expertImage.setHtml("<img src='" + YourTour.util.Context.getImageResource(expert.get('imageUrl'), 'medium') + "' style='width:64px; height:64px'>");
	 	}
    }   
});

