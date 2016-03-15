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
        var
            items = this.getItems(),
            activeIndex = this.getActiveIndex(),
            containerLength = this.getItemLength(),
            itemLengthX = containerLength.x * 0.6,
            itemLengthY = containerLength.y,
            spaceLength = containerLength.x/20,
            inactiveLength = (containerLength.x- itemLengthX - 2 * spaceLength)/2,
            useX = Ext.isNumber(x),
            useY = Ext.isNumber(y),
            offset, i, ln, item, translateX, translateY;

        for (i = 0, ln = items.length; i < ln; i++) {
            item = items[i];
            if (item) {
                offset = (i - activeIndex);

                if (useX) {
                    if (i == 0) {
                        translateX = x - (itemLengthX - inactiveLength);
                    } else if (i == items.length - 1) {
                        translateX = x + itemLengthX + inactiveLength + 2 * spaceLength;
                    } else {
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