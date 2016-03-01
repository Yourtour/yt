Ext.define('YourTour.view.place.PlaceCommunityView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar','YourTour.view.widget.XDataView','YourTour.view.place.PlaceMainDataItem'],
	config: {
    	id:'PlaceCommunityView',
      	layout:'card',
        items: [
			{    
				xtype: 'xheaderbar',
				title:'社区'
			},

			/*{
				xtype: 'xprocessing'
			},*/

			{
				xtype: 'xpagebody',
				layout: 'vbox',
				items: [
					{

					},

					{xtype:'xspacer'},

					{
						xtype: 'xlabel',
						html:'我的主题',
						cls:'underline font-medium icon-tag'
					},

					{xtype:'xspacer'},

					{
						xtype: 'xlabel',
						html:'关注主题',
						cls:'underline font-medium icon-tag'
					},

					{xtype:'xspacer'},

					{
						xtype: 'xlabel',
						html:'最热主题',
						cls:'underline font-medium icon-tag'
					},

					{xtype:'xspacer'},

					{
						xtype: 'xlabel',
						html:'最新主题',
						cls:'underline font-medium icon-tag'
					}
				]
			}
        ]
    }
});

