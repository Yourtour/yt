Ext.define('YourTour.view.widget.XUserLogo', {
    extend: 'YourTour.view.widget.XTappable',
    xtype: 'xuserlogo',
    config:{
        src:null,
        baseCls:'x-xuser-logo',
        binding:'imageUrl',
        editable:false,
        items:[
            {
                xtype: 'xnavselect',
                itemId: 'imageSelect',
                hidden: true,
                usePicker: true,
                options: [
                    {text: '相机', value: 'camera'},
                    {text: '相册', value: 'library'}
                ]
            }
        ]
    },

    initialize:function(){
        this.callParent(arguments);

        var me = this, editable = me.editable, imageSelect = me.down('#imageSelect');

        if(editable){
            imageSelect.on('change', function (select, newValue, oldValue, eOpts) {
                Ext.device.Camera.capture({
                    success: function (image) {
                        me.setData(image);
                    },
                    quality: 75,
                    width: 200,
                    height: 200,
                    source: newValue,
                    destination: 'data'
                });
            })
        }

        me.modified = false;
    },

    onTap:function(){
        var me = this, editable = me.editable;

        if(editable) {
            me.showPicker();
        }else{
            this.callParent(arguments);
        }
    },

    showPicker:function(){
        var me = this, imageSelect = me.down('#imageSelect');
        imageSelect.showPicker();
    },

    updateEditable:function(editable){
        this.editable = editable;
    },

    updateBinding:function(binding){
        this.binding = binding;
    },

    updateRecord:function(record){
        var binding =  this.binding || this.getBinding();
        var names = binding.split('.');
        var len = names.length;

        var data = record;
        var store = null;
        for(var index = 0; index < len - 1; index++){
            eval('store = data.' + [names[index]] + '()');
            data = store.first();
        }
        var name = names[len - 1];

        var url = data.get(name).split(';')[0];

        var url = YourTour.util.Context.getImageResource(url);
        this.setSrc(url);
    },

    updateSrc:function(url){
        this.setSrc(url);
    },

    setSrc:function(url){
        var style = {};
        style['background-image'] = 'url(' + url + ')';
        style['background-repeat'] = 'no-repeat';
        style['background-position'] = 'center center';
        style['background-size'] = '100% 100%';
        this.setStyle(style);
    },

    setData:function(data){
        var me = this,
            base64 = 'data:image/jpeg;base64,' + data;
        styles = {};
        styles['background-image'] = 'url(' + base64 + ')';
        styles['background-repeat'] = 'no-repeat';
        styles['background-position'] = 'center center';
        styles['background-size'] = '100% 100%';
        me.setStyle(styles);

        this.data = data;

        this.modified = true;
    },

    getModified:function(){
        return this.modified;
    }
});

