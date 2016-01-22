Ext.define('YourTour.view.widget.XNavigation', {
    extend: 'Ext.Container',
    xtype: 'xnavigation',
    config: {
        layout: 'vbox',
        baseCls: 'x-xnavigation',

    },

    initialize: function () {
        this.callParent(arguments);

        var me = this;
        var btnnav = me.down('#btnNav');
        btnnav.on({
            scope: me,
            tap: function () {
                var expanded = btnnav.expanded;
                if (expanded) {
                    btnnav.setSrc('resources/icons/icon_expand.png');
                    btnnav.expanded = false;
                } else {
                    btnnav.setSrc('resources/icons/icon_collapse.png');
                    btnnav.expanded = true;
                }

                var items = me.items;
                items.each(function (item) {
                    if (!(item instanceof Ext.Spacer || item.getItemId() == 'btnNav')) {
                        btnnav.expanded ? item.show() : item.hide();
                    }
                })
            }
        });
    },

    constructor: function (config) {
        var me = this;

        config = config || {};

        if (!config.items) {
            config.items = [];
        }

        var direction = 'up';
        if (config.direction) {
            direction = config.direction;
        }

        var navigationButton = {
            xtype: 'image',
            itemId: 'btnNav',
            mode: 'tag',
            margin: '5 0 0 0',
            src: 'resources/icons/icon_expand.png',
            style: 'width:48px;height:48px;'
        };

        if (direction == 'left'){
            config.style = 'height:48px;'
            config.layout = 'hbox';
            navigationButton.margin = '0 0 0 5';
        }else if(direction == 'right') {
            config.style = 'height:48px;'
            config.layout = 'hbox';
            navigationButton.margin = '0 5 0 0';
        }else if(direction == 'down'){
            config.style = 'width:48px;'
            config.layout = 'vbox';
            navigationButton.margin = '0 0 5 0';
        }else{
            config.style = 'width:48px;'
            config.layout = 'vbox';
            navigationButton.margin = '0 5 0 0';
        }

        var items = config.items;
        Ext.Array.forEach(items, function (item) {
            item.style = 'width:48px;height:48px;';
            item.mode = 'tag';
            item.hidden = true;

            if (direction == 'left' || direction == 'right') {
                item.margin = '0 5 0 5';
            } else {
                item.margin = '5 0 5 0';
            }
        })

        if (direction == 'left' || direction == 'up') {
            items.unshift({xtype: 'spacer', flex: 1});
            items.push(navigationButton);
        } else {
            items.unshift(navigationButton)
            items.push({xtype: 'spacer', flex: 1});
        }

        this.callParent([config]);
    },

    collapse: function () {
        var me = this;

        var items = me.items;
        items.each(function (item) {
            if (!(item instanceof Ext.Spacer || item.getItemId() == 'btnNav')) {
                item.hide();
            }
        })

        var btnnav = me.down('#btnNav');
        btnnav.setSrc('resources/icons/icon_expand.png');
        btnnav.expanded = false;
    }
});

