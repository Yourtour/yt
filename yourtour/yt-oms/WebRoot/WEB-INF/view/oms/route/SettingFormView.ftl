<div id="Page_RouteSettingView" class="row" data-role="page" >
    <input type="hidden" id="type" value="${type}"/>
    <div class="col-lg-12">
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <span>行程设置</span>
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
                            <input type="hidden" value="-1" class="form-control" id="id" name="id">

                            <div class="form-group">
                                <label for="name" class="col-md-1 control-label">行程名称</label>
                                <div class="col-md-5">
                                    <input type="text" class="form-control" name="name" id="name" placeholder="行程名称">
                                </div>

                                <label for="code" class="col-md-1 control-label">行程线路</label>
                                <div class="col-md-5">
                                    <input type="text" class="form-control" name="lineName" id="lineName" placeholder="行程线路">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="value" class="col-md-1 control-label">日程安排</label>
                                <div class="col-md-3">
                                    <input id="startDate" name="startDate" disabled class="form-control input-inline date-picker" size="24" type="text" value="" />
                                    <span class="help-inline">开始, 一共</span>
                                </div>

                                <div class="col-md-2">
                                    <input type="text" value="0" class="form-control input-inline" name="duration" disabled id="duration" size="15" placeholder="行程天数">
                                    <span class="help-inline">天</span>
                                </div>

                                <label for="value" class="col-md-1 control-label">目的地</label>
                                <div class="col-md-5">
                                    <div class="input-group">
                                        <input type="text" class="form-control" name="toPlaces" id="toPlaces" placeholder="目的地">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="value" class="col-md-1 control-label">行程标签</label>
                                <div class="col-md-5">
                                    <div class="input-group select2-bootstrap-append" style="width:100%">
                                        <select id="tags" class="form-control select2" multiple>
                                            <option></option>
                                            <#list routeTags as tag>
                                                <option value="${tag.id}">${tag.name}</option>
                                            </#list>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="value" class="col-md-1 control-label">行程特点</label>
                                <div class="col-md-5">
                                    <textarea class="form-control" rows="6" id="feature" name="feature" placeholder="行程特点"></textarea>
                                </div>

                                <label for="value" class="col-md-1 control-label">推荐理由</label>
                                <div class="col-md-5">
                                    <textarea class="form-control" rows="6" id="reason" name="reason" placeholder="推荐理由"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="value" class="col-md-1 control-label">适合人群</label>
                                <div class="col-md-5">
                                    <textarea class="form-control" rows="6" id="suitable" name="suitable" placeholder="适合人群"></textarea>
                                </div>

                                <label for="value" class="col-md-1 control-label">费用信息</label>
                                <div class="col-md-5">
                                    <textarea class="form-control" rows="6" id="charge" name="charge" placeholder="费用信息"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="value" class="col-md-1 control-label">费用包括</label>
                                <div class="col-md-5">
                                    <textarea class="form-control" rows="6" id="chargeIncludes" name="chargeIncludes" placeholder="费用包括"></textarea>
                                </div>

                                <label for="value" class="col-md-1 control-label">费用不包括</label>
                                <div class="col-md-5">
                                    <textarea class="form-control" rows="6" id="chargeExcludes" name="chargeExcludes" placeholder="费用不包括"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="value" class="col-md-1 control-label">服务承诺</label>
                                <div class="col-md-5">
                                    <textarea class="form-control" rows="6" id="promises" name="promises" placeholder="服务承诺"></textarea>
                                </div>

                                <label for="value" class="col-md-1 control-label">注意事项</label>
                                <div class="col-md-5">
                                    <textarea class="form-control" rows="6" id="attentions" name="attentions" placeholder="注意事项"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="value" class="col-md-1 control-label">图片</label>
                                <div class="col-md-11" >
                                    <input type="text" name="imageUrl" id="imageUrl">
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