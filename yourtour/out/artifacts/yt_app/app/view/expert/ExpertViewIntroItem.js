Ext.define('YourTour.view.expert.ExpertViewIntroItem', {
    extend: 'YourTour.view.common.CarouselItem',
    requires: ['YourTour.view.widget.XLabel'],
    xtype:'ExpertViewIntroItem',
    config: {
        layout: 'vbox',
        items: [
            {
                xtype:'panel',
                height:180,
                style:'background-image:url(./resources/images/bg/personal.jpg)',
                layout:'vbox',
                items:[
                    {
                        xtype: 'panel',
                        layout: 'hbox',
                        margin:'10 0 0 0',
                        items: [
                            {
                                xtype: 'spacer',
                                flex: 1
                            },
                            {
                                xtype: 'ximage',
                                itemId: 'image',
                                imageCls: 'img-small',
                                binding: 'profile.imageUrl'
                            },
                            {
                                xtype: 'spacer',
                                flex: 1
                            }
                        ]
                    },

                    {
                        xtype: 'panel',
                        layout: 'hbox',
                        margin:'5 0 0 0',
                        items: [
                            {
                                xtype:'spacer',
                                flex:1
                            },
                            {
                                xtype: 'xfield',
                                itemId: 'nickName',
                                fieldCls:'font-white',
                                underline: false,
                                padding: '0',
                                binding:'profile.nickName'
                            },

                            {
                                xtype: 'xfield',
                                itemId: 'gender',
                                underline: false,
                                padding: '0',
                                margin: '0 0 0 30',
                                dataChange: function (field, record) {
                                    var profile = record.profileStore.first();

                                    field.setText('&nbsp;');
                                    var gender = profile.get('gender');
                                    if (gender == 'MALE') {
                                        field.setLabelCls('icon-male');
                                    } else {
                                        field.setLabelCls('icon-female');
                                    }
                                }
                            },

                            {
                                xtype:'spacer',
                                flex:1
                            },
                        ]
                    },

                    {
                        xtype: 'xfield',
                        underline: false,
                        padding:0,
                        margin:'10 0 0 0',
                        fieldCls:'font-white text-align-center',
                        dataChange:function(field, record){
                            var profile = record.profileStore.first();
                            field.setText(profile.get('age') + ' | ' + profile.get('identity'));
                        }
                    },

                    {
                        xtype: 'panel',
                        layout: 'hbox',
                        padding: '0 10 0 10',
                        margin:'5 0 0 0',
                        defaults: {
                            flex: 1
                        },
                        items: [
                            {
                                xtype: 'xfield',
                                itemId: 'idAuthenticate',
                                text: '身份认证',
                                fieldCls:'font-white',
                                padding: 0,
                                underline:false,
                                dataChange: function (field, record) {
                                    var profile = record.profileStore.first();
                                    if (profile.get('idAuthenticate') == 1) {
                                        field.setLabelCls('icon-checked');
                                    } else {
                                        field.setLabelCls('icon-unchecked');
                                    }
                                }
                            },

                            {
                                xtype: 'xfield',
                                itemId: 'mobileAuthenticate',
                                text: '手机认证',
                                fieldCls:'font-white',
                                padding: 0,
                                underline:false,
                                dataChange: function (field, record) {
                                    var profile = record.profileStore.first();
                                    if (profile.get('mobileAuthenticate') == 1) {
                                        field.setLabelCls('icon-checked');
                                    } else {
                                        field.setLabelCls('icon-unchecked');
                                    }
                                }
                            },

                            {
                                xtype: 'xfield',
                                itemId: 'snsAuthenticate',
                                text: '社交认证',
                                fieldCls:'font-white',
                                underline:false,
                                padding: 0,
                                dataChange: function (field, record) {
                                    var profile = record.profileStore.first();
                                    if (profile.get('snsAuthenticate') == 1) {
                                        field.setLabelCls('icon-checked');
                                    } else {
                                        field.setLabelCls('icon-unchecked');
                                    }
                                }
                            }
                        ]
                    }
                ]
            },

            {
                xtype: 'xspacer'
            },
            {
                xtype: 'panel',
                layout: 'hbox',
                height:55,
                defaults: {
                    flex: 1
                },
                cls:'underline font-grey font-medium text-align-center',
                padding:'5 10 5 10',
                items: [
                    {
                        xtype: 'panel',
                        layout:'vbox',
                        style:'border-right: 1px solid #EDEDED;',
                        items:[
                            {
                                xtype:'label',
                                html:'关注',
                            },
                            {
                                xtype:'xfield',
                                itemId:'profile.followingNum',
                                padding:'8 0 0 0',
                                fieldCls:'text-align-center',
                                underline:false
                            }
                        ]
                    },

                    {
                        xtype: 'panel',
                        layout:'vbox',
                        items:[
                            {
                                xtype:'label',
                                html:'粉丝'
                            },
                            {
                                xtype:'xfield',
                                itemId:'profile.followedNum',
                                underline:false,
                                padding:'8 0 0 0',
                                fieldCls:'text-align-center'
                            }
                        ]
                    },

                    {
                        xtype: 'panel',
                        layout:'vbox',
                        style:'border-left: 1px solid #EDEDED;',
                        items:[
                            {
                                xtype:'label',
                                html:'点赞'
                            },
                            {
                                xtype:'xfield',
                                itemId:'profile.thumbupNum',
                                underline:false,
                                padding:'8 0 0 0',
                                fieldCls:'text-align-center'
                            }
                        ]
                    },

                    {
                        xtype: 'panel',
                        layout:'vbox',
                        style:'border-left: 1px solid #EDEDED;',
                        items:[
                            {
                                xtype:'label',
                                html:'相册'
                            },
                            {
                                xtype:'xfield',
                                itemId:'profile.albumNum',
                                underline:false,
                                padding:'8 0 0 0',
                                fieldCls:'text-align-center'
                            }
                        ]
                    }
                ]
            },

            {
                xtype: 'xspacer'
            },
            {
                xtype: 'xmultifield',
                itemId:'tags',
                cls: 'font-medium',
                label: '标签',
                dataChange:function(field, record){
                    field.setText('<span class="x-xtag"> 文艺</span><span class="x-xtag">景观</span><span class="x-xtag">人文</span><span class="x-xtag">美食</span><br/><br/><span class="x-xtag"> 文艺</span><span class="x-xtag">景观</span><span class="x-xtag">人文</span><span class="x-xtag">美食</span>')
                }
            },
            {
                xtype: 'xmultifield',
                underline:false,
                itemId: 'profile.memo',
                label:'简介'
            }
        ]
    }
});

