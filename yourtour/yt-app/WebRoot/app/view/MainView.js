Ext.define('YourTour.view.MainView', {
    extend: 'Ext.Panel',
    requires:['Ext.Toolbar'],
    xtype: 'MainView',
    config: {
    	itemId:'MainView',
    	id:'MainView',
    	fullscreen: true,
    	layout:'vbox',
		        
        items: [
        	{	
        		xtype:'panel',
        		itemId:'pageContainer',
        		layout:'card',
        		flex:1
        	},
        	
        	{
        		xtype:'toolbar',
        		docked:'bottom',
        		id:'navigationBar',
        		defaults:{
        			flex:1,
        			height:'55px',
        			pack:'center',
        			padding:'5 0 0 0',
        			style:'color:grey'
        		},
        		baseCls:'navigationBar',
        		
        		ui:'light',
	            items:[
	            	{
	            		itemId:'btnHome', 
		                text:'目的地',
		                iconCls:'home',
		                iconAlign:'top'
		            },
		            {
		                itemId:'btnRoute',
		                text:'旅行',
		                iconCls:'home',
		                iconAlign:'top'
		            },
		            {
		            	itemId:'btnPersonal',
		                text:'个人',
		                iconCls:'user',
		                iconAlign:'top'
		            }
	            ]
        	}
        ]
    }
});
