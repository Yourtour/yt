Ext.define('YourTour.view.common.PlaceListItemView', {
	extend: 'Ext.dataview.component.DataItem',
    requires:['Ext.Panel', 'YourTour.view.widget.XField','YourTour.view.widget.XPanel','YourTour.view.widget.XGridView'],
    xtype: 'PlaceListItemView',
    config: {
      	layout:'vbox',
        items: [
            {
            	xtype:'panel',
            	layout:'hbox',
            	itemId:'province',
            	padding:'10 5 10 10',
            	cls:'underline',
            	items:[
					{
						itemId:'name',
						xtype:'xfield',
					    flex:5
					},
					
					{
						itemId:'cityCount',
						xtype:'xfield',
						flex:2
					},
					
					{
						itemId:'moreCities',
						xtype : 'image',
						mode : 'tag',
						src:'resources/icons/icon_arrow_down.png',
						width:16,
						height:16
					}
            	]
            },    

            {
	   			xtype:'xgridview',
	   			style:'background:#EDEDED',
	   			itemId:'cities',
	   			defaults:{
	   				style:'line-height:37px; height:37px; text-align:center'
	   			},
				cols:3,
				item:'YourTour.view.common.PlaceGridItemView'
	   		}
        ]
    },
    
    updateRecord: function(record){
    	var me = this;
       	if(record){
       		var typeEl = me.down('#name');
       		typeEl.setHtml(record.get('name'));
       		
       		var cityCountEl = me.down('#cityCount');
       		var moreCitiesEl = me.down('#moreCities');
       		var cities = me.down('#cities');
       		if(record.get('cityCount') == '0'){
       			cityCountEl.hide();
       			moreCitiesEl.hide();
       		}else{
       			cityCountEl.setHtml(record.get('cityCount'));
           		cities.setModels(record.cities());
           		moreCitiesEl.on('tap', function(){
           			if(moreCitiesEl.getSrc() == 'resources/icons/icon_arrow_down.png'){
           				moreCitiesEl.setSrc('resources/icons/icon_arrow_up.png');
           				cities.show();
           			}else{
           				moreCitiesEl.setSrc('resources/icons/icon_arrow_down.png');
           				cities.hide();
           			}
           		});
       		}
       		cities.hide();
	 	}
    }
});

