Ext.define('YourTour.view.widget.XKVField', {
    extend: 'Ext.Label',
    xtype: 'xkvfield',
    config:{
    	cls:'field nav_arrow',
		padding:'0 20 0 0',
		value:''
    },

    initialize : function(){
    	this.callParent(arguments);
    	
		var me = this;
		me.element.on({
			scope : me,
			tap : function(e, t) {
				me.fireEvent('tap', me, e, t);
			}
		});
    },
    
	setValue:function(value){
		this.value = value;
	},

	getValue:function(){
		return this.value == '' ? this.getText() : '' + this.value;
	},

	setText:function(text){
		this.setHtml(text);
	},

	getText:function(){
		return this.getHtml();
	},

	getPair:function(){
		var values = this.getValue().split(',');
		var texts =  this.getText().split(',');

		var pair = '';
		for(var index = 0; index < values.length; index++){
			if(pair != ''){
				pair = pair + '|';
			}

			pair = pair + values[index] + ',' + texts[index];
		}

		return pair;
	},

	setPair:function(pair){
		if(pair){
			var pairs = pair.split('|');
			var values = '', texts = '', pArray;
			pairs.forEach(function(p){
				if(values != ''){
					values = values + ',';
					texts = texts + ',';
				}

				pArray = p.split(',');
				values = values + pArray[0];
				texts = texts + pArray[1];
			});

			this.setValue(values);
			this.setText(texts);
		}
	}
});