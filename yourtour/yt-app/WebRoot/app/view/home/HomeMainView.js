Ext.define('YourTour.view.home.HomeMainView', {
    extend: 'YourTour.view.widget.XPage',
    requires: ['Ext.Carousel', 'Ext.Panel', 'Ext.Img', 'Ext.DataView', 'YourTour.view.widget.XPanel', 'YourTour.view.widget.XPlainButton', 'YourTour.view.widget.XField', 'YourTour.view.widget.XLabel', 'YourTour.view.widget.XHeaderBar', 'YourTour.view.line.LineResourceItem'],
    xtype: 'HomeMainView',
    config: {
        id: 'HomeMainView',
        layout: 'vbox',
        items: [
            {
                xtype: 'xheaderbar',
                title: '首页',
                backButton: false,
                items:[
                    {
                        xtype: 'xbutton',
                        itemId: 'btnPlace',
                        align: 'right',
                        iconAlign:'right',
                        text:'目的地',
                        icon:'resources/icons/16/icon_arrow_down.png'
                    }
                ]
            },
            {
                xtype: 'carousel',
                itemId: 'placeCarousel',
                height: 200
            },

            {
                xtype:'xspacer'
            },

            {
                xtype: 'xlabel',
                cls: 'row font-large underline nav-arrow',
                html: '目的地'
            },

            {
                xtype:'xspacer'
            },

            {
                xtype: 'xlabel',
                cls: 'row font-large underline nav-arrow',
                html: '新荐线路'
            },

            {
                xtype:'xspacer'
            },

            {
                xtype: 'xlabel',
                cls: 'row font-large underline nav-arrow',
                html: '新荐达人'
            }
        ]
    },

    scroll: function (scroller, x, y) {
        var headerbar = this.down('#headerbar');
        if(y < 10){
            headerbar.show();
        }else{
            headerbar.hide();
        }
    }
});

