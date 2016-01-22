Ext.define('YourTour.view.place.PlaceSelectionView', {
	extend: 'YourTour.view.widget.XPage',
    requires:['YourTour.view.widget.XHeaderBar','YourTour.view.widget.XCarousel','YourTour.view.place.PlaceSelectionHomeItem','YourTour.view.place.PlaceSelectionRecommendItem','YourTour.view.place.PlaceSelectionAbroadItem'],
    config: {
        id:'PlaceSelectionView',
        itemId:'PlaceSelectionView',
        items: [
            {
                xtype: 'xheaderbar',
                title:'想去哪儿玩'
            },
            {
                xtype: 'xcarousel',
                itemId:'placeCarousel',
                flex:1,
                items:[
                    {
                        xtype:'PlaceSelectionRecommendItem',
                        itemId:'recommendItem'
                    },
                    {
                        xtype:'PlaceSelectionHomeItem',
                        itemId:'homeItem'
                    },
                    {
                        xtype:'PlaceSelectionAbroadItem',
                        itemId:'abroadItem'
                    }
                ]
            }
        ]
    }
});

