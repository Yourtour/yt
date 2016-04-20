Ext.define('YourTour.view.DemoView', {
    extend: 'Ext.Container',
    config: {
    	layout:'vbox',
    	id:'DemoView',
		activeIndex:0,
		spacing:5,
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
					},

					{
						xtype:'image',
						itemId:'5',
						src:'resources/images/shanghai.jpg'
					},

					{
						xtype:'image',
						itemId:'6',
						src:'resources/images/guangdong.jpg'
					}
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
		me.displyWidth = Math.floor((width * (1 - itemPercent) - 4 * spacing) / 2);
		me.hideWidth = me.itemWidth - me.displyWidth;
		me.activeIndex = 1;

		console.log("width=" + width);

		items = me.query("image");
		Ext.each(items, function(item){
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
		var me = this,
			items = me.query("image"),
			len = items.length,
			spacing = me.spacing || me.getSpacing();

		if(me.activeIndex == len - 1){
			me.activeIndex = 0;
		}else{
			me.activeIndex += 1;
		}

		console.log("activeIndex=" + me.activeIndex);
		Ext.each(items, function(item, index){
			if(me.activeIndex == 1) { //恢复初始位置
				item.translatex = -1 * (me.hideWidth + spacing);
			}else if(me.activeIndex == len - 1){ //最后一个
				if(index == 0){
					item.translate(me.width + me.hideWidth + 2 * spacing, 0);
					item.translatex =  me.itemWidth + me.displyWidth + 3 * spacing;
				}else{
					item.translatex = item.translatex - me.itemWidth - 2 * spacing;
				}
			}else if(me.activeIndex == 0) {
				if(index != len - 1){
				}

				item.translatex = item.translatex - me.itemWidth - 2 * spacing;
			}else{
				if(index > 0 && index < len - 1){
					item.translatex = items[index-1].translatex;
				}else {
					console.log("item = " + index + ", translatex=" + item.translatex);
					item.translatex = item.translatex - me.itemWidth - 2 * spacing;
				}
			}
		});

		this.doMove();
	},

	moveRight:function(){
		var me = this,
			items = me.query("image"),
			len = items.length,
			spacing = me.spacing || me.getSpacing();

		if(me.activeIndex == 0){
			me.activeIndex = len - 1;
		}else{
			me.activeIndex -= 1;
		}

		Ext.each(items, function(item, index){
			if(me.activeIndex == 1) { //恢复初始位置
				item.translatex = -1 * (me.hideWidth + spacing);
			}else if(me.activeIndex == 0) { //第一个
				if(index == len - 1) {
					item.translatex = -((index + 1) * me.itemWidth + me.displyWidth + 3 * spacing);
				}else {
					item.translatex = item.translatex + me.itemWidth + 2 * spacing;
				}
			}else{
				if(index == me.activeIndex - 1) {
					item.translatex = items[index + 1].translatex;
				}

				item.translatex = item.translatex + me.itemWidth + 2 * spacing;
			}
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

