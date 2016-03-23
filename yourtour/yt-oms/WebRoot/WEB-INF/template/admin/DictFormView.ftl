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
            <span>基础数据编辑</span>
        </li>
    </ul>
</div>
<!-- END PAGE HEADER-->

<div class="row">
    <div class="col-md-12">
        <!-- BEGIN SAMPLE FORM PORTLET-->
        <div class="portlet light ">
            <div class="portlet-body">
                <form id="dictForm" class="form-horizontal" role="form">
                    <input type="hidden" class="form-control" id="id">

                    <div class="form-group">
                        <label for="type" class="col-md-2 control-label">分类</label>
                        <div class="col-md-4">
                            <select id="type" class="form-control">
                                <option value="">请选择</option>

                                <#list types as type>
                                    <option value="${type.code}">${type.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="name" class="col-md-2 control-label">名称</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" id="name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="code" class="col-md-2 control-label">编码</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" id="code">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="value" class="col-md-2 control-label">值</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" id="value"> </div>
                    </div>

                    <hr>
                    <div class="form-group">
                        <div class="col-md-12 pull-right">
                            <button id="btnSave" type="button" class="btn blue">保存</button>

                            <button id="btnCancle" type="button" class="btn blue">取消</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function() {
        $.DictFormView.initial();
    } );
</script>
</body>
</html>  