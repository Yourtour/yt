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

        $("#place", formview).popupSearch({popup: $.Popups.place});

        $("#imageUrl", formview).imageInput();

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
            $.Request.get("/oms/resources/restaurants/" + id, null, function (result) {
                $.Page.show("Page_RestaurantFormView", function () {
                    var restaurant = result,
                        specialty = restaurant.specialty;
                    if (specialty) {
                        restaurant.deliciouFood = specialty.deliciouFood;
                        restaurant.foodStandard = specialty.foodStandard;
                        restaurant.foodTags = specialty.foodTags;
                        restaurant.networkInfo = specialty.networkInfo;
                        delete restaurant.specialty;
                    }
                    $("#RestaurantForm").deserialize(restaurant);
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
            $.Request.delete("/oms/resources/restaurants/" + id + "/delete", null, function (result) {
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
                        "mData": "star",
                        "sWidth": "10%"
                    }, {
                        "mData": "price",
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

        // 拼装饭店个性化字段，并删除
        var specialty = {
            'deliciouFood': restaurant.deliciouFood, 'foodStandard': restaurant.foodStandard,
            'foodTags': restaurant.foodTags, 'networkInfo': restaurant.networkInfo
        };
        restaurant.specialty = specialty;
        delete restaurant.deliciouFood;
        delete restaurant.foodStandard;
        delete restaurant.foodTags;
        delete restaurant.networkInfo;

        var fd = new FormData();
        fd.append('restaurant', JSON.stringify(restaurant));
        var ctlFiles = $("#imageUrl")[0];
        for (var index = 0; index < ctlFiles.files.length; index++) {
            fd.append("files", ctlFiles.files[index]);
        }

        $.Request.postFormData("/oms/resources/restaurants/save", fd, function (result) {
            bootbox.alert("保存成功。", function () {
                $.Page.back(function () {
                    me.query();
                });
            });
        })
    },

};
