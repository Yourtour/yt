Ext.define('YourTour.controller.CommonMainCtrl', {
    extend: 'Ext.app.Controller',
    config: {
        refs: {
            fieldEditView: '#FieldEditView',

            commentMainView: '#CommentMainView',
            commentList: '#CommentMainView #commentList',

            timeSelectionView: '#TimeSelectionView',

            consultMainView: '#ConsultMainView'
        },

        control: {
            '#FieldEditView #btnOk':{
                tap:'saveFieldValue'
            },

            '#CommentMainView #commentNum': {
                tap: 'onCommentFilterTap'
            },

            '#CommentMainView #goodNum': {
                tap: 'onCommentFilterTap'
            },

            '#CommentMainView #mediumNum': {
                tap: 'onCommentFilterTap'
            },

            '#CommentMainView #badNum': {
                tap: 'onCommentFilterTap'
            },

            '#CommentMainView #imageNum': {
                tap: 'onCommentFilterTap'
            },

            '#TimeSelectionView #btnNext': {
                tap: 'onTimeSelectionNextTapHandler'
            },

            '#TimeSelectionView #calendar': {
                itemtap: 'onTimeSelectionActiveItemChangeHandler'
            }
        }
    },

    showConsultMainView:function(){
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.common.ConsultMainView'));
    },

    showContentReadView: function (title, content) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.common.ContentReadView'));

        var view = this.getContentReadView();

        var headerbar = view.down('#headerbar');
        headerbar.setHtml(title);

        var contentEl = view.down('#content');
        contentEl.setHtml(content);
    },

    showCommentMainView: function (id, type, handler) {
        var me = this;
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.common.CommentMainView'));

        var params = [{name: 'id', value: id}, {name: 'type', value: type}, {
            name: 'nextCursor',
            value: 0
        }, {name: 'filter', value: 'commentNum'}];

        me.getCommentStore(id, params, handler);
    },

    getCommentStore: function (id, params, handler) {
        var me = this;
        var options = {
            model: 'YourTour.model.CommentModel',
            url: '/comments/' + id,
            params: params,
            success: function (store) {
                var view = me.getCommentMainView();

                if (handler) {
                    handler(view);
                }

                me.getCommentList().setStore(store);

                view.bindData(params);
            }
        };
        me.getApplication().query(options);
    },

    onCommentFilterTap: function (field) {
        var itemId = field.getItemId();

        var view = this.getCommentMainView();
        var filterPanel = view.down('#filterPanel');
        var items = filterPanel.getItems();
        items.each(function (item) {
            if (item instanceof YourTour.view.widget.XField) {
                var value = item.down('#value');
                if (item.getItemId() == itemId) {
                    value.addCls('active');
                } else {
                    value.removeCls('active');
                }
            }
        })

        var params = view.getData();
        params[2].value = 0;
        params[3].value = itemId;
        this.getCommentStore(params);
    },

    /*************************************************************************************************
     * 日历选择部分
     ************************************************************************************************/
    showTimeSelectionView: function (options, callback) {
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.common.TimeSelectionView', {callback: callback}));
        var view = this.getTimeSelectionView();

        var defaults = {
            date:new Date(),
            single:true,
            title:'行程日期安排'
        };
        Ext.applyIf(options, defaults);
        view.bindData(options);

        this.initializeTimeSelectionView(view);
    },

    initializeTimeSelectionView:function(view){
        var options = view.getData();
        var date = options.date, year = date.getFullYear(), month = date.getMonth() + 1, day = date.getDate();
        var calendar = view.down('#calendar');

        calendar.setDate(year, month,day);

        var headerbar = view.down('#headerbar');
        headerbar.setTitle(options.title);

        if(options.single){
            view.down('#infoPanel').hide();
        }
    },

    onTimeSelectionNextTapHandler: function () {
        var me = this, view = me.getTimeSelectionView(), startDate = view.down('#startDate'), endDate = view.down('#endDate'), duration = view.down('#duration');

        var callback = view.getCallback();
        if (callback) {
            callback(startDate.getValue(), endDate.getValue(), duration.getValue());
        }
    },

    onTimeSelectionActiveItemChangeHandler: function (calendar, panel, item, date, active) {
        var me = this, view = me.getTimeSelectionView(), options = view.getData();
        if(options.single){
            return this.handleTimeSingleSelection(calendar, panel, item, date, active);
        }else{
            return this.handleTimeRangeSelection(calendar, panel, item, date, active);
        }
    },

    /**
     * 处理单个时间选择
     * @param calendar
     * @param panel
     * @param item
     * @param date
     * @param active
     * @returns {boolean}
     */
    handleTimeSingleSelection:function(calendar, panel, item, date, active){
        if(! item){
            return true;
        }

        if (active) {
            calendar.reset();
        }

        var me = this, view = me.getTimeSelectionView(), startDate = view.down('#startDate');
        startDate.setText(date);
        return true;
    },

    /**
     * 处理范围选择
     * @param calendar
     * @param panel
     * @param item
     * @param date
     * @param active
     * @returns {boolean}
     */
    handleTimeRangeSelection:function(calendar, panel, item, date, active){
        if(! item){
            return;
        }

        var me = this;
        if (active && calendar.getActiveItems().length >= 2) {
            me.getApplication().alert('只能选择一个时间范围，请先取消已选择的。');
            return false;
        }

        var view = me.getTimeSelectionView(), dDate = Ext.Date.format(new Date(date), 'Y-m-d');
        var startDate = view.down('#startDate'), endDate = view.down('#endDate'), dStart = startDate.getValue(), dEnd = endDate.getValue();
        var duration = view.down('#duration');

        var dDuration = 0;
        if (active == false) {
            var value = startDate.getValue();
            if (value == date) {
                startDate.setText(endDate.getText());
            }

            endDate.setText('');
        } else {
            if (dStart == null) {
                startDate.setText(date);
            } else {
                dStart = Ext.Date.format(new Date(dStart), 'Y-m-d');
                if (dDate > dStart) {
                    endDate.setText(date);
                } else {
                    endDate = startDate.getValue();
                    startDate.setText(date);
                }

                dStart = startDate.getValue() == '' ? null : new Date(startDate.getValue());
                dEnd = endDate.getValue() == '' ? null : new Date(endDate.getValue());
                dDuration = parseInt((new Date(dEnd) - new Date(dStart)) / (1000 * 60 * 60 * 24)) + 1;
            }
        }

        duration.setText(dDuration);

        return true;
    },

    editField:function(field){
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.common.FieldEditView'));

        var me = this, view = me.getFieldEditView(), headerbar = view.down('#headerbar'), content = view.down('#content');
        view.bindData(field);

        headerbar.setTitle(field.getLabelText());

        var text = field.getText();
        if(text == null){
            content.setPlaceHolder(field.getPlaceHolder())
        }else {
            content.setValue(field.getText());
        }
    },

    saveFieldValue:function(){
        var me = this, view = me.getFieldEditView(), field = view.getData(), content = view.down('#content');
        field.modifyText(content.getValue());

        Ext.ComponentManager.get('MainView').pop();
    }
});
