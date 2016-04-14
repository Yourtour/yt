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
                    callback(message);
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
        return str == '' || str == 'null' || str == null || str == undefined;
    },

    isNotNull:function(str){
        return ! this.isNull(str);
    },
};

/**
 * 隐藏所有页面
 */
$('.page-content').find("[data-role='page']").each(function(index, page){
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
            if(element.hasClass("wysihtml5")){
                $('.wysihtml5-sandbox', element.parent()).contents().find('body').html(value);
            }
        }else if(element.is('img')){
            if(value != undefined && value != ''){
                element.attr('src', $.URL.get(value));
            }
        }else{
            element.text(value);
        }

        element.trigger("valueChanged");
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

                //setValue.apply(element, element, value);
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

            //对于下拉多选框的处理
            var fields = $("select[multiple]",$(this));
            $.each(fields, function(index, field){
                var _field = $(field), values=""
                    parent = _field.parent(),
                    selected = $("ul.select2-selection__rendered>li.select2-selection__choice");
                if(selected.length > 0){
                    $.each(selected, function(index, item){
                        if(index > 0) values +='|';

                        $.each(_field.children("option"), function(oindex, oitem){
                            if($(oitem).text() == $(item).attr("title")){
                                values = values + $(oitem).attr("value") + "," + $(oitem).text();
                            }
                        })
                    })
                }

                serializeObj[_field.attr("id")] = values;
            });

            //对于图片文件的处理
            fields = $("input.file-field",$(this));
            $.each(fields, function(index, field){
                var _field = $(field);
                serializeObj[_field.attr("id")] = _field.getImages()
            });

            //对于弹出式多选控件取值
            fields = $("input.multi-value",$(this));
            $.each(fields, function(_index, field){
                var me = $(this),values="",
                    spans = me.siblings("div.form-control").children("span");

                $.each(spans, function(i, span){
                    var _span = $(span);
                    if(i > 0) values += "|";

                    values += _span.attr("value") + "," + _span.text();
                })

                serializeObj[me.attr("id")] = values;
            })

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

            _this.css("display","none").addClass("file-field");
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

        getImages:function(){
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
        }
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
    },

    alert:function(message){
        bootbox.alert(message);
    },

    confirm:function(message, callback){
        bootbox.confirm(message, function(result){
            if (result) {
                callback();
            }
        })
    }
},

/**
 * 目的地选择框市区
 */
(function($){
    function open(value, callback){
        var html = '<form id="PlaceForm" class="form-horizontal form-place" role="form">';
        html +=  '<div class="form-group"><label for="name" class="col-md-3 control-label">地区</label><div class="col-md-9"><select id="district" class="form-control"></select></div></div>';
        html +=  '<div class="form-group"><label for="name" class="col-md-3 control-label">省/市/州</label><div class="col-md-9"><select id="state" class="form-control"></select></div></div>';
        html +=  '<div class="form-group"><label for="name" class="col-md-3 control-label">市</label><div class="col-md-9"><div class="input-group select2-bootstrap-append" style="width:100%"><select id="city" class="form-control select2" multiple></select></div></div></div>';
        html +=  '</form>';

        bootbox.dialog({
            message:html,
            title: "目的地选择",
            buttons:
            {
                "success" :
                {
                    "label" : "<i class='icon-ok'></i> 确定",
                    "className" : "btn-sm btn-success",
                    "callback": function() {
                        var placeForm = $(".form-place"),values=""
                            city = $("#city", placeForm);
                        parent = city.parent(),
                            selected = $("ul.select2-selection__rendered>li.select2-selection__choice");
                        if(selected.length > 0){
                            $.each(selected, function(index, item){
                                if(index > 0) values +='|';

                                $.each(city.children("option"), function(oindex, oitem){
                                    if($(oitem).text() == $(item).attr("title")){
                                        values = values + $(oitem).attr("value") + "," + $(oitem).text();
                                    }
                                })
                            })
                        }

                        callback(values);
                    }
                }
            }
        });

        var placeForm = $(".form-place"),
            district = $("#district", placeForm),
            state = $("#state", placeForm),
            city = $("#city", placeForm);

        $.each(value, function(index, item){
            if(item.parent == "#") {
                district.append('<option value="' + item.id + '">' + item.text + '</option>');
            }
        });

        state.on("click", function(){
            var me = $(this),
                parent = me.data("parent"),
                districtId = district.val();

            if(parent && parent == districtId){
                return;
            }

            me.empty();
            city.empty();

            $.each(value, function(index, item){
                if(item.parent == districtId) {
                    me.append('<option value="' + item.id + '">' + item.text + '</option>');
                }
            });

            me.data("parent", districtId);
        });

        city.select2({placeholder:"选择行程标签",width:"100%"});
        state.on("change", function(){
            var me = $(this),
                stateId = me.val();

            city.empty();
            $.each(value, function(index, item){
                if(item.parent == stateId) {
                    city.append('<option value="' + item.id + '">' + item.text + '</option>');
                }
            });
        });
    };

    $.fn.place=function(){
        var me = $(this),
            parent = me.parent(),
            placeContainer = $('<div class="form-control"></div>'),
            searchButton = $('<span class="input-group-addon"><i class="fa fa-search"></i></span>');

        me.css("display", "none").addClass("multi-value");
        parent.addClass("place-selector");

        placeContainer.appendTo(parent);
        placeContainer.delegate("span", "click", function(){ //选择项删除
            $(this).remove();
        });

        searchButton.appendTo(parent);
        $.Request.get("/oms/places",null, function(result){
            searchButton.on("click", function(){
                open(result, function(values){
                    if(values){
                        var array = values.split("|");
                        $.each(array, function(index, _value){
                            var places = _value.split(",");
                            var span = $('<span value="' + places[0] + '"><i class="fa fa-times"></i>' + places[1] + "</span>");
                            span.appendTo(placeContainer);
                        })
                    }
                });
            });
        });
    }
})(jQuery);

