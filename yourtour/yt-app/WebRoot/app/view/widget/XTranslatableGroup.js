/**
 * @private
 */
Ext.define('YourTour.view.widget.XTranslatableGroup', {
    extend: 'Ext.util.translatable.Abstract',
    config: {
        items: [],

        activeIndex: 0,

        itemLength: {
            x: 0,
            y: 0
        }
    },

    applyItems: function (items) {
        return Ext.Array.from(items);
    },

    doTranslate: function (x, y) {
        var items = this.getItems(),
            activeIndex = this.getActiveIndex(),
            containerLength = this.getItemLength(),
            itemLengthX = containerLength.x * 0.6, //
            itemLengthY = containerLength.y,
            spaceLength = containerLength.x/20, //分隔空间大小
            scaleLengthX = itemLengthX * 0.3, //水平最大缩放宽度，为原始尺寸的30%
            scalePercentX = Math.abs(x) > scaleLengthX? 25 : Math.abs(x) * 0.6 * 100/ scaleLengthX, //缩放比例
            inactiveLength = (containerLength.x- itemLengthX - 2 * spaceLength)/2,
            useX = Ext.isNumber(x),
            useY = Ext.isNumber(y),
            offset, i, ln, item, translateX, translateY, itemHeight;

        for (i = 0, ln = items.length; i < ln; i++) {
            item = items[i];
            if (item) {m
                itemHeight = item.itemHeight;
                offset = (i - activeIndex);

                if (useX) {
                    if (i == 0) {
                        if(x > 0){
                            item.setHeight((75 + scalePercentX) + '%');
                            item.setTop(itemHeight * (1- (75 + scalePercentX) / 100) / 2);
                        }

                        translateX = x - (itemLengthX - inactiveLength);
                    } else if (i == items.length - 1) {
                        if(x < 0){
                            item.setHeight((75 + scalePercentX) + '%');
                            item.setTop(itemHeight * (1- (75 + scalePercentX) / 100) / 2);
                        }

                        translateX = x + itemLengthX + inactiveLength + 2 * spaceLength;
                    } else {
                        item.setHeight((100 - scalePercentX) + '%');
                        item.setTop(itemHeight * (1- (100 - scalePercentX) / 100) / 2);

                        translateX = x + spaceLength +  inactiveLength;
                    }

                    //console.log('i= ' + i + ',translateX=' + translateX + ', x=' + x + ',offset=' + offset + ', itemLengthX=' + itemLengthX);
                }

                if (useY) {
                    translateY = y + offset * itemLengthY;
                }

                item.translate(translateX, translateY);
            }
        }
    }
});