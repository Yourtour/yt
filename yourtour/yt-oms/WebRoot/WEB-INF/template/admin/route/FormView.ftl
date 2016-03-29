<div id="Page_RouteFormView" class="row" data-role="page">
    <div class="col-lg-12">
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="index.html">首页</a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span>行程编辑</span>
                </li>
            </ul>
        </div>
        <!-- END PAGE HEADER-->

        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN SAMPLE FORM PORTLET-->
                <div class="portlet light ">
                    <div class="portlet-body">
                        <form id="RouteForm" class="form-horizontal" role="form">
                            <input type="hidden" class="form-control" id="id" name="id">

                            <div class="form-group">
                                <label for="name" class="col-md-1 control-label">名称</label>
                                <div class="col-md-5">
                                    <input type="text" class="form-control" name="name" id="name" placeholder="行程名称">
                                </div>

                                <label for="code" class="col-md-1 control-label">线路</label>
                                <div class="col-md-5">
                                    <input type="text" class="form-control" name="lineName" id="lineName" placeholder="行程线路">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="value" class="col-md-1 control-label">天数</label>
                                <div class="col-md-5">
                                    <input type="text" class="form-control" name="duration" id="duration" placeholder="行程天数"> </div>

                                <label for="value" class="col-md-1 control-label">目的地</label>
                                <div class="col-md-5">
                                    <input type="text" class="form-control" name="toPlaces" id="toPlaces" placeholder="选择目的地"> </div>
                            </div>

                            <div class="form-group">
                                <label for="value" class="col-md-1 control-label">费用</label>
                                <div class="col-md-5">
                                    <input type="text" class="form-control" name="charge" id="charge" placeholder="人均费用"> </div>
                            </div>

                            <div class="form-group">
                                <label for="value" class="col-md-1 control-label">包括</label>
                                <div class="col-md-11">
                                    <textarea class="form-control" rows="6" id="chargeIncludes" name="chargeIncludes"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="value" class="col-md-1 control-label">不包括</label>
                                <div class="col-md-11">
                                    <textarea class="form-control" rows="6" id="chargeExcludes" name="chargeExcludes"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="value" class="col-md-1 control-label">特点</label>
                                <div class="col-md-11">
                                    <textarea class="form-control" rows="6" id="feature" name="feature"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="value" class="col-md-1 control-label">图片</label>
                                <div class="col-md-11">
                                    <input type="file" name="imageUrl" id="imageUrl1">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="value" class="col-md-1 control-label"></label>
                                <div class="col-md-11">
                                    <input type="file" name="imageUrl" id="imageUrl2">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="value" class="col-md-1 control-label"></label>
                                <div class="col-md-11">
                                    <input type="file" name="imageUrl" id="imageUrl3">
                                </div>
                            </div>

                            <hr>
                            <div class="form-group">
                                <div class="col-md-12 pull-right">
                                    <button id="btn_save" type="button" class="btn red">保存</button>

                                    <button id="btn_cancel" type="button" class="btn blue">取消</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>