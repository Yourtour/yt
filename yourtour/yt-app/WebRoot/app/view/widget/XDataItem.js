Ext.define('YourTour.view.widget.XDataItem', {
    extend: 'Ext.dataview.component.DataItem',
    config: {
        cls:'x-xdataitem'
    },

    initialize:function(){
        this.callParent(arguments);

        /*this.element.on(
            {
                scope : this,
                longpress:function(){

                }
            }
        )
        if(this.longTap == true){

        }*/
    },

    updateRecord:function(record){
        var me = this;

        if(record) {
            YourTour.util.Context.fillViewFields(me, record);
        }
    }
});

