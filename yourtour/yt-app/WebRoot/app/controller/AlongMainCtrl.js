Ext.define('YourTour.controller.AlongMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
        refs: {
            alongFormView: '#AlongFormView',
            alongEditView: '#AlongEditView',
            alongListView: '#AlongListView',
            alongList: '#AlongListView #alongList'
        },

        control: {
            '#AlongFormView #btnNew': {
                tap: 'createAlongInfo'
            },

            '#AlongFormView #btnEdit': {
                tap: 'editAlongInfo'
            },

            '#AlongEditView #btnSave': {
                tap: 'saveAlongInfo'
            },

            alongList:{
                itemtap:'showAlongInfo4List'
            }
        }
    },

    /**
     *
     * @param place
     */
    showAlongList: function (place) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.along.AlongListView'));

        var me = this,
            alongList = me.getAlongList(),
            listview = me.getAlongListView(),
            headerbar = listview.down('#headerbar');

        headerbar.setTitle(place.get('name'));

        var options = {
            model: 'YourTour.model.AlongModel',
            url: '/along/place/' + place.get('id'),
            success: function (store) {
                alongList.setStore(store);
            }
        };
        me.getApplication().query(options);
    },

    /**
     *
     */
    createAlongInfo:function(){
        this.showAlongEditView();
    },

    /**
     *
     */
    editAlongInfo:function(){
        var me = this, formview = me.getAlongFormView(), along = formview.getData();

        this.showAlongEditView(along);
    },

    /**
     *
     * @param along
     */
    showAlongEditView: function (along) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.along.AlongEditView'));
        var me = this, view = me.getAlongEditView();

        if (along) {
            view.bindData(along);
        }
    },

    /**
     *
     */
    saveAlongInfo: function () {
        var controller = this.getApplication().getController('MemberMainCtrl');
        var me = this, view = me.getAlongEditView(), along = view.getData(), routeId = controller.getRouteInfo().get('id'), formview = me.getAlongFormView();

        var data = {}, alongId = '0';
        if(along){
            alongId = along.get('id');
        }

        data.title = view.down('#title').getValue();
        data.num = view.down('#num').getValue();
        data.intention = view.down('#intention').getValue();
        data.deadLine = new Date(view.down('#deadline').getValue()).getTime();
        data.memo = view.down('#memo').getValue();

        this.getApplication().callService({
            url: 'along/' + routeId + '/' + alongId + '/save',
            method: "POST",
            params:data,
            success: function (response) {
                var along = Ext.create('YourTour.model.AlongModel', data);
                along.set('id', response);

                me.showAlongInfo(along);

                Ext.ComponentManager.get('MainView').pop();
            }
        });
    },

    showAlongInfo4List:function(dataview, index, item, record){
        this.showAlongInfoView(record);
    },

    /**
     *
     * @param along
     */
    showAlongInfoView: function (record) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.along.AlongFormView'));

        var along = null, me = this, formview = me.getAlongFormView();
        if(record instanceof YourTour.model.RouteModel) {
            var options = {
                model: 'YourTour.model.AlongModel',
                url: '/along/route/' + record.get('id'),
                success: function (store) {
                    along = store.first();

                    me.showAlongInfo(along);
                }
            };
            me.getApplication().query(options);
        }else{
            along = record;
            this.showAlongInfo(along);
        }
    },

    /**
     *
     * @param along
     * @param action
     */
    showAlongInfo: function (along) {
        var me = this, formview = me.getAlongFormView(), btnNew = formview.down('#btnNew'), btnEdit = formview.down('#btnEdit');
        if (along) {
            formview.setActiveItem(1);
            formview.setData(along);
            btnNew.hide();
        } else {
            formview.setActiveItem(0);
            btnEdit.hide();
        }
    }
});
