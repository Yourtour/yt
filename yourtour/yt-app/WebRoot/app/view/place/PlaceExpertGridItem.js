Ext.define('YourTour.view.place.PlaceExpertGridItem', {
	extend: 'YourTour.view.widget.grid.component.DataItem',
    xtype: 'PlaceExpertGridItem',
    config: {
        layout:'hbox',
        height:70,
        padding:'10 10 5 10',
        cls:'underline',
        items: [
			{
				xtype:'ximage',
                itemId:'imageUrl',
                imageCls:'img-user-logo-48'
			},

            {
                xtype:'panel',
                layout:'vbox',
                flex:1,
                items:[
                    {
                        xtype:'xfield',
                        itemId:'nickName',
                        underline:false
                    }
                ]
            }
        ]
    }
});
