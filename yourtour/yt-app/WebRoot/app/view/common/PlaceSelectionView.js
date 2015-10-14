Ext.define('YourTour.view.common.PlaceSelectionView', {
	extend: 'YourTour.view.widget.XPage',
	requires:['Ext.Panel', 'Ext.DataView','YourTour.view.widget.ToolButton', 'YourTour.view.widget.XHeaderBar','YourTour.view.common.PlaceTypeListItemView', 'YourTour.view.common.PlaceListItemView'],
    xtype:'PlaceSelectionView',
    config: {
    	id:'PlaceSelectionView',
    	layout:'fit',
    	items:[
			{    
				xtype: 'xheaderbar',
				itemId:'headerbar',
				title:'目的地',
				items:[
					{
					   xtype:'toolbutton',
					   itemId:'btnOk',
					   text:'确定',
					   align:'right'
					}
				]
			}, 
			
			{
				xtype:'panel',
				layout:'hbox',
				items:[
					{
						xtype:'dataview',
						itemId:'placeType',
						style:'background:silver',
						useComponents: true,
				        defaultType: 'PlaceTypeListItemView',
			        	scrollable:false,
					    flex:2
					},
					
					{
						xtype:'panel',
					    flex:7,
					    layout:'vbox',
					    items:[
							{
								xtype:'panel',
								itemId:'placeList',
							    flex:7,
								scrollable: {
								    direction: 'vertical',
								    indicators: false	
								}
							},
							{
								xtype:'panel',
								itemId:'selection',
								style:'height:50px',
								layout:'hbox',
								defaults:{
									margin:'10 0 10 10'
								}
							}
					    ]
					}
				]
			}
    	]
    }
});