Ext.define('YourTour.view.home.HomeMainView', {
    extend: 'Ext.Container',
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
                        xtype: "xbutton",
                        icon:'resources/icons/24/icon_header_position.png',
                        itemId:'btnPosition',
                        align:'right'
                    }
                ]
            },

            {
                xtype: 'xpagebody',
                layout: 'vbox',
                items: [
                    {
                        xtype:'xcarousel',
                        itemId:'activityCarousel',
                        timer:2000,
                        height: 200,
                        items:[
                            {
                                xtype: 'img',
                                src: 'resources/images/img_home.jpg',
                                height: 200
                            },
                            {
                                xtype: 'img',
                                src: 'resources/images/img_personal_view.jpg',
                                height: 200
                            },
                            {
                                xtype: 'img',
                                src: 'resources/images/image_member.jpg',
                                height: 200
                            }
                        ]
                    },


                    {
                        xtype: 'xdataview',
                        itemId: 'routeList',
                        scrollable: null,
                        defaultType: 'RouteListDataItemHBox'
                    }
                ]
            }
        ]
    },

    initialize:function(){
        this.callParent(arguments);

        var carousel = this.down('#activityCarousel');
        this.on({
            activate:function() {
                carousel.reset();
            },

            deactivate:function() {
                carousel.pause();
            }
        })
    }
});

