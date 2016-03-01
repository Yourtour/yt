Ext.define('YourTour.view.expert.ExpertViewCommentItem', {
    extend: 'YourTour.view.common.CarouselItem',
    xtype:'ExpertViewCommentItem',
    requires:['YourTour.view.common.CommentListDataItem'],
    config: {
        layout: 'vbox',
        items: [
            {
                xtype: 'xdataview',
                itemId: 'commentList',
                flex:1,
                defaultType: 'CommentListDataItem'
            }
        ]
    }
});

