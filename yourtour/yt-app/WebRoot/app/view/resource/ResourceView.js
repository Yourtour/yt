Ext.define('YourTour.view.resource.ResourceView', {
	extend: 'Ext.Panel',
    requires:['Ext.Panel','Ext.Img','YourTour.view.widget.XHeaderBar','YourTour.view.widget.XToolbar', 'YourTour.view.widget.XLabel','YourTour.view.widget.XField'],
    config: {
    	layout:'vbox'
    },
    
    fillRecord:function(view, record){
    	if(record != null){
			var image = view.down('#image');
			image.setHtml("<img src='" + YourTour.util.Context.getImageResource(record.get('imageUrl')) + "' style='width:100%; max-height:150px'>");

			var address = view.down('#address');
			address.setText(record.get('address'));

			var openTime = view.down('#openTime');
			openTime.setText(record.get('openTime'));

			var phone = view.down('#phone');
			phone.setText(record.get('phone'));

			var intro = view.down('#intro');
			intro.setText(record.get('intro'));
    	}
    }
});

