/**
 *
 * @type {{query: Function, saveDictInfo: Function, loadDictInfo: Function}}
 */
jQuery.Banner = {
    init: function () {
        var me = this,
            listview = $("#Page_BannerListView"),
            formview = $("#Page_BannerFormView");

        $.Page.show("Page_BannerListView");

        $("#btn_add", listview).on('click', function () {
            me.createBannerInfo();
        });
        $("#btn_edit", listview).on('click', function () {
            me.loadBannerInfo();
        });
        $("#btn_delete", listview).on("click", function () {
            me.deleteBannerInfo();
        });

        // 保存按钮事件
        $("#btnSave", formview).on('click', function () {
            me.saveBannerInfo()
        });

        // 取消按钮事件
        $(" #btnCancel", formview).on('click', function () {
            $.Page.back();
        });

        this.query();
    },

    /**
     * 弹出Form创建
     */
    createBannerInfo: function () {
        var me = this,
            formview = $("#Page_BannerFormView");


        $.Page.show("Page_BannerFormView", function () {
            $("#BannerForm", formview).clear();
            $("#id", formview).val("-1");
        });
    },

    /**
     * 加载信息到Form便于修改
     */
    loadBannerInfo: function () {
        var me = this,
            formview = $("#Page_BannerFormView");

        $("#datatable_banner").edit(function (id) {
            $.Request.get("/oms/banners/" + id, null, function (result) {
                $.Page.show("Page_BannerFormView", function () {
                    $("#BannerForm").deserialize(result);
                });
            })
        })
    },

    /**
     * 逻辑删除
     */
    deleteBannerInfo: function () {
        var me = this;

        $("#datatable_banner").delete(function (id) {
            $.Request.delete("/oms/banners/" + id + "/delete", null, function (result) {
                me.query();
            })
        })
    },

    /**
     * 列表查询
     */
    query: function () {
        $("#datatable_banner").dataTable().fnDestroy();
        $("#datatable_banner")
            .dataTable(
            {
                "aoColumns": [
                    {
                        "mData": "id",
                        "sClass": "center",
                        "sWidth": "5%",
                        "mRender": function (data, type, row) {
                            return "<input type='checkbox' class='checkboxes' value='"
                                + data + "'/>";
                        }
                    }, {
                        "mData": "title",
                        "sWidth": "20%"
                    }, {
                        "mData": "subTitle",
                        "sWidth": "30%"
                    }, {
                        "mData": "content",
                        "sWidth": "40%"
                    }]
            });
    },

    /**
     * 保存字典数据
     */
    saveBannerInfo: function () {
        var banner = {}, bannerForm = $('#BannerForm'), me = this;
        banner = bannerForm.serialize();
        var fd = new FormData();
        fd.append('banner', JSON.stringify(banner));
        var ctlFiles = $("#files")[0];
        for (var index = 0; index < ctlFiles.files.length; index ++)
        {
            fd.append("files", ctlFiles.files[index]);
        }
        $.Request.postFormData("/oms/banners/save", fd, function (result) {
            bootbox.alert("保存成功。", function () {
                $.Page.back(function () {
                    me.query();
                });
            });
        })
    },

};