/**
 *关联行程选择
 */
(function($){
    var refresh = function(){
        var me = this,
            parent = me.parent();

        var ids = "";
        $(".route-item", parent).each(function(index, item){
            if(index > 0) ids += ",";
            ids += $(item).data("value").id;
        });
        me.val(ids);
    };

    var methods = {
        /**
         * 初始化
         * @param options
         * @returns {*|HTMLElement}
         */
        init: function (options) {
            var me = this,
                parent = me.parent(),
                button = $('<button type="button" class="btn purple-plum">关联行程</button>'),
                searchListView = $("#Page_Route_SearchListView"),
                btnSelect = $("#btn_ok", searchListView),
                btnBack = $("#btn_back", searchListView),
                dt = $("#datatable_routes", searchListView);

            button.appendTo(parent);

            button.on("click", function(){
                $.Route.search.init();
            });

            parent.delegate(".route-delete", "click", function(){
                methods['remove'].apply(me, [$(this)]);
            });

            btnSelect.on("click", function(){
                dt.select(function(routeIds, items){
                    methods['setValue'].apply(me, [items]);

                    $.Page.back();
                }, "选择需要关联的行程.");
            });

            btnBack.on("click", function(){
                $.Page.back();
            })

            return $(this);
        },

        /**
         * 显示关联行程
         * @param routes
         */
        setValue:function(routes){
            if(!routes || routes == null) return;

            var me = this,
                parent = me.parent(),
                button = $('button', parent);
            $.each(routes, function(index, item){
                var control = $('<div class="form-control route-item" style="margin-bottom:10px"><span>' + item.name + '</span><span class="pull-right route-delete"><i class="fa fa-times"></i></span></div>');
                control.data("value", item);
                control.insertBefore(button);
            });

            refresh.apply(this);
        },

        /**
         * 删除关联行程
         * @param item
         */
        remove:function(item){
            item.parent().remove();

            refresh.apply(this);
        },

        removeAll:function(){
            var me = this,
                parent = me.parent();

            $(".route-item", parent).each(function(index, item){
                item.remove();
            })
        }
    };

    $.fn.routeSelector = function() {
        var method = arguments[0];

        if(methods[method]) {
            method = methods[method];
            arguments = Array.prototype.slice.call(arguments, 1);
        } else if( typeof(method) == 'object' || !method ) {
            method = methods.init;
        } else {
            $.error( 'Method ' +  method + ' does not exist on jQuery.pluginName' );
            return this;
        }

        return method.apply(this, arguments);
    }
})(jQuery);


(function($){
    var refresh = function(){
        var me = this,
            parent = me.parent();

        var ids = "";
        $(".route-item", parent).each(function(index, item){
            if(index > 0) ids += ",";
            ids += $(item).data("value").id;
        });
        me.val(ids);
    };

    var methods = {
        /**
         * 初始化
         * @param options
         * @returns {*|HTMLElement}
         */
        init: function (options) {
            var me = this,
                parent = me.parent(),
                button = $('<button type="button" class="btn purple-plum">关联行程</button>'),
                searchListView = $("#Page_Route_SearchListView"),
                btnSelect = $("#btn_ok", searchListView),
                btnBack = $("#btn_back", searchListView),
                dt = $("#datatable_routes", searchListView);

            button.appendTo(parent);

            button.on("click", function(){
                $.Route.search.init();
            });

            parent.delegate(".route-delete", "click", function(){
                methods['remove'].apply(me, [$(this)]);
            });

            btnSelect.on("click", function(){
                dt.select(function(routeIds, items){
                    methods['setValue'].apply(me, [items]);

                    $.Page.back();
                }, "选择需要关联的行程.");
            });

            btnBack.on("click", function(){
                $.Page.back();
            })

            return $(this);
        },

        /**
         * 显示关联行程
         * @param routes
         */
        setValue:function(routes){
            if(!routes || routes == null) return;

            var me = this,
                parent = me.parent(),
                button = $('button', parent);
            $.each(routes, function(index, item){
                var control = $('<div class="form-control route-item" style="margin-bottom:10px"><span>' + item.name + '</span><span class="pull-right route-delete"><i class="fa fa-times"></i></span></div>');
                control.data("value", item);
                control.insertBefore(button);
            });

            refresh.apply(this);
        },

        /**
         * 删除关联行程
         * @param item
         */
        remove:function(item){
            item.parent().remove();

            refresh.apply(this);
        },

        removeAll:function(){
            var me = this,
                parent = me.parent();

            $(".route-item", parent).each(function(index, item){
                item.remove();
            })
        }
    };

    $.fn.routeSelector = function() {
        var method = arguments[0];

        if(methods[method]) {
            method = methods[method];
            arguments = Array.prototype.slice.call(arguments, 1);
        } else if( typeof(method) == 'object' || !method ) {
            method = methods.init;
        } else {
            $.error( 'Method ' +  method + ' does not exist on jQuery.pluginName' );
            return this;
        }

        return method.apply(this, arguments);
    }
})(jQuery);

