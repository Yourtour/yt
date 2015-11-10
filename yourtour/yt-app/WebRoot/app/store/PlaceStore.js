Ext.define('YourTour.store.PlaceStore', {
	extend: 'YourTour.store.AjaxStore',
    requires: [
       'YourTour.model.PlaceModel'
    ],
    config:{
    	model:'YourTour.model.PlaceModel',
    	
    	/*data:[
			{rowKey:'1', parent:'1', name:'安徽', cityCount:'62', cities:[{rowKey:'1.1', name:'黄山'},{rowKey:'1.2', name:'九华山'},{rowKey:'1.3', name:'合肥'},{rowKey:'1.4', name:'大别山'}]},
			{rowKey:'3', parent:'1', name:'重庆', cityCount:'0'},
			{rowKey:'4', parent:'1', name:'福建', cityCount:'62'},
			{rowKey:'5', parent:'1', name:'甘肃', cityCount:'62', cities:[{rowKey:'1.1', name:'黄山'},{rowKey:'1.2', name:'九华山'},{rowKey:'1.3', name:'合肥'},{rowKey:'1.4', name:'大别山'}]},
			{rowKey:'6', parent:'1', name:'广东', cityCount:'0'},
			{rowKey:'7', parent:'1', name:'广西', cityCount:'0'},
			{rowKey:'8', parent:'1', name:'贵州', cityCount:'62'},
			{rowKey:'9', parent:'1', name:'海南', cityCount:'62', cities:[{rowKey:'1.1', name:'黄山'},{rowKey:'1.2', name:'九华山'},{rowKey:'1.3', name:'合肥'},{rowKey:'1.4', name:'大别山'}]},
			{rowKey:'10', parent:'1', name:'河北', cityCount:'0'},
			{rowKey:'11', parent:'1', name:'黑龙江', cityCount:'0'},
			{rowKey:'12', parent:'1', name:'河南', cityCount:'62'},
			{rowKey:'13', parent:'1', name:'湖北', cityCount:'62', cities:[{rowKey:'1.1', name:'黄山'},{rowKey:'1.2', name:'九华山'},{rowKey:'1.3', name:'合肥'},{rowKey:'1.4', name:'大别山'}]},
			{rowKey:'14', parent:'1', name:'湖南', cityCount:'0'},
			{rowKey:'15', parent:'1', name:'江苏', cityCount:'0'},
			{rowKey:'16', parent:'1', name:'江西', cityCount:'62'},
			
			{rowKey:'17', parent:'1', name:'吉林', cityCount:'62', cities:[{rowKey:'1.1', name:'黄山'},{rowKey:'1.2', name:'九华山'},{rowKey:'1.3', name:'合肥'},{rowKey:'1.4', name:'大别山'}]},
			{rowKey:'18', parent:'1', name:'内蒙古', cityCount:'0'},
			{rowKey:'19', parent:'1', name:'宁夏', cityCount:'0'},
			{rowKey:'20', parent:'1', name:'青海', cityCount:'62'},
			
			{rowKey:'21', parent:'1', name:'陕西', cityCount:'62', cities:[{rowKey:'1.1', name:'黄山'},{rowKey:'1.2', name:'九华山'},{rowKey:'1.3', name:'合肥'},{rowKey:'1.4', name:'大别山'}]},
			{rowKey:'22', parent:'1', name:'山东', cityCount:'0'},
			{rowKey:'23', parent:'1', name:'上海', cityCount:'0'},
			{rowKey:'24', parent:'1', name:'山西', cityCount:'62'}
		]*/

    	/*proxy:{
	    	api:{
				 read: YourTour.util.Context.getContext('/place/3/query')
			}
    	}*/
    }
});
