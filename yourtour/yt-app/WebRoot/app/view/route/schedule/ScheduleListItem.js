Ext.define('YourTour.view.route.schedule.ScheduleListItem', {
    extend: 'Ext.dataview.component.DataItem',
    xtype: 'scheduleListItem',
    requires:['Ext.Label','Ext.field.Select','Ext.Panel'],
    config: {
		defaults:{
			style:'background:#fff',
			padding:'5 5 0 5'
		},
		
    	items:[
    		{
    			itemId:'preparePanel',
    			xtype:'panel',
    			layout:'hbox',
    			hidden:true,
    			margin:'5 0 5 0',
    			items:[
    				{
		    			xtype : 'image',
		    			mode : 'tag',
		    			padding:'0 5 0 0',
		    			src :'resources/icons/icon_prepare.png'
    				},
    				{
    					xtype:'label',
    					itemId:'name',
    					flex:1,
    					style:'font-size:15px; font-weight:bold; line-height:30px'
    				},
    				{
	                	xtype: "button", 
	                    ui: "normal", 
	                	text:'添加',
	                	itemId:'edit',
	                	style:'font-size:16px;font-weight:bold;'
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
		    			padding:'0 5 0 0',
		    			src :'resources/icons/icon_prepare_item.png'
    				},
    				{
    					xtype:'panel',
    					layout:'vbox',
    					padding:'0 0 10 0',
    					flex:1,
    					items:[
		    				{
		    					xtype:'label',
		    					itemId:'name',
		    					style:'font-size:14px;font-weight:1em;line-height:30px'
		    				},
		    				{
		    					xtype:'label',
		    					itemId:'desc',
		    					style:'font-size:13px; line-height:20px; padding-right:5px'
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
    			margin:'5 0 5 0',
    			items:[
    				{
		    			xtype : 'image',
		    			mode : 'tag',
		    			padding:'0 5 0 0',
		    			src :'resources/icons/icon_day.png'
    				},
    				{
    					xtype:'label',
    					itemId:'name',
    					flex:1,
    					style:'font-size:16px;font-weight:bold; line-height:30px'
    				},
    				{
	                	xtype: "button", 
	                    ui: "normal", 
	                	text:'添加',
	                	itemId:'edit',
	                	style:'font-size:16px;font-weight:bold;'
	                }
    			]
    		},
    		
    		{
    			itemId:'dayItemPanel',
    			xtype:'panel',
    			layout:'hbox',
    			hidden:true,
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
    					padding:'0 0 10 0',
    					flex:1,
    					items:[
		    				{
		    					xtype:'label',
		    					itemId:'name',
		    					style:'font-size:14px;font-weight:1em;line-height:30px'
		    				},
		    				{
		    					xtype:'label',
		    					itemId:'desc',
		    					style:'font-size:13px; line-height:20px; padding-right:5px'
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
		    							style:'font-size:13px; line-height:20px;'
		    						},
		    						
		    						{
		    							xtype:'label',
		    							itemId:'period',
		    							flex:1,
		    							style:'text-align:right; font-size:13px; line-height:20px;'
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
       			panel.show();
       			
       			var name = panel.down('#name');
       			name.setHtml(record.get('name'));
       			
       			var edit = panel.down('#edit');
		    	edit.on("tap", function(){
		    		dataview.fireEvent("edit", dataview, record);
		    	});
       		}else if(type == 'prepareItem'){
       			var panel = me.down('#prepareItemPanel');
       			panel.show();
       			
       			if(record.get('isLast') != '1'){
       				panel.getAt(1).setStyle('border-bottom:1px solid #EDEDED;');
       			}
       			
       			var name = panel.down('#name');
       			name.setHtml(record.get('name'));
       			
       			var desc = panel.down('#desc');
       			desc.setHtml(record.get('desc'));
       		}else if(type == 'day'){
       			var panel = me.down('#dayPanel');
       			panel.show();
       			
       			var name = panel.down('#name');
       			name.setHtml(record.get('name'));
       			
       			var edit = panel.down('#edit');
		    	edit.on("tap", function(){
		    		dataview.fireEvent("edit", dataview, record);
		    	});
       		}else{
       			var panel = me.down('#dayItemPanel');
       			panel.show();
       			
       			if(record.get('isLast') != '1'){
       				panel.getAt(1).setStyle('border-bottom:1px solid #EDEDED;');
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

