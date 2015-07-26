Ext.define('YourTour.view.widget.XBack', {
    extend: 'Ext.Panel',
    xtype: 'xback',
    requires:['Ext.Img','Ext.Label'],
    config: {
    	title:null,
    	layout:'hbox',
    	pack:'center',
    	items:[
	    	{
	    		xtype:'image',
	    		mode:'tag',
	    		itemId:'backImg',
            	src:'resources/icons/icon_back.png'
	    	},
	    	{
	    		xtype:'label',
	    		itemId:'title'
	    	}
	    	
    	]
    },
    
    initialize : function(){
    	this.callParent(arguments);
    	
    	var me = this;
    	
    	var backImg = me.down('#backImg');
    	backImg.element.on({
			scope : me,
			tap : function(e, t) {
				me.fireEvent('tap', me, e, t);
			}
		});
    },
    
    setTitle:function(title){
    	var me = this;
		me.title = title;
		
    	var titleEl = me.down('#title');
    	if(titleEl != null){
    		titleEl.setHtml(title);
    	}
    }
});

