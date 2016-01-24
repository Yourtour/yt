Ext.define('YourTour.view.expert.ExpertRecommendDataItem', {
    extend: 'Ext.Panel',
    xtype: 'ExpertRecommendDataItem',
    requires: ['Ext.Panel', 'Ext.Img', 'YourTour.view.widget.XIcon', 'YourTour.view.widget.XField'],
    config: {
        layout: 'vbox',
        items: [
            {
                xtype: 'panel',
                cls: 'spacer'
            },
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
                                        underline:false,

                                        flex:1
                                    },

                                    {
                                        xtype: 'image',
                                        mode: 'tag',
                                        itemId: 'rank',
                                        src: 'resources/images/raty_32.png'
                                    }
                                ]
                            },

                            {
                                xtype: 'xfield',
                                itemId:'age',
                                underline:false,
                                margin:'5 0 0 0'
                            },

                            {
                                xtype: 'xfield',
                                itemId:'identity',
                                underline:false,
                                margin:'5 0 0 0'
                            }
                        ]
                    },

                    {
                        xtype: 'panel',
                        layout: 'vbox',
                        margin:'10 0 0 0'
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
                cls: 'row underline font-medium font-grey',
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
                    }
                ]
            },

            {
                xtype: 'xfield',
                label:'推荐线路',
                labelCls:'font-medium font-grey',
                padding: '0 10 0 10',
                itemId: 'routeName'
            }
        ]
    },

    initialize : function(){
        var me = this;

        me.element.on({
            scope : me,
            tap : function(e, t) {
                me.fireEvent('tap', me.record);
            }
        });
    },

    updateRecord: function (record) {
        this.setRecord(record);
    },

    setRecord:function(record){
        var me = this;
        me.record = record;
        if (record) {
            var image = me.down('#image');
            image.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "' style='width:64px; height:64px'>");

            var nickName = me.down('#nickName');
            nickName.setText(record.get('nickName'));

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
        }
    },

    getRecord:function(){
        return this.record;
    }
});

