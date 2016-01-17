Ext.define('YourTour.view.widget.XMultiField', {
    extend: 'YourTour.view.widget.XField',
    xtype: 'xmultifield',
	config:{
		text:null,
		size:0,
		expanded:false,
		ellipsis:{
			size:0,
			expandable:false
		}
	},

	initialize:function(){
		this.callParent(arguments);

		var value = this.down('#value');
		value.addCls('multilineinfo');
	},

	updateText:function(text){
		this.setText(text);
	},

	setText:function(text){
		var me = this;
		me.text = text;
		var size = me.getSize();

		var valueEl = me.down('#value');
		if(text == null || text == '') {
			valueEl.setHtml(me.ifNull);
		}else if(size == 0) {
			valueEl.setHtml(text);
		}else{
			valueEl.setHtml(Ext.String.ellipsis(text, size, false));
		}

		if(text =='' || text == null || text == undefined){
			me.addCls('row');
		}
	},

	getText:function(){
		return this.text;
	},

	updateEllipsis:function(ellipsis){
		if(! ellipsis) return;

		var me = this;
		var meEllipsis = me.getEllipsis();

		Ext.apply(meEllipsis,ellipsis);
		if(meEllipsis.expandable > 0){
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
						value.setHtml(Ext.String.ellipsis(me.text, me.getSize(), false));
						me.expanded = false;

						me.removeCls('icon-arrow-up');
						me.addCls('icon-arrow-down');
					}
				}
			});
		}
	},

	getSize:function(){
		var ellipsis = this.getEllipsis();

		return ellipsis.size;
	}
});