/**
 * 与服务区请求交互工具包
 * @type {{post: Function, postFormData: Function, get: Function, delete: Function}}
 */
jQuery.Request={
    post:function(url, data, callback){
        var context = $('#context').val();
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
        var context = $('#context').val();
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
        var context = $('#context').val();

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
        var context = $('#context').val();

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
    }
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
                var name = this.name || this.id;
                var element = $(this);

                var value;

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
