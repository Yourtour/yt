<div id="detail-container" class="detail-container" data-role="page">
    <div class="portlet light bg-inverse">
        <div class="portlet-title">
            <div id="caption" class="caption" style="padding-left:60px">
                <div id="name" class="caption-subject bold font-green-haze uppercase">  </div>
                <div id="address"></div>
            </div>
            <div class="actions">
                <a id="btn_detail_hide" class="btn btn-circle btn-icon-only btn-default" href="javascript:;">
                    <i class="fa fa-times"></i>
                </a>
            </div>
        </div>
        <div class="portlet-body">
            <div class="scroller" style="height:75%" data-rail-visible="1" data-rail-color="yellow" data-handle-color="#a1b2bd">
                <div id="info-resource">
                    <div class="row static-info" height="100px">
                        <div class="col-lg-12">
                            <img id="image" style="width:100%"/>
                        </div>
                    </div>

                    <div class="row static-info">
                        <label for="type" class="col-md-3 control-label">简介</label>
                        <div class="col-md-9">
                            <span id="memo"></span>
                        </div>
                    </div>

                    <div class="row static-info">
                        <label for="type" class="col-md-3 control-label">推荐理由</label>
                        <div class="col-md-9">
                            <span id="memo"></span>
                        </div>
                    </div>

                    <div class="actions">
                        <button id="btn-schedule" class="btn btn-lg blue btn-block">加入行程</button>
                    </div>
                </div>

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