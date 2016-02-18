Ext.define('YourTour.view.along.AlongFormView', {
    extend: 'YourTour.view.widget.XPage',
    requires: ['YourTour.view.widget.XHeaderBar', 'YourTour.view.along.AlongListDataItem'],
    config: {
        id: 'AlongFormView',
        layout: 'card',
        items: [
            {
                xtype: 'xheaderbar',
                title: '结伴',
                items:[
                    {
                        xtype: 'xbutton',
                        itemId: 'btnNew',
                        align: 'right',
                        icon:'resources/icons/24/icon_header_add.png'
                    },

                    {
                        xtype: 'xbutton',
                        itemId: 'btnEdit',
                        align: 'right',
                        icon:'resources/icons/24/icon_edit.png'
                    }
                ]
            },

            {
                xtype:'panel',
                itemId:'addPanel'
            },

            {
                xtype:'panel',
                itemId:'contentPanel',
                layout:'vbox',
                items:[
                    {
                        xtype:'xfield',
                        itemId:'route',
                        indicator:'nav-arrow',
                        label:'行程名称',
                        binding:'route.name'
                    },

                    {
                        xtype:'xspacer'
                    },

                    {
                        xtype:'xfield',
                        itemId:'title',
                        label:'结伴标题'
                    },

                    {
                        xtype:'panel',
                        layout:'hbox',
                        defaults:{
                            flex:1
                        },
                        items:[
                            {
                                xtype:'xfield',
                                itemId:'intentionName',
                                label:'结伴类型'
                            },

                            {
                                xtype:'xfield',
                                itemId:'num',
                                label:'结伴人数'
                            }
                        ]
                    },

                    {
                        xtype:'xfield',
                        itemId:'deadLine',
                        label:'截止时间',
                        dataChange:function(field, record){
                            var date = new Date(Number(record.get('deadLine')));
                            field.setText(Ext.Date.format(date,'Y-m-d H:i:s'));
                        }
                    },

                    {
                        xtype:'xmultifield',
                        itemId:'memo',
                        label:'结伴描述'
                    },

                    {
                        xtype:'panel',
                        layout:'hbox',
                        defaults:{
                            flex:1
                        },
                        items:[
                            {
                                xtype:'xfield',
                                itemId:'type',
                                label:'关注人数'
                            },

                            {
                                xtype:'xfield',
                                itemId:'num',
                                label:'报名人数'
                            }
                        ]
                    },

                    {
                        xtype:'xspacer'
                    },

                    {
                        xtype:'xlabel',
                        html:'用户留言',
                        cls:'row underline font-medium'
                    }
                ]
            }
        ]
    }
});

