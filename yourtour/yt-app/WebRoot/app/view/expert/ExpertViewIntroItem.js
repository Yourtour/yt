Ext.define('YourTour.view.expert.ExpertViewIntroItem', {
    extend: 'Ext.Container',
    xtype:'ExpertViewIntroItem',
    config: {
        layout: 'vbox',
        title:'达人信息',
        items: [
            {
                xtype: 'panel',
                layout: 'hbox',
                padding:10,
                items: [
                    {
                        xtype: 'ximage',
                        itemId: 'image',
                        imageCls:'img-small'
                    },

                    {
                        xtype: 'panel',
                        layout: 'vbox',
                        flex:1,
                        margin:'0 0 0 10',
                        items: [
                            {
                                xtype:'panel',
                                layout:'hbox',
                                items:[
                                    {
                                        xtype: 'xfield',
                                        itemId: 'nickName',
                                        underline:false,
                                        padding:'0'
                                    },

                                    {
                                        xtype: 'xfield',
                                        itemId: 'gender',
                                        underline:false,
                                        padding:'0',
                                        margin:'0 0 0 30',
                                        dataChange:function(field, record){
                                            field.setText('&nbsp;');
                                            var gender = record.get('gender');
                                            if(gender == 'MALE'){
                                                field.setLabelCls('icon-male');
                                            }else{
                                                field.setLabelCls('icon-female');
                                            }
                                        }
                                    }
                                ]
                            },

                            {
                                xtype: 'xfield',
                                itemId:'age',
                                underline:false,
                                padding:'0',
                                margin:'10 0 0 0'

                            },

                            {
                                xtype: 'xfield',
                                itemId:'identity',
                                underline:false,
                                padding:'0',
                                margin:'10 0 0 0'
                            },
                        ]
                    }
                ]
            },

            {
                xtype: 'panel',
                layout: 'hbox',
                padding:'0 10 0 10',
                cls:'row',
                defaults: {
                    flex: 1
                },
                items: [
                    {
                        xtype: 'xfield',
                        itemId: 'idAuthenticate',
                        text: '身份认证',
                        padding:0,
                        dataChange:function(field, record){
                            if(record.get('idAuthenticate') == 1){
                                field.setLabelCls('icon-checked');
                            }else{
                                field.setLabelCls('icon-unchecked');
                            }
                        }
                    },

                    {
                        xtype: 'xfield',
                        itemId: 'mobileAuthenticate',
                        text: '手机认证',
                        padding:0,
                        dataChange:function(field, record){
                            if(record.get('mobileAuthenticate') == 1){
                                field.setLabelCls('icon-checked');
                            }else{
                                field.setLabelCls('icon-unchecked');
                            }
                        }
                    },

                    {
                        xtype: 'xfield',
                        itemId: 'snsAuthenticate',
                        text: '社交认证',
                        padding:0,
                        dataChange:function(field, record){
                            if(record.get('snsAuthenticate') == 1){
                                field.setLabelCls('icon-checked');
                            }else{
                                field.setLabelCls('icon-unchecked');
                            }
                        }
                    }
                ]
            },

            {
                xtype:'xspacer',
            },
            {
                xtype:'xlabel',
                cls:'font-medium',
                html:'标签'
            },
            {
                xtype:'xmultifield',
                itemId:'intro'
            },

            {
                xtype:'xspacer',
            },
            {
                xtype:'xlabel',
                cls:'font-medium text-align-center',
                html:'介绍'
            },
            {
                xtype:'xmultifield',
                itemId:'intro'
            },

            {
                xtype: 'xtoolbar',
                docked: 'bottom',
                items: [
                    {
                        xtype: 'spacer',
                        flex: 1
                    },

                    {
                        xtype: 'xbutton',
                        text: '私信',
                        icon: 'resources/icons/icon_message.png',
                        itemId: 'btnMessage'
                    },

                    {
                        xtype: 'spacer',
                        flex: 1
                    }
                ]
            }
        ]
    },

    updateRecord:function(record){
        var me = this;

        if(record) {
            YourTour.util.Context.fillViewFields(me, record);
        }
    }
});

