jQuery.Route = {
    recommend:{
        init:function(){
            $.Page.show("Page_RecommendListView");

            var me = this,
                listview = $("#Page_RecommendListView");

            $("#btn_add", listview).on('click', function(){
                me.createRouteInfo();
            });

            //
            $("#btn_clone", listview).on('click', function(){
                me.cloneRouteInfo();
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

            $("#btn_publish", listview).on('click', function(){
                me.publishRouteInfoes();
            });

            $("#btn_withdraw", listview).on('click', function(){
                me.withdrawRouteInfoes();
            });

            this.query();
        },

        /**
         * 列表查询
         */
        query:function(){
            console.log('recommend');
            var dt = $("#Page_RecommendListView #datatable_route")
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
                        "mData": "status",
                        "sWidth": "20%",
                        "mRender": function (data, type, row) {
                            if (data == 'DRAFT') {
                                return "起草";
                            } else if (data == 'PUBLISHED') {
                                return "发布";
                            } else if (data == 'WITHDRAW') {
                                return "撤回";
                            }
                        }
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
         * 新建行程
         */
        createRouteInfo:function(){
            var context = $("#context").val();
            window.open(context + "/view/oms/Schedule.html?type=recommend&routeId=-1",'newwindow');
        },

        /**
         * 新启窗口进行行程制定
         */
        openScheduleWindow:function(){
            $("#datatable_route").select(function(routeId){
                var context = $("#context").val();
                window.open(context + "/view/oms/Schedule.html?type=recommend&routeId=" + routeId,'newwindow');
            }, "请选择需要规划的行程。");
        },

        /**
         * 行程复制
         */
        cloneRouteInfo:function(){
            var type = $("#Page_RecommendListView #type").val(),
                context = $("#context").val();
            $("#datatable_route").select(function(routeId){
                $.Request.get("/oms/route/" + routeId + "/" + type + "/clone", null, function(result){
                    window.open(context + "/view/oms/Schedule.html?type=" + type + "/&routeId=" + result,'newwindow');
                })
            }, "请选择需要复制的行程。");
        },

        /**
         * 行程发布
         */
        publishRouteInfoes:function(){
            $("#datatable_route").select(function(routeId){
                $.Request.get("/oms/route/" + routeId + "/publish", null, function(result){
                })
            }, "请选择需要发布的行程。");
        },

        withdrawRouteInfoes:function(){
            $("#datatable_route").select(function(routeId){
                $.Request.get("/oms/route/" + routeId + "/withdraw", null, function(result){
                })
            }, "请选择需要撤回的行程。");
        }
    },

    /**
     * 行程定制
     */
    make:{
        init:function(){
            $.Page.show("Page_MakeListView");

            var me = this,
                listview = $("#Page_MakeListView");

            $("#btn_plan", listview).on("click", function(){
                me.openScheduleWindow();
            });

            $("#btn_delivery", listview).on("click", function(){
                me.deliveryRouteInfoes();
            })

            this.query();
        },

        /**
         * 列表查询
         */
        query:function(){
            var dt = $("#Page_MakeListView #datatable_make")
            dt.dataTable({
                "aoColumns": [
                    {"mData": "id", "sClass":"center", "sWidth": "5%", "mRender": function (data, type, row) {
                        return "<input type='checkbox' class='checkboxes' value='" + data + "|" + row.routeId + "'/>";
                    }},
                    {
                        "mData": "title",
                        "sWidth": "25%"
                    },

                    {
                        "mData": "user",
                        "sWidth": "10%",
                        "mRender": function (data, type, row) {
                            return data.nickName;
                        }
                    },

                    {
                        "mData": "createdTime",
                        "sWidth": "20%"
                    },

                    {
                        "mData": "endTime",
                        "sWidth": "15%",
                        "mRender": function (data, type, row) {
                            var value = "";
                            if(data > 0) {
                                value += $.Date.formatLong(data);
                            }
                            return value;
                        }
                    },

                    {
                        "mData": "usedTime",
                        "sWidth": "10%",
                        "sClass":"center",
                        "mRender": function (data, type, row) {
                            return data + "天";
                        }
                    },

                    {
                        "mData": "status",
                        "sWidth": "10%",
                        "sClass":"center",
                        "mRender": function (data, type, row) {
                            if("CANCELLED" == data){
                                return "已取消";
                            }

                            if("PAYED" == data){
                                return "已支付";
                            }

                            if("USED" == data){
                                return "已服务";
                            }
                        }
                    }
                ]
            });
        },

        /**
         * 新启窗口进行行程制定
         */
        openScheduleWindow:function(){
            $("#datatable_make").singleSelect(function(orderId){
                var context = $("#context").val(),
                    values = orderId.split("|");

                console.log(values);

                if($.Utils.isNull(values[1])){
                    $.Request.post("/oms/order/" + values[0] + "/route/save", null, function(response){
                        window.open(context + "/view/oms/Schedule.html?type=recommend&routeId=" + response.data,'newwindow');
                    });
                }else{
                    window.open(context + "/view/oms/Schedule.html?type=recommend&routeId=" + values[1],'newwindow');
                }


            }, "请选择一条客户订购的定制服务。");
        },

        deliveryRouteInfoes:function(){
            var me = this;
            $("#datatable_make").select(function(orderId){
                var context = $("#context").val(),
                    values = orderId.split(","),
                    orderIds="";

                $.each(values, function(index, value){
                    if(index > 0) orderIds += ",";

                    orderIds += value.split("|")[0];
                })

                $.Request.post("/oms/order/" + orderIds + "/use", null, function(response){
                    $.Dialog.alert("交付完成。");
                    me.query();
                });
            }, "请选择需要交付的定制行程。");
        }
    }
};

