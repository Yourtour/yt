<div id="Page_RestaurantFormView" class="row" data-role="page">
    <div class="col-lg-12">
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="index.html">首页</a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span>美食编辑</span>
                </li>
            </ul>
        </div>
        <!-- END PAGE HEADER-->

        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN SAMPLE FORM PORTLET-->
                <div class="portlet light ">
                    <div class="portlet-body">
                        <form id="RestaurantForm" class="form-horizontal" role="form">
                            <#include "ResourceFormView.ftl">

                            <div class="form-group">
                                <label for="deliciouFood" class="col-md-2 control-label">特色菜品</label>
                                <div class="col-md-4">
                                    <textarea class="form-control" name="deliciouFood" id="deliciouFood" rows="3" cols="160" placeholder="特色菜品"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="foodStandard" class="col-md-2 control-label">餐饮标准</label>
                                <div class="col-md-4">
                                    <textarea class="form-control" name="foodStandard" id="foodStandard" rows="3" cols="160" placeholder="餐饮标准"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="foodTags" class="col-md-2 control-label">美食标签</label>
                                <div class="col-md-4">
                                    <textarea class="form-control" name="foodTags" id="foodTags" rows="3" cols="160" placeholder="美食标签"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="networkInfo" class="col-md-2 control-label">网络信息</label>
                                <div class="col-md-4">
                                    <textarea class="form-control" name="networkInfo" id="networkInfo" rows="3" cols="160" placeholder="网络信息"></textarea>
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