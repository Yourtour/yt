<div id="Page_MakeListView" class="row" data-role="page">
    <input id="type" type="hidden" value="RECOMMEND"/>
    <div class="col-lg-12">
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="index.html">首页</a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span>行程定制</span>
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
                                        <button id="btn_delivery" class="btn red">
                                            <i class="fa fa-plus"></i>交付</button>

                                        <button id="btn_plan" class="btn red">
                                            <i class="fa fa-plus"></i>定制</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <table id="datatable_make" class="row-border" cellspacing="0" width="100%"
                               data-rest="${context}/rest/oms/order/service/route-make"
                               data-method="GET">
                            <thead>
                                <tr>
                                    <th>
                                        <input type="checkbox" class="group-checkable">
                                    </th>
                                    <th>
                                        订单名称
                                    </th>

                                    <th>
                                        提交用户
                                    </th>

                                    <th>
                                        提交时间
                                    </th>

                                    <th>
                                        计划交付时间
                                    </th>

                                    <th>
                                        实际交付时间
                                    </th>

                                    <th>
                                        状态
                                    </th>
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
