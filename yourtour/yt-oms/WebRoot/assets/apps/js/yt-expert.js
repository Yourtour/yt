/**
 *
 * @type {{query: Function, saveDictInfo: Function, loadDictInfo: Function}}
 */
jQuery.Expert = {
    init: function () {
        var me = this,
            listview = $("#Page_ExpertListView");

        $.Page.show("Page_ExpertListView");

        $("#btn_frozen", listview).on('click', function () {
            me.frozenExpert();
        });

        this.query();
    },


    /**
     * 冻结达人
     */
    frozenExpert: function () {
        var me = this;

        $("#datatable_expert").delete(function (id) {
            $.Request.get("/oms/experts/" + id + "/frozen", null, function (result) {
                me.query();
            })
        })
    },

    /**
     * 列表查询
     */
    query: function () {
        $("#datatable_expert").dataTable().fnDestroy();
        $("#datatable_expert")
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
                        "mData": "identityCode",
                        "sWidth": "30%"
                    }, {
                        "mData": "tags",
                        "sWidth": "40%"
                    }]
            });
    }

};
