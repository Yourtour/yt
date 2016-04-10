<div id="Page_ExpertListView" class="row" data-role="page">
    <div class="col-lg-12">
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="index.html">首页</a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span>达人管理</span>
                </li>
            </ul>
        </div>
        <!-- END PAGE HEADER-->

        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN SAMPLE FORM PORTLET-->
                <div class="portlet light ">
                    <div class="portlet-body">
                        <div id="DictListView_Criteria">
                            <div class="row bottom-space">
                                <div class="col-md-6 col-sm-12">
                                    <div class="table-group-actions pull-left">

                                    </div>
                                </div>

                                <div class="col-md-6 col-sm-12">
                                    <div class="pull-right">
                                        <button id="btn_frozen" class="btn red">
                                            <i class="fa fa-times"></i>冻结</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <table id="datatable_expert" class="row-border" cellspacing="0" width="100%"  data-rest="${context}/rest/oms/experts/query">
                            <thead>
                                <tr>
                                    <th>
                                        <input type="checkbox" class="group-checkable">
                                    </th>
                                    <th>姓名</th>
                                    <th>身份证号码</th>
                                    <th>标签</th>
                                </tr>
                            </thead>

                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>