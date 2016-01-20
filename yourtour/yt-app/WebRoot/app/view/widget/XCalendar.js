Ext.define('YourTour.view.widget.XCalendar', {
    extend: 'Ext.Container',

    alternateClassName: 'YourTour.XCalendar',
    requires:['YourTour.view.widget.XCalendarItem'],
    xtype: 'xcalendar',
    config: {
        baseCls: 'x-xcalendar',
        layout: 'vbox',
        year:null,
        month:null,
        selectedDate:[],
        allDays:[],
        items: [
            {
                xtype:'label',
                itemId:'date',
                cls:'row font-medium header '
            },
            {
                xtype:'panel',
                layout:'hbox',
                defaults:{
                    flex:1
                },
                cls:'underline week',
                items:[
                    {
                        xtype:'label',
                        html:'日'
                    },

                    {
                        xtype:'label',
                        html:'一'
                    },

                    {
                        xtype:'label',
                        html:'二'
                    },

                    {
                        xtype:'label',
                        html:'三'
                    },

                    {
                        xtype:'label',
                        html:'四'
                    },
                    {
                        xtype:'label',
                        html:'五'
                    },
                    {
                        xtype:'label',
                        html:'六'
                    }

                ]
            },

            {
                xtype:'panel',
                layout:'hbox',
                itemId:'week1',
                defaults:{
                    flex:1
                },
                cls:'underline day',
                items:[
                    {
                        xtype:'xcalendaritem'
                    },

                    {
                        xtype:'xcalendaritem'
                    },

                    {
                        xtype:'xcalendaritem',
                        itemId:'item3'
                    },

                    {
                        xtype:'xcalendaritem'
                    },

                    {
                        xtype:'xcalendaritem'
                    },
                    {
                        xtype:'xcalendaritem'
                    },
                    {
                        xtype:'xcalendaritem'
                    }
                ]
            },
            {
                xtype:'panel',
                layout:'hbox',
                itemId:'week2',
                defaults:{
                    flex:1
                },
                cls:'underline day',
                items:[
                    {
                        xtype:'xcalendaritem'
                    },

                    {
                        xtype:'xcalendaritem'
                    },

                    {
                        xtype:'xcalendaritem'
                    },

                    {
                        xtype:'xcalendaritem'
                    },

                    {
                        xtype:'xcalendaritem'
                    },
                    {
                        xtype:'xcalendaritem'
                    },
                    {
                        xtype:'xcalendaritem'
                    }
                ]
            },
            {
                xtype:'panel',
                layout:'hbox',
                itemId:'week3',
                defaults:{
                    flex:1
                },
                cls:'underline day',
                items:[
                    {
                        xtype:'xcalendaritem'
                    },

                    {
                        xtype:'xcalendaritem'
                    },

                    {
                        xtype:'xcalendaritem'
                    },

                    {
                        xtype:'xcalendaritem'
                    },

                    {
                        xtype:'xcalendaritem'
                    },
                    {
                        xtype:'xcalendaritem'
                    },
                    {
                        xtype:'xcalendaritem'
                    }
                ]
            },

            {
                xtype:'panel',
                layout:'hbox',
                itemId:'week4',
                defaults:{
                    flex:1
                },
                cls:'underline day',
                items:[
                    {
                        xtype:'xcalendaritem'
                    },

                    {
                        xtype:'xcalendaritem'
                    },

                    {
                        xtype:'xcalendaritem'
                    },

                    {
                        xtype:'xcalendaritem'
                    },

                    {
                        xtype:'xcalendaritem'
                    },
                    {
                        xtype:'xcalendaritem'
                    },
                    {
                        xtype:'xcalendaritem'
                    }
                ]
            },

            {
                xtype:'panel',
                layout:'hbox',
                itemId:'week5',
                defaults:{
                    flex:1
                },
                cls:'underline day',
                items:[
                    {
                        xtype:'xcalendaritem'
                    },

                    {
                        xtype:'xcalendaritem'
                    },

                    {
                        xtype:'xcalendaritem'
                    },

                    {
                        xtype:'xcalendaritem'
                    },

                    {
                        xtype:'xcalendaritem'
                    },
                    {
                        xtype:'xcalendaritem'
                    },
                    {
                        xtype:'xcalendaritem'
                    }
                ]
            },
            {
                xtype:'panel',
                layout:'hbox',
                itemId:'week6',
                defaults:{
                    flex:1
                },
                cls:'underline day',
                items:[
                    {
                        xtype:'xcalendaritem'
                    },

                    {
                        xtype:'xcalendaritem'
                    },

                    {
                        xtype:'xcalendaritem'
                    },

                    {
                        xtype:'xcalendaritem'
                    },

                    {
                        xtype:'xcalendaritem'
                    },
                    {
                        xtype:'xcalendaritem'
                    },
                    {
                        xtype:'xcalendaritem'
                    }
                ]
            }
        ]
    },

    initialize: function () {
        var me = this;

        me.callParent(arguments);

        me.on(
            {
                scope:me,
                itemtap:'onItemtap'
            }
        )
    },

    onItemtap:function(item, date, active){

    },

    getFirstDay:function(year, month){
        return year + '/' + month + '/01';
    },

    getLastDay:function(year, month){
        var lastDay = new Date(year,month,0).getDate();
        return year + '/' + month + '/' + lastDay;
    },

    getDayNum:function(year, month){
        var dayNum = new Date(year,month,0).getDate();

        return dayNum;
    },

    getWeekForFirstDay:function(year, month){
        return new Date(this.getFirstDay(year, month)).getDay()
    },

    getWeekForLastDay:function(year, month){
        return new Date(this.getLastDay(year, month)).getDay()
    },

    getDate:function(year, month, day){
        return year + '/' + month + '/' + day;
    },

    doDraw:function(){
        var me = this;

        this.getPreviousMonth();

        this.getCurrentMonth();

        this.getNextMonth();

        var weekItem, weekIndex, dayIndex, dayItem;
        var allDays = me.allDays || me.getAllDays()

        for(var index = 0; index < allDays.length; index++){
            weekIndex = index / 7 + 1;
            dayIndex = index % 7;

            if(dayIndex == 0){
                week = me.down('#week' + weekIndex);
            }

            dayItem = week.getAt(dayIndex);
            dayItem.setDate(allDays[index]);
            dayItem.updateCalendar(me);
        }
    },

    getPreviousMonth:function(){
        var currentMonth = this.getMonth();
        var currentYear = this.getYear();

        var month = currentMonth != 1 ? currentMonth - 1 : 12;
        var year = month == 12 ? currentYear - 1 : currentYear;

        var week = this.getWeekForLastDay(year, month);
        var dayNum = this.getDayNum(year, month)
        var allDays = this.allDays || this.getAllDays();
        for(var index = week; index >= 0; index--){
            allDays.push({value:this.getDate(year, month, dayNum - index), enabled:false});
        }
    },

    getCurrentMonth:function(){
        var month = this.getMonth();
        var year = this.getYear();

        var allDays = this.allDays || this.getAllDays();
        var dayNum = this.getDayNum(year, month);
        for(var index = 1; index <= dayNum; index++){
            allDays.push({value:this.getDate(year, month, index), enabled:true});
        }
    },


    getNextMonth:function(){
        var currentMonth = this.getMonth();
        var currentYear = this.getYear();

        var month = currentMonth != 12 ? currentMonth + 1 : 1;
        var year = month == 1 ? currentYear : currentYear + 1;

        var allDays = this.allDays || this.getAllDays();
        var len = 42 - allDays.length;
        for(var index = 1; index <= len; index++){
            allDays.push({value:this.getDate(year, month, index), enabled:false});
        }
    },

    setDate:function(year, month){
        this.year = year;
        this.month = month;

        var date = this.down('#date');
        date.setHtml(year + '-' + month);
        this.doDraw();
    },

    getYear:function(){
        return this.year;
    },

    getMonth:function(){
        return this.month;
    }
});