Ext.define('YourTour.view.along.AlongEditView', {
    extend: 'YourTour.view.widget.XPage',
    requires: ['YourTour.view.widget.XHeaderBar'],
    config: {
        id: 'AlongEditView',
        layout: 'vbox',
        items: [
            {
                xtype: 'xheaderbar',
                title: '结伴发布',
                items:[
                    {
                        xtype: 'xbutton',
                        itemId: 'btnSave',
                        align: 'right',
                        icon:'resources/icons/24/icon_header_ok.png'
                    }
                ]
            },

            {
                xtype:'xtextfield',
                itemId:'title',
                label:'标题',
                labelWidth:'25%'
            },

            {
                xtype:'selectfield',
                itemId:'intention',
                label:'目的',
                labelWidth:'25%',
                usePicker:1,
                options:[
                    {text:'拼车', value:'TOGETHER_CAR'},
                    {text:'拼吃', value:'TOGETHER_EAT'},
                    {text:'拼玩', value:'TOGETHER_TRAVEL'}
                ]
            },

            {
                xtype:'xtextfield',
                itemId:'num',
                labelWidth:'25%',
                label:'人数'
            },

            {
                xtype:'xtextfield',
                itemId:'deadline',
                labelWidth:'25%',
                label:'截止时间'
            },

            {
                xtype:'xtextarea',
                itemId:'memo',
                labelWidth:'25%',
                label:'内容描述'
            }
        ]
    }
});

