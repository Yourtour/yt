
/**
 *
 * @type {{query: Function, saveDictInfo: Function, loadDictInfo: Function}}
 */
jQuery.RouteRecommend = {
    init:function(){
        $.Page.show("Page_RouteListView");

        var me = this,
            listview = $("#Page_RouteListView"),
            formview = $("#Page_RouteFormView"),
            scheduleformview = $("#Page_ScheduleFormView");

        $('.date-picker', formview).datepicker({
            format: 'yyyy-mm-dd',
            clearBtn:true
        });

        $("#btn_add", listview).on('click', function(){
            me.createRouteInfo();
        });

        $("#btn_edit", listview).on('click', function(){
            me.loadRouteInfo();
        });

        $("#btn_delete", listview).on('click', function(){
            me.deleteRouteInfo();
        });

        $("#btn_plan", listview).on('click', function(){
            me.loadRouteScheduleInfo();
        });

        //保存按钮事件
        $("#btn_save", formview).on('click', function(){
            me.saveRouteInfo()
        });

        //取消按钮事件
        $(" #btn_cancel", formview).on('click', function(){
            $.Page.back();
        });

        $("#btn-hide", scheduleformview).on("click", function(){
            me.hideResourceDetail();
        });

        $("#btn-add", scheduleformview).on("click", function(){
            me.addToRouteSchedule();
        });

        this.query();
    },

    createRouteInfo:function(){
        var formview = $("#Page_RouteFormView"),
            form = $("#RouteForm", formview);

        $.Page.show("Page_RouteFormView", function(){
            form.clear();

            $('#id', form).val(-1);
        });
    },

    /**
     * 列表查询
     */
    query:function(){
        var dt = $("#Page_RouteListView #datatable_route")
        dt.dataTable({
            "aoColumns": [
                {"mData": "id", "sClass":"center", "sWidth": "5%", "mRender": function (data, type, row) {
                    return "<input type='checkbox' class='checkboxes' value='" + data + "'/>";
                }},
                {
                    "mData": "name",
                    "sWidth": "25%"
                },

                {
                    "mData": "lineName",
                    "sWidth": "25%"
                },

                {
                    "mData": "toPlaces",
                    "sWidth": "10%",
                    "mRender": function (data, type, row) {
                        var places = "";
                        if(data){
                            var arrPlaces = data.split("|");
                            $.each(arrPlaces, function(index, place){
                                if(index > 0) places += ",";

                                places += place.split(",")[1];
                            })
                        }
                        return places;
                    }
                },

                {
                    "mData": "startDate",
                    "sWidth": "15%",
                    "mRender": function (data, type, row) {
                        var value = "";
                        if(data > 0) {
                            value += $.Date.formatLong(row.startDate);
                            value += ' 到 ' + $.Date.formatLong(row.endDate);
                        }
                        return value;
                    }
                },

                {
                    "mData": "duration",
                    "sWidth": "10%",
                    "sClass":"center",
                    "mRender": function (data, type, row) {
                        return data + "天";
                    }
                },

                {
                    "mData": "tags",
                    "sWidth": "20%"
                }
            ]
        });
    },

    showSearchView:function(callback){
        $.Page.show("Page_Route_SearchListView");

        var me = this,
            searchview = $("#Page_Route_SearchListView");

        $("#btn_ok", searchview).on('click', function(){
            $("#datatable_routes").select(function(ids){
                callback(ids);
            },"请选择行程。");
        });

        $("#btn_back", searchview).on('click', function(){
            $.Page.back();
        })
    },

    search:function(){
        var dt = $("#Page_Route_SearchListView #datatable_routes");

        dt.dataTable({
            "aoColumns": [
                {"mData": "id", "sClass":"center", "sWidth": "5%", "mRender": function (data, type, row) {
                    return "<input type='checkbox' class='checkboxes' value='" + data + "'/>";
                }},

                {
                    "mData": "imageUrl",
                    "sWidth": "95%"
                }
            ]
        });
    },

    /**
     * 获取行程信息
     */
    loadRouteInfo:function(){
        var me = this,
            formview = $("#Page_RouteFormView"),
            form = $("#RouteForm", formview),
            dt = $("#Page_RouteListView #datatable_route");

        dt.edit(function(id){
            $.Request.get("/rest/oms/route/" + id, null, function(result){
                $.Page.show("Page_RouteFormView", function(){
                    form.deserialize(result);
                });
            })
        })
    },

    deleteRouteInfo:function(){
        var me = this;

        $("#datatable_route").delete(function(ids){
            $.Request.delete("/rest/oms/route/" + ids + "/delete", null, function(result){
                me.query();
            })
        })
    },

    /**
     * 保存行程基本信息
     */
    saveRouteInfo:function(){
        var me = this,
            formview = $("#Page_RouteFormView"),
            form = $('#RouteForm', formview),
            formdata = new FormData() ;

        formdata.append("route", JSON.stringify(form.serialize()));

        var images = $("input[type='file']");
        $.each(images, function(index, image){
            var file = $(image)[0].files[0];
            if(file) {
                formdata.append("imageUrl", file);
            }
        })

        $.Request.postFormData("/rest/oms/route/recommend/save",formdata,function(result){
            bootbox.alert("保存成功。", function(){
                $.Page.back(function(){
                    me.query();
                });
            });
        })
    },

    /**
     * 装载行程安排信息
     */
    loadRouteScheduleInfo:function(){
        var me = this,
            view = $('#Page_ScheduleFormView'),
            mapPanel = $("#mapPanel", view),
            detailPanel = $("#detailPanel", view);
        $("#datatable_route").select(function(routeId){
            $.Page.show("Page_ScheduleFormView",function(){
                mapPanel.show();
                detailPanel.hide();

                var map = new BMap.Map('map-container');//指向map的容器
                map.enableScrollWheelZoom(true);
                map.centerAndZoom('上海', 11);

                $.Request.get("/rest/oms/route/" + routeId + "/schedules", null, function(result){
                    if(result){
                        $.each(result, function(index, schedule){
                            me.showScheduleInfo(index, schedule);
                        })

                        $(".timeline-badge", view).on("click", function(){
                            me.selectTimelineItem($(this));
                        });

                        //$(".schedule-day",view).first().trigger('click');
                    }
                });
            });
        }, "请选择需要规划的行程。");
    },

    /**
     * 在行程列表中显示日程数据
     * @param index
     * @param schedule
     */
    showScheduleInfo:function(index, schedule){
        var me = this,
            scheduleFormView = $("#Page_ScheduleFormView");
            timeline = $('#schedule-timeline', scheduleFormView);

        var itemClass = "schedule-" + schedule.type.toLowerCase(),
            html =  '<div class="timeline-item" id="' + schedule.id + '">' +
                    '<div class="timeline-badge schedule ' + itemClass + '" >';
        if(schedule.type == 'DAY') {
            html += '<h2> D' + schedule.index + '</h2>';
        }

        html +='</div>' +
               '<div class="timeline-body inactive">' +
               '<div class="timeline-body-head">';

        if(schedule.type == "DAY"){
            html += '<div class="timeline-body-head-caption">' +
                        '<a href="javascript:;" class="timeline-body-title font-blue-madison">Andres Iniesta</a>' +
                        '<span class="timeline-body-time font-grey-cascade">Replied at 7:45 PM</span>' +
                    '</div>';
        }else{
            html += '<div class="timeline-body-head-caption">' +
                        '<a href="javascript:;" class="timeline-body-title font-blue-madison">Andres Iniesta</a>' +
                        '<span class="timeline-body-time font-grey-cascade">Replied at 7:45 PM</span>' +
                    '</div>';
        }

        html += '</div></div></div>';

        $(html).appendTo(timeline);
    },

    /**
     * 显示选择的资源信息
     * @param schedule
     */
    selectTimelineItem:function(schedule){
        var me = this,
            view = $('#Page_ScheduleFormView'),
            timeBody = schedule.siblings(".timeline-body"),
            mapPanel = $("#mapPanel", view),
            detailPanel = $("#detailPanel", view),
            map = new BMap.Map('map-container'),
            positions = "121.50715,31.242905|121.511578,31.240468".split("|"),
            pos, _item;

        timeBody.removeClass("inactive");

        $.each($(".timeline-badge", view), function(index, item){
            _item = $(item);
            _item.removeClass("active");
            if(_item.parent().attr("id") != schedule.parent().attr("id")){
                _item.siblings(".timeline-body").addClass("inactive");
            }
        });

        schedule.addClass("active");

        if(schedule.hasClass('schedule-day')) {
            $.each($(".schedule-item", view), function (index, item) {
                $(item).hide();
            });

            //当前日程的处理
            schedule.parent().nextUntil(".schedule-day").show();
        }

        detailPanel.hide();
        mapPanel.show();

        map.enableScrollWheelZoom(true);
        map.centerAndZoom('上海', 11);

        $.each(positions, function(index, position) {
                pos = position.split(',');
                var new_point = new BMap.Point(pos[0], pos[1]);
                var marker = new BMap.Marker(new_point);  // 创建标注
                marker.addEventListener("click",function(){
                    me.showResourceDetail(marker, position)
                });
                map.addOverlay(marker);              // 将标注添加到地图中
        });
    },

    /**
     * 显示资源明细Panel
     * @param marker
     * @param position
     */
    showResourceDetail:function(marker, position){
        var view = $('#Page_ScheduleFormView'),
            mapPanel = $("#mapPanel", view),
            detailPanel = $("#detailPanel", view);

        mapPanel.hide();
        detailPanel.slideDown(1000);
    },

    /**
     * 隐藏资源明细Panel
     * @returns {boolean}
     */
    hideResourceDetail:function(){
        var view = $('#Page_ScheduleFormView'),
            mapPanel = $("#mapPanel", view),
            detailPanel = $("#detailPanel", view);

        detailPanel.hide();
        mapPanel.slideDown(1000);
    },

    addToRouteSchedule:function(){
        this.hideResourceDetail();
    }
};

