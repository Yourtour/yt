Ext.define('YourTour.view.widget.XPickerField', {
    extend: 'YourTour.view.widget.XLabel',
    xtype: 'xpickerfield',
    config:{
        tappable:true,
        value:'',
        style:'text-align:right',
        padding:'0 10 0 0',
        html:'asfdasf'
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

