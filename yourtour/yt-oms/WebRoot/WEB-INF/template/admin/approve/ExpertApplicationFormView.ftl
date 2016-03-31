<div id="Page_ExpertApplicationFormView" class="row" data-role="page">
    <div class="col-lg-12">
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="index.html">首页</a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span>达人资格审核</span>
                </li>
            </ul>
        </div>
        <!-- END PAGE HEADER-->

        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN SAMPLE FORM PORTLET-->
                <div class="portlet light ">
                    <div class="portlet-body">
                        <form id="BannerForm" class="form-horizontal" role="form">
                            <input type="hidden" class="form-control" id="id" name="id">

                            <div class="form-group">
                                <label for="realName" class="col-md-2 control-label">姓名</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="realName" id="realName" placeholder="姓名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="status" class="col-md-2 control-label">审核结果</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="status" id="status" placeholder="审核结果">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="approveResult" class="col-md-2 control-label">审核评价</label>
                                <div class="col-md-8">
                                    <textarea class="form-control" name="approveResult" id="approveResult" rows="3" cols="160" placeholder="审核评价"></textarea>
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