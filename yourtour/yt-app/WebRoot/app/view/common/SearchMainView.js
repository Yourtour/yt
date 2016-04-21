Ext.define('YourTour.view.common.SearchMainView', {
    extend: 'YourTour.view.widget.XPage',
    requires: ['Ext.Label', 'Ext.Panel', 'YourTour.view.common.CommentListDataItem', 'YourTour.view.widget.XPageBody', 'YourTour.view.widget.XProcessing'],
    config: {
        id: 'ConsultMainView',
        layout: 'card',
        items: [
            {
                xtype: 'xheaderbar',
                itemId: 'headerbar',
                title: '咨询'
            },

            {
                xtype: 'xpagebody',
                layout: 'vbox',
                items: [
                    {
                        xtype: 'panel',
                        layout: 'hbox',
                        cls: 'underline row',
                        padding:'0 10',
                        defaults: {
                            underline: false,
                            padding: '10 0',
                            flex: 1
                        },
                        items: [
                            {
                                xtype: 'xfield',
                                itemId:'commentScore',
                                fieldCls:'font-striking'
                            },

                            {
                                xtype: 'xfield',
                                itemId: 'healthScore'
                            },

                            {
                                xtype: 'xfield',
                                itemId: 'environmentScore'
                            },

                            {
                                xtype: 'xfield',
                                itemId: 'serviceScore'
                            },

                            {
                                xtype: 'xfield',
                                itemId: 'facilityScore'
                            }
                        ]
                    },

                    {
                        xtype: 'panel',
                        itemId: 'filterPanel',
                        layout: 'hbox',
                        padding:'0 10',
                        cls: 'underline row',
                        defaults: {
                            underline: false,
                            padding: '10 0',
                            flex: 1
                        },
                        items: [
                            {
                                xtype: 'xfield',
                                itemId: 'commentNum'
                            },

                            {
                                xtype: 'xfield',
                                itemId: 'goodNum'
                            },

                            {
                                xtype: 'xfield',
                                itemId: 'mediumNum'
                            },

                            {
                                xtype: 'xfield',
                                itemId: 'badNum'
                            },

                            {
                                xtype: 'xfield',
                                itemId: 'imageNum'
                            }
                        ]
                    },

                    {
                        xtype: 'xspacer'
                    },

                    {
                        itemId: 'commentList',
                        xtype: 'xdataview',
                        scrollable: null,
                        defaultType: 'CommentListDataItem'
                    }
                ]
            }
        ]
    }
});

