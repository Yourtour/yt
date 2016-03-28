
/**
 *
 * @type {{query: Function, saveDictInfo: Function, loadDictInfo: Function}}
 */
jQuery.Activity = {
    init:function(){
        $.Page.show("Page_ActivityListView");

        var me = this,
            listview = $("#Page_ActivityListView"),
            formview = $("#Page_ActivityFormView");

        $("#btn_add", listview).on("click", function(){
            me.createActivityInfo();
        });

        $('.date-picker', formview).datepicker({
            format: 'yyyy-mm-dd',
            clearBtn:true
        });

        //me.query();
    },

    /**
     * 列表查询
     */
    query:function(){
        $("#datatable_activity").dataTable().fnDestroy();
        $("#datatable_activity").dataTable({
            "aoColumns": [
                {"mData": "id", "sClass":"center", "sWidth": "20px", "mRender": function (data, type, row) {
                    return "<input type='checkbox' class='checkboxes' value='" + data + "'/>";
                }},
                {"mData": "name", "sWidth": "30%"},
                {"mData": "code", "sWidth": "25%"},
                {"mData": "name", "sWidth": "20%"},
                {"mData": "code", "sWidth": "10%"},
                {"mData": "value", "sWidth": "10%"}
            ]
        });
    },

    createActivityInfo:function(){
        $.Page.show("Page_ActivityFormView");
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

