Ext.define('YourTour.view.widget.XGenderField', {
    extend: 'YourTour.view.widget.XPickerField',
    xtype: 'xgenderfield',
    config: {
        options: [
            {text: '男', value: 'MALE'},
            {text: '女', value: 'FEMALE'}
        ]
    }
});

