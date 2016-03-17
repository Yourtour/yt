Ext.define('YourTour.view.widget.XCarousel', {
    extend: 'Ext.Carousel',
    xtype: 'xcarousel',
    config: {
        cls: 'x-xcarousel',
        timer: 0, //定时间隔，单位为毫秒
        task:null,
        status: 'activate', //定时状态， normal/cancel,
        animation: {
            duration: 500,
            easing: {
                type: 'ease-out'
            }
        },
    },

    initialize: function () {
        this.callParent(arguments);

        this.on('activeitemchange', this.onActiveItemChange);
        this.reset();
    },

    updateTimer: function (timer) {
        this.timer = timer;
    },

    onActiveItemChange: function () {
        var me = this,
            task = me.task || me.getTask();

        if(task != null) {
            task.cancel();
            task = null;
        }

        var status = this.status || this.getStatus();
        if (status == 'deactivate') return;

        this.nextItem();
    },

    nextItem:function(){
        var me = this,
            timer = me.timer || me.getTimer(),
            activeIndex = me.getActiveIndex(),
            size = me.getItems().length;

        if(timer != 0) {
            me.task = Ext.create('Ext.util.DelayedTask', function () {
                if (activeIndex < size - 2) {
                    me.setActiveItem(activeIndex + 1);
                } else {
                    me.setActiveItem(0);
                }
            });
            me.task.delay(timer); //一秒后执行调用updateClock函数
        }
    },

    pause: function () {
       this.status = 'deactivate';
    },

    reset: function () {
        var me = this;

        me.status = 'activate';

        this.nextItem();
    }
})