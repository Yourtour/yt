Ext.define('YourTour.view.home.HomeMainView', {
    extend: 'Ext.Container',
    xtype: 'HomeMainView',
    requires:['YourTour.view.widget.XZoomCarousel'],
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
                        xtype:'xzoomcarousel',
                        itemId:'activityCarousel',
                        height: 200,
                        defaults:{
                            height: '100%',
                            width: '100%'
                        },
                        items:[
                            {
                                xtype: 'img',
                                src: 'resources/images/img_home.jpg',
                                mode:'tag'

                            },
                            {
                                xtype: 'img',
                                src: 'resources/images/img_personal_view.jpg',
                                mode:'tag'
                            },
                            {
                                xtype: 'img',
                                src: 'resources/images/image_member.jpg',
                                mode:'tag'
                            },
                            {
                                xtype: 'img',
                                src: 'resources/images/img_personal_view.jpg',
                                mode:'tag'
                            },
                            {
                                xtype: 'img',
                                src: 'resources/images/image_member.jpg',
                                mode:'tag'
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

    /*initialize:function(){
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
    }*/
});