/**
 * 带检索功能的输入框
 */
(function($){
    var methods = {
        /**
         * 初始化
         * @param options
         * @returns {*|HTMLElement}
         */
        init: function (options) {
            var me = this,
                parent = me.parent(),
                defaults = {single:true};

            $.extend(true, defaults, options);

            if(! me.hasClass("search-input")){
                me.addClass("search-input");
            }

            if(!parent.hasClass("search-field")){
                parent.addClass("search-field");
            }

            parent.delegate("ul>li", "click", function(){
                var li = $(this),
                    span = $("<span class='search-selected-item'>" + li.data("value").nickName + "</span>");

                span.appendTo($(".search-field-result", parent));
                $("<i class='fa fa-times'></i>").appendTo(span);
                span.data("value", li.data(value));
            }).delegate("span.search-selected-item", "click", function(){
                $(this).remove();
            });

            $("<div style='position:relative; padding-top:0px; border:none' class='form-control'><div class='search-field-container'></div></div>").appendTo(parent);

            var container = $(".search-field-container", parent),
                dropdown = $("<div class='search-field-dropdown'><ul class='search-field-list'></ul></div>");
            $("<div class='search-field-result form-control'><i class='caret'></i></div>").appendTo(container);
            dropdown.appendTo(container);
            me.prependTo(dropdown);
            dropdown.css("display", "none");

            $(".search-field-result", container).on("click", function(){
                if(dropdown.css("display") == "block"){
                    dropdown.css("display", "none");
                }else {
                    dropdown.css("display", "block");
                    $(".search-input",dropdown).focus();
                }
            });

            var ul = $(".search-field-list", parent);
            $(".search-input", container).on("keypress blur", function(event){
                var input = $(this);
                if(event.type == "keypress") {
                    if (event.keyCode == "13") {
                        ul.html("");
                        if (input.val() != "") {
                            var url = defaults.url + "?key=" + input.val();
                            $.Request.post(url, null,
                                function (result) {
                                    var datas = result.data;
                                    $.each(datas, function (index, data) {
                                        var li = $("<li>" + data.nickName + "</li>");
                                        li.appendTo(ul);
                                        li.data("value", data);
                                    })
                                }
                            );
                        }

                        ul.css("display", "block");
                    }
                }else if(event.type == "blur"){
                    setTimeout(function () {
                        dropdown.css("display", "none");
                    }, 1000);
                }
            });

            return $(this);
        },

        getValue:function(){
            var me = $(this),
                parent = me.parent();

            var spans = parent.find("span.search-selected-item"), values = "";
            spans.each(function(index, span){
                var _span = $(span);
                if(index > 0) values += ",";

                values += _span.data("value") + "," + _span.text();
            })

            return values;
        },

        /**
         *
         * @param value
         */
        setValue:function(value){
            if($.Utils.isNull(value)) return;

            var me = $(this),
                parent = me.parent(),
                values = value.split("|");

            $.each(values, function(index, value){
                var span = $("<span class='search-selected-item'>" + value.split(",")[1] + "</span>");
                span.insertBefore($(".search-input", parent));
                $("<i class='fa fa-times'></i>").appendTo(span);

                span.data("value", value.split("|")[0]);
            })
        },

        reset:function(){
            var me = $(this),
                parent = me.parent();

            var spans = parent.find("span.search-selected-item"), values = "";
            spans.each(function(index, span){
                $(span).remove();
            })
        }
    };

    $.fn.searchInput = function() {
        var method = arguments[0];

        if(methods[method]) {
            method = methods[method];
            arguments = Array.prototype.slice.call(arguments, 1);
        } else if( typeof(method) == 'object' || !method ) {
            method = methods.init;
        } else {
            $.error( 'Method ' +  method + ' does not exist on jQuery.searchInput' );
            return this;
        }

        return method.apply(this, arguments);
    }
})(jQuery);

