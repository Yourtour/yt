Ext.define('YourTour.view.expert.ExpertIntroFormView', {
    extend: 'YourTour.view.widget.XPage',
    requires: ['YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.XLabel'],
    config: {
        id: 'ExpertIntroFormView',
        layout: 'vbox',
        items: [
            {
                xtype: 'xheaderbar'
            },

            {
                xtype: 'panel',
                layout: 'hbox',
                items: [
                    {
                        xtype: 'image',
                        mode: 'tag',
                        itemId: 'image'
                    },

                    {
                        xtype: 'panel',
                        layout: 'hbox',
                        items: [
                            {
                                xtype: 'xfield',
                                itemId: 'nickName',
                                padding: '0 5 0 5',
                                cls: 'row underline font-medium font-grey'
                            },

                            {
                                xtype: 'image',
                                mode: 'tag',
                                itemId: 'gender'
                            }
                        ]
                    },

                    {
                        xtype: 'panel',
                        layout: 'hbox',
                        defaults: {
                            flex: 1
                        },
                        items: [
                            {
                                xtype: 'xlabel',
                                itemId: 'idAuthenticate',
                                html: '身份认证'
                            },

                            {
                                xtype: 'xlabel',
                                itemId: 'mobileAuthenticate',
                                html: '手机认证'
                            },

                            {
                                xtype: 'xlabel',
                                itemId: 'snsAuthenticate',
                                html: '社交认证'
                            }
                        ]
                    }
                ]
            }
        ]
    }
});

