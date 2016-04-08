jQuery.Route = {
    initRecommend:function(){
        $.Page.show("Page_RouteRecommendListView");

        var me = this,
            listview = $("#Page_RouteRecommendListView");

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
            me.openScheduleWindow();
        });

        this.queryRecommendRoutes();
    },

    /**
     * 列表查询
     */
    queryRecommendRoutes:function(){
        var dt = $("#Page_RouteRecommendListView #datatable_route")
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
     * 删除行程
     */
    deleteRouteInfo:function(){
        var me = this;

        $("#datatable_route").delete(function(ids){
            $.Request.delete("/oms/route/" + ids + "/delete", null, function(result){
                me.query();
            })
        })
    },

    /**
     * 保存行程基本信息
     */
    saveRouteInfo:function(url){
        var me = this,
            formview = $("#Page_RouteSettingView"),
            form = $('#RouteForm', formview),
            formdata = new FormData() ;

        formdata.append("route", JSON.stringify(form.serialize()));

        var images = $("input[name='imageUrl']", formview);
        $.each(images, function(index, image){
            var _image = $(image);
            if(_image.attr("type") == "file") {
                var file = _image[0].files[0];
                if (file) {
                    formdata.append("imageUrl", file);
                }
            }
        });

        $.Request.postFormData(url,formdata,function(result){
            bootbox.alert("保存成功。", function(){
                $.Page.back(function(){
                    me.query();
                });
            });
        })
    },

    /**
     * 新启窗口进行行程制定
     */
    openScheduleWindow:function(){
        var me = this,
            view = $('#Page_ScheduleFormView'),
            mapPanel = $("#mapPanel", view),
            detailPanel = $("#detailPanel", view);
        $("#datatable_route").select(function(routeId){
            var context = $("#context").val();
            window.open(context + "/view/oms/Schedule.html?type=recommend&routeId=" + routeId,'newwindow');
        }, "请选择需要规划的行程。");
    }
};

