<div id="Page_ActivityFormView" class="row" data-role="page">
    <div class="col-lg-12">
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="index.html">首页</a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span>活动编辑</span>
                </li>
            </ul>
        </div>
        <!-- END PAGE HEADER-->

        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN SAMPLE FORM PORTLET-->
                <div class="portlet box blue">
                    <div class="portlet-title">
                        <ul class="nav nav-tabs">
                            <li class="active">
                                <a href="#portlet_intro" data-toggle="tab">活动概述 </a>
                            </li>
                            <li>
                                <a href="#portlet_content" data-toggle="tab">活动内容</a>
                            </li>

                            <li>
                                <a href="#portlet_route" data-toggle="tab">推荐行程</a>
                            </li>
                        </ul>
                    </div>

                    <div class="portlet-body">
                        <div class="tab-content">
                            <div class="tab-pane active" id="portlet_intro">
                                <form id="ActivityForm" class="form-horizontal" role="form">
                                    <input type="hidden" class="form-control" id="id" name="id">

                                    <div class="form-group">
                                        <label for="type" class="col-md-2 control-label">活动名称</label>
                                        <div class="col-md-10">
                                            <input type="text" class="form-control" name="title" id="title">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="name" class="col-md-2 control-label">活动时间</label>
                                        <div class="col-md-3">
                                            <input class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="" />
                                        </div>
                                        <div class="col-md-7">
                                            <input class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="" />
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="code" class="col-md-2 control-label">活动主题</label>
                                        <div class="col-md-10">
                                            <input type="text" class="form-control" name="code" id="code">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="value" class="col-md-2 control-label">活动图片</label>
                                        <div class="col-md-10">
                                            <input type="text" class="form-control" name="value" id="value"> </div>
                                    </div>

                                    <hr>
                                    <div class="form-group">
                                        <div class="col-md-12 pull-right">
                                            <button id="btnSave" type="button" class="btn red">
                                                <i class="fa fa-save"></i>保存</button>

                                            <button id="btnCancel" type="button" class="btn blue" style=""padding-left:10px>
                                                <i class="fa fa-reply"></i>取消</button>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div class="tab-pane active" id="portlet_content">
                            </div>

                            <div class="tab-pane active" id="portlet_route">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>