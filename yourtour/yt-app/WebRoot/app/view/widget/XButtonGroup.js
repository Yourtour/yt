Ext.define('YourTour.view.widget.XButtonGroup', {
    extend: 'Ext.Container',
    xtype:'xbuttongroup',
    config: {
        layout:'hbox',
        defaults:{
            flex:1
        },
        items:[]
    },

    initialize:function(){
        this.callParent(arguments);

        var me = this, items = this.getInnerItems();
        Ext.Array.forEach(items, function(item, index){
            item.element.on('tap', function(){
                me.setActiveItem(item);
                me.fireEvent('itemtap', me, item, index);
            });
        });
    },

    setActiveItem:function(item){
        var items = this.getInnerItems(), clicked = false;
        Ext.Array.forEach(items, function(i, index){
            clicked = Ext.isObject(item)? item.getItemId() == i.getItemId() : item == index;

            if(clicked){
                i.addCls('x-xactive');
            }else{
                i.removeCls('x-xactive');
            }
        });
    }
});

