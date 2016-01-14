Ext.define('YourTour.view.route.RouteScheduleListDataItem', {
    extend: 'Ext.dataview.component.DataItem',
    xtype: 'RouteScheduleListDataItem',
    requires:['Ext.Label','Ext.field.Select','Ext.Panel'],
    config: {
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
    					cls:'title strong',
						padding:'0 0 0 50'
    				}
    			]
    		},
    		
    		{
    			itemId:'prepareItemPanel',
    			xtype:'panel',
    			hidden:true,
    			layout:'vbox',
				cls:'scheduleItem',
    			items:[
					{
						xtype:'label',
						itemId:'title',
						cls:'title icon-todo',
						padding:'0 0 0 50'
					},

					{
						xtype:'label',
						itemId:'memo',
						cls:'content memo',
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
    					flex:1,
    					cls:'title strong',
						padding:'0 0 0 50'
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
		    					cls:'content strong'
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
       var dataview = this.dataview || this.getDataview();
       if(record){
    	   var panel;
       	   var type = record.get('type');
       		if(type == 'Provision'){
       			panel = me.down('#preparePanel');
       			panel.show();
       			
       			var title = panel.down('#title');
       			title.setHtml(record.get('title'));
				title.addCls('icon-prepare');
       		}else if(type == 'ProvisionItem'){
       			panel = me.down('#prepareItemPanel');
       			panel.show();
       			
       			var title = panel.down('#title');
       			title.setHtml(record.get('title'));
       			
				var memo = panel.down('#memo');
				memo.setHtml(record.get('memo'));
       		}else if(type == 'Schedule'){
       			panel = me.down('#dayPanel');
       			panel.show();
       			
       			var title = panel.down('#title');
       			title.setHtml(record.get('title'));
				title.addCls('icon-time-32');
       		}else{
       			panel = me.down('#dayItemPanel');
       			panel.show();

				var title = panel.down('#title');
				title.setHtml(record.get('title'));
				var resourceType = record.get('resourceType');
				if(resourceType == 'SCENE'){
					title.addCls('icon-scene');
				}else if(resourceType == 'HOTEL'){
					title.addCls('icon-hotel');
				}else if(resourceType == 'FOOD'){
					title.addCls('icon-food');
				}else if(resourceType == 'traffic'){
					title.addCls('icon_restaurant');
				}

				var resImage = panel.down('#resImage');
				resImage.setHtml("<img src='" + record.get('imageUrl') + "' style='width:100%; max-height:100px'>");

				var price = panel.down('#price');
				price.setHtml(record.get('price'));

				var commentNum = panel.down('#commentNum');
				commentNum.setHtml(record.get('commentNum') + '条点评');

				var memo = panel.down('#memo');
				memo.setHtml(Ext.String.ellipsis(record.get('memo'),70,false));
				memo.show();

       			var time = panel.down('#time');
       			time.setHtml(record.get('startTime') + '至' + record.get('endTime'));
       		}
       }
    }   
});

