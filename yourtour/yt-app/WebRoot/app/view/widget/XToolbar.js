Ext.define('YourTour.view.widget.XToolbar', {
    extend: 'Ext.Toolbar',
    xtype: 'xtoolbar',
    requires:['Ext.Img','Ext.Label','Ext.Spacer','YourTour.view.widget.ToolButton'],
    config: {
    	title:null,
    	docked: 'top',
    	items:[]
    },
    
    constructor: function(config) {
    	var items = config.items;
    	if(items == null){
    		items = [];
    		config.items = items;
    	}
    	
    	items.unshift({xtype:'spacer',flex:1});
		items.unshift({xtype:'label', itemId:'title',html:''});
		items.unshift({xtype:'image', itemId:'close',
            	mode:'tag',
            	margin:'0 0 0 5',
            	src:'resources/icons/icon_back.png'
		});	
    	
    	this.callParent(arguments);
    },
    
    initialize : function(){
    	this.callParent(arguments);
    	
    	var me = this;
    	var close = me.down('#close');
    	if(close != null){
	    	close.element.on({
				scope : me,
				tap : function(e, t) {
					me.fireEvent('tap', me, e, t);
				}
			});
    	}
    },
    
    applyTitle:function(title){
    	var me = this;
    	
    	var titleEl = me.down('#title');
    	if(titleEl != null){
    		titleEl.setHtml(title);
    	}
    }
});

