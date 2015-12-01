Ext.define('YourTour.view.widget.XButton', {
    extend: 'Ext.Button',
    xtype: 'xbutton',
    config: {
    	baseCls:'button',
    	attr:null
    },
    
    updateAttr:function(attr){
    	this.attr = attr;
    }
});

