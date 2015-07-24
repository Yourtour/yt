Ext.define('YourTour.view.widget.XToolbar', {
    extend: 'Ext.Toolbar',
    xtype: 'xtoolbar',
    requires:['Ext.Img','Ext.Label','Ext.Spacer'],
    config: {
    	title:null,
    	docked: 'top'
    },
    
    initialize : function(){
    	this.callParent(arguments);
    	
    	var me = this;
    	var close = me.down('#close');
    	close.element.on({
			scope : me,
			tap : function(e, t) {
				me.fireEvent('back', me, e, t);
			}
		});
    },
    
    add: function(newItems){
    	newItems.unshift(Ext.create('Ext.Spacer',{}));
		newItems.unshift(Ext.create('Ext.Label',{itemId:'title'}));
		newItems.unshift(Ext.create('Ext.Img',{itemId:'close',
            	mode:'tag',
            	margin:'0 0 0 5',
            	src:'resources/icons/icon_back.png',
            	align:'left'
			}));	
    	
    	this.callParent(newItems);
    },
    
    applyTitle:function(title){
    	var me = this;
    	
    	var titleEl = me.down('#title');
    	titleEl.setHtml(title);
    }
});

