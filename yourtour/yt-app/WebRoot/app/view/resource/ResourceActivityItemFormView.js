Ext.define('YourTour.view.resource.ResourceActivityItemFormView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.resource.ResourceActivityItem'],
    config: {
        itemId: 'ResourceActivityItemFormView',
        items: [
            {
                xtype: 'xheaderbar'
            },

            {
                xtype: 'ResourceActivityItem',
                itemId:'ResourceActivityItem',
                flex:1
            },

            {
                xtype: 'xtoolbar',
                docked: 'bottom',
                items: [
                    {
                        xtype: 'spacer',
                        flex: 1
                    }, {
                        xtype: 'xbutton',
                        text: '取消日程',
                        itemId: 'btnCancel'
                    }
                ]
            }
        ]
    },

    updateRecord: function() {
        this.callParent(arguments);

        var me = this, data = me.data || me.getData();
        if(data){
            var activityItem = me.down('#ResourceActivityItem');
            console.log('background-image: url(' + data.get('imageUrl') + ');background-repeat: no-repeat;background-position: center center;');
            activityItem.setStyle('background-image: url(' + data.get('imageUrl') +  ');background-repeat: no-repeat;background-position: center center;');
            //this.setStyle('background-image','url(' + YourTour.util.Context.getImageResource(data.get('imageUrl')) + ')');
        }
    }
});

