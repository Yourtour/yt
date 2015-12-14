Ext.define('YourTour.view.member.MemberDataItem', {
	extend: 'Ext.dataview.component.DataItem',
    xtype:'MemberDataItem',
    config: {
	    layout:'hbox',
	    padding:'5 5 5 5',
	    height:75,
	    cls:'underline',
        items: [
			{
				xtype:'panel',
				layout:'vbox',
				items:[
					{
						   xtype:'spacer',
						   flex:1
					},    
					{
						xtype:'image',
						itemId:'imageUrl',
						mode:'tag'
					}, 
			       {
					   xtype:'spacer',
					   flex:1
			       },
				]
			},    
			
			{
				xtype:'panel',
				layout:'vbox',
				margin:'0 10 0 10',
				flex:1,
				items:[
			       {
			    	   xtype:'label',
			    	   itemId:'nickName'
			       }
				]
			},
			
			{
				xtype:'panel',
				layout:'vbox',
				items:[
					{
						   xtype:'spacer',
						   flex:1
					},    
					{  
						xtype : 'image',
	    				mode : 'tag',
	    				src:'resources/icons/icon_add_48.png',
		              	itemId:'btnAdd'  
					},
			       {
					   xtype:'spacer',
					   flex:1
			       },
				]
			}
        ]
    },
    
    initialize : function(){
    	var me = this;
    	
    	var dataview = me.dataview || me.getDataview();
    	
    	var btnAdd = me.down('#btnAdd');
    	btnAdd.on('tap', function(){
    		dataview.fireEvent('addTap', dataview, me.getRecord());
    	});
    },
    
    updateRecord: function(record) {
        var me = this;
        
        if(record){
        	var imageUrl = me.down('#imageUrl');
  	 	   	imageUrl.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "' style='width:100%; max-height:150px'>");
  	 	   
  	 	   	var nickName = me.down('#nickName');
  	 	   	nickName.setHtml(record.get('nickName'));
        }
    }   
});

