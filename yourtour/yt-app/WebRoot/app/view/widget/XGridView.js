Ext.define('YourTour.view.widget.XGridView', {
    extend: 'Ext.Panel',
    xtype:'xgridview',
    config:{
    	cols: 3,
    	models:null,
    	vSpace:0,
    	vLine:true,
    	hSpace:0,
    	hLine:true,
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
    
    applyModels: function(models){
    	var me = this;
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
    		
    		var itemConfig = {model:model, flex:1};
    		if(me.vLine){
    			if(index % me.getCols() > 0){
    				itemConfig['style'] = 'border-left:1px solid #EDEDED;';
    			}
    		}
    		
   			panel.add(Ext.create(me.getItem(), itemConfig));
 	   	});
    	
    	var panel = me.getAt(me.getItems().length - 1);
    	for(var index = panel.getItems().length; index < me.getCols(); index++){
    		panel.add(Ext.create("Ext.Panel", {flex:1}));
    	}
    },
    
    onTap:function(index, model){
    	
    }
});

