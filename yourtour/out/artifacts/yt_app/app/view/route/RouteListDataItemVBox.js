Ext.define('YourTour.view.route.RouteListDataItemVBox', {
	extend: 'YourTour.view.widget.XDataItem',
    xtype: 'RouteListDataItemVBox',
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
				minHeight:'150px',
				maxHeight:'150px',
				layout:'vbox',
				items:[
					{
						xtype:'panel',
						layout:'hbox',
						docked:'bottom',
						bottom:'0',
						cls:'x-xopacity row font-medium font-white',
						padding:'10',
						width:'100%',
						items:[
							{
								xtype:'label',
								itemId:'duration'
							},
							{
								xtype:'label',
								itemId:'topic'
							}
						]
					}
				]
			},

			{
				xtype: 'xmultifield',
				itemId: 'reason'
			}
		]
    },

	updateRecord:function(record){
		var me = this;
		if(record){
			var dataview = me.dataview || me.getDataview(), store = dataview.getStore();
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

			var reason = me.down('#reason');
			reason.setText('<span class="font-striking font-medium font-bold tab-space-right">推荐理由:</span>' + Ext.String.ellipsis(record.get('reason'), 76, false));

			var duration = me.down('#duration');
			duration.setHtml(record.get('duration'));

			var topic = me.down('#topic');
			topic.setHtml(record.get('topic'));
		}
	}
});

