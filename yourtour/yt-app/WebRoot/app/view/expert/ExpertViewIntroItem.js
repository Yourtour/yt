Ext.define('YourTour.view.expert.ExpertViewIntroItem', {
    extend: 'Ext.Container',
    xtype:'ExpertViewIntroItem',
    config: {
        itemId:'ExpertViewIntroItem',
        layout: 'vbox',
        padding:10,

        record:null,
        items: [
            {
                xtype: 'panel',
                layout: 'hbox',
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
                                xtype: 'xfield',
                                itemId: 'nickName',
                                padding: '0'
                            }
                        ]
                    }
                ]
            },

            {
                xtype: 'panel',
                layout: 'hbox',
                padding:'0 0 0 0',
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

