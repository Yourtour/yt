Ext.define('YourTour.view.route.schedule.SchedulePlanListItem', {
    extend: 'Ext.dataview.component.DataItem',
    xtype: 'SchedulePlanListItem',
    requires:['Ext.Label','Ext.field.Select','Ext.Panel'],
    config: {
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
		    			src :'resources/icons/icon_dot.png'
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
		    			src :'resources/icons/icon_dot.png'
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
       				dayItemIcon.setSrc('resources/icons/icon_dot.png');
				}else if(type == 'residence'){
					dayItemIcon.setSrc('resources/icons/icon_dot.png');
				}else if(type == 'food'){
					dayItemIcon.setSrc('resources/icons/icon_dot.png');
				}else if(type == 'traffic'){
					dayItemIcon.setSrc('resources/icons/icon_dot.png');
				}
       			
       			var name = panel.down('#name');
       			name.setHtml(record.get('name'));
       		}
       }	
    }   
});

