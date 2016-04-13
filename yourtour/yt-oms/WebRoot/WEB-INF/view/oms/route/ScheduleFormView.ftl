<div id="Page_ScheduleFormView" class="row" data-role="page">
    <div class="col-lg-12">
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <span>行程安排</span>
                </li>
            </ul>
        </div>
        <!-- END PAGE HEADER-->

        <div class="row">
            <div class="col-md-6">
                <div id="info-resource" class="portlet light">
                    <div class="portlet-title">
                        <div id="caption" class="caption" style="padding-left:60px">
                            <div id="name" class="caption-subject bold font-green-haze uppercase">  </div>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div id="info-resource">
                            <div class="row static-info" height="100px">
                                <div class="col-lg-12">
                                    <img id="image" style="width:100%; height:200px;"/>
                                </div>
                            </div>

                            <div class="row static-info">
                                <label for="type" class="col-md-2 control-label">地址</label>
                                <div class="col-md-10">
                                    <span id="address"></span>
                                </div>
                            </div>

                            <div class="row static-info">
                                <label for="type" class="col-md-2 control-label">简介</label>
                                <div class="col-md-10">
                                    <span id="intro"></span>
                                </div>
                            </div>

                            <div class="row static-info">
                                <label for="type" class="col-md-2 control-label">推荐理由</label>
                                <div class="col-md-10">
                                    <span id="reason"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div id="info-schedule" class="portlet light">
                    <div class="portlet-title">
                        <div class="caption">
                            <div class="caption-subject bold font-green-haze uppercase">行程安排</div>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <div id="info-schedule">
                            <div class="row static-info">
                                <label for="type" class="col-md-3 control-label">名称</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" name="scheduleName" id="scheduleName" placeholder="行程名称">
                                </div>
                            </div>

                            <div class="row static-info">
                                <label for="type" class="col-md-3 control-label">时间安排</label>
                                <div class="col-md-9">
                                    <div class="input-icon">
                                        <i class="fa fa-clock-o"></i>
                                        <input id="startTime" name="startTime" type="text" class="form-control timepicker timepicker-default">
                                    </div>
                                </div>
                            </div>

                            <div class="row static-info">
                                <label for="type" class="col-md-3 control-label"></label>
                                <div class="col-md-9">
                                    <div class="input-icon">
                                        <i class="fa fa-clock-o"></i>
                                        <input id="endTime" name="endTime" type="text" class="form-control timepicker timepicker-default">
                                    </div>
                                </div>
                            </div>

                            <div class="row static-info">
                                <label for="type" class="col-md-3 control-label">推荐理由</label>
                                <div class="col-md-9">
                                    <textarea class="form-control" rows="6" id="reason" name="reason"></textarea>
                                </div>
                            </div>

                            <div class="row static-info">
                                <label for="type" class="col-md-3 control-label">安排简述</label>
                                <div class="col-md-9">
                                    <textarea class="form-control" rows="6" id="memo" name="memo"></textarea>
                                </div>
                            </div>

                            <div class="actions">
                                <button id="btn-save" class="btn btn-lg blue btn-block">保存</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>