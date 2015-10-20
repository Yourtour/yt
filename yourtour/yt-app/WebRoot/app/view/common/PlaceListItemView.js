Ext.define('YourTour.view.common.PlaceListItemView', {
	extend: 'Ext.dataview.component.DataItem',
    requires:['Ext.Panel', 'YourTour.view.widget.XField','YourTour.view.widget.XPanel','YourTour.view.widget.XGridView'],
    xtype: 'PlaceListItemView',
    config: {
      	layout:'vbox',
      	target:null,
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
    
    initialize : function(){
    	this.callParent(arguments);
    	
    	var cities = this.down('#cities');
    	cities.addListener('itemtap', this.onGridViewTap, this);
    },
    
    
    onGridViewTap:function(record){
    	this.target.fireEvent('itemtap', record);
    },
    
    updateTarget:function(target){
    	this.target = target;
    },
    
    updateRecord: function(record){
    	var me = this;
    	
       	if(record){
       		var typeEl = me.down('#name');
       		typeEl.setHtml(record.get('name'));
       		
       		var cityCountEl = me.down('#cityCount');
       		var moreCitiesEl = me.down('#moreCities');
       		var cities = me.down('#cities');
       		var provinceEl = me.down('#province');
       		provinceEl.element.on({
				scope : me,
				tap : function(e, t) {
					if(record.get('num') == '0'){
						me.target.fireEvent('itemtap', record);
					}else{
						if(moreCitiesEl.getSrc() == 'resources/icons/icon_arrow_down.png'){
	           				moreCitiesEl.setSrc('resources/icons/icon_arrow_up.png');
	           				cities.show();
	           			}else{
	           				moreCitiesEl.setSrc('resources/icons/icon_arrow_down.png');
	           				cities.hide();
	           			}
					}
				}
			});
       		
       		if(record.get('num') == '0'){
       			cityCountEl.hide();
       			moreCitiesEl.hide();
       		}else{
       			cityCountEl.setHtml(record.get('num'));
           		cities.setModels(record.subs());
       		}
       		cities.hide();
	 	}
    }
});

