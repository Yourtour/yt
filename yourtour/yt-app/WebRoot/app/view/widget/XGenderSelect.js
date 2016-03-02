Ext.define('YourTour.view.widget.XGenderSelect', {
    extend: 'Ext.field.Select',
    xtype: 'xgenderselect',
    config:{
        usePicker:1,
        options:[
            {text:'男', value:'MALE'},
            {text:'女', value:'FEMALE'},
            {text:'未知', value:'NA'},
        ]
    }
});

