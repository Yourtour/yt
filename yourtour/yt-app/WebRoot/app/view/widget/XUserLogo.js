Ext.define('YourTour.view.widget.XUserLogo', {
    extend: 'Ext.Label',
    xtype: 'xuserlogo',
    config:{
        baseCls:'x-xuser-logo',
        binding:'imageUrl'
    },

    updateBinding:function(binding){
        this.binding = binding;
    },

    updateRecord:function(record){
        var binding =  this.binding || this.getBinding();
        var names = binding.split('.');
        var len = names.length;

        var data = record;
        var store = null;
        for(var index = 0; index < len - 1; index++){
            eval('store = data.' + [names[index]] + '()');
            data = store.first();
        }
        var name = names[len - 1];

        var url = data.get(name).split(';')[0];

        var url = YourTour.util.Context.getImageResource(url);
        this.setSrc(url);
    },

    setSrc:function(url){
        var style = {};
        style['background-image'] = 'url(' + url + ')';
        style['background-repeat'] = 'no-repeat';
        style['background-position'] = 'center center';
        style['background-size'] = '100% 100%';
        this.setStyle(style);
    }
});

