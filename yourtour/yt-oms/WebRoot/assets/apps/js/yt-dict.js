/**
 * 字典列表页面
 * @type {{initial: Function, query: Function}}
 */
jQuery.DictListView = {
    initial:function(){
        var me = this;

        $("#btn_add").on('click', function(){
            $.DictFormView.show();
        });

        $('#type').change(function(){
            me.query();
        })

        this.query();
    },

    show:function(){
        window.location="DictListView";
    },

    query:function(){
        $("#datatable_dict").dataTable().fnDestroy();
        $("#datatable_dict").dataTable({
            "aoColumns": [
                {
                    "mData": "type",
                    "sWidth": "20%"
                },
                {"mData": "name", "sWidth": "30%"},
                {"mData": "code", "sWidth": "20%"},
                {"mData": "value", "sWidth": "30%"}
            ]
        });
    }
};

/**
 * 字典表单页面
 * @type {{initial: Function}}
 */
jQuery.DictFormView = {
    initial:function(){
        $("#btnCancle").on('click', function(){
            $.DictListView.show();
        });

        $("#btnSave").on('click', this.saveDictInfo);
    },

    show:function(){
        window.location="DictFormView";
    },

    saveDictInfo:function(){
        var dict = {}, dictForm = $('#dictForm');
        dict.id = dictForm.find('#id').val();
        dict.name = dictForm.find('#name').val();
        dict.type = dictForm.find('#type').val();
        dict.code = dictForm.find('#code').val();
        dict.value = dictForm.find('#value').val();

        $.Request.post("/yt-oms/rest/admin/dicts/save",dict,function(result){
            bootbox.alert("保存成功。", function(){
                $.DictListView.show();
            });
        })
    }
}

