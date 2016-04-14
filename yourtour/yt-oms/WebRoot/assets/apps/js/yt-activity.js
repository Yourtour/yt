
/**
 *
 * @type {{query: Function, saveDictInfo: Function, loadDictInfo: Function}}
 */
jQuery.Activity = {
    init:function(){
        $.Page.show("Page_ActivityListView");

        var me = this,
            listview = $("#Page_ActivityListView"),
            formview = $("#Page_ActivityFormView");

        $(".wysihtml5", formview).wysihtml5({type:'activity', locale:'zh-CN', stylesheets:["../assets/global/plugins/bootstrap-wysihtml5/wysiwyg-color.css"]});

        $("#imageUrl", formview).imageSelector();

        $("#routeIds", formview).routeSelector();

        $("#btn_add", listview).on("click", function(){
            me.createActivityInfo();
        });

        $("#btn_delete", listview).on("click", function(){
            me.deleteActivityInfo();
        });

        $("#btn_edit", listview).on("click", function(){
            me.loadActivityInfo();
        });

        $('.date-picker', formview).datepicker({
            format: 'yyyy-mm-dd',
            clearBtn:true
        });

        $('#btn_save', formview).on("click", function(){
            me.saveActivityInfo();
        });

        $('#btn_cancel', formview).on("click", function(){
            $.Page.back();
        });

        $('#btn_content_add', formview).on("click", function(){
            me.createContentInfo();
        });

        $('#btn_route_add', formview).on("click", function(){
            $.Route.showSearchView(function(rooutes){
            });
        });

        me.query();
    },

    /**
     * 列表查询
     */
    query:function(){
        $("#datatable_activity").dataTable({
            "aoColumns": [
                {"mData": "id", "sClass":"center", "sWidth": "20px", "mRender": function (data, type, row) {
                    return "<input type='checkbox' class='checkboxes' value='" + data + "'/>";
                }},
                {"mData": "title", "sWidth": "20%"},
                {"mData": "tags", "sWidth": "20%"},
                {"mData": "startTime", "sWidth": "20%","mRender": function (data, type, row) {
                    return row.startTime + '<span style="padding-left:10px;padding-right:10px;">~</span>' + row.endTime;
                }},
                {"mData": "createdTime", "sWidth": "10%"},
                {"mData": "homeRecommend",  "sClass":"center", "sWidth": "10%","mRender": function (data, type, row) {
                    return data == 1 ? "是" : "";
                }},
                {"mData": "placeRecommend", "sClass":"center", "sWidth": "10%","mRender": function (data, type, row) {
                    return data == 1 ? "是" : "";
                }}
            ]
        });
    },

    createActivityInfo:function(){
        var me = this,
            formview = $("#Page_ActivityFormView");


        $.Page.show("Page_ActivityFormView", function(){
            $("#id", formview).val("-1");
            $("#ActivityForm", formview).clear();
        });
    },

    /**
     * 保存活动数据
     */
    saveActivityInfo:function(){
        var me = this,
            form = $('#ActivityForm'),
            formdata = new FormData(),
            formview = $("#Page_ActivityFormView"),
            json = form.serialize();
        delete json['_wysihtml5_mode'];

        formdata.append("activity", JSON.stringify(json));

        var image = $("#imageUrl")[0].files[0];
        if(image) {
            formdata.append("imageUrl", image);
        }

        $.Request.postFormData("/oms/activity/save",formdata,function(result){
            bootbox.alert("保存成功。", function(){
                $.Page.back();
            });
        })
    },

    /**
     * 删除活动数据
     */
    deleteActivityInfo:function(){
        var me = this;

        $("#datatable_activity").delete(function(ids){
            $.Request.delete("/oms/activity/" + ids + "/delete", null, function(result){
                me.query();
            })
        })
    },

    /**
     * 获取活动数据
     */
    loadActivityInfo:function(){
        var me = this,
            formview = $("#Page_ActivityFormView");

        $("#routeIds").routeSelector("removeAll");

        $("#datatable_activity").edit(function(id){
            $.Request.get("/oms/activity/" + id, null, function(result){
                $.Page.show("Page_ActivityFormView", function(){
                    $("#tab_content", formview).show();
                    $("#tab_route",formview).show();

                    $("#Page_ActivityFormView").deserialize(result);
                    $("#routeIds").routeSelector("setValue", result.routes);
                });
            })
        })
    },

};

