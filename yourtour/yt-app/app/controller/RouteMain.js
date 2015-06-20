Ext.define('YourTour.controller.RouteMain', {
    extend: 'Ext.app.Controller',
    config: {
        refs: {
        	mainView:'mainview',
        	routeCarousel:'#routeCarousel'
       },
        
        routes:{
        	'/main/route':'showRouteView'
        }
    },
    
    showRouteView:function(){
    	var panels = [];
    	var data = [
    	            {imgUrl:'resources/images/guangdong.jpg', name:'九寨沟、黄龙、峨眉、乐山双飞7日',lineName:'九寨沟、黄龙、峨眉、乐山', feeling:'旅行并不仅仅是你到了一个景点，在那儿拍照留念，当时的天气，当时空气中花粉的味道，还有当时你是否是气喘吁吁汗流浃背，这些都构成了你旅行的一部分，你整个的身体是在参与的，而不仅仅是拍照和看到'},
    	            {imgUrl:'resources/images/beijing.jpg', name:'九寨沟、黄龙、峨眉、乐山双飞7日',lineName:'九寨沟、黄龙、峨眉、乐山', feeling:'旅游，是人们多么喜欢的字眼，它可以使你中断每天周而复始的凡人琐事，对平凡俗气的生活，是一种暂时的解脱。旅游观光，领略山山水水，感受每一处的风土人情，不仅陶冶情操，增长见闻，还能修身养性，解悟释惑。正是：“离家三里远，别是一乡风”。只有走出去，才能享受大自然的乐趣，使自己的胸怀得以舒展，心灵得以净化！旅游，是人们多么喜欢的字眼，它可以使你中断每天周而复始的凡人琐事，对平凡俗气的生活，是一种暂时的解脱。旅游观光，领略山山水水，感受每一处的风土人情，不仅陶冶情操，增长见闻，还能修身养性，解悟释惑。正是：“离家三里远，别是一乡风”。只有走出去，才能享受大自然的乐趣，使自己的胸怀得以舒展，心灵得以净化！'},
    	            {imgUrl:'resources/images/chongqing.jpg', name:'九寨沟、黄龙、峨眉、乐山双飞7日',lineName:'九寨沟、黄龙、峨眉、乐山', feeling:'旅游，是人们多么喜欢的字眼，它可以使你中断每天周而复始的凡人琐事，对平凡俗气的生活，是一种暂时的解脱。旅游观光，领略山山水水，感受每一处的风土人情，不仅陶冶情操，增长见闻，还能修身养性，解悟释惑。正是：“离家三里远，别是一乡风”。只有走出去，才能享受大自然的乐趣，使自己的胸怀得以舒展，心灵得以净化！旅游，是人们多么喜欢的字眼，它可以使你中断每天周而复始的凡人琐事，对平凡俗气的生活，是一种暂时的解脱。旅游观光，领略山山水水，感受每一处的风土人情，不仅陶冶情操，增长见闻，还能修身养性，解悟释惑。正是：“离家三里远，别是一乡风”。只有走出去，才能享受大自然的乐趣，使自己的胸怀得以舒展，心灵得以净化！'},
    	            {imgUrl:'resources/images/yunnan.jpg', name:'九寨沟、黄龙、峨眉、乐山双飞7日',lineName:'九寨沟、黄龙、峨眉、乐山', feeling:'旅游，是人们多么喜欢的字眼，它可以使你中断每天周而复始的凡人琐事，对平凡俗气的生活，是一种暂时的解脱。旅游观光，领略山山水水，感受每一处的风土人情，不仅陶冶情操，增长见闻，还能修身养性，解悟释惑。正是：“离家三里远，别是一乡风”。只有走出去，才能享受大自然的乐趣，使自己的胸怀得以舒展，心灵得以净化！旅游，是人们多么喜欢的字眼，它可以使你中断每天周而复始的凡人琐事，对平凡俗气的生活，是一种暂时的解脱。旅游观光，领略山山水水，感受每一处的风土人情，不仅陶冶情操，增长见闻，还能修身养性，解悟释惑。正是：“离家三里远，别是一乡风”。只有走出去，才能享受大自然的乐趣，使自己的胸怀得以舒展，心灵得以净化！'},
    	            {imgUrl:'resources/images/shanghai.jpg', name:'九寨沟、黄龙、峨眉、乐山双飞7日',lineName:'九寨沟、黄龙、峨眉、乐山', feeling:'旅游，是人们多么喜欢的字眼，它可以使你中断每天周而复始的凡人琐事，对平凡俗气的生活，是一种暂时的解脱。旅游观光，领略山山水水，感受每一处的风土人情，不仅陶冶情操，增长见闻，还能修身养性，解悟释惑。正是：“离家三里远，别是一乡风”。只有走出去，才能享受大自然的乐趣，使自己的胸怀得以舒展，心灵得以净化！旅游，是人们多么喜欢的字眼，它可以使你中断每天周而复始的凡人琐事，对平凡俗气的生活，是一种暂时的解脱。旅游观光，领略山山水水，感受每一处的风土人情，不仅陶冶情操，增长见闻，还能修身养性，解悟释惑。正是：“离家三里远，别是一乡风”。只有走出去，才能享受大自然的乐趣，使自己的胸怀得以舒展，心灵得以净化！'}
    	           ];
    	
    	var tpl = new Ext.XTemplate([
               "<img src='{imgUrl}' style='width:100%'>",
               "<div class='route.content'>名称：{name}</div>",
               "<div class='route.content'>线路：{lineName}</div>",
               "<div class='route.content'>随记：{feeling}</div>",
        ]);
    	
    	Ext.each(data, function(item, index){
    		var tplHtml = tpl.apply(item);
    		
    		panels.push(Ext.create('Ext.Panel',{html:tplHtml, style:'padding:5px',scrollable:{
                direction:'vertical',
                directionLock:true
            }}));
    	});
    	
    	this.getRouteCarousel().removeAll(true,false);
    	this.getRouteCarousel().add(panels);
    	this.getRouteCarousel().setActiveItem(0);
    }
});
