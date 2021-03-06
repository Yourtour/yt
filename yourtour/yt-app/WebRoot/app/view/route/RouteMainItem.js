Ext.define('YourTour.view.route.RouteMainItem', {
    extend: 'Ext.Container',
    xtype: 'RouteMainItem',
    requires:['Ext.Img'],
    config: {
		record:null,
    	layout:'vbox',
        style:'background-color:grey',
        items: [
            {
                xtype:'panel',
                itemId:'item',
                margin:'20 0 0 0',
                items:[
                    {
                        xtype:'xfield',
                        itemId:'name',
                        underline:false,
                        fieldCls:'font-striking font-bold font-large-extra'
                    },
                    {
                        xtype: 'xmultifield',
                        itemId: 'lineName',
                        icon:'icon-name',
                        underline:false,
                        fieldCls:'font-white'
                    },

                    {
                        xtype: 'xfield',
                        itemId: 'time',
                        icon:'icon-time',
                        underline:false,
                        margin:'0 0 20 0',
                        fieldCls:'font-white'
                    }
                ]
            }
        ]
    },

    updateRecord: function(record) {
        var me = this;
        if(record){
            var item = me.down('#item');
            var url = YourTour.util.Context.getImageResource(record.get('imageUrl'));
            var style={};
            style['background-image'] = 'url(' + url +  ')';
            style['background-repeat'] = 'no-repeat';
            style['background-position'] = 'center center';
            style['background-size'] = '98% auto';
            me.setStyle(style);

            var name = me.down('#name');
            name.setText(record.get('name'));

            var lineName = me.down('#lineName');
            lineName.setText(record.get('lineName'));

            var time = me.down('#time');
            time.setText(record.get('startDate') +'-' + record.get('endDate') + '  合计：' + record.get('duration')+'天');
 	 	}
     }   
});

