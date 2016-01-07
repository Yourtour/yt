Ext.define('YourTour.view.member.MemberItemView', {
	extend: 'Ext.dataview.component.DataItem',
    requires:['Ext.Panel', 'YourTour.view.widget.XLabel'],
    xtype: 'MemberItemView',
    config: {
      	layout:'hbox',
      	height:50,
      	cls:'underline',
      	padding:5,
        items: [
		   	{
   				itemId : 'imageUrl',
				xtype : 'image',
				mode : 'tag'
    		},
    		
    		{
				itemId:'nickName',
				margin:'0 10 0 10',
				style:'line-height:40px',
				flex:1,
				xtype:'xlabel'
			},
			
			{
   				itemId : 'imageRole',
				xtype : 'image',
				mode : 'tag'
    		},
    		
    		{
   				itemId : 'imageDel',
				xtype : 'image',
				hidden:true,
				src:'resources/icons/icon-delete.png',
				mode : 'tag'
					
    		}
		]
    },
    
    initialize : function(){
    	var me = this;
    	
    	var dataview = me.dataview || me.getDataview();
    	var imageDel = me.down('#imageDel');
    	imageDel.on('tap', function(){
    		dataview.fireEvent('itemdelete', dataview, me.getRecord());
    	});
    },
    
    updateRecord:function(record){
    	var me = this;
    	
       	if(record){
       	   var imageUrlEl = me.down('#imageUrl');
	 	   imageUrlEl.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "' style='width:40px; max-height:40px'>");
	 	   
	 	   var nickName = me.down('#nickName');
	 	   nickName.setHtml(record.get('nickName'));
	 	   
	 	   var role = record.get('role');
	 	   var roleEl = me.down('#imageRole');
	 	   if(role == 'leader'){
	 		  roleEl.setHtml("<img src='resources/icons/icon_leader.png'/>");
	 	   }else if(role == 'expert'){
	 		  roleEl.setHtml("<img src='resources/icons/icon_expert.png'/>");
	 	   }	   
	 	}
    }
});

