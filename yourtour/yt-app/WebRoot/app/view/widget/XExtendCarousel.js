/**
 * 本插件主要实现了对插件中包含的内容进行循环播放功能，包括：手势滑动播放以及定时播放
 */
Ext.define('YourTour.view.widget.XExtendCarousel', {
    extend: 'Ext.Container',
	xtype:'xextendcarousel',
    config: {
		layout:'hbox',
		spacing:5,
		height:200,
		translateGroup:null,
		defaults:{
			margin:'0 5 0 5'
		}
    },

	initialize: function () {
		var me = this,items,
			width = Math.floor(window.innerWidth),
			itemPercent = 0.8,
			spacing = me.spacing || me.getSpacing();

		//手势滑动事件绑定
		this.element.on({
			dragstart: 'onDragStart',
			drag: 'onDrag',
			dragend: 'onDragEnd',
			scope: this
		});

		//初始化
		me.width = width;
		me.itemWidth = Math.floor(width * itemPercent);
		me.displayWidth = Math.floor((width * (1 - itemPercent) - 4 * spacing) / 2);
		me.hideWidth = me.itemWidth - me.displayWidth;

		me.translateGroup = [];
		items = me.getItems().items;
		Ext.each(items, function(item, index){
			me.translateGroup.push(index);

			item.setWidth(me.itemWidth);
			item.setHeight('' + (itemPercent * 100) + '%');
			item.translatex = -1 * (me.hideWidth + spacing);
		});

		me.doTranslate()
	},

	/**
	 * 取消定时
	 * @param event
	 */
	onDragStart:function(event){
		var me = this;
			task = me.task;
		task.cancel();
	},

	/**
	 * 手势滑动结束时，进行惯性平移
	 * @param event
	 */
	onDragEnd:function(event){
		var me = this,
			absDeltaX = event.absDeltaX,
			direction = event.deltaX < 0 ? "left":"right";

		if(absDeltaX > 50){
			if(direction == "left") {
				me.translateLeft();
			}else{
				me.translateRight(absDeltaX);
			}
		}
	},

	/**
	 * 滑动过程捕获，主要实现内容根据手势进行平移
	 * @param event
	 */
	onDrag:function(event){
		var me = this,
			deltaX = event.deltaX,
			items = me.getItems().items;

		Ext.each(items, function(item, index){
			item.translate(item.translatex + deltaX, 0);
		})
	},

	/**
	 * 内容向左平移，1）整体水平左移。2）左移完成后将最左边的内容再次平移到末尾
	 * @param deltaX。
	 */
	translateLeft:function(){
		var me = this, item,
			items = me.getItems().items,
			len = items.length,
			translateGroup = me.translateGroup,
			spacing = me.spacing || me.getSpacing(),
			distance = (len - 2 - translateGroup[0]) * me.itemWidth + me.displayWidth + (len - 2- translateGroup[0]) * 2 * spacing + spacing;

		Ext.each(translateGroup, function(_index, index) {
			item = items[_index];
			item.translatex = item.translatex - me.itemWidth - 2 * spacing;
		});

		this.doTranslate();

		item = items[translateGroup[0]];
		item.translate(distance);
		item.translatex = distance;

		translateGroup.push(translateGroup.shift());
	},

	/**
	 * 内容向右平移。1）将队列中最后边的内容左移到队列最前面，2）整体右移
	 * @param deltaX
	 */
	translateRight:function(deltaX){
		var me = this,item,distance,
			items = me.getItems().items,
			translateGroup = me.translateGroup,
			spacing = me.spacing || me.getSpacing();

		translateGroup.unshift(translateGroup.pop());
		if(deltaX) {
			distance = -((translateGroup[0] + 1) * (me.itemWidth + 2 * spacing) + (me.hideWidth + spacing)) + deltaX;
		}else{
			distance = -((translateGroup[0] + 1) * (me.itemWidth + 2 * spacing) + (me.hideWidth + spacing));
		}
		item = items[translateGroup[0]];
		item.translate(distance);
		item.translatex = distance - deltaX;

		Ext.each(translateGroup, function(_index, index){
			item = items[_index];
			item.translatex = item.translatex + me.itemWidth + 2* spacing;
		});

		this.doTranslate();
	},

	/**
	 * 根据设定的Translatex值进行滑动
	 */
	doTranslate:function() {
		var me = this,
			items = me.getItems().items;

		Ext.each(items, function(item, index){
			item.translate(item.translatex, 0, {type: 'slide', duration: 1000, easing:'ease-out'});
		});

		me.startTimer();
	},

	/**
	 * 定时录播显示
	 */
	startTimer:function(){
		var me = this, task;
		me.task = Ext.create('Ext.util.DelayedTask', function () {
			me.translateLeft()
		});
		me.task.delay(2000);
	}
});

