Ext.define('YourTour.view.widget.XPageBody', {
    extend: 'Ext.Container',
    xtype: 'xpagebody',
    config:{
        itemId:'pagebody',
        flex:1,
        scrollable: {
            direction: 'vertical',
            indicators: false,
            directionLock: true,
            momentumEasing:  {
                /*momentum: {
                 acceleration: 10,
                 friction: 0.9
                 },*/
                bounce: {
                    acceleration: 0.0001,
                    springTension: 0.9999,
                },
                /*minVelocity: 5*/
            },
            outOfBoundRestrictFactor: 0
        }
    }
});

