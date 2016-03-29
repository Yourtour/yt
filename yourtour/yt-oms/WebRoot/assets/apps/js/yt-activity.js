
/**
 *
 * @type {{query: Function, saveDictInfo: Function, loadDictInfo: Function}}
 */
jQuery.Activity = {
    init:function(){
        $.Page.show("Page_ActivityListView");

        var me = this,
            listview = $("#Page_ActivityListView"),
            formview = $("#Page_ActivityFormView"),
            contentformview = $("#Page_ActivityContentFormView");

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
                alert('afadsfadf');
            });
        });

        $('#btn_save', contentformview).on("click", function(){
            me.saveActivityContentInfo();
        });

        $('#btn_cancel', contentformview).on("click", function(){
            $.Page.back();
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
            $("#ActivityForm", formview).clear();

            $("#tab_content",formview).hide();
            $("#tab_route",formview).hide();
        });
    },

    /**
     * 保存活动数据
     */
    saveActivityInfo:function(){
        var me = this, form = $('#ActivityForm'), formdata = new FormData(), formview = $("#Page_ActivityFormView");
        formdata.append("activity", JSON.stringify(form.serialize()));

        var image = $("#imageUrl")[0].files[0];
        if(image) {
            formdata.append("imageUrl", image);
        }

        $.Request.postFormData("/rest/oms/activity/intro/save",formdata,function(result){
            bootbox.alert("保存成功。", function(){
                $("#id", form).val(result.id);
                $("#tab_content", formview).show();
                $("#tab_route",formview).show();
            });
        })
    },

    /**
     * 删除活动数据
     */
    deleteActivityInfo:function(){
        var me = this;

        $("#datatable_activity").delete(function(ids){
            $.Request.delete("/rest/oms/activity/" + ids + "/delete", null, function(result){
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

        $("#datatable_activity").edit(function(id){
            $.Request.get("/rest/oms/activity/" + id, null, function(result){
                $.Page.show("Page_ActivityFormView", function(){
                    $("#tab_content", formview).show();
                    $("#tab_route",formview).show();

                    $("#ActivityForm").deserialize(result);

                    var activityContent = $("#Page_ActivityFormView #activity_content");
                    activityContent.html("");

                    $.each(result.contents, function(index, content){
                        me.showContentInfo(activityContent,content);
                    });
                });
            })
        })
    },

    /**
     *
     */
    createContentInfo:function(){
        var me = this,
            formview = $("#Page_ActivityContentFormView");

        $.Page.show("Page_ActivityContentFormView", function(){
            $("#ActivityContentForm", formview).clear();

            var activityId = $("#Page_ActivityFormView #id").val();
                eActivityId = $("#Page_ActivityContentFormView #activityId");

            eActivityId.val(activityId);
        });
    },

    /**
     *
     */
    saveActivityContentInfo:function(){
        var me = this,
            form = $('#ActivityContentForm'),
            activityContent = $("#Page_ActivityFormView #activity_content"),
            formdata = new FormData();

        formdata.append("content", JSON.stringify(form.serialize()));

        var image = $("#imageUrl")[0].files[0];
        if(image) {
            formdata.append("imageUrl", image);
        }

        $.Request.postFormData("/rest/oms/activity/content/save",formdata,function(result){
            me.showContentInfo(result);
            bootbox.alert("保存成功。", function(){
                $.Page.back(function(){
                });
            });
        })
    },

    loadContentInfo:function(id){
        var me = this;

        $.Request.get("/rest/oms/activity/content/" + id, null, function(result){
            $.Page.show("Page_ActivityContentFormView", function(){
                $("#ActivityContentForm").deserialize(result);
            });
        })
    },

    /**
     *
     * @param id
     */
    deleteContentInfo:function(id){
        bootbox.confirm("确定要删除活动内容吗?", function(result){
            if (result) {
                $.Request.delete("/rest/oms/activity/content/" + id + "/delete", null, function (result) {
                    var content = $("#Page_ActivityFormView #portlet_content #" + result.id);
                    if(content){
                        content.remove();
                    }
                });
            }
        })
    },

    /**
     * 显示活动内容
     * @param node
     * @param content
     */
    showContentInfo:function(node, content){
        var html = "";
        if($.Utils.isNotNull(node.html())){
            html += '<hr>';
        }

        html += "<div id='" + content.id + "'>";

        if($.Utils.isNotNull(content.title)) {
            html += "<div>" + content.title + "</div>";
        }

        if($.Utils.isNotNull(content.content)){
            html += "<div>" + content.content + "</div>";
        }

        if($.Utils.isNotNull(content.imageUrl)){
            html += "<img src='" + content.imageUrl + "'/>";
        }

        html += '<div class="row" style="margin-top:10px"><div class="float:right">';
        html += '<a href="javascript:$.Activity.loadContentInfo(' + content.id + ')" class="btn btn-icon-only default"><i class="fa fa-edit"></i></a>';
        html += '<a href="javascript:$.Activity.deleteContentInfo(' + content.id + ')" class="btn btn-icon-only default"  style="margin-left:20px;"><i class="fa fa-times"></i></a>';
        html += '</div></div>';
        html += "</div>";

        $(html).appendTo(node);
    }
};

