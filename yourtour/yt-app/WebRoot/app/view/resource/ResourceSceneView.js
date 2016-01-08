Ext.define('YourTour.view.resource.ResourceSceneView', {
	extend: 'YourTour.view.resource.ResourceView',
    requires:['Ext.Panel','Ext.Img','YourTour.view.widget.XHeaderBar','YourTour.view.widget.XToolbar', 'YourTour.view.widget.XLabel','YourTour.view.widget.XField'],
    config: {
    	items:[
			{
				xtype:'panel',
				layout:'vbox',
				height:150,
				items:[
					{
						itemId : 'image',
						xtype : 'image',
						mode : 'tag'
					},

					{
						xtype: 'panel',
						layout: 'hbox',
						cls: 'row underline',
						padding: '0 10 0 10',
						docked: 'bottom',
						bottom: 0,
						style: 'background-color:grey;opacity:0.2; width:100%; text-align:center',
						items: [
							{
								xtype:'spacer',
								flex:1
							},

							{
								xtype: 'image',
								src: 'resources/images/raty_32.png',
								mode: 'tag'
							}
						]
					}
				]
			},
	    	
	    	{
	    		xtype:'panel',
	    		layout:'vbox',
		    	items:[
					{
						xtype:'panel',
						layout:'hbox',
						padding:'0 0 0 10',
						cls:'underline icon-memo',
						items:[
							{
								xtype: 'xlabel',
								itemId:'intro',
								cls:'font-medium font-grey multilineinfo',
								flex:1,
								tappable:true,
								margin:'0 5 0 30'
							}
						]
					},
					{
						xtype:'panel',
						layout:'hbox',
						padding:'0 0 0 10',
						cls:'row underline icon-position',
						items:[
							{
								xtype: 'xlabel',
								itemId:'address',
								cls:'font-medium font-grey nav-arrow',
								flex:1,
								tappable:true,
								margin:'0 5 0 30'
							}
						]
					},

			    	{
						xtype:'panel',
						layout:'hbox',
						cls:'row underline icon-phone',
						items:[
							{
							    xtype: 'label',  
							    itemId:'phone',
							    cls:'font-medium font-grey',
							    flex:1,
							    margin:'0 5 0 10'
							}
						]
					},

					{
						xtype:'panel',
						layout:'hbox',
						padding:'0 0 0 10',
						cls:'underline icon-open',
						items:[
							{
								xtype: 'xlabel',
								itemId:'openTime',
								cls:'font-medium font-grey multilineinfo',
								flex:1,
								margin:'0 5 0 30',
							}
						]
					}
	    		]
	    	},
        ]
    },
    
    updateRecord:function(record){
		this.fillRecord(this, record);
    }
});

