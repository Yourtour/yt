Ext.define('YourTour.view.widget.XMultiField', {
    extend: 'YourTour.view.widget.XField',
    xtype: 'xmultifield',
	config:{
		text:null,
		cls:'x-xfield-multi',

		expanded:false,
		ellipsis:{
			size:0,
			expandable:false
		}
	},

	initialize:function(){
		var me = this;
		me.callParent(arguments);

		var ellipsis = me.ellipsis || me.getEllipsis();
		if(ellipsis.expandable > 0){
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

		this.setText();
	},

	updateText:function(text){
		this.text = text;
	},

	setText:function(text){
		var me = this;

		if(text) {
			me.text = text;
		}else{
			text = me.text;
		}

		var size = me.getSize();

		var valueEl = me.down('#value');
		if(text == null || text == '') {
			valueEl.setHtml(me.ifNull);
		}else if(size == 0) {
			valueEl.setHtml(text);
		}else{
			valueEl.setHtml(Ext.String.ellipsis(text, size, false));
		}
	},

	getText:function(){
		return this.text;
	},

	updateEllipsis:function(ellipsis){
		if(! ellipsis) return;

		var me = this, meEllipsis = me.ellipsis || me.getEllipsis();
		Ext.apply(meEllipsis,ellipsis);
	},

	getSize:function(){
		var ellipsis = this.ellipsis || this.getEllipsis();

		return ellipsis.size;
	}
});