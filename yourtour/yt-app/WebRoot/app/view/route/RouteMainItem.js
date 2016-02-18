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
                docked:'bottom',
                style: 'background-color:grey;opacity:0.8;',
                items:[
                    {
                        xtype: 'xmultifield',
                        itemId: 'lineName',
                        paddingLeft:0,
                        icon:'icon-name',
                        underline:false,
                        fieldCls:'font-white'
                    },

                    {
                        xtype: 'xfield',
                        itemId: 'time',
                        icon:'icon-time',
                        underline:false,
                        fieldCls:'font-white'
                    },

                    {
                        xtype: 'xmultifield',
                        itemId: 'impression',
                        icon:'icon-impression',
                        ifNull:'赶快记录下你的旅行印象吧.........',
                        underline:false,
                        ellipsis:{
                            size:80,
                            expandable:true
                        },
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

            var lineName = me.down('#lineName');
            lineName.setText(record.get('lineName'));

            var time = me.down('#time');
            time.setText(record.get('startDate') +'-' + record.get('endDate') + '  合计：' + record.get('duration')+'天');

            var impression = me.down('#impression');
            impression.setText(record.get('impression'));
 	 	}
     }   
});

