Ext.define('YourTour.view.widget.XImageSelect', {
    extend: 'Ext.Container',
    xtype: 'ximageselect',
    requires:['YourTour.view.widget.XImageField'],
    config: {
        layout: 'vbox',
        flex: 1,
        image: {
            fileName: 'unknown.jpg',
            quality: 75,
            width: 200,
            height: 200,
            maximumImageCount: 9,  //需要采集的图片总数
            maximumImageColCount: 3 //每行允许显示的图片
        },
        imageContainer: null,
        items: [
            {
                xtype: 'selectfield',
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
        me.imageContainer = me.query('.container')[0];

        imageSelect.on('change', function (select, newValue, oldValue, eOpts) {
            Ext.device.Camera.capture({
                success: function (image) {
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

                    me.imageCount += 1;
                    if (activeIndex == image.maximumImageColCount - 1 && me.imageCount < image.maximumImageCount) {
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
        this.imageContainer = Ext.create('Ext.Container', {
            layout: 'hbox',
            flex: 1,
            cls: 'x-ximage-row',
            defaults: {
                flex: 1,
                padding: 5
            },
            items: []
        });
        this.add(this.imageContainer);

        var image = this.image || this.getImage();
        for (var i = 0; i < image.maximumImageColCount; i++) {
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
        }
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
    }
});

