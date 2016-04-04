
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
        pageContainer.delegate("div.timeline-badge.icon-schedule-add", "click", function(){
            me.addScheduleDay();
        });

        //选择日程
        pageContainer.delegate(".timeline-badge:not(.icon-schedule-add)","click", function(){
            me.selectTimelineItem($(this).parent());
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
                    me.appendScheduleInfo(index, schedule);
                });

                $(".schedule-day",pageContainer).first().trigger('click');

                me.getResources();
            }

            me.appendAddButton();
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
    appendAddButton:function(){
        var me = this,
            pageContainer = $('#schedule-page-container'),
            timeline = $('#schedule-timeline', pageContainer),
            itemClass = "schedule-add",
            iconClass = "icon-" + itemClass,
            html =  '<div class="timeline-item schedule '+ itemClass + '"  >' +
                '<div class="timeline-badge ' + iconClass + '">';

        html +='</div><div class="timeline-body "><div class="timeline-body-head">';
        html += '</div></div></div>';

        var timeItem = $(html);
        timeItem.appendTo(timeline);
    },

    /**
     * 在行程列表中显示日程数据
     * @param index
     * @param schedule
     */
    appendScheduleInfo:function(index, schedule){
        var me = this,
            pageContainer = $('#schedule-page-container'),
            timeline = $('#schedule-timeline', pageContainer);

        var timeItem = $(this.getScheduleTimeItem(schedule));
        timeItem.appendTo(timeline);
        timeItem.data("value",schedule);
    },

    /**
     * 在行程列表中显示日程数据
     * @param index
     * @param schedule
     */
    insertScheduleInfo:function(schedule){
        var pageContainer = $('#schedule-page-container'),
            timeline = $('#schedule-timeline', pageContainer),
            selectedItem = $(".timeline-item.active", timeline);

        var timeItem = $(this.getScheduleTimeItem(schedule));

        var days = selectedItem.nextAll(".schedule-day");
        if(days.length > 0){
            timeItem.prependTo($(days[0]));
        }else{
            timeItem.appendTo(timeline);
        }

        timeItem.data("value",schedule);
    },

    /**
     * 添加日程
     */
    addScheduleDay:function(){
        var me = this, schedule = {},
            pageContainer = $('#schedule-page-container'),
            timeline = $('#schedule-timeline', pageContainer),
            addItem = $('.timeline-item.schedule-add', timeline);

        var length = $(".schedule-day", timeline).length;

        schedule.id=length + 1;
        schedule.type="DAY";
        schedule.index = length + 1;

        var timeItem = $(this.getScheduleTimeItem(schedule));
        timeItem.insertBefore(addItem);
        timeItem.data("value",schedule);
    },

    /**
     * 创建时间轴节点
     * @param schedule
     */
    getScheduleTimeItem:function(schedule){
        var itemClass = "schedule-" + schedule.type.toLowerCase(),
            iconClass = "icon-" + itemClass,
            html =  '<div class="timeline-item schedule '+ itemClass + '" id="' + schedule.id + '"data-parent="' + schedule.parentId + '">' +
                '<div class="timeline-badge ' + iconClass + '">';

        if(schedule.type == 'DAY') {
            html += '<h2> D' + schedule.index + '</h2>';
        }

        html +='</div><div class="timeline-body "><div class="timeline-body-head">';

        if(schedule.type == "DAY"){
            html += '<div class="timeline-body-head-caption"></div>';
        }else{
            html += '<div class="timeline-body-head-caption">' +
                '<a href="javascript:;" class="timeline-body-title font-blue-madison">' + schedule.name + '</a>' +
                '<span class="timeline-body-time font-grey-cascade">' + schedule.startTime + '-' + schedule.endTime + '</span>' +
                '</div>';
        }

        html += '</div></div></div>';

        return html;
    },

    /**
     * 显示选择的资源信息
     * @param schedule
     */
    selectTimelineItem:function(schedule){
        var me = this,
            pageContainer = $('#schedule-page-container'),
            mapPanel = $("#mapPanel", pageContainer),
            detailPanel = $("#detailPanel", pageContainer),
            _item;

        //当前选中节点标识为Active， 其他的节点为非Active
        schedule.addClass("active");
        $.each(schedule.nextAll(), function(index, item){
            _item = $(item);
            _item.removeClass("active");
        });

        $.each(schedule.prevAll(), function(index, item){
            _item = $(item);
            _item.removeClass("active");
        });

        //如果当前选中节点为日程型的，同时展开该日程下的安排节点
        if(schedule.hasClass('schedule-day')) {
            $.each($(".timeline-item:not(.schedule-day, .schedule-add)", pageContainer), function (index, item) {
                $(item).hide();
            });

            //当前日程的处理
            $.each(schedule.nextUntil(".schedule-day"), function(index, item){
                var _item = $(item);
                _item.show();
            });

            me.showResourceMap();
        }

        mapPanel.show();
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
            timeline = $('#schedule-timeline', pageContainer),
            selectedItem = $(".timeline-item.active", timeline),
            selectedValue = selectedItem.data("value");

        map.clearOverlays();

        //选取已经在行程中的资源
        var schedules = $(".timeline-item[data-parent='" + selectedValue.id + "']");

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
            $("#name", infoSchedule).html(schedule.name);
            $("#memo", infoSchedule).html(schedule.memo);
            $("#reason", infoSchedule).html(schedule.reason);
            infoResource.hide();
            infoSchedule.show();
        }else {
            $("#caption", infoResource).attr("class", "caption icon-" + type);
            $("#name", infoResource).html(resource.name);
            $("#address", infoResource).html(resource.address);
            $("#image", infoResource).attr('src', context + "/" + resource.imageUrl);

            infoResource.show();
            infoSchedule.hide();
        }

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

;