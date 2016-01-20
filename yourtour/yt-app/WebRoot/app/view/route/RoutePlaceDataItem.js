Ext.define('YourTour.view.route.RoutePlaceDataItem', {
    extend: 'YourTour.view.widget.XDataItem',
    xtype: 'RoutePlaceDataItem',
	requires:['YourTour.view.widget.XImage','YourTour.view.widget.XField','YourTour.view.widget.XScore'],
    config: {
		layout:'hbox',

    	items:[
			{
				xtype:'xfield',
				itemId:'name',
				underline:false,
				fieldCls:'font-normal',
				padding:'0',
				flex:1
			},

			{
				xtype:'xscore',
				itemId:'score',
				star:true,
				flex:1
			},

			{
				xtype:'label',
				itemId:'action',
				width:40
			}
		]
    },

	updateRecord:function(record){
		var me = this;
		if(record) {
			var name = me.down('#name');
			name.setText(record.get('name'));

			var score = me.down('#score');
			score.setHtml('4.5');

			var action = me.down('#action');
			if(record.get('action') == 'delete'){
				action.addCls('icon-delete');
			}else{
				action.addCls('icon-add');
			}

			action.element.on({
				scope:me,
				tap:function(){
					dataview = me.dataview || me.getDataview();
					dataview.fireEvent('itemoperation', dataview, action, record);
				}
			})
		}
	}
});

