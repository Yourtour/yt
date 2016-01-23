Ext.define('YourTour.view.resource.ResourceSelectionView', {
    extend: 'YourTour.view.widget.XPage',
    xtype: 'SelectionListView',
    requires:['YourTour.view.widget.XToolbar', 'YourTour.view.widget.XButton', 'YourTour.view.widget.XSearchField','YourTour.view.widget.XDataView','YourTour.view.resource.ResourcePlayDataItem'],
    config: {
    	id:'ResourceSelectionView',
    	layout:'card',
        items: [
            {    
				xtype: 'xheaderbar',
				title:'',
				items:[
					{
						xtype: 'xsearchfield',
						name: 'query',
						flex:1,
						html:'搜索游徒旅游资源',
						align:'right'
					},
					{
						xtype: "xbutton",
						ui: "normal",
						icon:'resources/icons/icon_header_filter.png',
						itemId:'filter',
						align:'right'
					}
				]
			},

			{
				xtype: 'xprocessing'
			},

			{
				xtype: 'xpagebody',
				layout: 'vbox',
				items: [
					{
						xtype:'xdataview',
						itemId:'resourceList',
						flex:1,
						defaultType: 'ResourcePlayDataItem'
					}
				]
			}
        ]
    }
});

