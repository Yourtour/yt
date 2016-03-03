Ext.define('YourTour.view.widget.XGridSheet', {
    extend: 'Ext.ActionSheet',
    requires:['YourTour.view.common.GridSheetDataItem'],
    xtype:'xgridsheet',
    config: {
        layout:'vbox',
        items:[
            {
                xtype:'container',
                layout:'vbox',
                items:[
                    {
                        xtype:'container',
                        layout:'hbox',
                        cls:'underline row',
                        items:[
                            {
                                xtype: 'xbutton',
                                text: '取消',
                                itemId: 'btnCancel'
                            },
                            {
                                xtype:'spacer',
                                flex:1
                            },
                            {
                                xtype: 'xbutton',
                                text: '确定',
                                itemId: 'btnDone'
                            }
                        ]
                    },

                    {
                        xtype: 'xgridview',
                        itemId: 'gridview',
                        useComponents: true,
                        defaultType: 'GridSheetDataItem'
                    }
                ]
            }
        ]
    },

    initialize:function(){
        this.callParent(arguments);

        this.down('#btnCancel').on('tap', this.onCancelButtonTap, this);
        this.down('#btnDone').on('tap', this.onDoneButtonTap, this);
        this.down('#gridview').on('itemtap', this.onGridItemTap, this);
    },

    onGridItemTap:function(gridview, index, item, record, e){
        var selected = record.get('selected');
        if(selected){
            record.set('selected', false);
        }else{
            record.set('selected', true);
        }

        var name = item.down('#name');
        if(record.get('selected')){
            name.removeLabelCls('icon-unchecked');
            name.setLabelCls('icon-checked');
        }else{
            name.removeLabelCls('icon-checked');
            name.setLabelCls('icon-unchecked');
        }
    },

    onCancelButtonTap:function(){
        this.fireEvent('cancel', this);
        this.hide();
        Ext.util.InputBlocker.unblockInputs();
    },

    onDoneButtonTap:function(){
        this.fireEvent('done', this);
        this.hide();
        Ext.util.InputBlocker.unblockInputs();
    },

    setStore:function(store){
        this.down('#gridview').setStore(store);
    },

    show: function() {
        if (this.getParent() === undefined) {
            Ext.Viewport.add(this);
        }

        this.callParent(arguments);
        Ext.util.InputBlocker.blockInputs();
    },

    destroy: function() {
        this.callParent();
        Ext.destroy(this);
    }
});

