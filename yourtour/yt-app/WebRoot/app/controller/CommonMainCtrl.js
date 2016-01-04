Ext.define('YourTour.controller.CommonMainCtrl', {
    extend: 'Ext.app.Controller',
    config: {
        refs:{
            contentReadView:'#ContentReadView'

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
    }
});
