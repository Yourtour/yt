Ext.define('YourTour.controller.ChargeMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
        refs: {
            chargeMainView:'#ChargeMainView',
            chargeListView:'#ChargeListView',
            chargeList:'#ChargeListView #chargeList',
            chargeFormView:'#ChargeFormView'
        },

        control: {
            '#ChargeMainView #btnCharge':{
                tap:'createChargeInfo'
            },

            '#ChargeMainView #ticket':{
                tap:'showChargeListInfo'
            },

            '#ChargeMainView #food':{
                tap:'showChargeListInfo'
            },

            '#ChargeMainView #hotel':{
                tap:'showChargeListInfo'
            },

            '#ChargeMainView #traffic':{
                tap:'showChargeListInfo'
            },

            '#ChargeMainView #shopping':{
                tap:'showChargeListInfo'
            },

            '#ChargeMainView #other':{
                tap:'showChargeListInfo'
            },

            '#ChargeFormView #btnSave':{
                tap:'saveChargeInfo'
            },

            '#ChargeFormView #chargeDate':{
                tap:'selectChargeDate'
            }
        }
    },


    /**
     * 显示行程费用主界面
     * @param routeId
     */
    showPage: function (routeId) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.charge.ChargeMainView'));
        var me = this, mainview = me.getChargeMainView(), data = {routeId:routeId};

        var options = {
            model: 'YourTour.model.ChargeModel',
            url: '/route/' + routeId + '/charge/query',
            success: function (store) {
                store.setSorters({property: 'chargeDate', direction: 'ASC'});

                data.store = store;
                mainview.bindData(data);

                me.countTotalCharge();
            }
        };
        me.getApplication().query(options);
    },

    countTotalCharge:function(){
        var me = this, mainview = me.getChargeMainView(), data = mainview.getData(), store = data.store;
        var total = mainview.down('#total'), ticket = mainview.down('#ticket'), food = mainview.down('#food'),
            hotel = mainview.down('#hotel'), shopping = mainview.down('#shopping'), traffic = mainview.down('#traffic'),
            other = mainview.down('#other');

        var charge = {total:0, Ticket:0, Food:0, Hotel:0, Shopping:0, Traffic:0, Other:0}, amount;

        store.each(function(item){
            amount = Number(item.get('amount'));
            charge.total = charge.total + amount;
            charge[item.get('item')] = charge[item.get('item')] + amount;
        });

        total.setText(charge.total);
        ticket.setText(charge.Ticket);
        food.setText(charge.Food);
        hotel.setText(charge.Hotel);
        shopping.setText(charge.Shopping);
        traffic.setText(charge.Traffic);
        other.setText(charge.Other);
    },

    /**
     * 显示费用明细
     * @param item
     */
    showChargeListInfo:function(field){
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.charge.ChargeListView'));

        var itemId = field.getItemId();
        var me = this, chargeList = me.getChargeList(), mainview = me.getChargeMainView(), data = mainview.getData(), store = data.store;
        store.each(function(item){
            if(Ext.String.capitalize(item.get('item')) == Ext.String.capitalize(itemId)){
                item.set('hidden', false);
            }else{
                item.set('hidden', true);
            }
        });

        chargeList.setStore(store);
    },

    /**
     *
     */
    createChargeInfo:function(){
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.charge.ChargeFormView'));
    },

    selectChargeDate:function(){
        var me = this, view = me.getChargeFormView();
        var controller = this.getApplication().getController('CommonMainCtrl');
        var options = {single:true, title:'费用日期'};
        controller.showTimeSelectionView(options, function (startDate) {
            var chargeDate = view.down('#chargeDate');
            chargeDate.setText(startDate);

            Ext.ComponentManager.get('MainView').pop();
        });
    },

    saveChargeInfo:function(){
        var me = this;
        var formview = me.getChargeFormView(), charge = formview.getData();
        var mainview = me.getChargeMainView(), route = mainview.getData();

        var data = this.getValues(formview, '#amount, #item, #type, #memo, #name, #chargeDate');
        if(charge){
            data.id = charge.get('id');
        }else{
            data.id = 0;
        }

        this.getApplication().callService({
            url: '/route/' + route.routeId + '/charge/save',
            method: "POST",
            params: data,
            success: function (response) {
                var store = route.store;
                data.id = response;
                var model = Ext.create('YourTour.model.ChargeModel',data)
                store.add(model);
                me.countTotalCharge();
                Ext.ComponentManager.get('MainView').pop();
            }
        });
    }
});
