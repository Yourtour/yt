Ext.define('YourTour.controller.ChargeMainCtrl', {
    extend: 'YourTour.controller.BaseCtrl',
    config: {
        refs: {
            chargeMainView:'#ChargeMainView',
            chargeFormView:'#ChargeFormView'
        },

        control: {
            '#ChargeMainView #btnCharge':{
                tap:'createChargeInfo'
            },

            '#ChargeFormView #btnSave':{
                tap:'saveChargeInfo'
            }
        }
    },

    showPage: function (routeId) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.charge.ChargeMainView'));

        var me = this, mainview = me.getChargeMainView(), data = {routeId:routeId};

        mainview.bindData(data);
    },

    createChargeInfo:function(){
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.charge.ChargeFormView'));
    },

    saveChargeInfo:function(){
        var me = this, formview = me.getChargeFormView(), charge = formview.getData();

        var data = this.getValues(formview, '#amount, #item, #type, #memo');
        if(charge){
            data.id = charge.get('id');
        }else{
            data.id = 0;
        }

        console.log(data);
    }
});
