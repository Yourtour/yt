Ext.define('YourTour.view.user.UserProfileView', {
    extend: 'YourTour.view.widget.XPage',
    requires: ['YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.XPlaceField', 'YourTour.view.widget.XField', 'YourTour.view.widget.XMultiField', 'YourTour.view.widget.XDateField', 'YourTour.view.widget.XSelectField', 'YourTour.view.widget.XGenderField'],
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
                        label:{
                            text:'个性'
                        },
                        field:{
                            cls:'x-field-more',
                            ifNull: '请简单描述您个人',
                            editable:{
                                enable:true
                            }
                        }
                    },

                    {
                        xtype: 'panel',
                        cls: 'spacer'
                    },

                    {
                        xtype: 'xfield',
                        itemId: 'nickName',
                        label:{
                            text:'昵称'
                        },
                        field:{
                            editable:{
                                enable:true
                            }
                        }
                    },

                    {
                        xtype: 'xdatefield',
                        itemId: 'birthday',
                        label:{
                            text:'生日'
                        },
                        field:{
                            editable:{
                                enable:true
                            }
                        }
                    },

                    {
                        xtype: 'xgenderfield',
                        itemId: 'gender',
                        label:{
                            text:'性别'
                        },
                        field:{
                            editable:{
                                enable:true
                            }
                        }
                    },

                    {
                        xtype: 'xplacefield',
                        itemId: 'residence',
                        label:{
                            text:'居住地'
                        },
                        field:{
                            editable:{
                                enable:true
                            }
                        }
                    },

                    {
                        xtype: 'xplacefield',
                        itemId: 'nativePlace',
                        label:{
                            text:'籍贯'
                        },
                        field:{
                            editable:{
                                enable:true
                            }
                        }
                    },

                    {
                        xtype: 'xspacer'
                    },
                    {
                        xtype: 'xselectfield',
                        itemId: 'tags',
                        label:{
                            text:'旅行标签'
                        },
                        field:{
                            editable:{
                                enable:true
                            }
                        },
                        selectable: {
                            style: 'grid',
                            multiselect: true
                        }
                    }
                ]
            }
        ]
    }
});

