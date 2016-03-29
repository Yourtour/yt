<div id="Page_BannerFormView" class="row" data-role="page">
    <div class="col-lg-12">
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="index.html">首页</a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span>广告位编辑</span>
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
                                <label for="title" class="col-md-2 control-label">标题</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="title" id="title" placeholder="标题">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="subTitle" class="col-md-2 control-label">子标题</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" name="subTitle" id="subTitle" placeholder="子标题">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="content" class="col-md-2 control-label">内容</label>
                                <div class="col-md-8">
                                    <textarea class="form-control" name="content" id="content" rows="3" cols="160" placeholder="内容"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="startTime" class="col-md-2 control-label">开始时间</label>
                                <div class="col-md-4">
                                    <input type="date" class="form-control" name="startTime" id="startTime" placeholder="开始时间">
                                </div>
                                <label for="endTime" class="col-md-2 control-label">结束时间</label>
                                <div class="col-md-4">
                                    <input type="date" class="form-control" name="endTime" id="startTime" placeholder="结束时间">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="files" class="col-md-2 control-label">Banner图片</label>
                                <textarea class="form-control" name="imageUrl" disabled id="imageUrl"></textarea>
                                <div class="col-md-2">
                                    <input type="file" id="files" name="files" multiple="multiple">
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