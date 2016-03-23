<html>
<head>
    <title>基础数据管理</title>
</head>

<body>
    <!-- END THEME PANEL -->
    <h3 class="page-title"> 基础数据管理
    </h3>

    <div class="page-bar">
        <ul class="page-breadcrumb">
            <li>
                <i class="icon-home"></i>
                <a href="index.html">首页</a>
                <i class="fa fa-angle-right"></i>
            </li>
            <li>
                <span>基础数据管理</span>
            </li>
        </ul>
    </div>
    <!-- END PAGE HEADER-->

    <div class="row">
        <div class="col-md-12">
            <!-- BEGIN SAMPLE FORM PORTLET-->
            <div class="portlet light ">
                <div class="portlet-body">
                    <form id="frm_dictlistview">
                        <div class="row bottom-space">
                            <div class="col-md-6 col-sm-12">
                                <div class="table-group-actions pull-left">
                                    <label class="control-label">Inline Help</label>
                                    <select id="type" name="type" class="table-group-action-input form-control input-inline">
                                        <option value="">请选择</option>

                                        <#list types as type>
                                            <option value="${type.code}">${type.name}</option>
                                        </#list>
                                    </select>
                                </div>
                            </div>

                            <div class="col-md-6 col-sm-12">
                                <div class="table-group-actions pull-right">
                                    <button id="btn_add" class="btn btn-sm red table-group-action-submit">
                                        <i class="fa fa-plus"></i> 新增</button>
                                </div>
                            </div>
                        </div>
                    </form>

                    <table id="datatable_dict" class="row-border" cellspacing="0" width="100%" data-criteria="frm_dictlistview" data-rest="${context}/rest/admin/dicts/query">
                        <thead>
                            <tr>
                                <th>分类</th>
                                <th>名称</th>
                                <th>编码</th>
                                <th>值</th>
                            </tr>
                        </thead>

                        <tbody>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        $(document).ready(function() {
            $.DictListView.initial();
        } );
    </script>
</body>
</html>  