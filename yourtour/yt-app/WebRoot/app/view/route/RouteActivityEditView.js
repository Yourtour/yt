Ext.define('YourTour.view.route.RouteActivityEditView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','YourTour.view.widget.XLabel','YourTour.view.widget.XField','YourTour.view.widget.ToggleField','YourTour.view.widget.XHeaderBar'],
    config: {
    	id:'RouteActivityEditView',
    	layout:'vbox',
    	resource:null,
    	scrollable: {
    	    direction: 'vertical'
    	},
    	items:[
			{    
				xtype: 'hiddenfield',
				itemId:'id'	,
				value:'0'
			},
			
			{    
				xtype: 'hiddenfield',
				itemId:'resourceId'
			},
			
			{    
				xtype: 'hiddenfield',
				itemId:'resourceType'
			},
			
    		{    
				xtype: 'xheaderbar',
				title: '日程安排',
				items:[{
                	xtype: "toolbutton", 
                    ui: "normal", 
                	text:'保存',
                	itemId:'btnSave',
                	align:'right'
                }]
			},
			
    		{
    	   		itemId : 'imageUrl',
	    		xtype : 'image',
	    		mode : 'tag'
	    	},
	    	
	    	{
		    	xtype:'panel',
		    	layout:'hbox',
		    	padding:'0 5 0 5',
		    	cls:'row underline bold font-large',
		    	items:[
			    	{
					   xtype:'xlabel',
					   pack:'center',
					   align:'center',
					   itemId:'resName',
					   html : ''
		    		}
	    		]
	    	},
	    	
	    	{
	    		xtype : 'panel',
	    		layout:'hbox',
	    		padding:'0 5 0 5',
	    		cls:'row raty underline',
	    		items:[
		    		{
		    			xtype:'xlabel',
		    			flex:1,
		    			html:'推荐指数'
		    			
		    		},
		    		{
		    			xtype:'image',
		    			src:'resources/images/raty_32.png',
		    			flex:2,
		    			mode:'tag'
		    		},
		    		{
		    			xtype:'xlabel',
		    			flex:1,
		    			html:'综合评分'
		    		},
		    		{
		    			xtype:'image',
		    			flex:2,
		    			mode:'tag',
		    			src:'resources/images/raty_32.png'
		    		}
	    		]
	    	},
	    	
    		{
				xtype:'panel',
				layout:'hbox',
				padding:'0 5 0 5',
				cls:'row underline',
				items:[
					{
						xtype:'xlabel',
						html: '活动名称',
						margin:'0 10 0 0'
					},
					{  
						xtype:'xtextfield', 
	            		flex:1,
	            		itemId: 'title'
					}
				]
			},
			
			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 5 0 5',
				cls:'underline',
				items:[
					{
						xtype:'xlabel',
						html: '活动概述',
						margin:'0 10 0 0'
					},   
					{  
					    xtype: 'textareafield',  
					    itemId:'memo',
					    flex:1,
					    maxRows:4,
					    clearIcon: true
					}
				]
			},
			
    		{
				xtype:'panel',
				layout:'hbox',
				padding:'0 5 0 5',
				cls:'row underline',
				items:[
					{
						xtype:'xlabel',
						html: '开始时间',
						margin:'0 10 0 0'
					},
					{
						xtype:'spacer',
						flex:1
					},
					{  
					    xtype: 'selectfield',  
					    itemId:'startHourSelect',
					    name:'startHourSelect',
					    width:40,
					    options:[
					             {text:'01',value:'01'},{text:'02',value:'02'},{text:'03',value:'03'},{text:'04',value:'04'},{text:'05',value:'05'},{text:'06',value:'06'},
					             {text:'07',value:'07'},{text:'08',value:'08'},{text:'09',value:'09'},{text:'10',value:'10'},{text:'11',value:'11'},{text:'12',value:'12'},
					             {text:'13',value:'13'},{text:'14',value:'14'},{text:'15',value:'15'},{text:'16',value:'16'},{text:'17',value:'17'},{text:'18',value:'18'},
					             {text:'19',value:'19'},{text:'20',value:'20'},{text:'21',value:'21'},{text:'22',value:'22'},{text:'23',value:'23'}
					    ]
					},
					{
						xtype:'xlabel',
						html:'：',
						width:40,
						style:'font-weight:bold',
						margin:'0 5 0 5'
					},
					{  
					    xtype: 'selectfield',  
					    itemId:'startMinSelect',
					    name:'startMinSelect',
					    width:40,
					    options:[
					             {text:'00',value:'00'},{text:'15',value:'15'},{text:'30',value:'30'},{text:'45',value:'45'}
					    ]
					}
				]
			},
			
			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 5 0 5',
				cls:'row underline',
				items:[
					{
						xtype:'xlabel',
						html: '结束时间',
						margin:'0 10 0 0'
					},
					{
						xtype:'spacer',
						flex:1
					},
					{  
					    xtype: 'selectfield',  
					    itemId:'endHourSelect',
					    name:'endHourSelect',
					    width:40,
					    options:[
					             {text:'01',value:'01'},{text:'02',value:'02'},{text:'03',value:'03'},{text:'04',value:'04'},{text:'05',value:'05'},{text:'06',value:'06'},
					             {text:'07',value:'07'},{text:'08',value:'08'},{text:'09',value:'09'},{text:'10',value:'10'},{text:'11',value:'11'},{text:'12',value:'12'},
					             {text:'13',value:'13'},{text:'14',value:'14'},{text:'15',value:'15'},{text:'16',value:'16'},{text:'17',value:'17'},{text:'18',value:'18'},
					             {text:'19',value:'19'},{text:'20',value:'20'},{text:'21',value:'21'},{text:'22',value:'22'},{text:'23',value:'23'}
					    ]
					},
					{
						xtype:'xlabel',
						html:'：',
						width:40,
						style:'font-weight:bold',
						margin:'0 5 0 5'
					},
					{  
					    xtype: 'selectfield',  
					    itemId:'endMinSelect',
					    name:'endMinSelect',
					    width:40,
					    options:[
					        {text:'00',value:'00'},{text:'15',value:'15'},{text:'30',value:'30'},{text:'45',value:'45'}
					    ]
					}
				]
			},
			
            {
            	xtype:'subtitlebar',
            	margin:'5 0 5 0',
            	html:'事项'
            }
        ]
    },
    
    updateResource:function(resource){
    	this.resource = resource;
    }
});

