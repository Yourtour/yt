Ext.define('YourTour.view.widget.XHeaderBar2', {
    extend: 'YourTour.view.widget.XHeaderBar',
    xtype: 'xheaderbar2',
    config: {
		itemId:'headerbar',
		top:0,
		width:'100%',
		baseCls:'x-xheaderbar',
		items:[]
    },

	initialize: function () {
		delete this.applyItems;

		this.add(this.initialItems);
		delete this.initialItems;

		var me = this;

		me.leftPanel.addCls('icon-user');
		me.leftPanel.element.on({
			scope : me,
			tap : function(e, t) {
			}
		});

		me.middlePanel.setFlex(1);
		me.middlePanel.addCls('text-align-center');

		me.doSetTitle();

		console.log(me.middlePanel.getItems().length);
		if(me.middlePanel.getItems().length == 0) {
			me.middlePanel.add({xtype: 'xbutton', hidden: true});
		}else if(me.middlePanel.getItems().length > 2){

		}
	},

	doSetTitle:function(){
		var me = this, title = me.title;

		if(! title) return;

		me.middlePanel.setHtml(title);
	},
});

