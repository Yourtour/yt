/**
 *
 * @type {{query: Function, saveDictInfo: Function, loadDictInfo: Function}}
 */
jQuery.ExpertApplicationApprove = {
    init: function () {
        var me = this,
            listview = $("#Page_ExpertApplicationListView"),
            formview = $("#Page_ExpertApplicationFormView");

        $.Page.show("Page_ExpertApplicationListView");

        $("#btn_approve", listview).on("click", function () {
            me.approve();
        });

        // 保存按钮事件
        $("#btnSave", formview).on('click', function () {
            me.saveExpertApplicationApproveInfo()
        });

        // 取消按钮事件
        $(" #btnCancel", formview).on('click', function () {
            $.Page.back();
        });

        this.query();
    },

    /**
     * 审核
     */
    approve: function () {
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
        $("#datatable_expertApplication").dataTable().fnDestroy();
        $("#datatable_expertApplication")
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
                        "mData": "realName",
                        "sWidth": "20%"
                    }, {
                        "mData": "identityCardCode",
                        "sWidth": "30%"
                    }, {
                        "mData": "tags",
                        "sWidth": "40%"
                    }]
            });
    },

    /**
     * 保存字典数据
     */
    saveExpertApplicationApproveInfo: function () {
        var banner = {}, expertApplicationForm = $('#ExpertApplicationForm'), me = this;
        $.Request.postFormData("/oms/banners/save", expertApplicationForm, function (result) {
            bootbox.alert("保存成功。", function () {
                $.Page.back(function () {
                    me.query();
                });
            });
        })
    },

};
