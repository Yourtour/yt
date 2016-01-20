Ext.define('YourTour.view.widget.XScore', {
    extend: 'Ext.Label',
    xtype: 'xscore',
    config: {
        baseCls: 'x-xscore',
        binding: null,
        align: null,
        star:false
    },

    constructor: function(config) {
        var me = this;

        if(config.star){
            if(! config.padding){
                config.padding = '0 0 0 90';
            }
        }

        me.callParent(arguments);
    },

    updateStar:function(star){
        this.setStyle('background-image: url(./resources/icons/icon_score.png);background-repeat: no-repeat;background-position: left center;');
    },

    updateRecord: function (record) {
        var binding = this.binding;
        var name = binding == null ? this.getItemId() : binding;
        var score = record.get(name);

        this.setHtml(record.get(name) + ' 分');
    }
});

