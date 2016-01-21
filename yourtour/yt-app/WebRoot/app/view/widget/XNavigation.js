Ext.define('YourTour.view.widget.XNavigation', {
    extend: 'Ext.Container',
    xtype: 'xnavigation',
    config: {
        layout:'vbox',
    	baseCls:'x-xnavigation',
        style:'width:48px;'
    },

    initialize:function(){
        this.callParent(arguments);

        var me = this;
        var btnnav = me.down('#btnNav');
        btnnav.on({
            scope:me,
            tap:function(){
                var expanded = btnnav.expanded;
                if(expanded){
                    btnnav.setSrc('resources/icons/icon_expand.png');
                    btnnav.expanded = false;
                }else{
                    btnnav.setSrc('resources/icons/icon_collapse.png');
                    btnnav.expanded = true;
                }

                var items = me.items;
                items.each(function(item){
                    if(! (item instanceof Ext.Spacer || item.getItemId() == 'btnNav')){
                        btnnav.expanded? item.show() : item.hide();
                    }
                })
            }
        });
    },

    constructor: function(config) {
        config = config || {};

        if (!config.items) {
            config.items = [];
        }

        var items = config.items;
        Ext.Array.forEach(items, function(item){
            item.hidden = true;
            item.margin = '5 0 5 0';
        })

        items.unshift({xtype:'spacer', flex:1});
        items.push({xtype:'image', itemId:'btnNav', mode : 'tag', margin:'5 0 0 0', src:'resources/icons/icon_expand.png',style:'width:48px;height:48px;'})

        this.callParent([config]);
    },

    collapse:function(){
        var me = this;

        var items = me.items;
        items.each(function(item){
            if(! (item instanceof Ext.Spacer || item.getItemId() == 'btnNav')){
                item.hide();
            }
        })

        var btnnav = me.down('#btnNav');
        btnnav.setSrc('resources/icons/icon_expand.png');
        btnnav.expanded = false;
    }
});

