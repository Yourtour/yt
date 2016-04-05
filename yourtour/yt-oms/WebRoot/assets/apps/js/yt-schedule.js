
/**
 *
 * @type {{query: Function, saveDictInfo: Function, loadDictInfo: Function}}
 */
var map, zoom = 13;

jQuery.Schedule = {
    init:function(routeId){
        var me = this,
            pageContainer = $("#schedule-page-container");

        map = new BMap.Map('map-container');//指向map的容器
        map.enableScrollWheelZoom(true);
        this.changePlaceMap("上海");

        this.loadRouteScheduleInfo(routeId);

        $("#btn-hide", pageContainer).on("click", function(){
            me.hideResourceDetail();
        });

        $("#btn-add", pageContainer).on("click", function(){
            me.addToRouteSchedule();
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
            me.saveScheduleInfo();
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

        $('#detail-container .timepicker').timepicker({
            showSeconds: false
        });

        //添加日程
        pageContainer.delegate(".page-sidebar-menu .icon-schedule-add", "click", function(){
            me.addScheduleDay();
        });

        //选择日程
        pageContainer.delegate(".page-sidebar-menu .icon-schedule-day", "click", function(){
            me.selectScheduleDay($(this));
        });

        //选择日程
        pageContainer.delegate(".page-sidebar-menu .schedule-item .fa-times", "click", function(){
            me.deleteRouteSchedule($(this));
        });
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
            this.addDayScheduleInfo(index, schedule);
        }else{
            this.addScheduleActivityInfo(index, schedule);
        }
    },

    /**
     * 添加日程信息
     * @param index
     * @param schedule
     */
    addDayScheduleInfo:function(index, schedule){
        var pageSidebar = $('#schedule-page-container .page-sidebar-menu'),
            addItem  = $('.schedule-add', pageSidebar);

        var itemHtml =  '<li class="nav-item schedule-day" id="' + schedule.id + '">' +
                    '<a href="javascript:;" class="nav-link nav-toggle">' +
                    '<div class="icon-schedule-day">D' + schedule.index + '</div>' +
                    '<span class="title">D' + schedule.index+'</span>' +
                    '<span class="selected"></span>' +
                    '<span class="arrow open"></span>' +
                    '</a>' +
                    '</li>';

        var item = $(itemHtml);
        item.insertBefore(addItem);
        pageSidebar.data("value",schedule);
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
     * 添加日程
     */
    addScheduleDay:function(){
        var me = this,
            schedule = {},
            pageSidebar = $('#schedule-page-container .page-sidebar-menu');

        var length = $(".schedule-day", pageSidebar).length;

        schedule.type="DAY";
        schedule.routeId = $("#routeId").val();
        schedule.index = length + 1;

        $.Request.post("/rest/oms/route/" + schedule.routeId + "/schedule/save",schedule,function(result){
           schedule.id = result.data;
           me.addDayScheduleInfo(length + 1, schedule);
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

        //当前选中节点标识为Active， 其他的节点为非Active
        dayItem.parent().parent().addClass("active");

        me.showResourceMap();
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
            pageSidebar = $('.page-sidebar-menu', pageContainer),
            schedule = item.parent().data('value');

        $.Request.delete("/rest/oms/route/" + schedule.routeId + "/schedule/" + schedule.id + "/delete", null, function(result){
            item.parent().remove();
        })
    },

    /**
     * 保存行程安排信息
     */
    saveScheduleInfo:function(){
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

        $.Request.post("/rest/oms/route/" + routeId + "/schedule/save",scheduleInfo,function(result){
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