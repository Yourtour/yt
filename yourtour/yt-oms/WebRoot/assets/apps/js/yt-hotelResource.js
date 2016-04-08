/**
 *
 * @type {{query: Function, saveDictInfo: Function, loadDictInfo: Function}}
 */
jQuery.HotelResource = {
    init: function () {
        var me = this,
            listview = $("#Page_HotelListView"),
            formview = $("#Page_HotelFormView");

        $.Page.show("Page_HotelListView");

        $("#btn_add", listview).on('click', function () {
            me.createHotelInfo();
        });
        $("#btn_edit", listview).on('click', function () {
            me.loadHotelInfo();
        });
        $("#btn_delete", listview).on("click", function () {
            me.deleteHotelInfo();
        });

        // 保存按钮事件
        $("#btnSave", formview).on('click', function () {
            me.saveHotelInfo()
        });

        // 取消按钮事件
        $(" #btnCancel", formview).on('click', function () {
            $.Page.back();
        });

        this.query();
    },

    /**
     * 弹出Form创建一个新的宾馆
     */
    createHotelInfo: function () {
        var me = this,
            formview = $("#Page_HotelFormView");


        $.Page.show("Page_HotelFormView", function () {
            $("#HotelForm", formview).clear();
            $("#id", formview).val("-1");
        });
    },

    /**
     * 加载宾馆信息到Form便于修改
     */
    loadHotelInfo: function () {
        var me = this,
            formview = $("#Page_HotelFormView");

        $("#datatable_hotel").edit(function (id) {
            $.Request.get("/oms/resources/hotels/" + id, null, function (result) {
                $.Page.show("Page_HotelFormView", function () {
                    $("#HotelForm").deserialize(result);
                });
            })
        })
    },

    /**
     * 逻辑删除宾馆
     */
    deleteHotelInfo: function () {
        var me = this;

        $("#datatable_hotel").delete(function (id) {
            $.Request.delete("/oms/resources/hotels/" + id + "/delete", null, function (result) {
                me.query();
            })
        })
    },

    /**
     * 列表查询
     */
    query: function () {
        $("#datatable_hotel").dataTable().fnDestroy();
        $("#datatable_hotel")
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
                        "mData": "specialRoom",
                        "sWidth": "40%"
                    }]
            });
    },

    /**
     * 保存字典数据
     */
    saveHotelInfo: function () {
        var hotel = {}, hotelForm = $('#HotelForm'), me = this;
        hotel = hotelForm.serialize();
        var fd = new FormData();
        fd.append('hotel', JSON.stringify(hotel));
        var ctlFiles = $("#files")[0];
        for (var index = 0; index < ctlFiles.files.length; index ++)
        {
            fd.append("files", ctlFiles.files[index]);
        }

        $.Request.postFormData("/oms/resources/hotels/save", fd, function (result) {
            bootbox.alert("保存成功。", function () {
                $.Page.back(function () {
                    me.query();
                });
            });
        })
    },

};
