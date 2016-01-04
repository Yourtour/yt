Ext.define('YourTour.view.route.RouteRecommendIntroductionView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.Panel','Ext.Img','Ext.DataView','YourTour.view.widget.HSpacer', 'YourTour.view.widget.XPanel', 'YourTour.view.widget.XButton','YourTour.view.widget.XField','YourTour.view.widget.XLabel', ,'YourTour.view.widget.XHeaderBar', 'YourTour.view.line.LineResourceItem'],
    config: {
    	id:'LineIntroductionView',
		scrollable:{
			direction: 'vertical',
		 	indicators: false
		},
      	layout:'vbox',
        items: [
        	{    
				xtype: 'xheaderbar',
				title:'行程介绍'
			},

			{
				itemId : 'image',
				xtype : 'image',
				mode : 'tag',
				height:150,
			},

			{
			   xtype:'xlabel',
			   pack:'center',
			   align:'center',
			   itemId:'name',
				padding:'0 10 0 10',
			   cls:'row underline font-bold font-medium',
			   html : ''
			},
			    
			{
				xtype:'panel',
				layout:'hbox',
				cls:'row underline font-medium font-grey raty',
				padding:'0 10 0 10',
				items:[
					{
						xtype:'xlabel',
						margin:'0 10 0 0',
						html:'推荐指数:'
					},

					{
						xtype:'image',
						src:'resources/images/raty_32.png',
						flex:1,
						mode:'tag'
					},

					{
						xtype:'xlabel',
						margin:'0 10 0 0',
						html:'评价指数:'
					},
					{
						xtype:'image',
						src:'resources/images/raty_32.png',
						flex:1,
						mode:'tag'
					}
				]
			},
					   		
			{
				xtype:'panel',
				layout:'hbox',
				cls:'row underline font-medium font-grey',
				padding:'0 10 0 10',
				items:[
					{
						xtype:'xlabel',
						margin:'0 10 0 0',
						html:'推荐月份:'
					},
					{
						xtype:'xfield',
						flex:1,
						html:'7、8、9月'
					},
					{
						xtype:'xlabel',
						flex:1
					},
					{
						xtype:'xlabel',
						flex:1
					}
				]
			},

			{
				xtype:'hspacer'
			},

			{
				xtype:'xlabel',
				itemId:'featureTitle',
				html:'线路特点',
				tappable:true,
				padding:'0 10 0 10',
				cls:'row underline font-grey font-medium nav_arrow'
			},

			{
				xtype:'xlabel',
				itemId:'feature',
				padding:'0 10 0 10',
				cls:'multilineinfo font-grey font-medium',
			},

			{
				xtype:'hspacer'
			},

			{
				xtype:'xlabel',
				itemId:'reasonTitle',
				html:'推荐理由',
				tappable:true,
				padding:'0 10 0 10',
				cls:'row underline font-grey font-medium nav_arrow'
			},

			{
				xtype:'xlabel',
				itemId:'reason',
				padding:'0 10 0 10',
				cls:'multilineinfo font-grey font-medium'
			},

			{
				xtype:'hspacer'
			},
		    {
		    	xtype:'dataview',
		    	itemId:'resources',
		    	cls:'space-top',
				scrollable:null,
				flex:1,
		    	useComponents:true,
		    	defaultType:'LineResourceItem'
		    },

			{
				xtype: 'toolbar',
				docked: 'bottom',
				items: [
					{
						xtype: 'button',
						text: '联系达人',
						ui: 'normal',
						padding:'0 20 0 20',
						itemId: 'btnConsult'
					},{
						xtype: 'spacer',
						flex:1
					},{
						xtype: 'xbutton',
						text: '开始规划',
						padding:'0 20 0 20',
						ui: 'normal',
						itemId: 'btnPlan'
					}
				]
			}
        ]
    }
});

