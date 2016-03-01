Ext.define('YourTour.view.member.MemberItemView', {
    extend: 'Ext.dataview.component.DataItem',
    requires: ['Ext.Panel', 'YourTour.view.widget.XLabel'],
    xtype: 'MemberItemView',
    config: {
        layout: 'hbox',
        height: 50,
        cls: 'underline',
        padding: 5,
        items: [
            {
                itemId: 'imageUrl',
                xtype: 'image',
                mode: 'tag'
            },

            {
                xtype: 'xfield',
                itemId: 'nickName',
                flex: 1,
                underline: false
            },

            {
                xtype: 'xlabel',
                itemId: 'role',
                cls:'font-medium font-grey'
            }
        ]
    },

    updateRecord: function (record) {
        var me = this;

        if (record) {
            var imageUrlEl = me.down('#imageUrl');
            imageUrlEl.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "' style='width:40px; height:40px'/>");

            var nickName = me.down('#nickName');
            nickName.setText(record.get('nickName'));

            var role = me.down('#role');
            if (record.get('role') == 'leader') {
                role.setHtml('领队');
                role.addCls('icon-rect');
            } else if (record.get('role') == 'expert') {
                role.setHtml('达人');
                role.addCls('icon-rect');
            }
        }
    }
});

