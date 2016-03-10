Ext.define('YourTour.view.widget.XCarousel', {
    extend: 'Ext.Carousel',
    xtype: 'xcarousel',
    config: {
        cls: 'x-xcarousel',
        timer:0,
        task:null
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
        var me = this, timer = this.timer || this.getTimer();


        var task = Ext.create('Ext.util.DelayedTask',function(){
            me.move();
        });
        task.delay(timer); //一秒后执行调用updateClock函数
    },

    destroy: function() {
        var task = this.task || this.getTask();

        if(task != null){
            task.cancel();
        }

        this.callParent(arguments);
    }
})