Ext.define('YourTour.view.place.ResidenceSelectionView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['Ext.List', 'YourTour.view.widget.XHeaderBar','YourTour.view.place.ResidenceSelectionListDataItem'],
    config: {
        id:'ResidenceSelectionView',
        items: [
            {
                xtype: 'xheaderbar'
            },

            {
                xtype: 'xpagebody',
                layout: 'vbox',
                items: [
                    {
                        xtype: 'list',
                        itemId: 'placeList',
                        flex: 1,
                        itemTpl:'{name}',
                        indexBar: {
                            zIndex: 2,
                            //listPrefix: '#'
                            letters:[
                                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
                            ]
                        },//在list里 显示类似快速查找
                        grouped: true,  //数据分类
                        hideOnMaskTap: false,
                        singleSelect : true,
                        scrollable: {
                            direction: 'vertical',
                            indicators: false,
                            directionLock: true,
                            momentumEasing: {
                                momentum: {
                                 acceleration: 10,
                                 friction: 0.9
                                 },
                                bounce: {
                                    acceleration: 0.0001,
                                    springTension: 0.9999
                                },
                                minVelocity: 5
                            },
                            outOfBoundRestrictFactor: 0
                        }
                    }
                ]
            }
        ]
    }
});

