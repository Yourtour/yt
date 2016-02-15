Ext.define('YourTour.view.place.PlaceMainView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Img','YourTour.view.widget.XHeaderBar','YourTour.view.place.PlaceExpertItem'],
    config: {
    	id:'PlaceMainView',
      	layout:'vbox',
        items: [
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'上海'
			},

			{
				xtype: 'xpagebody',
				layout: 'vbox',
				items: [
					{
						xtype: 'image',
						itemId: 'image',
						height: 150,
						src: 'resources/images/shanghai.jpg'
					},

					{xtype: 'xspacer'},

					{
						xtype: 'panel',
						layout: 'hbox',
						height: 150,
						defaults: {
							flex: 1
						},
						items: [
							{
								xtype: 'xpanel',
								cls:'icon-place-chat',
								itemId: 'placeChatRoom',
								layout:'vbox',
								style: 'border-right:1px solid #EDEDED'
							},

							{
								xtype: 'panel',
								layout: 'vbox',
								defaults: {
									flex: 1
								},
								items: [
									{
										xtype: 'image',
										src: 'resources/icons/icon_place_intro.png'
									},

									{
										xtype: 'panel',
										cls: 'icon-place-live'
									}
								]
							}
						]
					},

					{
						xtype:'PlaceExpertItem',
						itemId:'placeExpertItem'
					},

					{
						xtype:'panel',
						itemId:'placeRouteItem',
						layout:'vbox',
						items:[
							{
								xtype: 'xspacer'
							},
							{
								xtype: 'xlabel',
								itemId:'placeMorelines',
								cls: 'underline x-xlabel-normal',
								indicator:'nav-arrow',
								html: '游徒行程'
							}
						]
					},

					{
						xtype: 'panel',
						itemId:'placeAlongItem',
						layout:'vbox',
						items:[
							{
								xtype: 'xspacer'
							},
							{
								xtype: 'xlabel',
								itemId:'placeMorelines',
								cls: 'underline x-xlabel-normal',
								indicator:'nav-arrow',
								html: '游徒结伴'
							}
						]
					}
				]
			}
        ]
    }
});

