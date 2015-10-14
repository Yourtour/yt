Ext.define('YourTour.view.route.RouteSettingItem', {
    extend: 'Ext.dataview.component.DataItem',
    requires:['Ext.Img','Ext.field.Spinner', 'YourTour.view.widget.XLabel', 'YourTour.view.widget.XField'],
    xtype: 'RouteSettingItem',
    config: {
    	layout:'hbox',
    	cls:'row underline',
    	padding:'5 10 5 10',
        items: [
			{
				itemId:'name',
				xtype:'xfield',
				flex:1
			},
			
			{
				itemId:'num',
				xtype:'spinnerfield',
				minValue:1,
				maxValue:10,
				stepValue:1,
				margin:'0 0 0 10',
				groupButtons:false
			},
			
			{
				itemId:'del',
				xtype:'image',
				mode : 'tag',
				margin:'0 0 0 10',
				src:'resources/icons/icon_delete.png',
				width:24,
				height:24
			}
        ]
    },
    
    initialize : function(){
    	this.callParent(arguments);
    	
    	var delEl = this.down('#del');
    	delEl.addListener('tap', this.onDeleteTap, this);
    },
    
    onDeleteTap:function(record){
    	this.getDataview().fireEvent('itemdel', this.getRecord());
    },
    
	updateRecord: function(record) {
	    var me = this;
	    
	    if(record){
	 	   var nameEl = me.down('#name');
	 	   nameEl.setHtml(record.get('name'));
	 	}
	 }   
});

