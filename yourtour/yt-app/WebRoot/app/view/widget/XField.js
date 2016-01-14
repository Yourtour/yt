Ext.define('YourTour.view.widget.XField', {
    extend: 'Ext.Panel',
    xtype: 'xfield',
	config:{
		label:null,
		value:null,
		tappable:null,
		icon:null,
		fieldCls:null,
		labelCls:null,
		heightCls:'x-xfield-padding-normal',
		underline:true,
		layout:'hbox',
		style:'background-color:white;',
		items:[
			{
				xtype:'label',
				itemId:'value',
				flex:1,
				cls:'x-xfield font-grey x-xfield-padding-normal'
			}
		]
	},

	updateHeightCls:function(heightCls){
		this.heightCls = heightCls;

		var value = this.down('#value');
		value.removeCls('x-xfield-padding-normal');
		value.addCls(heightCls);
	},

	updateUnderline:function(underline){
		if(underline){
			this.addCls('underline');
		}else{
			this.removeCls('underline');
		}
	},

	updateIcon:function(icon){
		this.setIcon(icon);
	},

	setIcon:function(icon){
		this.addCls(icon);
		var value = this.down('#value');
		value.setMargin('0 5 0 30');
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
		this.insert(0, {
			xtype:'label',
			itemId:'label',
			margin:'0 10 0 0',
			padding:'10 0 10 0',
			cls:'x-xfield',
			html:label
		});
	},

	setValue:function(value){
		this.value = value;
	},

	getValue:function(){
		return this.value == null ? this.getText():this.value;
	},

	updateText:function(text){
		this.setText(text);
	},

	setText:function(text){
		var valueEl = this.down('#value');
		valueEl.setHtml(text);
	},

	getText:function(){
		return this.text;
	}
});

