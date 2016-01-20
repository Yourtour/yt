Ext.define('YourTour.view.widget.XCalendarItem', {
    extend: 'Ext.Label',
    xtype: 'xcalendaritem',
    config: {
    	cls:'day',
        enabled:true,
        date:null,
        calendar:null,
        active:false
    },

    initialize: function () {
        var me = this;

        me.callParent(arguments);

        me.element.on({
            scope:me,
            'tap' : me.onItemTap
        })
    },

    onItemTap:function(){
        var me = this;

        if(me.enabled) {
            me.active = !me.active;
            if(me.active){
                me.addCls('active')
            }else{
                me.removeCls('active');
            }

            me.calendar.fireEvent('itemtap', me, me.date.value, me.active);
        }
    },

    updateCalendar:function(calendar){
        this.calendar = calendar;
    },

    updateEnabled:function(enabled){
        this.enabled = enabled
    },

    updateDate:function(date){
        this.date = date;

        var value = date.value;
        this.setHtml(value.split('/')[2]);

        var enabled = date.enabled;
        this.enabled = enabled;

        var active = date.active;
        if(active){
            this.addCls('active');
        }
    }
});

