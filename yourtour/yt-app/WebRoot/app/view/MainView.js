Ext.define('YourTour.view.MainView', {
	extend: 'Ext.NavigationView',
    requires:['Ext.tab.Panel'],
    xtype: 'MainView',
    config: {
    	id:'MainView',
    	
    	navigationBar:false,
    	layout: {
            type: 'card',
            animation: {
                duration: 300,
                easing: 'ease-out',
                type: 'slide',
                direction: 'left'
            }
        },
        
        items: [
        	{	
        		xtype:'tabpanel',
        		itemId:'menuTab',
        		ui:'dark',
        		tabBarPosition:'bottom',
        		tabBar : {
                    defaults: {
                        flex:1
                    }
                },
        	    items:[
					{
						xtype:'HomeMainView',
						itemId:'btnHome', 
					    title:'首页',
					    iconCls:'home',
					    iconAlign:'top'
					},
					{
						xtype:'RouteMainView',
					    itemId:'btnRoute',
					    title:'旅行',
					    iconCls:'home',
					    iconAlign:'top'
					},
					{
						xtype:'PersonalMainView',
						itemId:'btnPersonal',
						title:'个人',
					    iconCls:'user',
					    iconAlign:'top'
					}
        	    ]
        	}
        ]
    },
    
    /*push:function(item){
    	this.add(item);
    	this.setActiveItem(item);
    },
    
    pop:function(){
    	console.log('before');
    	var currentItem = this.getActiveItem(), prevItem = this.getAt(this.items.length - 2);
    	
    	this.remove(currentItem);
    	this.setActiveItem(prevItem);
    	console.log('after');
    }*/
});
