Ext.define('YourTour.view.widget.XCarousel', {
    extend: 'Ext.Carousel',
    xtype: 'xcarousel',
    config: {
        cls: 'x-xcarousel',
        timer:0, //定时间隔，单位为毫秒
        status:'normal' //定时状态， normal/cancel
    },

    initialize:function(){
        this.callParent(arguments);

        var me = this, timer = this.timer || this.getTimer();
        if(timer != 0){
            var task = Ext.create('Ext.util.DelayedTask',function(){
                me.move();
            });
            task.delay(timer); //一秒后执行调用updateClock函数
        }
    },

    updateTimer:function(timer){
        this.timer = timer;
    },

    move:function(){
        var me = this,
            timer = this.timer || this.getTimer(),
            activeIndex = this.getActiveIndex(),
            size = this.items().length,
            status = this.status || this.getStatus();

        if(status == 'cancel') return;

        if(activeIndex < size - 1){
            this.setActiveItem(activeIndex + 1);
        }else{
            this.setActiveItems(0);
        }

        var task = Ext.create('Ext.util.DelayedTask',function(){
            me.move();
        });
        task.delay(timer); //一秒后执行调用updateClock函数
    },

    destroy: function() {
        var task = this.task || this.getTask();

        if(task != null){
            task.cancel();
            this.status = 'cancel';
        }

        this.callParent(arguments);
    }
})