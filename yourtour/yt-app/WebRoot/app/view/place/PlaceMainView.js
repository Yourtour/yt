Ext.define('YourTour.view.place.PlaceMainView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Img','YourTour.view.widget.XHeaderBar','YourTour.view.widget.XGridView'],
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
						xtype: 'xspacer'
					},
					{
						xtype: 'xlabel',
						cls: 'underline x-xlabel-normal',
						indicator:'nav-arrow',
						html: '服务达人'
					},

					{
						xtype: 'xgridview',
						itemId:'experts',
						height:150,
						itemTpl:new Ext.XTemplate('<div class="row">张皓然</div>')
					},

					/*{
						xtype: 'xspacer'
					},
					{
						xtype: 'xlabel',
						cls: 'underline x-xlabel-normal',
						indicator:'nav-arrow',
						html: '旅行线路'
					},

					{
						xtype: 'panel',
						layout: 'vbox',
						height: 150,
						items: []
					},

					{
						xtype: 'xspacer'
					},
					{
						xtype: 'xlabel',
						cls: 'underline x-xlabel-normal',
						indicator:'nav-arrow',
						html: '一起旅游'
					},

					{
						xtype: 'panel',
						layout: 'vbox',
						height: 150,
						items: []
					}*/
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

