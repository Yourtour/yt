<div id="Page_SceneFormView" class="row" data-role="page">
    <div class="col-lg-12">
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="index.html">首页</a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span>游玩编辑</span>
                </li>
            </ul>
        </div>
        <!-- END PAGE HEADER-->

        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN SAMPLE FORM PORTLET-->
                <div class="portlet light ">
                    <div class="portlet-body">
                        <form id="SceneForm" class="form-horizontal" role="form">
                            <input type="hidden" class="form-control" id="id" name="id">

                            <div class="form-group">
                                <label for="code" class="col-md-2 control-label">代码</label>
                                <div class="col-md-4">
                                    <input type="text" class="form-control" name="code" id="code" placeholder="代码">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="name" class="col-md-2 control-label">名称</label>
                                <div class="col-md-4">
                                    <input type="text" class="form-control" name="name" id="name" placeholder="名称">
                                </div>
                            </div>

							<div class="form-group">
                                <label for="name" class="col-md-2 control-label">名称</label>
                                <div class="col-md-4">
                                    <input type="text" class="form-control" name="name" id="name" placeholder="名称">
                                </div>
                            </div>

                            <hr>
                            <div class="form-group">
                                <div class="col-md-12 pull-right">
                                    <button id="btnSave" type="button" class="btn red">保存</button>

                                    <button id="btnCancel" type="button" class="btn blue">取消</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>