Ext.define('YourTour.view.widget.XSelectField', {
    extend: 'YourTour.view.widget.XField',
    xtype: 'xselectfield',
    config: {
        /**
         * 数据源
         */
        store:null,

        render:{
            style:'list',
            multiselect:false,
            cols:3,
            value:'id',
            text:'name'
        },

        view:null
    },

    /**
     * 编辑状态
     */
    onEditTap: function () {
        var me = this, render = me.render;
        if(render.style == 'list'){
            me.view = me.getListView();
        }else{
            me.view = me.getGridView();
        }

        me.view.show();
    },

    /**
     * 获取列表式选择视图
     * @returns {YourTour.view.widget.XPicker}
     */
    getListView:function(){
        var me = this, render = me.render,  options = [] ;

        me.store.each(function(item){
            options.push({text:item[me.render.text], value:item[me.render.value]});
        });

        var picker = Ext.create('YourTour.view.widget.XPicker', {
            slots: [
                {
                    name: 'slot',
                    data: options
                }
            ]
        });

        picker.on('change', function(picker, newValue){
            me.modifyText(newValue.slot);
            me.setValue(newValue.slot);
        });

        Ext.Viewport.add(picker);

        return picker;
    },

    /**
     * 获取Grid式选择视图
     */
    getGridView:function(){
        var me = this, render = me.render;

        var picker = Ext.create('YourTour.view.widget.XPicker', {
            slots: [
                {
                    name: 'slot',
                    data: options
                }
            ]
        });

        picker.on('change', function(picker, newValue){
            me.modifyText(newValue.slot);
            me.setValue(newValue.slot);
        });

        Ext.Viewport.add(picker);

        return picker;
    },

    setText:function(text){
        var me = this;
        Ext.Array.forEach(me.options, function(item){
           if(item.value == text){
               text = item.text;
           }
        });

        me.callParent([text]);
    }
});

