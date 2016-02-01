Ext.define('YourTour.view.place.PlaceMainView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Img','YourTour.view.widget.XHeaderBar','YourTour.view.place.PlaceExpertItem','YourTour.view.place.PlaceLineItem','YourTour.view.place.PlaceAlongItem'],
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
								xtype: 'panel',
								cls:'icon-place-chat',
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
						xtype:'PlaceLineItem',
						itemId:'placeLineItem'
					},

					{
						xtype: 'PlaceAlongItem',
						itemId:'placeAlongItem'
					}
				]
			}
        ]
    },

	initialize:function(){
		var me = this;
		me.callParent(arguments);

		var store = Ext.create('YourTour.store.AlongListStore');
		var grid = me.down('#experts');
		grid.setStore(store);
	}
});

