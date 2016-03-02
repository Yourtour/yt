Ext.define('YourTour.view.widget.XHeaderBar', {
    extend: 'Ext.Container',
    xtype: 'xheaderbar',
    requires:['YourTour.view.widget.XBack', 'YourTour.view.widget.XPlainButton'],
    config: {
		itemId:'headerbar',
    	docked: 'top',
		layout:'hbox',
		padding:'0 0 0 0',
		baseCls:'x-xheaderbar',
		title:null,
		backButton:true,
		backAction:function(headerbar){
			Ext.ComponentManager.get('MainView').pop();
		},
		items:[]
    },

	beforeInitialize: function () {
		this.applyItems = this.applyInitialItems;
	},

	initialize: function () {
		delete this.applyItems;

		this.add(this.initialItems);
		delete this.initialItems;

		var me = this;
		var back = me.backButton;
		if(back  == false){
			me.middlePanel.addCls('x-xmiddle x-xtitle');

			me.leftPanel.setFlex(1);
			me.rightPanel.setFlex(1);
		}else{
			me.leftPanel.addCls('x-xleft icon-back x-xtitle');
			me.leftPanel.element.on({
				scope : me,
				tap : function(e, t) {
					var backAction = me.getBackAction();
					backAction(me);
				}
			});

			me.middlePanel.setFlex(1);
		}

		me.doSetTitle();
	},

	applyInitialItems: function (items) {
		var me = this;
		me.initialItems = items;

		me.leftPanel = me.add({xtype:'panel', itemId:'left'});
		me.middlePanel = me.add({xtype:'panel', itemId:'middle', layout:'hbox'});
		me.rightPanel = me.add({xtype:'panel', itemId:'right', layout:'hbox', items:[{xtype:'spacer', flex:1}]});

		me.doAdd = me._doAdd;
		me.remove = me.doItemRemove;
		me.doInsert = me.doItemInsert;
	},

	_doAdd: function (item) {
		var me = this;
		var align = item.align;
		if(!align) align='right';

		if(align == 'middle'){
			item.setMargin('7 0 7 0');
			me.middlePanel.add(item);
		}else if(align == 'right'){
			item.setMargin('7 0 7 0');
			me.rightPanel.add(item);
		}
	},

	doItemRemove: function (item, destroy) {
	},

	doItemInsert: function (index, item) {
	},
	updateBackButton:function(backButton){
		this.backButton = backButton;
	},

	updateTitle:function(title){
		this.setTitle(title);
	},

	setTitle:function(title){
		var me = this;
		me.title = title;

		me.doSetTitle();
	},

	doSetTitle:function(){
		var me = this, title = me.title, back = me.backButton, label;

		if(title == ''){
			me.middlePanel.hide();
			me.rightPanel.getAt(0).hide();
			me.rightPanel.setFlex(1);
		}else {
			if (back == false) {
				label = me.down('#middle');
			} else {
				label = me.down('#left');
			}

			label.setHtml(title);
		}
	},

	updateBackAction:function(action){
		this.setBackAction(action);
	}
});
