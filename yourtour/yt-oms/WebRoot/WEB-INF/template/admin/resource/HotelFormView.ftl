<div id="Page_HotelFormView" class="row" data-role="page">
    <div class="col-lg-12">
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="index.html">首页</a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span>住宿编辑</span>
                </li>
            </ul>
        </div>
        <!-- END PAGE HEADER-->

        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN SAMPLE FORM PORTLET-->
                <div class="portlet light ">
                    <div class="portlet-body">
                        <form id="HotelForm" class="form-horizontal" role="form">
                            <#include "ResourceFormView.ftl">

                            <div class="form-group">
                                <label for="accommodationStandard" class="col-md-2 control-label">住宿标准</label>
                                <div class="col-md-4">
                                    <textarea class="form-control" name="accommodationStandard" id="accommodationStandard" rows="3" cols="160" placeholder="住宿标准"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="specialRoom" class="col-md-2 control-label">特色房</label>
                                <div class="col-md-4">
                                    <textarea class="form-control" name="specialRoom" id="specialRoom" rows="3" cols="160" placeholder="特色房"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="roomEquipment" class="col-md-2 control-label">房间设施</label>
                                <div class="col-md-4">
                                    <textarea class="form-control" name="roomEquipment" id="roomEquipment" rows="3" cols="160" placeholder="房间设施"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="networkInfo" class="col-md-2 control-label">网络信息</label>
                                <div class="col-md-4">
                                    <textarea class="form-control" name="networkInfo" id="networkInfo" rows="3" cols="160" placeholder="网络信息"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="files" class="col-md-2 control-label">酒店图片</label>
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