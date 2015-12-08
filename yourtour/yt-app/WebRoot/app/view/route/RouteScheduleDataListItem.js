Ext.define('YourTour.view.route.RouteScheduleDataListItem', {
    extend: 'Ext.dataview.component.DataItem',
    xtype: 'RouteScheduleDataListItem',
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
    			items:[
    				{
		    			xtype : 'image',
		    			mode : 'tag',
		    			cls:'icon',
		    			src :'resources/icons/icon_prepare_item.png'
    				},
    				{
    					xtype:'panel',
    					layout:'vbox',
    					flex:1,
    					items:[
		    				{
		    					xtype:'label',
		    					itemId:'title',
		    					cls:'title'
		    				},
		    				{
		    					xtype:'label',
		    					itemId:'memo',
		    					hidden:true,
		    					cls:'content memo'
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
    			itemId:'dayItemPanel',
    			xtype:'panel',
    			layout:'hbox',
    			hidden:true,
    			cls:'scheduleItem',
    			items:[
    				{
		    			xtype : 'image',
		    			mode : 'tag',
		    			cls:'icon',
		    			src :'resources/icons/icon_day.png'
    				},
    				
    				{
    					xtype:'panel',
    					layout:'vbox',
    					flex:1,
    					items:[
		    				{
		    					xtype:'label',
		    					itemId:'title',
		    					cls:'title'
		    				},
		    				{
    							xtype:'label',
    							itemId:'time',
    							cls:'content'
    						},
		    				{
		    					xtype:'label',
		    					itemId:'memo',
		    					hidden:true,
		    					cls:'content strong'
		    				},
    						
    						{
		    					xtype:'panel',
		    					layout:'hbox',
		    					items:[
		    					       {
											   xtype:'spacer',
											   flex:1
		    					       },
		    					       {
		    					    	   xtype : 'image',
		    					    	   mode : 'tag',
		    					    	   src :'resources/icons/icon_thumbup.png'
		    					       },{
		    					    	   xtype : 'image',
		    					    	   mode : 'tag',
		    					    	   src :'resources/icons/icon_thumbup.png'
		    					       },{
		    					    	   xtype:'spacer',
		    					    	   width:10
		    					       }
		    					]
		    				}
		    			]
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
       			
       			if(record.get('memo') != ''){
	       			var memo = panel.down('#memo');
	       			memo.setHtml(record.get('memo'));
	       			memo.show();
       			}
       		}else if(type == 'Schedule'){
       			panel = me.down('#dayPanel');
       			panel.show();
       			
       			var title = panel.down('#title');
       			title.setHtml(record.get('title'));
       		}else{
       			panel = me.down('#dayItemPanel');
       			panel.show();
       			
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
       			
       			var title = panel.down('#title');
       			title.setHtml(record.get('title'));
       			
       			if(record.get('memo') != ''){
	       			var memo = panel.down('#memo');
	       			memo.setHtml(record.get('memo'));
	       			memo.show();
       			}
       			
       			var time = panel.down('#time');
       			time.setHtml(record.get('startTime') + '至' + record.get('endTime'));
       		}
       		
       		me.element.on('longpress', function(e){
       			me.longpressed = true;
       			
       			dataview.fireEvent('itemlongtap', dataview, dataview.items.length, me, record);
       		});
       }
    }   
});

