Ext.define('YourTour.view.widget.XGridView', {
    extend: 'Ext.Panel',
    xtype:'xgridview',
    config:{
    	cols: 3,
    	models:null,
    	vSpace:0,
    	hSpace:0,
    	item:null,
    	layout:'vbox',
        items: [
			
        ]
    },
    
    applyHSpace:function(hSpace){
    	this.hSpace = hSpace;
    },
    
    applyVSpace:function(vSpace){
    	this.vSpace = vSpace;
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
    
    applyModels: function(models){
    	var me = this;
    	models.each(function(model, index){
    		if(index % me.getCols() == 0){
    			me.add(Ext.create("Ext.Panel",{layout:'hbox'}));
    		}
    		
    		var panel = me.getAt(me.getItems().length - 1);
    		if(index != 0 && me.hSpace != 0){
    			var margin = me.hSpace + ' 0 0 0';
    			panel.add(Ext.create(me.getItem(), {margin:margin,model:model, flex:1}));
    		}else{
    			panel.add(Ext.create(me.getItem(), {model:model, flex:1}));
    		}
    		
 	   	});
    	
    	var panel = me.getAt(me.getItems().length - 1);
    	for(var index = panel.getItems().length; index < me.getCols(); index++){
    		panel.add(Ext.create("Ext.Panel", {flex:1}));
    	}
    },
    
    onTap:function(index, model){
    	
    }
});

