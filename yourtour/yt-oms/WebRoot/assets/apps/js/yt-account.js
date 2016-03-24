
/**
 *
 * @type {{query: Function, saveDictInfo: Function, loadDictInfo: Function}}
 */
jQuery.AccountManagement = {
    init:function(){
        $.Page.show("Page_AccountListView");

        this.query();
    },

    /**
     * 列表查询
     */
    query:function(){
        $("#datatable_account").dataTable().fnDestroy();
        $("#datatable_account").dataTable({
            "aoColumns": [
                {"mData": "id", "sClass":"center", "sWidth": "20px", "mRender": function (data, type, row) {
                    return "<input type='checkbox' class='checkboxes' value='" + data + "'/>";
                }},
                {
                    "mData": "userName",
                    "sWidth": "20%"
                },
                {"mData": "realName", "sWidth": "30%"},
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

