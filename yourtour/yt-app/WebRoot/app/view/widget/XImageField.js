Ext.define('YourTour.view.widget.XImageField', {
	extend: 'YourTour.view.widget.XLabel',
    xtype: 'ximagefield',
    config:{
        cls: 'x-ximage',

        fileName:null,
        active:false,
        captured:false
    },

    updateActive:function(active){
        this.active = active;
    },

    setActive:function(active){
        this.active = active;
    },

    getActive:function(){
        return this.active;
    },

    updateCaptured:function(captured){
        this.captured = captured;
    },

    setCaptured:function(captured){
        this.captured = captured;
    },

    getCaptured:function(){
        return this.captured;
    },

    setData:function(data){
        var me = this,
            base64 = 'data:image/jpeg;base64,' + data;
            styles = {};
        styles['background-image'] = 'url(' + base64 + ')';
        styles['background-repeat'] = 'no-repeat';
        styles['background-position'] = 'center center';
        styles['background-size'] = '100% 100%';
        me.setStyle(styles);

        this.data = data;
    },

    getAsBase64: function () {
        return 'data:image/jpeg;base64,' + this.data;
    },

    updateFileName: function (fileName) {
        this.fileName = fileName;
    },

    getFileName: function () {
        return this.fileName;
    }
});

