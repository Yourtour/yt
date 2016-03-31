<div id="Page_ScheduleFormView" class="row" data-role="page">

    <div class="col-lg-12">
        <div class="page-bar" id="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a href="index.html">首页</a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span>行程规划</span>
                </li>
            </ul>
        </div>
        <!-- END PAGE HEADER-->

        <div class="row">
            <div class="col-md-3">
                <div class="portlet light portlet-fit" style="height: 600px">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="font-green"></i>
                            <span class="caption-subject bold font-green uppercase"> 行程</span>
                        </div>
                    </div>

                    <div class="portlet-body">
                        <div class="scroller" style="height:500px" data-rail-visible="1" data-rail-color="yellow" data-handle-color="#a1b2bd">
                            <div id="schedule-timeline" class="timeline">

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="mapPanel" class="col-md-9">
                <div class="portlet light portlet-fit bg-inverse bordered">
                    <div class="portlet-body" id="map-container" style="height: 600px;">

                    </div>
                </div>
            </div>

            <div id="detailPanel" class="col-md-9">
                <div class="portlet light detail">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="icon-puzzle font-grey-gallery"></i>
                            <span class="caption-subject bold font-grey-gallery uppercase"> Tools </span>
                            <span class="caption-helper">more samples...</span>
                        </div>
                        <div class="tools">
                            <a id="btn-add" href="javascript:;" class="btn btn-sm"> <i class="fa fa-plus"></i>加入行程</a>
                            <a id="btn-hide" href="javascript:;" class="btn btn-sm"> <i class="fa fa-times"></i>隐藏</a>
                        </div>
                    </div>
                    <div class="portlet-body">
                        <h4>Heading text goes here...</h4>
                        <p> Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget
                            lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet
                            fermentum. Duis mollihs, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. consectetur purus sit amet fermentum. Duis mollis, est
                            non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>