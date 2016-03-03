Ext.define('YourTour.view.widget.XGenderField', {
    extend: 'YourTour.view.widget.XPickerField',
    xtype: 'xgenderfield',
    config: {
        placeHolder:'性别选择',
        options: [
            {text: '男', value: 'MALE'},
            {text: '女', value: 'FEMALE'},
            {text: '未知', value: 'NA'}
        ]
    }
});

