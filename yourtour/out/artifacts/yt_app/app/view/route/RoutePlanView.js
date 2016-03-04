Ext.define('YourTour.view.route.RoutePlanView', {
    extend: 'YourTour.view.widget.XPage',
    requires:['Ext.form.Panel', 'YourTour.view.widget.XSpacer', 'YourTour.view.widget.XDateField', 'YourTour.view.widget.XHeaderBar', 'YourTour.view.widget.XLabel', 'YourTour.view.widget.XField', 'Ext.field.Text'],
    config: {
    	id:'RoutePlanView',
    	defaults:{
    		labelWidth: '20'
		},
        items: [
            {    
				xtype: 'xheaderbar',
				title: '行程计划'
			},
			
			{  
			    xtype: 'hiddenfield',  
			    itemId:'id',
			    value:'0'
			},

			{
				xtype:'xfield',
				itemId:'name',
				label:{
					text:'行程名称'
				},
				field:{
					placeHolder:'请输入行程名称',
					ifNull: '请简单描述您个人',
					editable:{
						enable:true
					}
				}
			},
			{
				xtype:'xfield',
				itemId:'budget',
				label:{
					text:'行程预算'
				},
				field:{
					placeHolder:'请输入行程预算',
					editable:{
						enable:true
					}
				}
			},

			{
				xtype:'xdatefield',
				itemId:'routeDate',
				single:false,
				label:{
					text:'行程日期'
				},
				field:{
					placeHolder:'请选择行程日期安排',
					editable:{
						enable:true
					}
				},

				listeners:{
					getter: function (field, value, options) {
						if(value == null || value == ''){
							options.result = 0;
						}else{

							options.result = value;
						}

						return true;
					},

					setter:function(field, value, options){
						console.log(vlaue);
					}
				}
			},

			{
				xtype:'xspacer'
			},

			{
				xtype:'xplacefield',
				itemId:'fromPlace',
				label:{
					text:'出发地'
				},
				field:{
					placeHolder:'请选择行程出发地',
					editable:{
						enable:true
					}
				}
			},

			{
				xtype:'xplacefield',
				itemId:'toPlaces',
				label:{
					text:'目的地'
				},
				field:{
					placeHolder:'请选择行程目的地',
					editable:{
						enable:true
					}
				}
			},
			{
				xtype:'xspacer'
			},

			{
				xtype:'xfield',
				itemId:'adultNum',
				label:{
					text:'成人人数'
				},
				field:{
					placeHolder:'请输入成人人数',
					editable:{
						enable:true
					}
				}
			},

			{
				xtype:'xfield',
				itemId:'childNum',
				label:{
					text:'儿童人数'
				},
				field:{
					placeHolder:'请输入儿童人数',
					editable:{
						enable:true
					}
				}
			},

			{
				xtype:'xfield',
				itemId:'olderNum',
				label:{
					text:'老人人数'
				},
				field:{
					placeHolder:'请输入老人人数',
					editable:{
						enable:true
					}
				}
			},

			{
				xtype: 'xtoolbar',
				docked: 'bottom',
				itemId: 'toolbar',
				items: [
					{
						xtype:'spacer',
						flex:1
					},
					{
						xtype: 'xbutton',
						itemId: 'btnRefer',
						text: '达人推荐',
						icon:'resources/icons/24/icon_favorite.png'
					},{
						xtype:'xvline',
						flex:1
					} ,
					{
						xtype: 'xbutton',
						itemId: 'btnResevation',
						text: '预约定制',
						icon:'resources/icons/24/icon_booking.png'
					},{
						xtype:'xvline',
						flex:1
					} ,
					{
						xtype: 'xbutton',
						itemId: 'btnCutomized',
						text: '行程定制',
						icon:'resources/icons/24/icon_customize.png'
					},
					{
						xtype:'spacer',
						flex:1
					}
				]
			}
		]
    }
});

