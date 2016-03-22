/**
 * Created by 林平 on 2016/3/22.
 */

/* Set the defaults for DataTables initialisation */
$.extend( true, $.fn.dataTable.defaults, {
    "bAutoWidth": false,
    "bLengthChange":false,
    "iDisplayLength" : 10,
    "bFilter": false,
    "bSort": false,
    "bStateSave" : false,
    "bRetrieve":true,
    "bServerSide": true,
    "bProcessing": true,
    "sAjaxSource" : 'Query/Pagination.action',
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
    }
});
