Ext.define('YourTour.view.widget.XPicker', {
    extend: 'Ext.Picker',
    xtype: 'xpicker',
    config:{
        text:'',
        stretchX:true,
        stretchY:false,
        enter:'bottom',
        exit:'bottom',
        centered:true,
        docked:'bottom',
        hidden:true,

        listeners:{
            pick:function(picker, The, slot, eOpts ){
                var data = picker.getData();
                var selectedIndex = slot.selectedIndex;
                picker.text = data[selectedIndex].text;
            },

            change:function(picker, value, eOpts){
                this.fireEvent('DoneTap', picker, value.value, picker.getText(), eOpts);
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

    getText:function(){
        return this.text;
    },

    setText:function(text){
        this.text = text;
    }
});

