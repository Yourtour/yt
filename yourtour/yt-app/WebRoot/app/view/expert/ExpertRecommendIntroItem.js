Ext.define('YourTour.view.expert.ExpertRecommendIntroItem', {
    extend: 'YourTour.view.common.CarouselItem',
    requires: ['Ext.Panel'],
    xtype:'ExpertRecommendIntroItem',
    config: {
        layout: 'vbox',
        label: '行程达人',
        /*scrollable: {
            direction: 'vertical',
            indicators: false
        },*/
        items: [
            {
                xtype: 'panel',
                layout: 'hbox',
                cls: 'underline',
                padding: '10 10 10 10',
                items: [
                    {
                        xtype: 'image',
                        mode: 'tag',
                        itemId: 'image'

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
                                        heightCls:'x-xfield-padding-zero',
                                        underline:false,
                                        flex:1
                                    },

                                    {
                                        xtype: 'image',
                                        mode: 'tag',
                                        itemId: 'rank',
                                        src: 'resources/images/raty_32.png',
                                    },
                                ]
                            },

                            {
                                xtype: 'xfield',
                                itemId:'age',
                                underline:false,
                                heightCls:'x-xfield-padding-zero',
                                margin:'5 0 0 0'
                            },

                            {
                                xtype: 'xfield',
                                itemId:'identity',
                                underline:false,
                                heightCls:'x-xfield-padding-zero',
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
                    flex: 1
                },
                cls: 'row underline',
                items: [
                    {
                        xtype: 'xlabel',
                        itemId: 'idAuthenticate',
                        padding:'0 0 0 40',
                        html: '身份认证'
                    },

                    {
                        xtype: 'xlabel',
                        itemId: 'mobileAuthenticate',
                        padding:'0 0 0 40',
                        html: '手机认证'
                    },

                    {
                        xtype: 'xlabel',
                        itemId: 'snsAuthenticate',
                        padding:'0 0 0 40',
                        html: '社交认证'
                    },
                ]
            },

            {
                xtype: 'xmultifield',
                padding: '0 10 0 10',
                itemId: 'memo'
            },

            {
                xtype: 'toolbar',
                docked: 'bottom',
                items: [
                    {
                        xtype: 'spacer',
                        flex:1
                    }, {
                        xtype: 'button',
                        text: '联系达人',
                        baseCls:'button',
                        flex: 1,
                        padding: '0 20 0 20',
                        itemId: 'btnContact'
                    }
                ]
            }
        ]
    },

    setRecord:function(record){
        this.callParent(arguments);

        var view = this;
        if(record){
            var image = view.down('#image');
            image.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "' style='width:64px; height:64px'>");

            var nickName = view.down('#nickName');
            nickName.setHtml(record.get('nickName'));

            var identity = view.down('#identity');
            identity.setText(record.get('identity'));

            var age = view.down('#age');
            age.setText(record.get('age'));

            var idAuthenticate = view.down('#idAuthenticate');
            idAuthenticate.addCls(record.get('idAuthenticate') == 1?'icon-checked':'icon-unchecked');

            var snsAuthenticate = view.down('#snsAuthenticate');
            snsAuthenticate.addCls(record.get('snsAuthenticate') == 1?'icon-checked':'icon-unchecked');

            var mobileAuthenticate = view.down('#mobileAuthenticate');
            mobileAuthenticate.addCls(record.get('mobileAuthenticate') == 1?'icon-checked':'icon-unchecked');

            var memo = view.down('#memo');
            memo.setText(record.get('memo'));
        }
    }
});

