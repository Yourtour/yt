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
                            <li id="tab_content">
                                <a href="#portlet_content" data-toggle="tab">活动内容</a>
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
                                            <input id="startTime" name="startTime" class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="" />
                                        </div>
                                        <div class="col-md-7">
                                            <input id="endTime" name="endTime" class="form-control form-control-inline input-medium date-picker" size="16" type="text" value="" />
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="code" class="col-md-2 control-label">活动主题</label>
                                        <div class="col-md-10">
                                            <input type="text" class="form-control" name="tag" id="tag">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="code" class="col-md-2 control-label">推荐位置</label>
                                        <div class="col-md-10">
                                            <div class="checkbox-list">
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" name="homeRecommend" id="homeRecommend" value="1">首页</label>
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" name="placeRecommend" id="placeRecommend" value="1">目的地</label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="value" class="col-md-2 control-label">活动图片</label>
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

                            <div class="tab-pane active" id="portlet_content">
                                <div id="activity_content">

                                </div>

                                <div style="margin-top:20px;">
                                    <button id="btn_content_add" class="btn red">
                                        <i class="fa fa-plus"></i>添加</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>