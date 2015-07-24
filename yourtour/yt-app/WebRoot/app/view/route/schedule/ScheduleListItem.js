Ext.define('YourTour.view.route.schedule.ScheduleListItem', {
    extend: 'Ext.dataview.component.DataItem',
    xtype: 'scheduleListItem',
    requires:['Ext.Label','Ext.field.Select','Ext.Panel'],
    config: {
		defaults:{
			style:'background:#fff',
			padding:'0 5 0 5'
		},
		
		baseCls:'ScheduleListItem',
    	items:[
    		{
    			itemId:'preparePanel',
    			xtype:'panel',
    			layout:'hbox',
    			hidden:true,
    			cls:'prepare',
    			items:[
    				{
		    			xtype : 'image',
		    			mode : 'tag',
		    			src :'resources/icons/icon_prepare.png'
    				},
    				{
    					xtype:'label',
    					itemId:'name',
    					flex:1,
    					margin:'0 0 0 5'
    				},
    				{
	                	xtype: "button", 
	                    ui: "normal", 
	                	text:'添加',
	                	itemId:'edit'
	                }
    			]
    		},
    		
    		{
    			itemId:'prepareItemPanel',
    			xtype:'panel',
    			hidden:true,
    			layout:'hbox',
    			items:[
    				{
		    			xtype : 'image',
		    			mode : 'tag',
		    			src :'resources/icons/icon_prepare_item.png'
    				},
    				{
    					xtype:'panel',
    					layout:'vbox',
    					flex:1,
    					margin:'0 0 0 5',
    					items:[
		    				{
		    					xtype:'label',
		    					itemId:'name',
		    					cls:'name'
		    				},
		    				{
		    					xtype:'label',
		    					itemId:'desc',
		    					cls:'multilineinfo'
		    				}
		    			]
    				}
    			]
    		},
    		
    		{
    			itemId:'dayPanel',
    			xtype:'panel',
    			hidden:true,
    			layout:'hbox',
    			cls:'day',
    			height:50,
    			items:[
    				{
		    			xtype : 'image',
		    			mode : 'tag',
		    			src :'resources/icons/icon_day.png'
    				},
    				{
    					xtype:'label',
    					itemId:'name',
    					flex:1,
    					margin:'0 0 0 5'
    				},
    				{
	                	xtype: "button", 
	                    ui: "normal", 
	                	text:'添加',
	                	itemId:'edit'
	                }
    			]
    		},
    		
    		{
    			itemId:'dayItemPanel',
    			xtype:'panel',
    			layout:'hbox',
    			hidden:true,
    			minHeight:30,
    			items:[
    				{
		    			xtype : 'image',
		    			itemId:'dayItemIcon',
		    			mode : 'tag',
		    			padding:'0 5 0 0',
		    			src :'resources/icons/icon_day.png'
    				},
    				
    				{
    					xtype:'panel',
    					layout:'vbox',
    					flex:1,
    					items:[
		    				{
		    					xtype:'label',
		    					itemId:'name',
		    					cls:'name'
		    				},
		    				{
		    					xtype:'label',
		    					itemId:'desc',
		    					cls:'multilineinfo'
		    				},
		    				{
		    					xtype:'panel',
		    					layout:'hbox',
		    					style:'padding-top:5px; padding-right:5px',
		    					items:[
		    						{
		    							xtype:'label',
		    							itemId:'time',
		    							flex:1,
		    							cls:'time'
		    						},
		    						
		    						{
		    							xtype:'label',
		    							itemId:'period',
		    							flex:1,
		    							cls:'time'
		    						}
		    					]
		    				}
		    			]
    				}
    			]
    		}
    	]
    },
    
   	/**
   	 * 
   	 * @param {} record
   	 */
    updateRecord: function(record) {
       var me = this;
       
       if(record){
       		var dataview = me.dataview || this.getDataview();
       		
       		var type = record.get('type');
       		if(type == 'prepare'){
       			var panel = me.down('#preparePanel');
       			panel.addCls('space-bottom');
       			panel.show();
       			
       			var name = panel.down('#name');
       			name.setHtml(record.get('name'));
       			
       			var edit = panel.down('#edit');
       			
       			if(dataview.readonly){
       				edit.hide();	
       			}else{
			    	edit.on("tap", function(){
			    		dataview.fireEvent("edit", dataview, record);
			    	});
       			}
       		}else if(type == 'prepareItem'){
       			var panel = me.down('#prepareItemPanel');
       			panel.show();
       			
       			if(record.get('isLast') != '1'){
       				panel.getAt(1).addCls('horizentalLine');
       			}
       			
       			var name = panel.down('#name');
       			name.setHtml(record.get('name'));
       			
       			var desc = panel.down('#desc');
       			desc.setHtml(record.get('desc'));
       		}else if(type == 'day'){
       			var panel = me.down('#dayPanel');
       			panel.addCls('space-both');
       			panel.show();
       			
       			var name = panel.down('#name');
       			name.setHtml(record.get('name'));
       			
       			var edit = panel.down('#edit');
       			if(dataview.readonly){
       				edit.hide();	
       			}else{
			    	edit.on("tap", function(){
			    		dataview.fireEvent("edit", dataview, record);
			    	});
       			}
       		}else{
       			var panel = me.down('#dayItemPanel');
       			panel.show();
       			
       			if(record.get('isLast') != '1'){
       				panel.getAt(1).addCls('horizentalLine');
       			}
       			
       			var dayItemIcon = panel.down('#dayItemIcon');
				if(type == 'scene'){
       				dayItemIcon.setSrc('resources/icons/icon_scene.png');
				}else if(type == 'residence'){
					dayItemIcon.setSrc('resources/icons/icon_hotel.png');
				}else if(type == 'food'){
					dayItemIcon.setSrc('resources/icons/icon_food.png');
				}else if(type == 'traffic'){
					dayItemIcon.setSrc('resources/icons/icon_traffic.png');
				}
       			
       			var name = panel.down('#name');
       			name.setHtml(record.get('name'));
       			
       			var desc = panel.down('#desc');
       			desc.setHtml(record.get('desc'));
       			
       			var time = panel.down('#time');
       			time.setHtml(record.get('time'));
       			
       			var period = panel.down('#period');
       			period.setHtml(record.get('period'));
       		}
       }	
    }   
});

