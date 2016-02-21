Ext.define('YourTour.view.widget.XScore', {
    extend: 'Ext.Label',
    xtype: 'xscore',
    config: {
        baseCls: 'x-xscore',
        binding: null,
        align: null,
        shape:'star'
    },

    initialize:function(){
        var me = this;
        this.callParent(arguments);

        if(me.shape == 'round'){
            if(me.align == 'left'){
                me.addCls('x-round-left');
            }else{
                me.addCls('x-round-right');
            }
        }
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

    updateAlign:function(align){
        this.align = align;
    },

    updateShape:function(shape){
        this.shape = shape;
    },

    updateRecord: function (record) {
        var binding = this.binding;
        var name = binding == null ? this.getItemId() : binding;
        var score = record.get(name);

        this.setText(score);
    },

    setText:function(text){
        if(! text) text = 0;

        if(this.shape == 'star'){
            this.setStyle('background-image: url(./resources/icons/icon_score.png);background-repeat: no-repeat;background-position: left center;');
        }

        this.setHtml(text + ' 分');
    }
});

