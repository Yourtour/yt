Ext.define('YourTour.view.place.PlaceSelectionDataItem', {
	extend: 'Ext.dataview.component.DataItem',
    requires:['Ext.Panel', 'YourTour.view.widget.XField'],
    xtype: 'PlaceSelectionDataItem',
    config: {
      	layout:'fit',
		cls:'row underline',
		padding:'0 10 0 10',
		expandable:true,

        items: [
			{
				xtype:'container',
				itemId:'item',
				layout:'hbox',
				items:[
					{
						xtype:'xfield',
						itemId:'name',
						underline:false
					},

					{
						xtype:'spacer',
						flex:1
					},

					{
						xtype:'xfield',
						itemId:'num',
						underline:false
					}
				]
			}
        ]
    },
    
    updateRecord: function(record){
    	var me = this;
		var item = me.down('#item');

       	if(record){
       		var name = me.down('#name');
			name.setText(record.get('name'));
       		
       		var num = me.down('#num');
			num.setText(record.get('num'));

			if(record.get('leaf') == 'true'){
				item.removeCls('icon-arrow-down-right');
				item.setPadding('0 0 0 20');
				num.hide();
			}else{
				item.addCls('icon-arrow-down-right');
				item.setPadding('0 20 0 0');
			}

			if(record.get('hidden')){
				me.setHidden(true);
			}else{
				me.setHidden(false);
			}
	 	}
    },

	setExpandable:function(expandable){
		var me = this;

		me.expandable = expandable;
		var item = me.down('#item');

		if(me.expandable){
			item.removeCls('icon-arrow-up-right');
			item.addCls('icon-arrow-down-right');
		}else{
			item.addCls('icon-arrow-up-right');
			item.removeCls('icon-arrow-down-right');
		}
	},

	getExpandable:function(){
		return this.expandable;
	}
});

