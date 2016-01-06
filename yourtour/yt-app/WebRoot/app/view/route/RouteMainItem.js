Ext.define('YourTour.view.route.RouteMainItem', {
    extend: 'Ext.Panel',
    xtype: 'RouteMainItem',
    requires:['Ext.Label', 'Ext.Img','Ext.Toolbar', 'YourTour.view.widget.MarkedLabel', 'YourTour.view.widget.XLabel', 'YourTour.view.widget.XField'],
    config: {
    	record:null,
    	carousel:null,
    	layout:'vbox',
        items: [
			{
    	   		itemId : 'imageUrl',
	    		xtype : 'image',
	    		mode : 'tag',
	    		height:150
	    	},
			{
			   xtype:'markedlabel',
			   pack:'center',
			   align:'center',
			   itemId:'routeName',
			   html : ''
		    },
		    
		    {
			   xtype:'toolbar',
			   itemId:'toolbuttons',
			   padding:5,
			   layout:'hbox',
			   ui:'light',
			   defaults:{
			   		flex:1,
			   		pack:'center',
			   		align:'center'
			   },
			   items:[
			   		{
		              	xtype : 'image',
	    				mode : 'tag',
	    				src:'resources/icons/icon_route.png',
		              	itemId:'btnRoute'
		           	},
		           	{
		              	xtype : 'image',
	    				mode : 'tag',
	    				src:'resources/icons/icon_partner.png',
		              	itemId:'btnMember'  
		            },
		           	{
		              	xtype : 'image',
	    				mode : 'tag',
	    				src:'resources/icons/icon_expense.png',
		              	itemId:'btnCharge'  
		            } ,
		           	{
		              	xtype : 'image',
	    				mode : 'tag',
	    				src:'resources/icons/icon_comment.png',
		              	itemId:'btnComment'  
		            }     
			   ]
		    },

			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 0 0 10',
				cls:'underline icon_memo',
				items:[
					{
						xtype: 'xlabel',
						itemId:'lineName',
						cls:'font-medium font-grey multilineinfo',
						flex:1,
						margin:'0 5 0 30'
					}
				]
			},

			{
				xtype:'panel',
				layout:'hbox',
				padding:'0 0 0 10',
				cls:'row underline icon_time',
				items:[
					{
						xtype: 'xlabel',
						itemId:'time',
						cls:'font-medium font-grey',
						flex:1,
						margin:'0 5 0 30'
					}
				]
			},

			{
				xtype:'panel',
				itemId:'impressionEdit',
				layout:'hbox',
				padding:'0 0 0 10',
				cls:'underline icon_impression',
				items:[
					{
						xtype: 'xlabel',
						itemId:'impression',
						cls:'font-medium font-grey multilineinfo',
						flex:1,
						tappable:true,
						margin:'0 5 0 30'
					}
				]
			}
        ]
    },
    
    initialize : function(){
    	this.callParent(arguments);
    	
    	var btnRoute = this.down('#btnRoute');
    	btnRoute.addListener('tap', this.onRouteBtnTap, this);
    	
    	var btnMember = this.down('#btnMember');
    	btnMember.addListener('tap', this.onMemberBtnTap, this);
    	
    	var impressionEdit = this.down('#impressionEdit');
    	impressionEdit.addListener('tap', this.onImpressionEdit, this);
    	
    	var image = this.down('#imageUrl');
    	image.addListener('tap', this.onImageTap, this);
    },
    
    onRouteBtnTap:function(){
    	this.carousel.fireEvent("onRouteTap", this.record);
    },
    
    onMemberBtnTap:function(){
    	this.carousel.fireEvent("onMemberTap", this.record);
    },
    
    onImageTap:function(){
    	this.carousel.fireEvent("onImageTap", this.record);
    },
    
    onImpressionEdit:function(){
    	this.carousel.fireEvent("onImpressionEdit", this.record);
    },
    
    setCarousel:function(carousel){
    	this.carousel = carousel;
    },
    
    setRecord: function(record) {
        var me = this;
        me.record = record;
        if(record){
 	 	   var imageUrl = me.down('#imageUrl');
 	 	   imageUrl.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "' style='width:100%; max-height:150px'>");
 	 	   
 	 	   var name = me.down('#routeName');
 	 	   name.setHtml(record.get('name'));
 	 	   
 	 	   var time = me.down('#time');
 	 	   time.setHtml(record.get('startDate') +'-' + record.get('endDate') + '  合计：' + record.get('duration')+'天');
 	 	   
 	 	   var lineName = me.down('#lineName');
 	 	   lineName.setHtml(record.get('lineName'));
 	 	   
 	 	   var impression = me.down('#impression');
			if(record.get('impression') == '' || record.get('impression') == null){
				impression.setHtml('赶快记录下你的旅行印象吧.........');
			}else {
				impression.setHtml(Ext.String.ellipsis(record.get('impression'), 70, false));
			}
 	 	}
     }   
});

