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
				cls:'scheduleItem',
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
    			layout:'vbox',
    			hidden:true,
    			cls:'scheduleItem',
    			items:[
					{
						xtype:'label',
						itemId:'title',
						cls:'title',
						padding:'0 0 0 50'
					},

    				{
    					xtype:'panel',
    					layout:'vbox',
						padding:'0 0 0 50',
    					items:[
							{
								xtype:'panel',
								layout:'hbox',
								cls:'content',
								items:[
									{
										xtype: 'image',
										src: 'resources/images/raty_32.png',
										mode: 'tag'
									},
									{
										xtype: 'label',
										margin:'0 0 0 10',
										itemId: 'commentNum',
										html:'120条点评'
									},
									{
										xtype:'spacer',
										flex:1
									},
									{
										xtype: 'label',
										itemId: 'price',
										cls:'font-remark',
										html:'¥ 450元/人/天'
									}
								]
							},

							{
								xtype : 'image',
								itemId:'resImage',
								mode : 'tag'
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

				var resImage = panel.down('#resImage');
				resImage.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:100px'>");

				var price = panel.down('#price');
				price.setHtml(record.get('price'));

				var commentNum = panel.down('#commentNum');
				commentNum.setHtml(record.get('commentNum') + '条点评');

       			if(record.get('memo') != ''){
	       			var memo = panel.down('#memo');
	       			memo.setHtml(Ext.String.ellipsis(record.get('memo'),70,false));
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

