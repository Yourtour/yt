Ext.define('YourTour.view.route.RouteMainItem', {
    extend: 'Ext.Panel',
    xtype: 'RouteMainItem',
    requires:['Ext.Img'],
    config: {
		record:null,
		carousel:null,

    	layout:'vbox',
		style:'background-color:grey',
        items: [
			{
				xtype:'spacer',
				flex:1
			},
			{
    	   		itemId : 'imageUrl',
	    		xtype : 'image',
				padding:'5 5 5 5',
	    		mode : 'tag'
	    	},
			{
				xtype:'spacer',
				flex:1
			}
        ]
    },

	initialize:function(){
		this.callParent(arguments);

		var me = this;
		me.element.on({
			scope : me,
			tap : function(e, t) {
				me.carousel.fireEvent('tap', me.model);
			}
		});
	},

	updateCarousel:function(carousel){
		this.carousel = carousel;
	},

    updateRecord: function(record) {
        var me = this;
        if(record){
 	 	   var imageUrl = me.down('#imageUrl');
 	 	   imageUrl.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "' style='width:100%;height:100%'>");
 	 	}
     }   
});

