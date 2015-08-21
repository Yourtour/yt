Ext.define('YourTour.view.widget.ToggleField', {
    extend: 'Ext.Panel',
    xtype: 'yttogglefield',
    requires:['Ext.Button'],
    
    config: {
    	value:'0',
    	baseCls:'togglefield',
    	layout:'hbox',
    	padding:'5 0 0 0',
    	defaults:{
    		width:'50px'
    	},
    	texts:null,
    	items:[
	    	{
	    		xtype:'button',
	    		text:'可选',
	    		ui: "normal",
	    		style:'font-size:14px'
	    	},
	    	{
	    		xtype:'button',
	    		text:'可选',
	    		ui: "normal",
	    		style:'font-size:14px'
	    	}
    	]
    },
    
    initialize:function(){
    	var me = this;
    	
    	var button0 = me.getAt(0), button1 = me.getAt(1);
    	
    	var texts = this.getTexts();
    	button0.setText(texts[0]);
    	button1.setText(texts[1]);
    	
    	button0.on("tap", function(){
    		me.value = '0';
    		
    		button0.addCls('active');
    		button1.removeCls('active');
    	});
    	
    	button1.on("tap", function(){
    		me.value = '0';
    		button1.addCls('active');
    		button0.removeCls('active');
    	});
    	
    	button0.fireEvent('tap');
    },
    
    setValue:function(value){
    	this.value = value;
    },
    
    getValue:function(){
    	return this.valueOf();
    }
});

