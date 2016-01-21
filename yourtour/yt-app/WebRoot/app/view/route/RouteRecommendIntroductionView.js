Ext.define('YourTour.view.route.RouteRecommendIntroductionView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar','YourTour.view.widget.XNavigation', 'YourTour.view.route.RouteRecommendScheduleItem', 'YourTour.view.route.RouteRecommendIntroductionItem','YourTour.view.expert.ExpertRecommendIntroItem'],
    config: {
    	id:'RouteRecommendIntroductionView',
		layout:'card',
        items: [
        	{    
				xtype: 'xheaderbar',
				title:'行程介绍'
			},

			{
				xtype:'xprocessing'
			},

			{
				xtype: 'RouteRecommendIntroductionItem',
				itemId: 'introductionItem'
			},

			{
				xtype: 'ExpertRecommendIntroItem',
				itemId: 'expertItem'
			},

			{
				xtype: 'RouteRecommendScheduleItem',
				itemId: 'scheduleItem'
			},

			{
				xtype:'xnavigation',
				itemId:'navigation',
				docked: 'bottom',
				bottom:30,
				left: 10,
				defaults:{
					style:'width:48px;height:48px;',
					mode : 'tag'
				},
				items:[
					{
						xtype:'image',
						itemId:'btnIntroduction',
						src:'resources/icons/icon_route.png'
					},

					{
						xtype:'image',
						itemId:'btnExpert',
						src:'resources/icons/icon_partner.png'
					},

					{
						xtype:'image',
						itemId:'btnSchedule',
						src:'resources/icons/icon_expense.png'
					}
				]
			}
        ]
    },

	initialize:function(){
		this.callParent(arguments);

		var me = this;

		var btnIntroduction = me.down('#btnIntroduction');
		btnIntroduction.on({
			scope:btnIntroduction,
			tap:function(){
				me.changeItem('introductionItem');
			}
		});

		var btnExpert = me.down('#btnExpert');
		btnExpert.on({
			scope:btnIntroduction,
			tap:function(){
				me.changeItem('expertItem');
			}
		});

		var btnSchedule = me.down('#btnSchedule');
		btnSchedule.on({
			scope:btnIntroduction,
			tap:function(){
				me.changeItem('scheduleItem');
			}
		});
	},

	changeItem:function(itemId){
		var me = this;

		var introductionItem = me.down('#introductionItem');
		itemId == 'introductionItem' ? introductionItem.show() : introductionItem.hide();

		var expertItem = me.down('#expertItem');
		itemId == 'expertItem' ? expertItem.show() : expertItem.hide();

		var scheduleItem = me.down('#scheduleItem');
		itemId == 'scheduleItem' ? scheduleItem.show() : scheduleItem.hide();

		var navigation = me.down('#navigation');
		navigation.collapse();
	},

	hideProcessing:function() {
		var me = this;
		var introductionItem = me.down('#introductionItem');
		this.setActiveItem(introductionItem);
	}
});

