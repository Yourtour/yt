Ext.define('YourTour.controller.CommonMainCtrl', {
    extend: 'Ext.app.Controller',
    config: {
        refs:{
            contentReadView:'#ContentReadView',

            commentListView:'#CommentListView',
            commentList:'#CommentListView #commentList'
        },
       
        control:{
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

    showCommentListView:function(id, type){
        var me = this;

        var params = [{name:'id', value:id},{name:'type', value:type},{name:'nextCursor', value:1000}];

        var options = {
            model:'YourTour.model.CommentModel',
            url:'/comments',
            params:params,
            success:function(store){
                Ext.ComponentManager.get('MainView').push(Ext.create('YourTour.view.common.CommentListView'));
                var view = me.getCommentListView();

                me.getCommentList().setStore(store);
            }
        };
        me.getApplication().query(options);
    }
});
