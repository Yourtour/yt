
/**
 *
 * @type {{query: Function, saveDictInfo: Function, loadDictInfo: Function}}
 */
jQuery.Route = {
    init:function(){
        $.Page.show("Page_RouteListView");

        var me = this,
            listview = $("#Page_RouteListView"),
            formview = $("#Page_RouteFormView");

        $("#btn_add", listview).on('click', function(){
            $.Page.show("Page_RouteFormView");
        });

        //保存按钮事件
        $("#btn_save", formview).on('click', function(){
            me.saveRouteInfo()
        });

        //取消按钮事件
        $(" #btn_cancel", formview).on('click', function(){
            $.Page.back();
        });

        this.query();
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
                    "sWidth": "95%"
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

        $.Request.postFormData("/rest/oms/route/main/save",formdata,function(result){
            bootbox.alert("保存成功。", function(){
                "use strict";
                $.Page.back(function(){
                    me.query();
                });
            });
        })
    }
};

