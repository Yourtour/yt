Ext.define('YourTour.controller.HomeMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
        refs: {
            homeMainView: '#HomeMainView',
            routeList: '#HomeMainView #routeList'
        },

        control: {}

    },

    showMainPage: function () {
        YourTour.util.Context.mainview = this.getHomeMainView();
        var me = this;

        var options = {
            model: 'YourTour.model.HomeModel',
            url: '/home/0',
            success: function (store) {
            }
        };
        me.getApplication().query(options);
    }
});
