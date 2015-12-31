Ext.define('YourTour.view.widget.XPicker', {
    extend: 'Ext.Picker',
    xtype: 'xpicker',
    config:{
        selectedIndex:0,
        stretchX:true,
        stretchY:false,
        enter:'bottom',
        exit:'bottom',
        centered:true,
        docked:'bottom',
        hidden:true,

        listeners:{
            pick:function(picker, The, slot, eOpts ){
                picker.setSelectedIndex(slot.selectedIndex);
            },

            change:function(picker, value, eOpts){
                var datas = picker.getData();
                var data = datas[picker.getSelectedIndex()];

                this.fireEvent('donetap', picker, data.value, data.text, eOpts);
            }
        }
    },

    initialize : function(){
        this.callParent(arguments);

        this.setSlots(
            {
                name:'value',
                align: 'center',
                data:this.getData()
            }
        );
    },

    setSelectedIndex:function(selectedIndex){
        this.selectedIndex = selectedIndex;
    },

    getSelectedIndex:function(){
        return this.selectedIndex;
    }
});

