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
						xtype: 'xmultifield',
						itemId:'intro',
						icon:'icon-memo',
						size:140
					},

					{
						xtype: 'xfield',
						itemId:'address',
						tappable:true,
						icon:'icon-position',
					},

					{
						xtype: 'xfield',
						itemId:'phone',
						icon:'icon-phone',
						tappable:true,
					},

					{
						xtype: 'xmultifield',
						itemId:'openTime',
						icon:'icon-open',
					}
	    		]
	    	},
        ]
    },
    
    updateRecord:function(record){
		this.fillRecord(this, record);
    }
});

