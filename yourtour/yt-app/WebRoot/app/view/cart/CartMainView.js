Ext.define('YourTour.view.cart.CartMainView', {
	extend: 'Ext.Container',
    requires:['YourTour.view.widget.XHeaderBar','YourTour.view.widget.XDataView','YourTour.view.place.PlaceMainDataItem'],
    xtype:'CartMainView',
	config: {
    	id:'CartMainView',
        items: [
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				backButton: false
			},

			{
				xtype: 'xpagebody',
				itemId:'pagebody',
				layout: 'card',
				items: [
					{
						xtype:'panel',
						itemId:'entryPanel',
						layout:'vbox',
						items:[
							{
								xtype:'carousel',
								itemId:'imageCarousel',
								flex:7
							},
							{
								xtype:'xdataview',
								itemId:'resourceList',
								defaultType: 'PlaceMainDataItem',
								direction:'horizontal',
								flex:1
							}
						]
					},{
						xtype:'panel',
						itemId:'navPanel',
						layout:'vbox',
						items:[
							{
								xtype:'panel',
								layout:'vbox',
								docked:'bottom',
								padding:'20 10',
								items:[
									{
										xtype:'panel',
										layout:'hbox',
										defaults:{
											flex:1,
											cls:'x-xnav-button'
										},
										items:[
											{
												xtype: 'xlabel',
												html: '导览',
												style:'background-image: url(./resources/icons/48/icon_intro.png);'
											},
											{
												xtype: 'xlabel',
												itemId:'placeLines',
												html: '行程',
												style:'background-image: url(./resources/icons/48/icon_line.png);'
											},
											{
												xtype: 'xlabel',
												itemId:'placeExperts',
												html: '达人',
												style:'background-image: url(./resources/icons/48/icon_expert.png);'
											},
											{
												xtype: 'xlabel',
												itemId:'placeScene',
												html: '游玩',
												style:'background-image: url(./resources/icons/48/icon_scene.png);'
											}
										]
									},

									{
										xtype:'panel',
										layout:'hbox',
										margin:'10 0 0 0',
										defaults:{
											flex:1,
											cls:'x-xnav-button'
										},
										items:[
											{
												xtype: 'xlabel',
												itemId:'placeAlongs',
												html: '捡人',
												style:'background-image: url(./resources/icons/48/icon_along.png);'
											},
											{
												xtype: 'xlabel',
												html: '晒一晒',
												style:'background-image: url(./resources/icons/48/icon_live.png);'
											},
											{
												xtype: 'xlabel',
												itemId:'placeCommunity',
												html: '社区',
												style:'background-image: url(./resources/icons/48/icon_chat.png);'
											},
											{
												xtype: 'xlabel',
												itemId:'placePosition'
											}
										]
									}
								]
							}
						]
					}
				]
			}
        ]
    },

	initialize:function(){
		this.callParent(arguments);
		var me = this, pagebody = me.down('#pagebody'), entryPanel = pagebody.down('#entryPanel'), navPanel = pagebody.down('#navPanel');

		entryPanel.element.on('swipe', function(e, target, options, eOpts){
			if (e.direction === 'up' && e.distance >= 20) {
				pagebody.animateActiveItem(navPanel,{ type: 'slide', direction: 'up' });
			}
		});

		navPanel.element.on('swipe', function(e, target, options, eOpts){
			if (e.direction === 'down' && e.distance >= 20) {
				pagebody.animateActiveItem(entryPanel,{ type: 'slide', direction: 'down' });
			}
		})
	}
});

