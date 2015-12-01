Ext.define('YourTour.view.route.RouteScheduleDataListItem', {
    extend: 'Ext.dataview.component.DataItem',
    xtype: 'RouteScheduleDataListItem',
    requires:['Ext.Label','Ext.field.Select','Ext.Panel'],
    config: {
    	longpressed:false,
    	
		defaults:{
			style:'background:#fff',
			padding:'0 5 0 5'
		},
		
		cls:'ScheduleListItem',
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
    					itemId:'title',
    					flex:1,
    					margin:'0 0 0 5'
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
		    					itemId:'title',
		    					cls:'title'
		    				},
		    				{
		    					xtype:'label',
		    					itemId:'memo',
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
    			/*minHeight:50,
    			maxHeight:200,*/
    			items:[
    				{
		    			xtype : 'image',
		    			mode : 'tag',
		    			src :'resources/icons/icon_day.png'
    				},
    				{
    					xtype:'panel',
    					layout:'vbox',
    					flex:1,
    					margin:'0 0 0 5',
    					items:[
		    				{
		    					xtype:'label',
		    					itemId:'title'
		    				},
		    				{
		    					xtype:'label',
		    					itemId:'memo',
		    					cls:'multilineinfo'
		    				}
		    			]
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
		    					itemId:'title',
		    					cls:'title'
		    				},
		    				{
		    					xtype:'label',
		    					itemId:'memo',
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
       			panel.addCls('space-bottom');
       			panel.show();
       			
       			var title = panel.down('#title');
       			title.setHtml(record.get('title'));
       		}else if(type == 'ProvisionItem'){
       			panel = me.down('#prepareItemPanel');
       			panel.show();
       			
       			if(record.get('isLast') != '1'){
       				panel.getAt(1).addCls('horizentalLine');
       			}
       			
       			var title = panel.down('#title');
       			title.setHtml(record.get('title'));
       			
       			var memo = panel.down('#memo');
       			memo.setHtml(record.get('memo'));
       		}else if(type == 'Schedule'){
       			panel = me.down('#dayPanel');
       			panel.addCls('space-both');
       			panel.show();
       			
       			var title = panel.down('#title');
       			title.setHtml(record.get('title'));
       			
       			if(record.get('memo') != undefined){
       				var memo = panel.down('#memo');
       				memo.setHtml(record.get('memo'));
       			}
       		}else{
       			panel = me.down('#dayItemPanel');
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
       			
       			var title = panel.down('#title');
       			title.setHtml(record.get('title'));
       			
       			var memo = panel.down('#memo');
       			memo.setHtml(record.get('memo'));
       			
       			var time = panel.down('#time');
       			time.setHtml(record.get('time'));
       			
       			var period = panel.down('#period');
       			period.setHtml(record.get('period'));
       		}
       		
       		me.element.on('longpress', function(e){
       			me.longpressed = true;
       			
       			dataview.fireEvent('itemlongtap', dataview, dataview.items.length, me, record);
       		});
       }
    }   
});

