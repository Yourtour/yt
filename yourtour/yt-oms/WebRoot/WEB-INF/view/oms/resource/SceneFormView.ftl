<div id="Page_SceneFormView" class="row" data-role="page">
    <div class="col-lg-12">
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="index.html">首页</a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span>游玩编辑</span>
                </li>
            </ul>
        </div>
        <!-- END PAGE HEADER-->

        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN SAMPLE FORM PORTLET-->
                <div class="portlet light ">
                    <div class="portlet-body">
                        <form id="SceneForm" class="form-horizontal" role="form">
                            <#include "ResourceFormView.ftl">

                            <div class="form-group">
                                <label for="ticket" class="col-md-2 control-label">门票信息</label>
                                <div class="col-md-10">
                                    <textarea class="form-control" name="ticket" id="ticket" rows="3" cols="160" placeholder="门票信息"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="sceneMap" class="col-md-2 control-label">景区地图</label>
                                <div class="col-md-10">
                                    <input type="text" class="form-control" name="sceneMap" id="sceneMap" placeholder="景区地图">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="specialScene" class="col-md-2 control-label">必玩景点</label>
                                <div class="col-md-10">
                                    <textarea class="form-control" name="specialScene" id="specialScene" rows="3" cols="160" placeholder="必玩景点"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="sceneTraffic" class="col-md-2 control-label">景区交通</label>
                                <div class="col-md-10">
                                    <input type="text" class="form-control" name="sceneTraffic" id="sceneTraffic" placeholder="景区交通">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="imageUrl" class="col-md-2 control-label">游玩图片</label>
                                <div class="col-md-10">
                                    <input type="file" name="imageUrl" id="imageUrl">
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