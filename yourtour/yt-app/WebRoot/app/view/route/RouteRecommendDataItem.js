Ext.define('YourTour.view.route.RouteRecommendDataItem', {
	extend: 'YourTour.view.widget.XDataItem',
    xtype: 'RouteRecommendDataItem',
    requires:['Ext.Panel', 'Ext.Img', 'YourTour.view.widget.XField','YourTour.view.widget.XLabel'],
    config: {
		layout:'vbox',
    	items:[
			{
				xtype: 'xfield',
				itemId: 'lineName',
				indicator:'nav-arrow'
			},
			{
				xtype:'panel',
				itemId:'image',
				maxHeight:'150px',
				minHeight:'150px'
			}
		]
    },

	updateRecord:function(record){
		var me = this;
		if(record){
			var me = this, dataview = me.dataview || me.getDataview(), store = dataview.getStore();
			if (store.indexOf(record) > 0) {
				me.insert(0, {xtype:'xspacer'});
			}

			var image = me.down('#image');
			var url = YourTour.util.Context.getImageResource(record.get('imageUrl'));
			var style={};
			style['background-image'] = 'url(' + url +  ')';
			style['background-repeat'] = 'no-repeat';
			style['background-position'] = 'center center';
			style['background-size'] = '100% auto';
			image.setStyle(style);

			var lineName = me.down('#lineName');
			lineName.setText(record.get('lineName'));
		}
	}
});

