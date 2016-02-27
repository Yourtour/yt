Ext.define('YourTour.view.widget.XImageSelect', {
    extend: 'Ext.Container',
    xtype: 'ximageselect',
    config:{
        layout:'vbox',
        image:{
            fileName:'unknown.jpg',
            quality: 75,
            width: 200,
            height: 200
        },
        items:[
            {
                xtype:'selectfield',
                itemId:'imageSelect',
                hidden:true,
                usePicker:true,
                options: [
                    {text: '相机',  value: 'camera'},
                    {text: '相册', value: 'library'}
                ]
            }
        ]
    },

    initialize:function(){
        this.callParent(arguments);

        var me = this, imageSelect = me.down('#imageSelect'), image = me.image || me.getImage();
        me.addCls('icon-add');

        me.captured = false;
        imageSelect.on('change', function(select, newValue, oldValue, eOpts ){
            Ext.device.Camera.capture({
                success: function (image) {
                    me.removeCls('icon-add');

                    me.captured = true;
                    var base64 = 'data:image/jpeg;base64,' + image;
                    me.setData(base64);

                    var style = {};
                    style['background-image'] = 'url(' + base64 + ')';
                    style['background-repeat'] = 'no-repeat';
                    style['background-position'] = 'center center';
                    style['background-size'] = '100% 100%';
                    me.setStyle(style);
                },
                quality: image.quality,
                width: image.width,
                height: image.height,
                source: newValue,
                destination: 'data'
            });
        })

        me.element.on('tap', function(){
            if (!me.captured) {
                imageSelect.showPicker();
            }
        })
    },

    getAsBase64:function(){
        return this.getData();
    },

    updateImage:function(image){
        Ext.apply(this.image, image);
    },

    updateFileName:function(fileName){
        this.fileName = fileName;
    },

    getFileName:function(){
        var me = this, image = me.image || me.getImage();

        return image.fileName;
    }
});

