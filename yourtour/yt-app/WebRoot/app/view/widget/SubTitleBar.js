Ext.define('YourTour.view.widget.SubTitleBar', {
    extend: 'Ext.Panel',
    xtype: 'subtitlebar',
    config: {
    	buttons:[],
    	
    	layout:{
    		type:'hbox'
    	},
    	cls:'subtitlebar',
    	items:[
    		{
    			xtype:'label',
    			style:'width:10px !important; background:#FF6100',
    			html:'&nbsp;&nbsp;&nbsp;'
    		},
    		
    		{	xtype:'panel',
    			layout:'hbox',
    			flex:1,
    			margin:'0 0 0 5',
    			style:'background:#4A708B',
    			items:[
		    		{
		    			padding:'0 0 0 10',
		    			itemId: 'title',
		    			xtype:'label',
		    			flex:1,
		    			html:''
		    		}
		    	]
    		}
    	]
    },
    
    constructor: function(args){
        this.callParent(arguments);
    },
    
    setHtml:function(newHtml){
    	var label = this.down('#title');
        label.setHtml(newHtml);
    },
    
    setButtons:function(buttons){
    	if(buttons.length == 0) return;
    	this.getAt(1).add(buttons);
    }
});

