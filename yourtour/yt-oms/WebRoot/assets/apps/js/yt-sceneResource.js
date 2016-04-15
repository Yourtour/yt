/**
 *
 * @type {{query: Function, saveDictInfo: Function, loadDictInfo: Function}}
 */
jQuery.SceneResource = {
    init: function () {
        var me = this,
            listview = $("#Page_SceneListView"),
            formview = $("#Page_SceneFormView");

        $.Page.show("Page_SceneListView");

        $("#place", formview).place();
        $("#imageUrl", formview).imageSelector();

        $("#btn_add", listview).on('click', function () {
            me.createSceneInfo();
        });
        $("#btn_edit", listview).on('click', function () {
            me.loadSceneInfo();
        });
        $("#btn_delete", listview).on("click", function () {
            me.deleteSceneInfo();
        });

        // 保存按钮事件
        $("#btnSave", formview).on('click', function () {
            me.saveSceneInfo()
        });

        // 取消按钮事件
        $(" #btnCancel", formview).on('click', function () {
            $.Page.back();
        });

        this.query();
    },

    /**
     * 弹出Form创建一个新的景点
     */
    createSceneInfo: function () {
        var me = this,
            formview = $("#Page_SceneFormView");


        $.Page.show("Page_SceneFormView", function () {
            $("#SceneForm", formview).clear();
            $("#id", formview).val("-1");
        });
    },

    /**
     * 加载景点信息到Form便于修改
     */
    loadSceneInfo: function () {
        var me = this,
            formview = $("#Page_SceneFormView");

        $("#datatable_scene").edit(function (id) {
            $.Request.get("/oms/resources/scenes/" + id, null, function (result) {
                $.Page.show("Page_SceneFormView", function () {
                    var scene = result, specialty = scene.specialty;
                    if (specialty) {
                        scene.ticket = specialty.ticket;
                        scene.sceneMap = specialty.sceneMap;
                        scene.specialScene = specialty.specialScene;
                        scene.sceneTraffic = specialty.sceneTraffic;
                        delete scene.specialty;
                    }
                    $("#SceneForm").deserialize(scene);
                });
            })
        })
    },

    /**
     * 逻辑删除景点
     */
    deleteSceneInfo: function () {
        var me = this;

        $("#datatable_scene").delete(function (id) {
            $.Request.delete("/oms/resources/scenes/" + id + "/delete", null, function (result) {
                me.query();
            })
        })
    },

    /**
     * 列表查询
     */
    query: function () {
        $("#datatable_scene").dataTable().fnDestroy();
        $("#datatable_scene")
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
    saveSceneInfo: function () {
        var scene = {}, sceneForm = $('#SceneForm'), me = this;
        scene = sceneForm.serialize();
        // 封装景点个性化字段，删除不需要的字段
        var specialty = {
            'ticket': scene.ticket, 'sceneMap': scene.sceneMap,
            'specialScene': scene.specialScene, 'sceneTraffic': scene.sceneTraffic
        };
        scene.specialty = specialty;
        delete scene.ticket;
        delete scene.sceneMap;
        delete scene.specialScene;
        delete scene.sceneTraffic;

        var fd = new FormData();
        fd.append('scene', JSON.stringify(scene));
        var ctlFiles = $("#imageUrl")[0];
        for (var index = 0; index < ctlFiles.files.length; index++) {
            fd.append("imageUrl", ctlFiles.files[index]);
        }

        $.Request.postFormData("/oms/resources/scenes/save", fd, function (result) {
            bootbox.alert("保存成功。", function () {
                $.Page.back(function () {
                    me.query();
                });
            });
        })
    },

};
