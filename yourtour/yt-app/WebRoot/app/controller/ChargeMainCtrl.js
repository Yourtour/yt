Ext.define('YourTour.controller.ChargeMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
        refs: {
            chargeMainView: '#ChargeMainView',
            chargeListView: '#ChargeListView',
            chargeList: '#ChargeListView #chargeList',
            chargeFormView: '#ChargeFormView',
            chargeView: '#ChargeView',
            divideList: '#ChargeView #divideList',

            chargeDivisionFormView: '#ChargeDivisionFormView'
        },

        control: {
            '#ChargeMainView #btnCharge': {
                tap: 'createChargeInfo'
            },

            '#ChargeMainView #ticket': {
                tap: 'showChargeListInfo'
            },

            '#ChargeMainView #food': {
                tap: 'showChargeListInfo'
            },

            '#ChargeMainView #hotel': {
                tap: 'showChargeListInfo'
            },

            '#ChargeMainView #traffic': {
                tap: 'showChargeListInfo'
            },

            '#ChargeMainView #shopping': {
                tap: 'showChargeListInfo'
            },

            '#ChargeMainView #other': {
                tap: 'showChargeListInfo'
            },

            '#ChargeFormView #btnSave': {
                tap: 'saveChargeInfo'
            },

            '#ChargeFormView #chargeDate': {
                tap: 'selectChargeDate'
            },

            '#ChargeListView #chargeList': {
                itemtap: 'showChargeInfo'
            },

            '#ChargeView #btnEdit': {
                tap: 'editChargeInfo'
            },

            '#ChargeView #btnDelete': {
                tap: 'deleteChargeInfo'
            },

            '#ChargeView #btnDivide': {
                tap: 'queryRouteMemberInfo'
            },

            '#ChargeDivisionFormView #btnSave': {
                tap: 'saveChargeDivision'
            }
        },

        routeId:null
    },


    /**
     * 显示行程费用主界面
     * @param routeId
     */
    showPage: function (routeId) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.charge.ChargeMainView'));
        var me = this, mainview = me.getChargeMainView();

        me.routeId = routeId;

        var options = {
            model: 'YourTour.model.ChargeModel',
            url: '/route/' + routeId + '/charge/query',
            success: function (store) {
                store.setSorters({property: 'chargeDate', direction: 'ASC'});
                mainview.bindData(store);

                me.countTotalCharge();
            }
        };
        me.getApplication().query(options);
    },

    /**
     * 总账计算
     */
    countTotalCharge: function () {
        var me = this, mainview = me.getChargeMainView(), store = mainview.getData();
        var total = mainview.down('#total'), ticket = mainview.down('#ticket'), food = mainview.down('#food'),
            hotel = mainview.down('#hotel'), shopping = mainview.down('#shopping'), traffic = mainview.down('#traffic'),
            other = mainview.down('#other');

        var charge = {total: 0, Ticket: 0, Food: 0, Hotel: 0, Shopping: 0, Traffic: 0, Other: 0}, amount;

        store.each(function (item) {
            amount = Number(item.get('payment'));
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
    showChargeListInfo: function (field) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.charge.ChargeListView'));

        var itemId = field.getItemId();
        var me = this, chargeList = me.getChargeList(), mainview = me.getChargeMainView(), store = mainview.getData();
        store.each(function (item) {
            if (Ext.String.capitalize(item.get('item')) == Ext.String.capitalize(itemId)) {
                item.set('hidden', false);
            } else {
                item.set('hidden', true);
            }
        });

        chargeList.setStore(store);
    },

    showChargeInfo: function (dataview, index, item, record) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.charge.ChargeView'));

        var me = this;
        var chargeview = me.getChargeView(), divideList = me.getDivideList();
        chargeview.setData(record);

        var options = {
            model: 'YourTour.model.ChargeModel',
            url: Ext.String.format('/route/{0}/charge/{1}/division/query', me.routeId, record.get('id')),
            success: function (store) {
                divideList.setStore(store);

                me.countChargeDivision();
            }
        };
        me.getApplication().query(options);
    },

    /**
     * 计算费用分摊
     */
    countChargeDivision: function () {
        var me = this, divideview = me.getChargeView(), divideList = me.getDivideList(), divisionStore = divideList.getStore(), amount = 0;
        divisionStore.each(function (charge) {
            amount += charge.get('payment');
        });

        var divideAmount = divideview.down('#divideAmount');
        divideAmount.setText(amount);

        var charge = divideview.getData();
        charge.set('payment', charge.get('payment') - amount);
    },

    /**
     *
     */
    editChargeInfo: function () {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.charge.ChargeFormView'));

        var me = this, chargeview = me.getChargeView(), formview = me.getChargeFormView(), charge = chargeview.getData();

        formview.setData(charge);
    },

    /**
     *
     */
    deleteChargeInfo: function () {
        var me = this;
        var chargeview = me.getChargeView(), charge = chargeview.getData();
        var mainview = me.getChargeMainView(), store = mainview.getData();

        this.getApplication().callService({
            url: '/route/' + me.routeId + '/charge/' + charge.get('id') + '/delete',
            method: "GET",
            success: function (response) {
                store.remove(charge);
                me.countTotalCharge();
                Ext.ComponentManager.get('MainView').pop();
            }
        });
    },

    /**
     *
     */
    createChargeInfo: function () {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.charge.ChargeFormView'));
    },

    selectChargeDate: function () {
        var me = this, view = me.getChargeFormView();
        var controller = this.getApplication().getController('CommonMainCtrl');
        var options = {single: true, title: '费用日期'};
        controller.showTimeSelectionView(options, function (startDate) {
            var chargeDate = view.down('#chargeDate');
            chargeDate.setText(startDate);

            Ext.ComponentManager.get('MainView').pop();
        });
    },

    saveChargeInfo: function () {
        var me = this;
        var formview = me.getChargeFormView(), charge = formview.getData();
        var mainview = me.getChargeMainView(), store = mainview.getData();

        var data = this.getValues(formview, '#amount, #item, #type, #memo, #name, #chargeDate');
        if (charge) {
            data.id = charge.get('id');
        } else {
            data.id = 0;
        }

        data.type = 1;

        this.getApplication().callService({
            url: '/route/' + me.routeId + '/charge/save',
            method: "POST",
            params: data,
            success: function (response) {
                if (data.id == 0) {
                    data.id = response;
                    var model = Ext.create('YourTour.model.ChargeModel', data)
                    store.add(model);
                    Ext.ComponentManager.get('MainView').pop();
                } else {
                    charge.set('name', data.name);
                    charge.set('item', data.item);
                    charge.set('amount', data.amount);
                    charge.set('chargeDate', data.chargeDate);
                    charge.set('type', data.type);
                    charge.set('memo', data.memo);
                    Ext.ComponentManager.get('MainView').pop(2);
                }
                me.countTotalCharge();
            }
        });
    },

    queryRouteMemberInfo: function () {
        var me = this, controller = me.getApplication().getController('MemberMainCtrl');
        var view = me.getChargeView(), charge = view.getData();
        controller.showMemberSelectionView(me.routeId, function (record) {
            Ext.ComponentManager.get('MainView').pop();
            Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.charge.ChargeDivisionFormView'));

            var divisionview = me.getChargeDivisionFormView();
            divisionview.updateData(charge);

            var userId = divisionview.down('#userId');
            userId.setText(record.get('id'));

            var nickName = divisionview.down('#nickName');
            nickName.setText(record.get('nickName'));

            var divisionAmount = divisionview.down('#divisionAmount');
            divisionAmount.setText('0');
        });
    },

    /**
     * 保存分摊信息
     */
    saveChargeDivision: function () {
        var me = this, divisionview = me.getChargeDivisionFormView(), charge = divisionview.getData(), divideList = me.getDivideList();

        var chargeId = charge.get('id');

        var params = {};
        params.name = charge.get('name');
        params.chargeDate = charge.get('chargeDate');
        params.item = charge.get('item');
        params.memo = charge.get('memo');
        params.type = '2';

        var divisionAmount = divisionview.down('#divisionAmount');
        params.amount = divisionAmount.getValue();
        params.payment = divisionAmount.getValue();

        var userId = divisionview.down('#userId');
        var uid = userId.getValue();

        this.getApplication().callService({
            url: Ext.String.format('/route/{0}/charge/{1}/division/{2}/save', me.routeId, chargeId, uid),
            method: "POST",
            params: params,
            success: function (response) {
                params.id = response;
                Ext.ComponentManager.get('MainView').pop();
                var store = divideList.getStore();
                store.add(Ext.create('YourTour.model.ChargeModel', params));
            }
        });
    }
});
