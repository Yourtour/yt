Ext.define('YourTour.view.place.PlaceMainItem', {
	extend: 'Ext.Container',
	xtype:'PlaceMainItem',
    config: {
		style:'background-color:grey',
        items: [

        ]
    },

	updateData:function(data){
		var me = this;
		var url = YourTour.util.Context.getImageResource(data);
		var style={};
		style['background-image'] = 'url(' + url +  ')';
		style['background-repeat'] = 'no-repeat';
		style['background-position'] = 'center center';
		style['background-size'] = '98% auto';
		me.setStyle(style);
	}
});

