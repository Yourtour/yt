Ext.define('YourTour.view.home.HomeMainView', {
    extend: 'Ext.Container',
    xtype: 'HomeMainView',
    requires:['YourTour.view.widget.XHeaderBar2'],
    config: {
        id: 'HomeMainView',
        layout: 'card',
        items: [
            {
                xtype:'xheaderbar2',
                title:'推荐'
            },
            {
                xtype:'container',
                itemId:'container',
                layout:'vbox',
                scrollable: {
                    direction: 'vertical',
                    indicators: false,
                    directionLock: true,
                    momentumEasing:  {
                        bounce: {
                            acceleration: 0.0001,
                            springTension: 0.9999
                        }
                    },
                    outOfBoundRestrictFactor: 0
                },
                items:[
                    {
                        xtype:'xcarousel',
                        itemId:'bannerCarousel',
                        minHeight:200,
                        items:[
                            {
                                xtype:'img',
                                src:'resources/images/img_home.jpg'
                            },

                            {
                                xtype:'img',
                                src:'resources/images/img_personal_view.jpg'
                            },
                        ]
                    }
                ]
            }
        ]
    },

    initialize:function(){
        this.callParent(arguments);

        var me = this, container = me.down('#container');

        container.getScrollable().getScroller().on({
            scroll:'onScroll',
            scrollstart:'onScrollStart',
            scope:me
        });
    },

    onScroll:function(scroller, x, y){
        var me = this, headerbar = me.down('#headerbar');

        if(y < 10){
            if(headerbar.hidden) return;
            headerbar.removeCls('x-xheaderbar-color');
        }else if( y > 10 && y < 30){
            headerbar.addCls('x-xheaderbar-color');

            var startY = this.startY, opacity;
            if( y > startY){ //向下滚动
                opacity = 100 * (y-10) / 20;
            }else{ //向上滚动
                opacity = 100 * Math.abs((10 - y) / 20);
            }
            headerbar.setStyle('-webkit-filter:opacity(' + opacity + '%)');
            headerbar.show();
        }else{
            headerbar.addCls('x-xheaderbar-color');
            headerbar.setStyle('-webkit-filter:opacity(100%)');
            headerbar.show();
        }
    },

    onScrollStart:function(scroller, x, y){
        this.startY = y;
    }
});

