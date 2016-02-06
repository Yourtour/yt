Ext.define('YourTour.view.expert.ExpertView', {
    extend: 'YourTour.view.widget.XPage',
    requires: [ 'Ext.Carousel', 'YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.XLabel','YourTour.view.expert.ExpertViewIntroItem',
                'YourTour.view.expert.ExpertViewServiceItem','YourTour.view.expert.ExpertViewRecommendItem',
                'YourTour.view.expert.ExpertViewParticipateItem','YourTour.view.expert.ExpertViewCommentItem'
              ],
    config: {
        id: 'ExpertView',
        layout: 'card',
        items: [
            {
                xtype: 'xheaderbar',
                title: '达人',
                items:[
                    {
                        xtype:'xbutton',
                        text:'介绍',
                        align:'right',
                        padding:'0 15 0 20'
                    },

                    {
                        xtype:'xbutton',
                        text:'服务',
                        align:'right',
                        padding:'0 15 0 15'
                    },

                    {
                        xtype:'xbutton',
                        text:'推荐',
                        align:'right',
                        padding:'0 15 0 15'
                    },

                    {
                        xtype:'xbutton',
                        text:'过往',
                        align:'right',
                        padding:'0 15 0 15'
                    },

                    {
                        xtype:'xbutton',
                        text:'评论',
                        align:'right',
                        padding:'0 20 0 15'
                    }
                ]
            },

            {
                xtype: 'xprocessing'
            },

            {
                xtype: 'xpagebody',
                items: [
                    {
                        xtype:'carousel',
                        itemId:'expertCarousel',
                        indicator:false,
                        flex:1,
                        items:[
                            {xtype:'ExpertViewIntroItem', itemId:'expertViewIntroItem'},
                            {xtype:'ExpertViewServiceItem', itemId:'expertViewServiceItem'},
                            {xtype:'ExpertViewRecommendItem', itemId:'expertViewRecommendItem'},
                            {xtype:'ExpertViewParticipateItem', itemId:'expertViewParticipateItem'},
                            {xtype:'ExpertViewCommentItem', itemId:'expertViewCommentItem'}
                        ]
                    },
                    {
                        xtype: 'xtoolbar',
                        itemId:'toolbar',
                        docked: 'bottom',
                        items: [
                            {
                                xtype:'spacer',
                                flex:1
                            },
                            {
                                xtype: 'xbutton',
                                text: '私聊',
                                icon:'resources/icons/24/icon_chat.png',
                                itemId: 'btnMessage'
                            },
                            {
                                xtype:'spacer',
                                flex:1
                            },
                            {
                                xtype: 'xbutton',
                                text: '关注',
                                icon:'resources/icons/24/icon_follow.png',
                                itemId: 'btnFollow'
                            },
                            {
                                xtype:'spacer',
                                flex:1
                            },
                            {
                                xtype: 'xbutton',
                                text: '点赞',
                                icon:'resources/icons/24/icon_thumbup.png',
                                itemId: 'btnAppraise'
                            },
                            {
                                xtype:'spacer',
                                flex:1
                            }
                        ]
                    }
                ]
            }
        ]
    }
});

