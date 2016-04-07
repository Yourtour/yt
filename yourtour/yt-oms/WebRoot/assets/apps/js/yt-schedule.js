
/**
 *
 * @type {{query: Function, saveDictInfo: Function, loadDictInfo: Function}}
 */
var map, zoom = 13;

jQuery.Schedule = {
    init:function(routeId){
        var me = this,
            pageContainer = $("#schedule-page-container");

        me.showRouteMapView();

        map = new BMap.Map('Page_RouteMapView');//指向map的容器
        map.enableScrollWheelZoom(true);
        this.changePlaceMap("上海");

        $("#Page_RouteSettingView #imageUrl", pageContainer).upload();

        this.loadRouteScheduleInfo(routeId);

        $("#btn-hide", pageContainer).on("click", function(){
            me.hideResourceDetail();
        });

        $("#btn-add", pageContainer).on("click", function(){
            me.addToRouteSchedule();
        });

        $("#schedule-page-header #btn-setting").on("click", function(){
            me.showRouteSettingView();
        });

        $("#schedule-page-header #btn-map").on("click", function(){
            me.showRouteMapView();
        });

        $("#detail-container #btn_detail_hide").on("click", function(){
            var view = $('#Page_ScheduleFormView'),
                detailPanel = $("#detail-container", view);

            detailPanel.animate({right:"-400px"});
        });

        $("#detail-container #btn-schedule").on("click", function(){
            var detailPanel = $("#Page_ScheduleFormView #detail-container"),
                infoResource = $("#info-resource", detailPanel),
                infoSchedule = $("#info-schedule", detailPanel);

            infoResource.hide();
            infoSchedule.show();
        });

        $("#detail-container #btn-save").on("click", function(){
            me.saveScheduleActivityInfo();
        });

        $(".schedule-types>li").on('click', function(){
            var _this = $(this);
            _this.addClass("selected");

            $.each(_this.siblings(), function(index, item){
                $(item).removeClass("selected");
            });

            _this.parent().siblings("a").first().children("span").first().html(_this.html());

            me.getResources();
        });

        $("#detail-container .timepicker").timepicker({
            showSeconds: false
        });

        //添加日程
        pageContainer.delegate(".page-sidebar-menu .icon-schedule-add", "click", function(){
            me.addRouteScheduleDay();
        });

        //选择日程
        pageContainer.delegate(".page-sidebar-menu .icon-schedule-day", "click", function(event){
            me.selectScheduleDay($(this));

            event.stopPropagation();
        });

        //选择日程
        pageContainer.delegate(".page-sidebar-menu .schedule-item .fa-times", "click", function(){
            me.deleteRouteSchedule($(this));
        });

        pageContainer.delegate(".page-sidebar-menu>li", "mouseenter",
            function (event) {
                if(event.type == "mouseenter"){
                    $(".page-sidebar-menu .nav-item.schedule-day .page-actions").remove();

                    if($(this).hasClass("schedule-day")) {
                        var item = $(me.getScheduleDayAction());
                        item.appendTo($(this))
                    }
                }
            }
        );

        pageContainer.delegate(".page-sidebar-menu>li .page-actions .fa-edit", "click",
            function (event) {
                var dayItem = $(this).parentsUntil("ul").filter(".schedule-day");
                me.editScheduleDayInfo(dayItem);
                event.stopPropagation();
            }
        );

        pageContainer.delegate(".page-sidebar-menu>li .page-actions .fa-times", "click",
            function (event) {
                var dayItem = $(this).parentsUntil("ul").filter(".schedule-day");
                me.deleteScheduleDayInfo(dayItem);
                event.stopPropagation();
            }
        );

        pageContainer.delegate(".page-sidebar .page-sidebar-menu .nav-item", "click", function(event){
            me.selectScheduleActivity($(this));

            event.stopPropagation();
        });
    },

    /**
     * 行程设置
     */
    showRouteSettingView:function(){
        var pageContainer = $("#schedule-page-container");

        $.Page.show("Page_RouteSettingView", function(){
            $("#page-container", pageContainer).removeClass("padding-zero");
        });
    },

    showRouteMapView:function(){
        var pageContainer = $("#schedule-page-container");

        $.Page.show("Page_RouteMapView", function(){
            $("#page-container", pageContainer).addClass("padding-zero");
        });
    },

    /**
     * 编辑日程信息
     * @param dayItem
     */
    editScheduleDayInfo:function(dayItem){
        var me = this;
        me.selectScheduleDay(dayItem);
        $.Dialog.popup(
            {
                message:'<div id="message-body"><textarea class="form-control" rows="6" id="memo" name="memo">' + dayItem.data("value").memo + '</textarea></div>',
                title:"日程概述",
                success:function(messageBody){
                    var dayInfo = {memo : $("#memo",messageBody).val()};

                    me.saveRouteScheduleDayInfo(dayInfo);
                }
            }
        )
    },

    /**
     * 编辑日程信息
     * @param dayItem
     */
    deleteScheduleDayInfo:function(dayItem){
        var pageContainer = $("#schedule-page-container"),
            dayInfo = dayItem.data('value');

        $.Request.delete("/rest/oms/route/" + schedule.routeId + "/schedule/DAY/" + dayInfo.id + "/delete", null, function(result){
            item.remove();
        })
    },

    /**
     *
     * @returns {string}
     */
    getScheduleDayAction:function(){
        var html =  '<div class="page-actions" style="position:absolute;z-index:100; bottom:0px; left:0px; height:40px;background-color: #000;opacity: 0.6;">' +
                    '<div class="btn-group">' +
                        '<a href="javascript:;" style="width:33%" class="schedule-day-action btn fa fa-plus">插入</a>' +
                        '<a href="javascript:;" style="width:33%" class="schedule-day-action btn fa fa fa-edit">编辑</a>' +
                        '<a href="javascript:;" style="width:33%" class="schedule-day-action btn fa fa-times">删除</a>' +
                    '</div></div>'

        return html;
    },

    /**
     * 资源查询
     */
    getResources:function(){
        var me = this,pageContainer = $("#schedule-page-container"),
            params = {
                placeId:$(".schedule-places>li.selected").data('value'),
                type:$(".schedule-types>li.selected").data('value'),
                keyword:$("#keyword").val()
            };

        $.Request.post("/rest/oms/resources/query", params, function(result){
            pageContainer.data('resource', result);

            me.showResourceMap()
        })
    },

    /**
     * 装载行程安排信息
     */
    loadRouteScheduleInfo:function(routeId){
        var me = this,
            pageContainer = $('#schedule-page-container');

        $.Request.get("/rest/oms/route/" + routeId, null, function(result){
            pageContainer.data("value", result);

            if(result){
                $("#Page_RouteSettingView", pageContainer).deserialize(result);

                me.addPlaces(result.toPlaces);

                $.each(result.schedules, function(index, schedule){
                    me.addScheduleInfo(index, schedule);
                });

                me.getResources();
            }
        });
    },

    /**
     * 添加行程目的地数据
     * @param toPlaces
     */
    addPlaces:function(toPlaces){
        var me = this,
            pageHeader = $("#schedule-page-header"),
            schedulePlaces = $(".schedule-places", pageHeader);

        var places = toPlaces.split("|"), values;
        $.each(places, function(index, place){
            values = place.split(",");
            if(index == 0){
                schedulePlaces.siblings("a").first().children("span").first().html(values[1]);
            }

            schedulePlaces.append("<li data-value='" + values[0] +"' "+ (index == 0 ? "selected":"") + ">" + values[1] +"</li>");
        });

        $(".schedule-places>li").on('click', function(){
            var _this = $(this);
            _this.addClass("selected");

            $.each(_this.siblings(), function(index, item){
                $(item).removeClass("selected");
            });

            _this.parent().siblings("a").first().children("span").first().html(_this.html());

            if(_this.parent().hasClass("schedule-places")) {
                me.changePlaceMap(_this.html())
            }
        });

        $(".schedule-places>li").first().trigger('click');
    },

    /**
     * 在行程列表中显示日程数据
     * @param index
     * @param schedule
     */
    addScheduleInfo:function(index, schedule){
        if(schedule.parentId == 0){
            this.addScheduleDayInfo(index, schedule);
        }else{
            this.addScheduleActivityInfo(index, schedule);
        }
    },

    /**
     * 添加日程信息
     * @param index
     * @param schedule
     */
    addScheduleDayInfo:function(index, schedule){
        var pageSidebar = $('#schedule-page-container .page-sidebar-menu'),
            addItem  = $('.schedule-add', pageSidebar);

        var itemHtml =  '<li class="nav-item schedule-day" style="position: relative" id="' + schedule.id + '">' +
                            '<a href="javascript:;" class="nav-link nav-toggle">' +
                            '<div class="icon-schedule-day">D' + schedule.index + '</div>' +
                            '<span class="title">D' + schedule.index+'</span>' +
                            '</a>' +
                        '</li>';

        var item = $(itemHtml);
        item.insertBefore(addItem);
        item.data("value",schedule);
    },

    /**
     * 添加日程信息
     * @param index
     * @param schedule
     */
    addScheduleActivityInfo:function(index, schedule){
        var pageContainer = $('#schedule-page-container'),
            parent  = $('.page-sidebar-menu', pageContainer),
            dayItem = $("#" + schedule.parentId, parent);

        var itemHtml =  '<li class="nav-item schedule-item" style="height:70px;">' +
                        '<div style="height:100%; width:80%; float: left;display: inline-block;" class="icon-schedule-' + schedule.type.toLowerCase() +'"><span><h4>' + schedule.name + '</h4><div><small>'+ schedule.startTime +'</small></div></span></div>' +
                        '<a href="javascript:;" style="float:right;display: inline-block;width:10%" class="btn fa fa-times"></a>' +
                        '</li>';

        if(dayItem.children('ul').length == 0){
            dayItem.append($('<ul class="sub-menu"></ul>'));
        }

        dayItem = dayItem.children("ul")[0];
        var item = $(itemHtml);
        item.appendTo(dayItem);
        item.data("value",schedule);
    },

    /**
     * 保存行程日程信息
     */
    saveRouteScheduleDayInfo:function(info){
        var me = this,
            pageSidebar = $('#schedule-page-container .page-sidebar-menu'),
            dayItem = $(".schedule-day.active", pageSidebar);

        if(dayItem.length > 0) {
            var dayInfo = $(dayItem[0]).data("value");
            dayInfo.memo = info.memo;

            $.Request.post("/rest/oms/route/" + dayInfo.routeId + "/schedule/day/save", dayInfo, function (result) {
            })
        }
    },

    /**
     * 添加日程
     */
    addRouteScheduleDay:function(){
        var me = this,
            schedule = {},
            pageSidebar = $('#schedule-page-container .page-sidebar-menu');

        var length = $(".schedule-day", pageSidebar).length;

        schedule.type="DAY";
        schedule.routeId = $("#routeId").val();
        schedule.index = length + 1;

        $.Request.post("/rest/oms/route/" + schedule.routeId + "/schedule/save",schedule,function(result){
           schedule.id = result.data;
           me.addScheduleDayInfo(length + 1, schedule);
        })
    },

    /**
     * 用户点击行程上的日程条目
     * @param dayItem
     */
    selectScheduleDay:function(dayItem){
        var me = this,
            pageSidebar = $('#schedule-page-container .page-sidebar-menu');

        $(".schedule-day", pageSidebar).removeClass("active");

        if(dayItem.hasClass("icon-schedule-day")) { //当前选中节点为日程节点
            dayItem.parent().parent().addClass("active");
        }else{
            dayItem.addClass("active");
        }

        me.showResourceMap();
    },

    /**
     * 用户点击行程上的日程安排条目
     * @param dayItem
     */
    selectScheduleActivity:function(scheduleItem){
        var me = this,
            pageSidebar = $('#schedule-page-container .page-sidebar-menu'),
            schedule = scheduleItem.data("value");

        me.selectScheduleDay($("#" + schedule.parentId, pageSidebar));

        me.showResourceDetail(schedule.resource, schedule);
    },

    /**
     * 地图切换到目的地城市
     * @param place
     */
    changePlaceMap:function(place){
        map.centerAndZoom('歙县', zoom);
    },

    /**
     *
     * @param map
     * @param schedules
     */
    showResourceMap:function(){
        var me = this,schedule,
            pageContainer = $("#schedule-page-container"),
            resources = pageContainer.data('resource'),
            pageSidebar = $('.page-sidebar-menu', pageContainer),
            dayItem = $(".schedule-day.active", pageSidebar);

        map.clearOverlays();

        //选取已经在行程中的资源
        var schedules = $("ul>li", dayItem);

        //在地图上显示还没有被规划的资源
        var found = false;
        $.each(resources.data, function(index, resource){
            found = false;
            $.each(schedules, function(_index, _schedule){
                schedule = $(_schedule).data("value");

                if(resource.id == schedule.resource.id){
                    found = true;
                }
            })

            if(!found){
                me.addMarker(resource);
            }
        });

        //在地图上显示已经规划的资源
        $.each(schedules, function(index, _schedule){
            schedule = $(_schedule).data("value");
            me.addMarker(schedule.resource, schedule);
        })
    },

    /**
     * 添加地图图标
     * @param resource
     * @param schedule
     */
    addMarker:function(resource, schedule){
        var me = this,
            position = resource.position,
            type = resource.type.toLowerCase(),
            pos = position.split(','),
            icon = "http://localhost:8080/yt-oms/assets/apps/icon/icon-map-" + type + (schedule?"-scheduled":"") + ".png";

        var new_point = new BMap.Point(pos[0], pos[1]);
        var myIcon = new BMap.Icon(icon, new BMap.Size(24,24));
        var marker = new BMap.Marker(new_point,{icon:myIcon});  // 创建标注
        marker.addEventListener("click", function () {
            me.showResourceDetail(resource, schedule)
        });

        map.addOverlay(marker);
    },

    /**
     * 删除行程安排
     * @param item
     */
    deleteRouteSchedule:function(item){
        var pageContainer = $("#schedule-page-container"),
            schedule = item.parent().data('value');

        $.Request.delete("/rest/oms/route/" + schedule.routeId + "/schedule/" + schedule.type +"/" + schedule.id + "/delete", null, function(result){
            item.parent().remove();
        })
    },

    /**
     * 保存行程安排信息
     */
    saveScheduleActivityInfo:function(){
        var me = this,scheduleInfo = {},
            pageContainer = $("#schedule-page-container"),
            pageHeader = $("#schedule-page-header"),
            detailPanel = $("#detail-container", pageContainer),
            resource = detailPanel.data('value'),
            timeline = $('#schedule-timeline', pageContainer);

        var selectedItem = $(".timeline-item.active", timeline),
            selectedValue = selectedItem.data("value");

        if(selectedItem.hasClass("schedule-day")){ //当前选中节点是日程型的
            scheduleInfo.parentId = selectedValue.id;
        }else{
            scheduleInfo.parentId = selectedValue.parentId;
        }

        var routeId = $("#routeId", pageContainer).val();
        scheduleInfo.id=$("#id", detailPanel).val();
        scheduleInfo.type = resource.type;
        scheduleInfo.resourceId = resource.id;
        scheduleInfo.routeId = routeId;
        scheduleInfo.name = $("#scheduleName", detailPanel).val();
        scheduleInfo.date = selectedValue.date;
        scheduleInfo.startTime = $("#startTime", detailPanel).val();
        scheduleInfo.endTime = $("#endTime", detailPanel).val();

        var selectedPlace = $(".schedule-places>li.selected", pageHeader);
        scheduleInfo.place = selectedPlace.data('value') + "," + selectedPlace.html();
        scheduleInfo.memo = $("#memo", detailPanel).val();

        $.Request.post("/rest/oms/route/" + routeId + "/schedule/activity/save",scheduleInfo,function(result){
            bootbox.alert("保存成功。", function(){
                me.insertScheduleInfo(scheduleInfo);
            });
        })
    },

    /**
     * 显示资源明细Panel
     * @param marker
     * @param position
     */
    showResourceDetail:function(resource, schedule){
        var view = $('#Page_ScheduleFormView'),
            detailPanel = $("#detail-container", view),
            type = resource.type.toLowerCase(),
            context = $('#context').val(),
            infoResource = $("#info-resource", detailPanel),
            infoSchedule = $("#info-schedule", detailPanel);

        detailPanel.data('value', resource);
        if(schedule){
            $("#id", infoSchedule).html(schedule.id);
            $("#scheduleName", infoSchedule).val(schedule.name);
            $("#startTime", infoSchedule).val(schedule.startTime);
            $("#endTime", infoSchedule).val(schedule.endTime);
            $("#memo", infoSchedule).val(schedule.memo);
            $("#reason", infoSchedule).val(schedule.reason);
            infoResource.hide();
            infoSchedule.show();
        }else {
            $("#image", infoResource).attr('src', context + "/" + resource.imageUrl);
            $("#intro", infoResource).html(resource.intro);
            infoResource.show();
            infoSchedule.hide();
        }

        $(".portlet-title", detailPanel).attr("class", "portlet-title icon-" + type);
        $("#name", detailPanel).html(resource.name);
        $("#address", detailPanel).html(resource.address);

        detailPanel.animate({right:"0px"});
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