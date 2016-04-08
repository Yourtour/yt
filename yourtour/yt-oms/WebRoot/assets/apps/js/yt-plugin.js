/**
 * 与服务区请求交互工具包
 * @type {{post: Function, postFormData: Function, get: Function, delete: Function}}
 */
jQuery.Request={
    post:function(url, data, callback){
        var context = $('#context').val() + '/rest';
        $.ajax({
            type: "POST",
            url: context + url,
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (message) {
                if(callback) {
                    callback(message);
                }
            },
            error: function (message) {
                bootbox.alert("系统异常，请稍后再试。");
            }
        });
    },

    postFormData:function(url, formdata, callback){
        var context = $('#context').val() + '/rest';
        $.ajax({
            type: "POST",
            url: context + url,
            data: formdata,
            processData: false,
            contentType: false,
            success: function (message) {
                if(callback) {
                    callback(message.data);
                }
            },
            error: function (message) {
                bootbox.alert("系统异常，请稍后再试。");
            }
        });
    },

    get:function(url, data, success, fail){
        var context = $('#context').val() + '/rest';

        $.get(context + url, data, function(message){
            if(message.errorCode == "0") {
                success(message.data);
            }else{
                if(fail){
                    fail(message);
                }
            }
        } );
    },

    delete:function(url, data, success, fail){
        var context = $('#context').val() + '/rest';

        $.get(context + url, data, function(message){
            if(message.errorCode == "0") {
                success(message.data);
            }else{
                if(fail){
                    fail(message);
                }
            }
        } );
    }
};

/**
 * 日期工具包
 * @type {{format: Function, formatLong: Function}}
 */
jQuery.Date = {
    /**
     * 日期格式化输出
     * @param date
     * @param pattern
     * @returns {string}
     */
    format:function(date, pattern){
        if (date == undefined) {
            date = new Date();
        }
        if (pattern == undefined) {
            pattern = "yyyy-MM-dd";
        }

        var o = {
            "M+": date.getMonth() + 1,
            "d+": date.getDate(),
            "h+": date.getHours(),
            "m+": date.getMinutes(),
            "s+": date.getSeconds(),
            "q+": Math.floor((date.getMonth() + 3) / 3),
            "S": date.getMilliseconds()
        }
        if (/(y+)/.test(pattern)) {
            pattern = pattern.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(pattern)) {
                pattern = pattern.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
            }
        }
        return pattern;
    },

    formatLong:function(l, pattern){
        return this.format(new Date(l), pattern);
    }

};

/**
 * 工具包
 */
jQuery.Utils ={
    isNull:function(str){
        return str == '' || str == 'null' || str == null;
    },

    isNotNull:function(str){
        return ! this.isNull(str);
    },
};

/**
 * 隐藏所有页面
 */
$('#page-content').find("[data-role='page']").each(function(index, page){
    $(page).hide();
});

var pages = [];
jQuery.Page={
    show:function(page, callback){
        if(pages.length > 0){
            var current = pages[pages.length - 1];
            $('#' + current).hide();
        }

        $('#' + page).show();
        pages.push(page);

        if(callback){
            callback();
        }
    },

    back:function(callback){
        var current = pages.pop();
        $('#' + current).hide();
        if(pages.length > 0){
            var next = pages[pages.length - 1];
            $('#' + next).show();
        }

        if(callback){
            callback();
        }
    }
};

/**
 * 表单页面插件，包括如下功能：
 * deserialize：将Json数据填充到表单控件
 * serialize：将表单数据以Json格式返回
 * reset：
 * clear：
 *
 */
(function($){
    var re = /^(?:hidden|color|date|datetime|email|month|number|password|range|search|tel|text|time|url|week)$/i;

    function setValue(element, value){
        var type = element.attr('type');
        var name = element.attr("name");
        if(element.is('input')){
            if(type.match(re)){
                if(element.hasClass('date-picker')){
                    if( typeof value =='number' && value != 0){
                        element.val($.Date.formatLong(value));
                    }
                }else {
                    element.val(value);

                    if (element.hasClass('search-item')) {
                        element.lookup();
                    }
                }
            }else if (type == 'radio'){
                var radios = $("input[name='" + name + "']");
                radios.each(function(){
                    var radio = $(this);
                    if(radio.attr('value') == value){
                        radio.attr('checked', 'checked');
                        radio.parent().addClass("checked");
                    }else{
                        radio.attr("checked",false);
                        radio.parent().removeClass("checked");
                    }
                })
            }else if (type == 'checkbox'){
                if(element.attr("value") == value){
                    element.parent().addClass("checked");
                }
            }else if(type == 'file'){
                element.val("");
            }
        }else if(element.is('select')){
            element.find('option[value="' + value + '"]').attr("selected", true);
        }else if(element.is('textarea')){
            element.val(value);
        }else if(element.is('img')){
            if(value != undefined && value != ''){
                element.attr('src', $.URL.get(value));
            }
        }else{
            element.text(value);
        }
    }

    $.fn.extend({
        /**
         * 将数据填写到界面控件
         */
        deserialize:function(data, action){
            var frm = $(this);
            frm.find('[name]').each(function(){
                var name = this.name || this.id,
                    element = $(this),
                    value;

                if(data) {
                    var bind = element.data('bind');
                    if (bind == undefined || bind == '') {
                        bind = element.attr('name');
                    }

                    value = eval('data.' + bind);
                }

                setValue(element, value);
                if(action == 'view'){
                    if(element.attr("disabled") != 'disabled'){
                        element.attr('disabled', 'disabled');
                    }
                }
            });
        },

        serialize:function(){
            var serializeObj={};
            $(this.serializeArray()).each( function (){
                serializeObj[this.name]= this.value;
            });

            return serializeObj;
        },

        reset:function(){
            $(this)[0].reset();
        },

        clear:function(){
            this.deserialize()
        }
    });
})(jQuery);

