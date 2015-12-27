Ext.define('YourTour.view.widget.XGenderPicker', {
    extend: 'Ext.Picker',
    xtype: 'xgenderpicker',
    config:{
        stretchX:true,
        stretchY:false,
        centered:true,
        docked:'bottom',
        slots:[
            {
                name:'value',
                title: 'text',
                align: 'center',
                data:[
                    {text:'男', value:'M'},
                    {text:'女', value:'F'},
                    {text:'未知', value:'U'}
                ]
            }
        ]
    }
});

