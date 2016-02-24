Ext.define('YourTour.view.resource.ResourceSelectionView', {
    extend: 'YourTour.view.widget.XPage',
    xtype: 'SelectionListView',
    requires:['YourTour.view.widget.XToolbar', 'YourTour.view.widget.XButton', 'YourTour.view.widget.XSearchField','YourTour.view.widget.XDataView','YourTour.view.resource.ResourceListDataItem'],
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
						icon:'resources/icons/24/icon_header_filter.png',
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
						xtype:'xfield',
						itemId:'reference',
						cls:'icon-position icon-space',
						placeHolder:'选择当前位置'
					},
					{
						xtype:'xdataview',
						itemId:'resourceList',
						flex:1,
						defaultType: 'ResourceListDataItem'
					}
				]
			},

			{
				xtype: 'xtoolbar',
				docked: 'bottom',
				itemId: 'toolbar',
				items: [
					{
						xtype:'spacer',
						flex:1
					},
					{
						xtype: 'xbutton',
						itemId: 'btnConsult',
						cls:'x-button-primary',
						text:'开始咨询',
						flex:1
					}
				]
			}
        ]
    }
});

