Ext.define('YourTour.view.widget.XPlaceField', {
    extend: 'YourTour.view.widget.XField',
    xtype: 'xplacefield',
    config: {
        editable: true
    },

    onEditTap: function () {
        var me = this, application = YourTour.util.Context.getApplication(), controller = application.getController('PlaceMainCtrl');

        controller.showResidenceSelectionView(this);
    },

    setText:function(text){
        if(text == null) return;

        var places = text.split('|'), str='';
        Ext.Array.forEach(places, function(place){
            if(str != '') str += ',';

            if(place.split(',').length > 1){
                str += place.split(',')[1];
            }else{
                str += place;
            }
        })

        this.callParent([str]);
    }
});

