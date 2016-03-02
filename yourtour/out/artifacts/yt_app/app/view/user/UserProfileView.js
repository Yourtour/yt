Ext.define('YourTour.view.user.UserProfileView', {
    extend: 'YourTour.view.widget.XPage',
    requires: ['YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.XPlaceField', 'YourTour.view.widget.XField', 'YourTour.view.widget.XMultiField', 'YourTour.view.widget.XDateField', 'YourTour.view.user.UserTagDataItem', 'YourTour.view.widget.XGenderField'],
    xtype: 'UserProfileView',
    config: {
        id: 'UserProfileView',
        items: [
            {
                xtype: 'xheaderbar',
                title: '个人信息',
                items: [
                    {
                        xtype: "xbutton",
                        icon: 'resources/icons/24/icon_header_ok.png',
                        itemId: 'btnSave',
                        align: 'right'
                    }
                ]
            },

            {
                xtype: 'xpagebody',
                layout: 'vbox',
                items: [
                    {
                        xtype: 'container',
                        layout: 'vbox',
                        height: 95,
                        padding: 10,
                        cls: 'underline',
                        items: [
                            {
                                xtype: 'ximageselect',
                                itemId: 'userLogo',
                                cls: 'x-xuserlogo',
                                image: {
                                    fileName: 'user.jpg',
                                    maximumImageCount: 1
                                }
                            }
                        ]
                    },

                    {
                        xtype: 'xmultifield',
                        itemId: 'slogan',
                        label: '个性',
                        editable: true,
                        fieldCls: 'x-field-more',
                        ifNull: '请简单描述您个人'
                    },

                    {
                        xtype: 'panel',
                        cls: 'spacer'
                    },

                    {
                        xtype: 'xfield',
                        itemId: 'nickName',
                        label: '昵称',
                        editable: true
                    },

                    {
                        xtype: 'xdatefield',
                        itemId: 'birthday',
                        label: '生日',
                        editable: true
                    },

                    {
                        xtype: 'xgenderfield',
                        itemId: 'gender',
                        label: '性别',
                        editable: true
                    },

                    {
                        xtype: 'xplacefield',
                        itemId: 'residence',
                        label: '居住地',
                        editable: true
                    },

                    {
                        xtype: 'xplacefield',
                        itemId: 'nativePlace',
                        label: '籍贯',
                        editable: true
                    },

                    {
                        xtype: 'xspacer'
                    },
                    {
                        xtype:'xlabel',
                        html:'旅行标签',
                        cls:'underline font-normal'
                    },
                    {
                        xtype: 'xgridview',
                        itemId: 'tagList',
                        useComponents: true,
                        defaultType: 'UserTagDataItem',
                        cols:3
                    }
                ]
            }
        ]
    }
});
