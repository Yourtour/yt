Ext.define('YourTour.view.widget.XImageSelect', {
    extend: 'YourTour.view.widget.XPicker',
    xtype: 'ximageselect',
    config:{
        data:[
            {text:'相册', value:'PhotoLib'},
            {text:'照相', value:'Camera'}
        ]
    }
});

