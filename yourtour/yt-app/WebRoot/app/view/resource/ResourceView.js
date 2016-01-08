Ext.define('YourTour.view.resource.ResourceView', {
	extend: 'Ext.Panel',
    requires:['Ext.Panel','Ext.Img','YourTour.view.widget.XHeaderBar','YourTour.view.widget.XToolbar', 'YourTour.view.widget.XLabel','YourTour.view.widget.XField'],
    config: {
    	layout:'vbox',
		scrollable:{
			direction: 'vertical',
			indicators: false
		},
    },
    
    fillRecord:function(view, record){
    	if(record != null){
			var image = view.down('#image');
			image.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "' style='width:100%; max-height:150px'>");

			var address = view.down('#address');
			address.setHtml(record.get('address'));

			var openTime = view.down('#openTime');
			openTime.setHtml(record.get('openTime'));

			var intro = view.down('#intro');
			intro.setHtml(record.get('intro'));
    	}
    }
});

