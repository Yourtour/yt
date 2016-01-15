Ext.define('YourTour.view.widget.XMultiField', {
    extend: 'YourTour.view.widget.XField',
    xtype: 'xmultifield',
	config:{
		text:null,
		size:0,
		expanded:false,
	},

	initialize:function(){
		this.callParent(arguments);

		var value = this.down('#value');
		value.addCls('multilineinfo');
	},

	updateSize:function(size){
		var me = this;

		me.size = size;
		if(size > 0){
			var value = this.down('#value');
			me.addCls('icon-arrow-down');

			me.element.on({
				scope : me,
				tap : function(e, t) {
					if(! me.expanded){
						value.setHtml(me.text);
						me.expanded = true;
						me.addCls('icon-arrow-up');
						me.removeCls('icon-arrow-down');
					}else{
						value.setHtml(Ext.String.ellipsis(me.text, me.size, false));
						me.expanded = false;

						me.removeCls('icon-arrow-up');
						me.addCls('icon-arrow-down');
					}
				}
			});
		}
	},

	updateText:function(text){
		this.setText(text);
	},

	setText:function(text){
		this.text = text;

		var valueEl = this.down('#value');
		if(text == null || text == '') {
			valueEl.setHtml(this.ifNull);
		}else if(this.size == 0) {
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
		this.showMore = showMore;

	},

	output:function(text){
		if(this.size >0){
			this.setText(Ext.String.ellipsis(record.get('memo'),this.size,false))
		}
	}
});