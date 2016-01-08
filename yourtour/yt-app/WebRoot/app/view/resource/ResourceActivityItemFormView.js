Ext.define('YourTour.view.resource.ResourceActivityItemFormView', {
    extend: 'YourTour.view.widget.XPage',
    xtype:'ResourceActivityItemFormView',
    config: {
    	itemId:'ResourceActivityItemFormView',
    	layout:'card',
        items: [
			{
				xtype:'panel',
				style:'background-color:grey',
				layout:'vbox',
				items:[
					{
						xtype: 'xheaderbar',
					},

					{
						xtype:'spacer',
						flex:1
					},
					{
						margin:'5 5 10 5',
						itemId : 'image',
						xtype : 'image',
						mode : 'tag'
					},
					{
						xtype:'panel',
						docked:'bottom',
						bottom:0,
						layout:'vbox',
						padding:'5 0 10 5',
						style: 'background-color:grey;opacity:0.8; width:100%; text-align:center',
						items:[
							{
								xtype:'label',
								itemId:'memo',
								cls:'multilineinfo font-grey font-medium'
							}
						]
					},
					{
						xtype:'spacer',
						flex:1
					},

					{
						xtype: 'toolbar',
						docked: 'bottom',
						items: [
							{
								xtype: 'spacer',
								flex:1
							},{
								xtype: 'button',
								text: '取消日程',
								ui: 'normal',
								baseCls:'button',
								itemId: 'btnCancel',
								padding:'0 40 0 40'
							}
						]
					}
				]
			}
        ]
    }
});

