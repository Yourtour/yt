/**
 *
 * @type {{query: Function, saveDictInfo: Function, loadDictInfo: Function}}
 */
jQuery.RestaurantResource = {
    init: function () {
        var me = this,
            listview = $("#Page_RestaurantListView"),
            formview = $("#Page_RestaurantFormView");

        $.Page.show("Page_RestaurantListView");

        $("#btn_add", listview).on('click', function () {
            me.createRestaurantInfo();
        });
        $("#btn_edit", listview).on('click', function () {
            me.loadRestaurantInfo();
        });
        $("#btn_delete", listview).on("click", function () {
            me.deleteRestaurantInfo();
        });

        // 保存按钮事件
        $("#btnSave", formview).on('click', function () {
            me.saveRestaurantInfo()
        });

        // 取消按钮事件
        $(" #btnCancel", formview).on('click', function () {
            $.Page.back();
        });

        this.query();
    },

    /**
     * 弹出Form创建一个新的饭店
     */
    createRestaurantInfo: function () {
        var me = this,
            formview = $("#Page_RestaurantFormView");


        $.Page.show("Page_RestaurantFormView", function () {
            $("#RestaurantForm", formview).clear();
            $("#id", formview).val("-1");
        });
    },

    /**
     * 加载饭店信息到Form便于修改
     */
    loadRestaurantInfo: function () {
        var me = this,
            formview = $("#Page_RestaurantFormView");

        $("#datatable_restaurant").edit(function (id) {
            $.Request.get("/rest/oms/resources/restaurants/" + id, null, function (result) {
                $.Page.show("Page_RestaurantFormView", function () {
                    $("#RestaurantForm").deserialize(result);
                });
            })
        })
    },

    /**
     * 逻辑删除饭店
     */
    deleteRestaurantInfo: function () {
        var me = this;

        $("#datatable_restaurant").delete(function (id) {
            $.Request.delete("/rest/oms/resources/restaurants/" + id + "/delete", null, function (result) {
                me.query();
            })
        })
    },

    /**
     * 列表查询
     */
    query: function () {
        $("#datatable_restaurant").dataTable().fnDestroy();
        $("#datatable_restaurant")
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
                        "mData": "code",
                        "sWidth": "20%"
                    }, {
                        "mData": "name",
                        "sWidth": "30%"
                    }, {
                        "mData": "deliciouFood",
                        "sWidth": "40%"
                    }]
            });
    },

    /**
     * 保存字典数据
     */
    saveRestaurantInfo: function () {
        var restaurant = {}, restaurantForm = $('#RestaurantForm'), me = this;
        restaurant = restaurantForm.serialize();
        var fd = new FormData();
        fd.append('restaurant', JSON.stringify(restaurant));
        //fd.append("imageUrl", $("#imageUrl")[0].files[0]);

        console.log(restaurant);

        $.Request.postFormData("/rest/oms/resources/restaurants/save", fd, function (result) {
            bootbox.alert("保存成功。", function () {
                $.Page.back(function () {
                    me.query();
                });
            });
        })
    },

};
