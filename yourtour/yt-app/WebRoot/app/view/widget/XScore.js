Ext.define('YourTour.view.widget.XScore', {
    extend: 'Ext.Label',
    xtype: 'xscore',
    config: {
        binding:null,
        align:null,
    	cls:'x-xscore'
    },

    updateAlign:function(align){
        if(align == 'right'){
            this.setStyle('text-align:right;background-image: url(./resources/icons/icon_round_48.png);background-repeat: no-repeat;background-position: left center;');
        }else{
            this.setStyle('text-align:left;background-image: url(./resources/icons/icon_round_48.png);background-repeat: no-repeat;background-position: right center;');
        }
    },

    updateRecord:function(record){
        var binding = this.binding;
        var name = binding == null ? this.getItemId():binding;
        this.setHtml(record.get(name) +' 分');
    }
});

