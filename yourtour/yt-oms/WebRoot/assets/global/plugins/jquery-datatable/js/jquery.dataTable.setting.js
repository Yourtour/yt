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
        var rest = this.attr("data-rest");

        console.log(aoData);
        var context = {};
        $.each(aoData, function(index, data){
            context[data.name] = data.value;
        })

        $.ajax({
            "dataType": 'json',
            "contentType" : "application/json",
            "type": "POST",
            "url": rest,
            "data": JSON.stringify(context),
            "success": function (response){
                fnCallback(response);
            }
        });
    },

    fnServerParams: function( aoData ){
        var frm = this.attr("data-criteria");
        if(frm != undefined){
            var values = $("#" + frm).serializeArray();
            $.each(values, function(i, field){
                var value = field.value.trim();

                if(value != ''){
                    aoData[field.name] = value;
                }
            });
        }
    }
});
