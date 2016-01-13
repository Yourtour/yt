Ext.define('YourTour.view.widget.XMultiField', {
    extend: 'YourTour.view.widget.XField',
    xtype: 'xmultifield',
	config:{
		text:null,
		showMore:false,
		size:0
	},

	initialize:function(){
		this.callParent(arguments);

		var value = this.down('#value');
		value.addCls('multilineinfo');
	},

	updateSize:function(size){
		this.size = size;
	},

	updateText:function(text){
		this.setText(text);
	},

	setText:function(text){
		var valueEl = this.down('#value');
		if(this.size == 0) {
			valueEl.setHtml(text);
		}else{
			valueEl.setHtml(Ext.String.ellipsis(text, this.size, false));
		}

		if(text =='' || text == null || text == undefined){
			this.addCls('row');
		}
	},

	getText:function(){
		return this.text;
	},

	updateShowMore:function(showMore){
		if(showMore){
			var value = this.down('#value');
			value.addCls('icon-arrow-down');
		}
	}
});