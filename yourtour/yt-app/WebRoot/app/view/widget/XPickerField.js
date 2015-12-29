Ext.define('YourTour.view.widget.XPickerField', {
    extend: 'YourTour.view.widget.XLabel',
    xtype: 'xpickerfield',
    config:{
        tappable:true,
        value:'',
        padding:'0 10 0 10'
    },

    setValue:function(value){
        this.value = value;
    },

    getValue:function(){
        return this.value;
    },

    setText:function(text){
        this.setHtml(text);
    },

    getText:function(){
        return this.getHtml();
    }
});

