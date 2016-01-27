Ext.define('YourTour.view.widget.XGenderSelect', {
    extend: 'Ext.field.Select',
    xtype: 'xgenderselect',
    config:{
        usePicker:1,
        options:[
            {text:'未知', value:''},
            {text:'男', value:'M'},
            {text:'女', value:'F'}
        ]
    }
});

