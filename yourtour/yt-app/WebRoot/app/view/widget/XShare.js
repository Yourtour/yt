Ext.define('YourTour.view.widget.XShare', {
    extend: 'Ext.ActionSheet',
    config:{
        layout:'vbox',
        padding:0,
        items:[
            {
                xtype:'xlabel',
                itemId:'cancel',
                html:'取消',
                style:'background-color:red',
                cls:'text-align-center'
            }
        ]
    },
    
    initialize : function(){
    	this.callParent(arguments);

        var cancel = this.down('#cancel');
        cancel.on('tap', this.onCancelButton, this);
    },

    onCancelButton:function(){
        this.fireEvent('cancel', this);
        this.hide();
        Ext.util.InputBlocker.unblockInputs();
    },

    show:function(){
        if (this.getParent() === undefined) {
            Ext.Viewport.add(this);
        }
        this.callParent(arguments);
        Ext.util.InputBlocker.blockInputs();
    }
});

