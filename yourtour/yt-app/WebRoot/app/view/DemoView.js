Ext.define('YourTour.view.DemoView', {
    extend: 'Ext.Container',
    config: {
    	layout:'vbox',
    	id:'DemoView',
		activeIndex:0,
		spacing:5,
		translateGroup:null,
        items: [
			{
				xtype:'container',
				itemId:'container',
				layout:'hbox',
				height:200,
				width:'100%',
				defaults:{
					margin:'0 5 0 5'
				},
				items:[
					{
						xtype:'image',
						itemId:'1',
						src:'resources/images/guangdong.jpg'
					},

					{
						xtype:'image',
						itemId:'2',
						src:'resources/images/beijing.jpg'
					},

					{
						xtype:'image',
						itemId:'3',
						src:'resources/images/shanghai.jpg'
					},

					{
						xtype:'image',
						itemId:'4',
						src:'resources/images/guangdong.jpg'
					}/*,

					{
						xtype:'image',
						itemId:'5',
						src:'resources/images/shanghai.jpg'
					},

					{
						xtype:'image',
						itemId:'6',
						src:'resources/images/guangdong.jpg'
					}*/
				]
			},

			{
				xtype:'button',
				itemId:'moveRight',
				text:'动画(Right)',
				margin:'20 0 0 0'
			},

			{
				xtype:'button',
				itemId:'moveLeft',
				text:'动画(Left)',
				margin:'20 0 0 0'
			}
        ]
    },

	initialize: function () {
		var me = this,items,
			width = Math.floor(window.innerWidth),
			itemPercent = 0.8,
			spacing = me.spacing || me.getSpacing();

		me.width = width;
		me.itemWidth = Math.floor(width * itemPercent);
		me.displayWidth = Math.floor((width * (1 - itemPercent) - 4 * spacing) / 2);
		me.hideWidth = me.itemWidth - me.displayWidth;
		me.activeIndex = 1;

		me.translateGroup = [];
		items = me.query("image");
		Ext.each(items, function(item, index){
			me.translateGroup.push(index);

			item.setWidth(me.itemWidth);
			item.setHeight('' + (itemPercent * 100) + '%');
			item.translatex = -1 * (me.hideWidth + spacing);
		});

		this.doMove();

		me.down('#moveRight').on(
			{
				scope: me,
				tap: me.moveRight
			}
		);

		me.down('#moveLeft').on(
			{
				scope: me,
				tap: me.moveLeft
			}
		);
	},

	moveLeft:function(){
		var me = this, item,
			items = me.query("image"),
			len = items.length,
			translateGroup = me.translateGroup,
			spacing = me.spacing || me.getSpacing(),
			distance = (len - 2 - translateGroup[0]) * me.itemWidth + me.displayWidth + (len - 2- translateGroup[0]) * 2 * spacing + spacing;

		Ext.each(translateGroup, function(_index, index) {
			item = items[_index];
			item.translatex = item.translatex - me.itemWidth - 2 * spacing;
		});

		this.doMove();

		item = items[translateGroup[0]];
		item.translate(distance);
		item.translatex = distance;

		translateGroup.push(translateGroup.shift());
	},

	moveRight:function(){
		var me = this,item,
			items = me.query("image"),
			translateGroup = me.translateGroup,
			spacing = me.spacing || me.getSpacing();

		translateGroup.unshift(translateGroup.pop());
		var distance = - ((translateGroup[0] + 1) * (me.itemWidth + 2* spacing) + (me.hideWidth + spacing));
		console.log(translateGroup);
		console.log(distance);
		item = items[translateGroup[0]];
		item.translate(distance);
		item.translatex = distance;

		Ext.each(translateGroup, function(_index, index){
			item = items[_index];
			item.translatex = item.translatex + me.itemWidth + 2* spacing;
		});

		this.doMove();
	},

	doMove:function() {
		var me = this,
			items = me.query("image");

		Ext.each(items, function(item, index){
			item.translate(item.translatex, 0, {type: 'slide', duration: 1000, easing:'ease-out'});
		})
	}
});

