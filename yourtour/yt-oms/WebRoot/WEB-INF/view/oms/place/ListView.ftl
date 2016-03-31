<div id="Page_PlaceListView" class="row" data-role="page">
    <div class="col-lg-12">
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="index.html">首页</a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span>目的地管理</span>
                </li>
            </ul>
        </div>
        <!-- END PAGE HEADER-->

        <div class="portlet light ">
            <div class="portlet-body">
                <div class="row">
                    <div class="col-md-2">
                        <div>
                            <button id="btn_add" class="btn red">
                                <i class="fa fa-plus"></i> 新增</button>

                            <button id="btn_delete" class="btn default">
                                <i class="fa fa-times"></i> 删除</button>
                        </div>

                        <div id="placetree" style="margin-top:20px;">
                        </div>
                    </div>
                    <div class="col-md-10">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="pull-right">
                                    <button id="btn_save" class="btn red">
                                        <i class="fa fa-save"></i> 保存</button>
                                </div>
                            </div>
                        </div>

                        <div style="margin-top:20px;">
                            <form id="PlaceForm" class="form-horizontal" role="form">
                                <input type="hidden" class="form-control" id="id" name="id"/>

                                <div class="form-group">
                                    <label for="type" class="col-md-2 control-label">所属目的地</label>
                                    <div class="col-md-10">
                                        <input type="hidden" class="form-control" id="parentId" name="parentId"/>
                                        <input type="text" readonly class="form-control" name="parentName" id="parentName"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="type" class="col-md-2 control-label">名称</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="name" id="name"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="type" class="col-md-2 control-label">编码</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="code" id="code"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="type" class="col-md-2 control-label">简介</label>
                                    <div class="col-md-10">
                                        <textarea class="form-control" name="intro" id="intro" rows="3"></textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="type" class="col-md-2 control-label">特色</label>
                                    <div class="col-md-10">
                                        <textarea class="form-control" name="feature" id="feature" rows="3"></textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="type" class="col-md-2 control-label">交通</label>
                                    <div class="col-md-10">
                                        <textarea class="form-control" name="traffic" id="traffic" rows="3"></textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="type" class="col-md-2 control-label">特产</label>
                                    <div class="col-md-10">
                                        <textarea class="form-control" name="specialty" id="specialty" rows="3"></textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="type" class="col-md-2 control-label">图片</label>
                                    <div class="col-md-10">
                                        <input type="file" id="imageUrl">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
