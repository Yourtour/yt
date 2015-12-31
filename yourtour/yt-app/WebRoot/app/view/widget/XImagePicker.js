Ext.define('YourTour.view.widget.XImagePicker', {
    extend: 'YourTour.view.widget.XPicker',
    xtype: 'ximagepicker',
    config:{
        data:[
            {text:'相册', value:'PhotoLib'},
            {text:'照相', value:'Camera'}
        ]
    }
});

