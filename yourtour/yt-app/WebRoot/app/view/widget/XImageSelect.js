Ext.define('YourTour.view.widget.XImageSelect', {
    extend: 'Ext.Container',
    xtype: 'ximageselect',
    config:{
        layout:'vbox',
        docked:'bottom',
        success:Ext.emptyFn,
        fail:Ext.emptyFn,
        items:[
            {
                xtype: 'xlabel',
                itemId:'photo',
                cls:'row underline font-medium font-grey text-align-center',
                html:'相册'
            },

            {
                xtype: 'xlabel',
                itemId:'camera',
                cls:'row underline font-medium font-grey  text-align-center',
                html:'照相'
            }
        ]
    },

    initialize:function(){
        this.callParent(arguments);

        var me = this, photo = me.down('#photo'), camera = me.down('#camera');
        photo.on({
            scope:photo,
            tap:function(){
                me.getPhoto(navigator.camera.PictureSourceType.PHOTOLIBRARY);
            }
        });

        camera.on({
            scope:photo,
            tap:function(){
                me.getPhoto(navigator.camera.PictureSourceType.CAMERA);
            }
        });
    },

    updateSuccess:function(success){
        this.success = success;
    },

    updateFail:function(fail){
        this.fail = fail;
    },

    getPhoto: function(source) {
        var me = this;

        navigator.camera.getPicture(me.success, me.fail, {
            quality: 50,
            destinationType: navigator.camera.DestinationType.FILE_URI,
            sourceType: source
        });
    },
});

