Ext.define('YourTour.controller.HomeMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
        refs: {
            homeMainView: '#HomeMainView',
            routeList: '#HomeMainView #routeList'
        },

        control: {}

    },

    init:function(){
      this.showMainPage();
    },

    showMainPage: function () {
        YourTour.util.Context.mainview = this.getHomeMainView();
        var me = this, view = me.getHomeMainView(), routeList = me.getRouteList();

        var options = {
            model: 'YourTour.model.RouteModel',
            url: '/routes/recommend/6/7',
            success: function (store) {
                routeList.setStore(store);
            }
        };
        me.getApplication().query(options);
    }
});
