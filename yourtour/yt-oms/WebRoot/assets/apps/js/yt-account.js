
/**
 *
 * @type {{query: Function, saveDictInfo: Function, loadDictInfo: Function}}
 */
jQuery.AccountManagement = {
    init:function(){
        var me = this;

        $.Page.show("Page_AccountListView");

        var listview = $("#Page_AccountListView");
        $("#btn_add", listview).on('click', function(){
            $.Page.show("Page_AccountFormView");
        });

        var formview = $("#Page_AccountFormView");
        //保存按钮事件
        $("#btnSave", formview).on('click', function(){
            me.saveAccountInfo()
        });

        //取消按钮事件
        $(" #btnCancel", formview).on('click', function(){
            $.Page.back();
        });

        this.query();
    },

    /**
     * 列表查询
     */
    query:function(){
        $("#datatable_account").dataTable().fnDestroy();
        $("#datatable_account").dataTable({
            "aoColumns": [
                {"mData": "id", "sClass":"center", "sWidth": "5%", "mRender": function (data, type, row) {
                    return "<input type='checkbox' class='checkboxes' value='" + data + "'/>";
                }},
                {
                    "mData": "userName",
                    "sWidth": "20%"
                },
                {"mData": "realName", "sWidth": "75%"}
            ]
        });
    },

    /**
     * 保存字典数据
     */
    saveAccountInfo:function(){
        var account = {}, accountForm = $('#accountForm'), me = this;
        account.id = accountForm.find('#id').val();
        account.realName = accountForm.find('#realName').val();
        account.userName = accountForm.find('#userName').val();
        account.pwd = accountForm.find('#password').val();

        $.Request.post("/rest/admin/account/save",account,function(result){
            bootbox.alert("保存成功。", function(){
                $.Page.back(function(){
                    me.query();
                });
            });
        })
    },

    login:function(){
        var loginview = $(".login"),
            account = {},
            username = $('#username', loginview).val(),
            password = $('#password', loginview).val();

        user.userName = userName;
        user.pwd = password;

        $.Request.post("/rest/admin/account/authenticate",account,function(result){

        })
    }
};

