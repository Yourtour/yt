Ext.define('YourTour.view.expert.ExpertGridDataItem', {
	extend: 'YourTour.view.widget.grid.component.DataItem',
    requires:['YourTour.view.widget.XImage'],
    xtype: 'ExpertGridDataItem',
    config: {
        layout:'hbox',
        height:70,
        padding:'10 10 5 10',
        cls:'underline',
        items: [
			{
				xtype:'xuserlogo',
                itemId:'imageUrl',
                cls:'img-small',
                binding:'profile.imageUrl'
			},

            {
                xtype:'panel',
                layout:'vbox',
                flex:1,
                items:[
                    {
                        xtype:'xfield',
                        itemId:'nickName',
                        underline:false,
                        binding:'profile.nickName'
                    }
                ]
            }
        ]
    }
});

