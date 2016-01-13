Ext.define('YourTour.view.widget.XLabelField', {
    extend: 'Ext.Panel',
    xtype: 'xlabelfield',
    config:{
		label:null,
		value:null,
		tappable:null,
		icon:null,
		fieldCls:null,
		labelCls:null,

		layout:'hbox',
    	cls:'row underline font-medium font-grey',
		style:'background-color:white',
		items:[
			{
				xtype:'label',
				itemId:'label',
				margin:'0 10 0 0',
			},{
				xtype:'label',
				itemId:'value',
				flex:1,
				cls:'multilineinfo'
			}
		]
    },

	updateIcon:function(icon){
		this.setIcon(icon);
	},

	setIcon:function(icon){
		this.removeAt(0);

		this.addCls(icon);
		this.setFieldCls('field-icon-medium');
	},

	updateFieldCls:function(cls){
		this.setFieldCls(cls);
	},

	setFieldCls:function(cls){
		var value = this.down('#value');
		value.addCls(cls);
	},

	updateLabelCls:function(cls){
		this.setLabelCls(cls);
	},

	setLabelCls:function(cls){
		var label = this.down('#label');
		label.addCls(cls);
	},

    updateTappable:function(tappable){
		var me = this;
		me.addCls('nav-arrow');
		me.element.on({
			scope : me,
			tap : function(e, t) {
				me.fireEvent('tap', me, e, t);
			}
		});
    },

	updateLabel:function(label){
		this.setLabel(label);
	},

	setLabel:function(label){
		var labelEl = this.down('#label');
		labelEl.setHtml(label);
	},

	setValue:function(value){
		var valueEl = this.down('#value');
		valueEl.setHtml(value);
	},

	getValue:function(){
		var valueEl = this.down('#value');
		return valueEl.getHtml();
	},
});