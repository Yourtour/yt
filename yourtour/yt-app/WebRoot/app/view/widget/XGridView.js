Ext.define('YourTour.view.widget.XGridView', {
    extend: 'Ext.Container',
    xtype:'xgridview',
    config:{
		cls:'x-xgridview',

		store: null,

    	cols: 3,

		models:null,
    	vLine:true,
    	hLine:true,
    	layout:'vbox',
        items: [
			
        ]
    },

	initialize: function() {
		this.callParent();
		var me = this;
	},

    
    applyHLine:function(hLine){
    	this.hLine = hLine;
    },
    
    applyVLine:function(vLine){
    	this.vLine = vLine;
    },
    
    setCols:function(cols){
    	this.cols = cols;
    },
    
    getCols:function(){
    	return this.cols;
    },
    
    applyItem:function(item){
    	this.item = item;
    },
    
    getItem:function(){
    	return this.item;
    },

	applyStore:function(store){
		this.store = store;
	},

    applyModels: function(models){
    	var me = this;
    	if(models){
	    	models.each(function(model, index){
	    		var panel;
	    		if(index % me.getCols() == 0){
	    			var config = {layout:'hbox'};
	    			
	    			if(me.hSpace != 0){
	    				config['margin'] = me.hSpace + ' 0 0 0';
	    			}
	    			
	    			if(me.hLine){
	    				config['cls'] = 'underline';
	    			}
	    		
	    			panel = Ext.create("Ext.Panel",config); 
	    			me.add(panel);
	    		}else{
	    			panel = me.getAt(me.getItems().length - 1);
	    		}
	    		
	    		var itemConfig = {model:model, flex:1, gridview:me};
	    		if(me.vLine){
	    			if(index % me.getCols() > 0){
	    				itemConfig['style'] = 'border-left:1px solid #EDEDED;';
	    			}
	    		}
	    		
	    		var item = Ext.create(me.getItem(), itemConfig);
	    		item.element.on({
					scope : item,
					tap : function(e, t) {
						me.fireEvent('onGridItemTap', index, model);
					}
				});
	   			panel.add(item);
	 	   	});
	    	
	    	var panel = me.getAt(me.getItems().length - 1);
	    	if(panel){
		    	for(var index = panel.getItems().length; index < me.getCols(); index++){
		    		panel.add(Ext.create("Ext.Panel", {flex:1}));
		    	}
	    	}
    	}
    },

	/**
	 * Refreshes the view by reloading the data from the store and re-rendering the template.
	 */
	refresh: function() {
		var me = this,
			container = me.container;

		if (!me.getStore()) {
			if (!me.hasLoadedStore && !me.getDeferEmptyText()) {
				me.showEmptyText();
			}
			return;
		}
		if (container) {
			me.fireAction('refresh', [me], 'doRefresh');
		}
	},

	doRefresh:function(){

	}
});

