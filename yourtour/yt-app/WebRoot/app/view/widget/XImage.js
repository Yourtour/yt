Ext.define('YourTour.view.widget.XImage', {
    extend: 'Ext.Panel',
    xtype: 'ximage',
    requires:['Ext.Img'],
    config:{
        items:[
            {
                itemId : 'image',
                xtype : 'image',
                mode : 'tag'
            }
        ]
    },

    applyUrl:function(url){
        var image = this.down('#image');
        image.setHtml("<img src='" + YourTour.util.Context.getImageResource(url) + "'>");
    }
});