/**
 * 图片附件选择插件
 */
(function($){
    $.fn.extend({
        imageSelector:function(options){
            var _this = $(this),
                parent = _this.parent();

            _this.css("display","none");
            if(! parent.hasClass("image-container")){
                parent.addClass("image-container");
            }

            $('<div class="row image-row" style="display:none"></div>').appendTo(parent);
            $('<button type="button" class="btn purple-plum">添加附件</button>').appendTo(parent);
            $("button", parent).on("click", function(){
                _this.selectImage();
            });

            _this.initImage();
        },

        /**
         * 图片初始化，主要显示已经保存的图片
         */
        initImage:function(){
            var me = $(this),
                imageContainer = me.parent(),
                imageRow = $(".image-row", imageContainer),
                imageUrls = me.val();

            imageRow.show();
            if(imageUrls != '') {
                $.each(imageUrls.split(","), function (index, item) {
                    me.createImageCol(item);
                });
            }
        },

        /**
         * 弹出文件选择对话框
         */
        selectImage:function(){
            var _this = $(this),
                imageContainer = _this.parent(),
                imageRow = $(".image-row", imageContainer),
                imageCols = $(".image-col", imageRow);

            if(imageCols.length == 6){
                bootbox.alert("最多只能选择6张图片。");
                return;
            }

            _this.createImageCol();
        },

        /**
         * 显示用户选择的图片
         */
        showImages:function(){
            var _this = $(this),
                imageContainer = _this.parent(),
                button = $("button", imageContainer),
                imageRow = $(".image-row", imageContainer);
                imageCol = imageRow.children(".image-col").last(),
                files = $("input", imageCol)[0].files,

            imageRow.show();
            $.each(files, function(index, file){
                var reader = new FileReader();

                reader.onload = (function(_file) {
                    return function(e) {
                        var image = $('<img class="thumb" src="'+ this.result +'" alt="'+ file.name +'" />');
                        image.appendTo(imageCol);

                        _this.showRemoveButton(imageCol);
                    };
                })(file);
                reader.readAsDataURL(file);
            })
        },

        /**
         * 创建图片显示单元
         */
        createImageCol:function(imageUrl){
            var _this = $(this),
                context = $("#context").val(),
                imageContainer = _this.parent(),
                imageRow = $(".image-row", imageContainer);

            var imageCol;
            if(imageUrl) {
                imageCol = $('<div class="col-md-2 image-col" data-image="' + imageUrl + '"><img class="thumb" src="'+ context + "/" + imageUrl +'" /></div>');
                imageCol.appendTo(imageRow);
                _this.showRemoveButton(imageCol);
            }else{
                imageCol = $('<div class="col-md-2 image-col"><input type="file" style="display:none" id="' + _this.attr("id") + '" name="' + _this.attr("id") + '"></div>');
                imageCol.appendTo(imageRow);
                $("input", imageCol).on("change", function(){
                    _this.showImages();
                }).click();
            }
        },

        /**
         * 显示图片删除按钮
         */
        showRemoveButton:function(imageCol){
            var removeButton = $('<a href="javascript:;" class="btn btn-circle btn-icon-only"><i class="fa fa-times"></i>删除</a>');
            removeButton.appendTo(imageCol);

            removeButton.on("click", function(){
                imageCol.remove();
            })
        },

        /*val:function(){
            var _this = $(this), imageUrls = "";
                imageContainer = _this.parent(),
                imageRow = $(".image-row", imageContainer),
                imageCols = imageRow.children(".image-col");

            $.each(imageCols, function(index, imageCol){
                var _col = $(imageCol);

                if(_col.attr("data-image")) {
                    if (imageUrls != "") imageUrls += ",";

                    imageUrls += _col.data("image");
                }
            })

            return imageUrls;
        }*/
    });
})(jQuery);

/**
 * 对话框
 * @type {{popup: Function}}
 */
jQuery.Dialog={
    popup:function(options){
        bootbox.dialog({
            message: options.message,
            title: options.title,
            buttons:
            {
                "success" :
                {
                    "label" : "<i class='icon-ok'></i> 确定",
                    "className" : "btn-sm btn-success",
                    "callback": function() {
                        options.success($("#message-body"));
                    }
                }
            }
        });
    }
}