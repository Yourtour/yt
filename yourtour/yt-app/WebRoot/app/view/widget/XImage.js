Ext.define('YourTour.view.widget.XImage', {
    extend: 'Ext.Img',
    xtype: 'ximage',
    config:{
        binding:'imageUrl',
        mode : 'tag'
    },

    updateBinding:function(binding){
        this.binding = binding;
    },

    updateRecord:function(record){
        var binding =  this.getBinding();
        var names = binding.split('.');
        var len = names.length;

        var data = record;
        var store = null;
        for(var index = 0; index < len - 1; index++){
            eval('store = data.' + [names[index]] + '()');
            data = store.first();
        }
        var name = names[len - 1];

        var url = data.get(name);
        var cls = this.getImageCls();
        this.setHtml("<img src='" + YourTour.util.Context.getImageResource(url) + "' class='" + cls +"'/>");
    }
});

