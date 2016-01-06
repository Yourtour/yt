Ext.define('YourTour.view.route.RouteSchedulePlanDataItem', {
    extend: 'Ext.dataview.component.DataItem',
    xtype: 'RouteSchedulePlanDataItem',
    requires:['Ext.Label','Ext.field.Select','Ext.Panel'],
    config: {
    	longpressed:false,

		defaults:{
			style:'background:#fff;font-size:14px',
			padding:'0 5 0 5'
		},

		cls:'ScheduleListItem',
    	items:[
    		{
    			itemId:'preparePanel',
    			xtype:'panel',
    			layout:'hbox',
    			hidden:true,
    			cls:'schedule',
    			items:[
    				{
    					xtype:'label',
    					itemId:'title',
    					flex:1,
    					cls:'title strong'
    				}
    			]
    		},
    		
    		{
    			itemId:'prepareItemPanel',
    			xtype:'panel',
    			hidden:true,
    			layout:'hbox',
				cls:'scheduleItem',
    			items:[
					{
						xtype:'label',
						itemId:'title',
						cls:'title icon_todo',
						padding:'0 0 0 50'
					}
    			]
    		},
    		
    		{
    			itemId:'dayPanel',
    			xtype:'panel',
    			hidden:true,
    			layout:'hbox',
    			cls:'schedule',
    			items:[
    				{
    					xtype:'label',
    					itemId:'title',
    					cls:'title strong'
    				}
    			]
    		},
    		
    		{
    			itemId:'dayItemPanel',
    			xtype:'panel',
    			layout:'vbox',
    			hidden:true,
    			cls:'scheduleItem',
    			items:[
					{
						xtype:'label',
						itemId:'title',
						cls:'title',
						padding:'0 0 0 50'
					}
    			]
    		}
    	]
    },
    
    updateLongpressed:function(longpressed){
    	this.longpressed = longpressed;
    },
    
   	/**
   	 * 
   	 * @param {} record
   	 */
    updateRecord: function(record) {
       var me = this;
       var dataview = this.dataview || this.getDataview();
       if(record){
    	   var panel;
       	   var type = record.get('type');
       		if(type == 'Provision'){
       			panel = me.down('#preparePanel');
       			panel.show();
       			
       			var title = panel.down('#title');
       			title.setHtml(record.get('title'));
       		}else if(type == 'ProvisionItem'){
       			panel = me.down('#prepareItemPanel');
       			panel.show();
       			
       			var title = panel.down('#title');
       			title.setHtml(record.get('title'));
       		}else if(type == 'Schedule'){
       			panel = me.down('#dayPanel');
       			panel.show();
       			
       			var title = panel.down('#title');
       			title.setHtml(record.get('title'));
       		}else{
       			panel = me.down('#dayItemPanel');
       			panel.show();

				var title = panel.down('#title');
				title.setHtml(record.get('title'));
				var resourceType = record.get('resourceType');
				if(resourceType == 'SCENE'){
					title.addCls('icon_scene');
				}else if(resourceType == 'HOTEL'){
					title.addCls('icon_hotel');
				}else if(resourceType == 'FOOD'){
					title.addCls('icon_food');
				}else if(resourceType == 'traffic'){
					title.addCls('icon_restaurant');
				}
       		}
       		
       		me.element.on('longpress', function(e){
       			me.longpressed = true;
       			
       			dataview.fireEvent('itemlongtap', dataview, dataview.items.length, me, record);
       		});
       }
    }   
});

