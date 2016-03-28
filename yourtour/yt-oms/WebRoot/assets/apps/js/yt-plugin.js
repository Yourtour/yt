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

jQuery.Utils ={
    isNull:function(str){
        return str == '' || str == 'null' || str == null;
    },

    isNotNull:function(str){
        return ! this.isNull(str);
    }
},

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
                element.val(value);

                if(element.hasClass('search-item')){
                    element.lookup();
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
