Ext.define('YourTour.view.widget.XImageSelect', {
    extend: 'Ext.Container',
    xtype: 'ximageselect',
    requires:['YourTour.view.widget.XImageField','YourTour.view.widget.XNavSelect'],
    config: {
        layout: 'vbox',
        flex: 1,
        image: {
            fileName: 'unknown.jpg',
            quality: 75,
            width: 200,
            height: 200,
            maximumImageCount: 9,  //需要采集的图片总数
            maximumImageColCount: 4 //每行允许显示的图片
        },
        imageContainer: null,
        items: [
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

    initialize: function () {
        this.callParent(arguments);

        var me = this,
            imageSelect = me.down('#imageSelect'),
            image = me.image || me.getImage();

        me.imageCount = 0;
        me.createImageRowContainer();

        imageSelect.on('change', function (select, newValue, oldValue, eOpts) {
            Ext.device.Camera.capture({
                success: function (image) {
                    me.modified = true;

                    var images = me.imageContainer.query('.ximagefield'), activeImage, activeIndex = -100;
                    Ext.Array.forEach(images, function (image, index) {
                        if (image.active) {
                            activeImage = image;
                            activeIndex = index;
                            image.removeCls('icon-add');

                            image.setActive(false);
                            activeImage.setCaptured(true);
                        } else if (activeIndex + 1 == index) {
                            image.addCls('icon-add');

                            activeImage.setCaptured(false);
                            image.setActive(true);
                        }
                    });

                    activeImage.setData(image);
                    if (activeIndex == image.maximumImageColCount - 1) {
                        me.createImageRowContainer();
                        me.add(me.imageContainer);
                    }
                },
                quality: image.quality,
                width: image.width,
                height: image.height,
                source: newValue,
                destination: 'data'
            });
        })
    },

    bindEvents: function (row) {
        var me = this, imageSelect = me.down('#imageSelect'), images = row.query('.ximagefield');
        Ext.Array.forEach(images, function (image) {
            image.element.on('tap', function () {
                if (image.getActive() || image.getCaptured()) {
                    imageSelect.showPicker();
                }
            })
        });
    },

    /**
     *
     */
    createImageRowContainer: function () {
        var imageCount = this.imageCount;
        this.imageContainer = Ext.create('Ext.Container', {
            layout: 'hbox',
            flex: 1,
            cls: 'x-ximage-row',
            defaults: {
                padding: '5 10'
            },
            items: []
        });
        this.add(this.imageContainer);

        var image = this.image || this.getImage();
        for (var i = 0; i < image.maximumImageColCount && imageCount < image.maximumImageCount; i++) {
            if (i == 0) {
                this.imageContainer.add({
                    xtype: 'ximagefield',
                    active: true,
                    fileName:image.fileName
                })
            } else {
                this.imageContainer.add({
                    xtype: 'ximagefield',
                    fileName:image.fileName
                })
            }

            imageCount += 1;
        }

        this.imageCount = imageCount;
        this.imageContainer.getAt(0).addCls('icon-add');

        this.bindEvents(this.imageContainer);
    },

    /**
     * 获取选择的图片
     * @returns {Array}
     */
    getImages:function(){
        var me = this, fields = me.query('.ximagefield'), results=[];
        Ext.Array.forEach(fields, function(field){
            if(field.getCaptured()){
                results.push(field);
            }
        });

        return results;
    },

    setSrc:function(src){
        var me = this, field = me.query('.ximagefield')[0];

       field.setSrc(src);
    },

    isModified:function(){
        return this.modified;
    }
});

