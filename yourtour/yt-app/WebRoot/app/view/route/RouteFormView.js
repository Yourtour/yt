Ext.define('YourTour.view.route.RouteFormView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar','YourTour.view.widget.XNavigation', 'YourTour.view.route.RouteFormScheduleItem', 'YourTour.view.route.RouteFormOverviewItem'],
    config: {
    	id:'RouteFormView',
		layout:'card',
        items: [
        	{    
				xtype: 'xheaderbar',
				title:'行程介绍',
				items:[
					{
						xtype:'xbutton',
						itemId:'btnOverview',
						text:'概述',
						align:'right',
						padding:'0 10 0 20'
					},

					{
						xtype:'xbutton',
						itemId:'btnSchedule',
						text:'行程',
						align:'right',
						padding:'0 10 0 10'
					},

					{
						xtype:'xbutton',
						itemId:'btnExpert',
						text:'达人',
						align:'right',
						padding:'0 10 0 10'
					}
				]
			},

			{
				xtype:'xprocessing'
			},

			{
				xtype: 'xpagebody',
				layout: 'card',
				items: [
					{
						xtype: 'RouteFormOverviewItem',
						itemId: 'overviewItem'
					},

					{
						xtype: 'RouteFormScheduleItem',
						itemId: 'scheduleItem'
					}
				]
			}
        ]
    },

	/*initialize:function(){
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
	},*/

	hideProcessing:function() {
		this.callParent(arguments);

		var me = this;
		var introductionItem = me.down('#introductionItem');
		this.setActiveItem(introductionItem);
	}
});

