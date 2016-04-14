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
                <form id="ActivityForm" class="form-horizontal" role="form">
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <ul class="nav nav-tabs" style="float:left">
                                <li class="active">
                                    <a href="#portlet_intro" data-toggle="tab">活动概述 </a>
                                </li>
                                <li id="tab_content">
                                    <a href="#portlet_content" data-toggle="tab">活动内容</a>
                                </li>
                            </ul>

                            <div class="actions">
                                <div class="btn-group">
                                    <button id="btn_save" type="button" class="btn red">
                                        <i class="fa fa-save"></i>保存</button>

                                    <button id="btn_cancel" type="button" class="btn blue">
                                        <i class="fa fa-reply"></i>取消</button>
                                </div>
                            </div>
                        </div>

                        <div class="portlet-body">
                            <div class="tab-content">
                                <div class="tab-pane active" id="portlet_intro">

                                    <input type="hidden" class="form-control" id="id" name="id">

                                    <div class="form-group">
                                        <label for="type" class="col-md-1 control-label">活动标题</label>
                                        <div class="col-md-5">
                                            <input type="text" class="form-control" name="title" id="title">
                                        </div>

                                        <label for="type" class="col-md-1 control-label">活动副标题</label>
                                        <div class="col-md-5">
                                            <input type="text" class="form-control" name="subTitle" id="subTitle">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="name" class="col-md-1 control-label">活动时间</label>
                                        <div class="col-md-2">
                                            <input id="startTime" name="startTime" class="form-control date-picker" size="16" type="text" value="" />
                                        </div>
                                        <div class="col-md-2">
                                            <input id="endTime" name="endTime" class="form-control date-picker" size="16" type="text" value="" />
                                        </div>
                                        <div class="col-md-1">
                                        </div>

                                        <label for="name" class="col-md-1 control-label">活动组织者</label>
                                        <div class="col-md-5">
                                            <input id="userId" name="userId" class="form-control" type="text" value="" />
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="value" class="col-md-1 control-label">活动概述</label>
                                        <div class="col-md-11">
                                            <textarea id="brief" name="brief" class="form-control" rows="5"></textarea>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="code" class="col-md-1 control-label">活动主题</label>
                                        <div class="col-md-11">
                                            <input type="text" class="form-control" name="tags" id="tags">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="code" class="col-md-1 control-label">推荐位置</label>
                                        <div class="col-md-11">
                                            <div class="checkbox-list">
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" name="homeRecommend" id="homeRecommend" value="1">首页</label>
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" name="placeRecommend" id="placeRecommend" value="1">目的地</label>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="value" class="col-md-1 control-label">封面图片</label>
                                        <div class="col-md-11">
                                            <input type="file" id="imageUrl">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="value" class="col-md-1 control-label">推荐行程</label>
                                        <div class="col-md-11">
                                            <input type="hidden" name="routeIds" id="routeIds"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="tab-pane" id="portlet_content">
                                    <div class="row" >
                                        <div class="col-md-12">
                                            <textarea id="content" name="content" class="wysihtml5 form-control" rows="20">afdasfasdfasfasdfdsafsd</textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>