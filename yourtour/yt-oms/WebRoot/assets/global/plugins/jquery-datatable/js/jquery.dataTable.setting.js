/**
 * Created by 林平 on 2016/3/22.
 */

/* Set the defaults for DataTables initialisation */
$.extend( true, $.fn.dataTable.defaults, {
    "bAutoWidth": false,
    "bLengthChange":false,
    "iDisplayLength" : 20,
    "bFilter": false,
    "bSort": false,
    "bStateSave" : false,
    "bRetrieve":true,
    "bProcessing": false, // 是否显示取数据时的那个等待提示
    "bServerSide": true,//这个用来指明是通过服务端来取数据
    "sPaginationType" : "full_numbers",
    "aoColumnDefs" : [{
        sDefaultContent : '',
        aTargets : [ '_all' ]
    }],

    "oLanguage" : { //主要用于设置各种提示文本
        sEmptyTable: "没有找到符合条件的数据",
        sInfo: "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
        sInfoEmpty: "",
        sInfoFiltered: "",
        sInfoPostFix: "",
        sInfoThousands: ",",
        sLoadingRecords: "Loading...",
        sProcessing: "Processing...",
        sUrl: "",
        sZeroRecords: "没有找到符合条件的数据。",
        oPaginate: {
            sFirst: "首页",
            sLast: "末页",
            sNext: "下一页",
            sPrevious: "上一页"
        }
    },

    fnServerData: function ( sSource, aoData, fnCallback ) {
        var rest = this.attr("data-rest"),
            type = this.data("method"), nextCursor, limit, total, params = {}, name;

        $.each(aoData, function(index, data){
            name = data.name;

            if(name == 'start'){
                nextCursor = data.value;
            }else if (name == 'length'){
                limit = data.value;
            }else if(name == 'params'){
                params = data.value;
            }
        })

        rest = rest + "?nextCursor=" + nextCursor + "&limit=" + limit + "&total=0" ;
        $.ajax({
            "dataType": 'json',
            "contentType" : "application/json",
            "type": type ? type : "GET",
            "url": rest,
            "data": JSON.stringify(params),
            "success": function (response){
                fnCallback(response);

                $("input[type='radio'], input[type='checkbox']").uniform();
            }
        });
    },

    fnServerParams: function( aoData ){
        var frm = this.attr("data-criteria");
        if(frm != undefined){
        	var params = {};
        	
        	var values = $("#" + frm).serializeArray();
            $.each(values, function(i, field){
                var value = field.value.trim();

                if(value != ''){
                    params[field.name] = value;
                }
            });
            
            aoData.params = params;
        }

        
    }
});

(function($){
    $.fn.extend({
        edit:function(callback){
            var datatable = $(this), selectedItems=[];

            datatable.find(".checkboxes").each(function(index, item){
                var checkbox = $(item);

                if(checkbox.is(":checked")) {
                    selectedItems.push(checkbox.val());
                }
            });

            if(selectedItems.length != 1){
                bootbox.alert("请选择一条需要修改的记录。");
                return;
            }

            callback(selectedItems[0]);
        },

        delete:function(callback){
            var datatable = $(this), selectedItems=[];

            datatable.find(".checkboxes").each(function(index, item){
                var checkbox = $(item);
                if(checkbox.is(":checked")) {
                    selectedItems.push(checkbox.val());
                }
            });

            if(selectedItems.length == 0){
                bootbox.alert("请选择需要删除的记录。");
                return;
            }

            bootbox.confirm("确定要删除选择的记录吗?", function(result){
                if (result) {
                    callback(selectedItems.join(","));
                }
            })
        },

        select:function(callback, message){
            var datatable = $(this), selectedItems=[];

            datatable.find(".checkboxes").each(function(index, item){
                var checkbox = $(item);
                if(checkbox.is(":checked")) {
                    selectedItems.push(checkbox.val());
                }
            });

            if(selectedItems.length == 0){
                if(message) {
                    bootbox.alert(message);
                }else{
                    bootbox.alert("请选择记录。");
                }
                return;
            }

            callback(selectedItems.join(","));
        },

        singleSelect:function(callback, message){
            var datatable = $(this), selectedItems=[];

            datatable.find(".checkboxes").each(function(index, item){
                var checkbox = $(item);
                if(checkbox.is(":checked")) {
                    selectedItems.push(checkbox.val());
                }
            });

            if(selectedItems.length == 0 || selectedItems.length > 1){
                if(message) {
                    bootbox.alert(message);
                }else{
                    bootbox.alert("请选择记录。");
                }
                return;
            }

            callback(selectedItems[0]);
        }

    });

    $(".group-checkable").each(function(){
        $(this).on("change", function(){
            var checked = $(this).is(":checked");
            $(".checkboxes",$(this).parents(".dataTable")).each(function(index){
                var e = $(this);
                if(checked)
                    e.prop("checked", !0);
                else
                    e.prop("checked", !1);

                jQuery.uniform.update(e);
            });
        });
    });
})(jQuery);
