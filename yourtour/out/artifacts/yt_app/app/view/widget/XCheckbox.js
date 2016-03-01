Ext.define('YourTour.view.widget.XCheckbox', {
    extend: 'Ext.Panel',
    xtype: 'xcheckbox',
    config:{
    	value:null,
    	label:null,
    	layout:'hbox',
    	items:[
			{
				xtype : 'image',
				mode : 'tag',
				src:'resources/icons/icon_unchecked.png',
			  	itemId:'unchecked'
			},
			
			{
				xtype : 'image',
				mode : 'tag',
				src:'resources/icons/icon_checked.png',
				hidden:true,
				itemId:'checked'
			},
			
			{
			    xtype: 'xlabel',
			    itemId : 'label',
			    margin:'0 0 0 10'
			}
    	]
    },
    
    initialize : function(){
    	this.callParent(arguments);
    	
    	var unchecked = this.down('#unchecked');
    	var checked = this.down('#checked');

    	unchecked.addListener('tap', function(){
    		checked.show();
    		
    		unchecked.hide();
    	});
    	
    	checked.addListener('tap', function(){
    		unchecked.show();
    		
    		checked.hide();
    	});
    },
    
    updateLabel:function(label){
    	var me = this;
    	var labelEl = me.down('#label');
    	labelEl.setHtml(label);
    },
    
    setValue:function(value){
    	this.value = value;
    },
    
    getValue:function(){
    	var me = this;
    	
    	var checked = me.down('#checked');
    	return checked.isHidden()?null:this.value;
    }
});

