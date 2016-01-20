Ext.define('YourTour.controller.CommonMainCtrl', {
    extend: 'Ext.app.Controller',
    config: {
        refs:{
            contentReadView:'#ContentReadView',

            commentListView:'#CommentListView',
            commentList:'#CommentListView #commentList',

            timeSelectionView:'#TimeSelectionView'
        },
       
        control:{
            '#CommentListView #commentNum':{
                tap:'onCommentFilterTap'
            },

            '#CommentListView #goodNum':{
                tap:'onCommentFilterTap'
            },

            '#CommentListView #mediumNum':{
                tap:'onCommentFilterTap'
            },

            '#CommentListView #badNum':{
                tap:'onCommentFilterTap'
            },

            '#CommentListView #imageNum':{
                tap:'onCommentFilterTap'
            },

            '#TimeSelectionView #btnNext':{
                tap:'onTimeSelectionNextTapHandler'
            }
        }
    },
    
    showContentReadView:function(title, content){
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.common.ContentReadView'));

        var view = this.getContentReadView();

        var headerbar = view.down('#headerbar');
        headerbar.setHtml(title);

        var contentEl = view.down('#content');
        contentEl.setHtml(content);
    },

    showCommentListView:function(id, type, handler){
        var me = this;
        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.common.CommentListView'));

        var params = [{name:'id', value:id},{name:'type', value:type},{name:'nextCursor', value:0},{name:'filter',value:'commentNum'}];
        me.getCommentStore(params, handler);
    },

    onCommentFilterTap:function(field){
        var itemId = field.getItemId();

        var view = this.getCommentListView();
        var filterPanel = view.down('#filterPanel');
        var items = filterPanel.getItems();
        items.each(function(item){
            if(item instanceof YourTour.view.widget.XField) {
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
        params[3].value=itemId;
        this.getCommentStore(params);
    },

    getCommentStore:function(params, handler){
        var me = this;
        var options = {
            model:'YourTour.model.CommentModel',
            url:'/comments',
            params:params,
            success:function(store){
                var view = me.getCommentListView();

                if(handler) {
                    handler(view);
                }

                me.getCommentList().setStore(store);

                view.bindData(params);
            }
        };
        me.getApplication().query(options);
    },

    /*************************************************************************************************
     * 日历选择部分
     ************************************************************************************************/
    showTimeSelectionView:function(date, callback){
        var year, month;

        if(! date){
            date = new Date();
        }

        year = date.getFullYear();
        month = date.getMonth() + 1;

        Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.common.TimeSelectionView',{callback:callback}));
        var view = this.getTimeSelectionView();

        var calendar = view.down('#calendar');
        calendar.setDate(year, month);
    },

    onTimeSelectionNextTapHandler:function(){
        var me = this;
        var view = this.getTimeSelectionView();

        var calendar = view.down('#calendar');

        var callback = view.getCallback();
        if(callback) {
            callback();
        }
    }
});
