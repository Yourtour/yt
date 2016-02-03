Ext.define('YourTour.view.expert.ExpertIntroItem', {
    extend: 'YourTour.view.widget.XPageBody',
    requires: ['Ext.Panel'],
    xtype:'ExpertRecommendIntroItem',
    config: {
        layout: 'vbox',
        items: [
            {
                xtype: 'panel',
                layout: 'hbox',
                cls: 'underline',
                padding: '10 10 10 10',
                items: [
                    {
                        xtype: 'image',
                        itemId: 'image',
                        mode: 'tag'
                    },

                    {
                        xtype: 'panel',
                        layout: 'vbox',
                        flex:1,
                        margin:'0 0 0 10',
                        items: [
                            {
                                xtype: 'panel',
                                layout: 'hbox',
                                items: [
                                    {
                                        xtype: 'xfield',
                                        itemId: 'nickName',
                                        underline:false,
                                        padding:'0 ',
                                        flex:1
                                    },

                                    {
                                        xtype: 'image',
                                        mode: 'tag',
                                        itemId: 'rank',
                                        src: 'resources/images/raty_32.png',
                                    }
                                ]
                            },

                            {
                                xtype: 'xfield',
                                itemId:'age',
                                underline:false,
                                padding:'0',
                                margin:'5 0 0 0'
                            },

                            {
                                xtype: 'xfield',
                                itemId:'identity',
                                underline:false,
                                padding:'0',
                                margin:'5 0 0 0'
                            },
                        ]
                    },

                    {
                        xtype: 'panel',
                        layout: 'vbox',
                        margin:'10 0 0 0',
                    }
                ]
            },

            {
                xtype: 'panel',
                layout: 'hbox',
                padding: '0 10 0 10',
                defaults: {
                    flex: 1,
                    padding:'0 0 0 40'
                },
                cls: 'row underline font-medium font-grey',
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
                    },
                ]
            },

            {
                xtype: 'xfield',
                itemId: 'memo'
            },

            {
                xtype: 'xtoolbar',
                docked: 'bottom',
                items: [
                    {
                        xtype: 'spacer',
                        flex:1
                    }, {
                        xtype: 'xbutton',
                        text: '联系达人',
                        baseCls:'button',
                        padding: '0 20 0 20',
                        itemId: 'btnContact'
                    }
                ]
            }
        ]
    },

    updateData:function(data){
        var me = this;
        var record = data;
        if(record){
            var image = me.down('#image');
            image.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "' style='width:64px; height:64px'>");

            var nickName = me.down('#nickName');
            nickName.setHtml(record.get('nickName'));

            var identity = me.down('#identity');
            identity.setText(record.get('identity'));

            var age = me.down('#age');
            age.setText(record.get('age'));

            var idAuthenticate = me.down('#idAuthenticate');
            idAuthenticate.addCls(record.get('idAuthenticate') == 1?'icon-checked':'icon-unchecked');

            var snsAuthenticate = me.down('#snsAuthenticate');
            snsAuthenticate.addCls(record.get('snsAuthenticate') == 1?'icon-checked':'icon-unchecked');

            var mobileAuthenticate = me.down('#mobileAuthenticate');
            mobileAuthenticate.addCls(record.get('mobileAuthenticate') == 1?'icon-checked':'icon-unchecked');

            var memo = me.down('#memo');
            memo.setText(record.get('memo'));
        }
    }
});

