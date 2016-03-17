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

		me.leftPanel.setWidth(45);
		me.leftPanel.addCls('x-xleft icon-user x-xtitle');
		me.leftPanel.element.on({
			scope : me,
			tap : function(e, t) {
			}
		});

		me.middlePanel.setFlex(1);

		me.doSetTitle();
	}
});

