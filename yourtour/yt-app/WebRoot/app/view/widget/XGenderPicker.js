Ext.define('YourTour.view.widget.XGenderPicker', {
    extend: 'YourTour.view.widget.XPicker',
    xtype: 'xgenderpicker',
    config:{
        data:[
            {text:'未知', value:''},
            {text:'男', value:'M'},
            {text:'女', value:'F'}
        ]
    }
});

