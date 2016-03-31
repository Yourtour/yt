<div id="Page_ActivityContentFormView" class="row" data-role="page">
    <div class="col-lg-12">
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="index.html">首页</a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span>内容编辑</span>
                </li>
            </ul>
        </div>
        <!-- END PAGE HEADER-->

        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN SAMPLE FORM PORTLET-->
                <div class="portlet">
                    <div class="portlet-body">
                        <form id="ActivityContentForm" class="form-horizontal" role="form">
                            <input type="hidden" class="form-control" id="id" name="id">
                            <input type="hidden" class="form-control" id="activityId" name="activityId">

                            <div class="form-group">
                                <label for="type" class="col-md-2 control-label">类型</label>
                                <div class="col-md-10 radio-list">
                                    <label class="radio-inline">
                                        <input type="radio" name="type" id="type_title" value="Title" checked>标题</label>

                                    <label class="radio-inline">
                                        <input type="radio" name="type" id="type_text" value="Text">文字</label>

                                    <label class="radio-inline">
                                        <input type="radio" name="type" id="type_image" value="Image">图片</label>

                                </div>
                            </div>

                            <div class="form-group">
                                <label for="name" class="col-md-2 control-label">主标题</label>
                                <div class="col-md-4">
                                    <input type="text" class="form-control" name="title" id="title">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="name" class="col-md-2 control-label">子标题</label>
                                <div class="col-md-4">
                                    <input type="text" class="form-control" name="subTitle" id="subTitle">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="name" class="col-md-2 control-label">内容</label>
                                <div class="col-md-10">
                                   <textarea class="form-control" rows="10" id="content" name="content"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="name" class="col-md-2 control-label">图片</label>
                                <div class="col-md-10">
                                    <input type="file" id="imageUrl">
                                </div>
                            </div>

                            <hr>
                            <div class="form-group">
                                <div class="col-md-12 pull-right">
                                    <button id="btn_save" type="button" class="btn red">
                                        <i class="fa fa-save"></i>保存</button>

                                    <button id="btn_cancel" type="button" class="btn blue">
                                        <i class="fa fa-reply"></i>取消</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>