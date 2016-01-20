Ext.define('YourTour.view.widget.XCalendarItem', {
    extend: 'Ext.Label',
    xtype: 'xcalendaritem',
    config: {
    	cls:'day',
        enabled:true,
        date:null
    },

    updateEnabled:function(enabled){
        this.enabled = enabled
    },

    updateDate:function(date){
        this.date = date;

        var value = date.value;
        this.setHtml(value.split('/')[2]);

        var enabled = date.enabled;
        if(! enabled){
            this.addCls('disabled')
        }
    }
});

