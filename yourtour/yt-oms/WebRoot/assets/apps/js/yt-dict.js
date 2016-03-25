
/**
 *
 * @type {{query: Function, saveDictInfo: Function, loadDictInfo: Function}}
 */
jQuery.DictManagement = {
    init:function(){
        $.Page.show("Page_DictListView");

        $.DictManagement.query();

        $("#Page_DictListView #btn_add").on('click', function(){
            $.DictManagement.loadDictInfo();
        });

        $("#Page_DictListView #btn_edit").on('click', function(){
            $("#datatable_dict").edit(function(id){
                $.DictManagement.loadDictInfo(id);
            })
        });

        $("#Page_DictListView #btn_delete").on('click', function(){
            $("#datatable_dict").delete(function(ids){
                $.DictManagement.deleteDictInfo(ids);
            })
        });

        $('#Page_DictListView #type').change(function(){
            $.DictManagement.query();
        });

        //保存按钮事件
        $("#Page_DictFormView #btnSave").on('click', function(){
            $.DictManagement.saveDictInfo()
        });

        //取消按钮事件
        $("#Page_DictFormView #btnCancel").on('click', function(){
            $.Page.back();
        });
    },

    /**
     * 列表查询
     */
    query:function(){
        $("#datatable_dict").dataTable().fnDestroy();
        $("#datatable_dict").dataTable({
            "aoColumns": [
                {"mData": "id", "sClass":"center", "sWidth": "20px", "mRender": function (data, type, row) {
                    return "<input type='checkbox' class='checkboxes' value='" + data + "'/>";
                }},
                {
                    "mData": "typeName",
                    "sWidth": "20%"
                },
                {"mData": "name", "sWidth": "30%"},
                {"mData": "code", "sWidth": "20%"},
                {"mData": "value", "sWidth": "30%"},
            ]
        });
    },

    /**
     * 保存字典数据
     */
    saveDictInfo:function(){
        var dict = {}, dictForm = $('#dictForm'), me = this;
        dict.id = dictForm.find('#id').val();
        dict.name = dictForm.find('#name').val();
        dict.type = dictForm.find('#type').val();
        dict.code = dictForm.find('#code').val();
        dict.value = dictForm.find('#value').val();

        $.Request.post("/yt-oms/rest/admin/dicts/save",dict,function(result){
            bootbox.alert("保存成功。", function(){
                $.Page.back(function(){
                    me.query();
                });
            });
        })
    },

    /**
     * 装载字典数据
     */
    loadDictInfo:function(dictId){
        $.Page.show("Page_DictFormView", function(){
            $("#Page_DictFormView").clear();
            $.Request.get("/yt-oms/rest/admin/dicts/" + dictId, null, function(data){
                $("#Page_DictFormView").deserialize(data);
            });
        });
    },

    /**
     * 装载字典数据
     */
    deleteDictInfo:function(dictIds){
        var me = this;

        $.Request.get("/yt-oms/rest/admin/dicts/" + dictIds + "/delete", null, function(data){
            me.query();
        });
    },
};
