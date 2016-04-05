<!--
	页面布局模板
-->
<html lang="en">
  <head>
  	<title>游徒管理平台-<sitemesh:write property='title'/></title>
	<meta name="keywords" content="">
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="${context}/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<link href="${context}/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
	<link href="${context}/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="${context}/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
	<link href="${context}/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
	<!-- END GLOBAL MANDATORY STYLES -->

	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<link href="${context}/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
      <link href="${context}/assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" rel="stylesheet" type="text/css" />
	<link href="${context}/assets/global/plugins/morris/morris.css" rel="stylesheet" type="text/css" />
	<link href="${context}/assets/global/plugins/jquery-datatable/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
	<link href="${context}/assets/global/plugins/jquery-datatable/css/jquery.dataTables.setting.css" rel="stylesheet" type="text/css" />
	<link href="${context}/assets/global/plugins/jquery-jstree/css/default/style.min.css" rel="stylesheet" type="text/css" />
	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN THEME GLOBAL STYLES -->
	<link href="${context}/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
	<link href="${context}/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
	<!-- END THEME GLOBAL STYLES -->
	<!-- BEGIN THEME LAYOUT STYLES -->
	<link href="${context}/assets/layouts/layout2/css/layout.min.css" rel="stylesheet" type="text/css" />
	<link href="${context}/assets/layouts/layout2/css/themes/blue.min.css" rel="stylesheet" type="text/css" id="style_color" />
	<link href="${context}/assets/layouts/layout2/css/custom.min.css" rel="stylesheet" type="text/css" />
	<!-- END THEME LAYOUT STYLES -->
	<link rel="shortcut icon" href="${context}/assets/favicon.ico" /> </head>

	<link href="${context}/assets/apps/css/admin.css" rel="stylesheet" type="text/css"/> </head>

	<script src="${context}/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
	<script src="${context}/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${context}/assets/global/plugins/jquery-datatable/js/jquery.dataTables.min.js" type="text/javascript"></script>

	<script src="http://api.map.baidu.com/api?v=1.4" type="text/javascript"></script>

	  <script src="${context}/assets/apps/js/yt-plugin.js" type="text/javascript"></script>
	  <script src="${context}/assets/apps/js/yt-schedule.js" type="text/javascript"></script>
	  <script src="${context}/assets/apps/js/yt-app.js" type="text/javascript"></script>
  </head>

  <body class="page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid page-schedule" style="height:100%; overflow:hidden!important;">
  	<input id="context" type="hidden" value="${context}"/>

    <div id="schedule-page-header" class="page-header navbar navbar-fixed-top">
        <!-- BEGIN HEADER INNER -->
        <div class="page-header-inner ">
            <!-- BEGIN LOGO -->
            <div class="page-logo">
                <a href="index.html">
                    <img src="${context}/assets/layouts/layout2/img/logo-default.png" alt="logo" class="logo-default" /> </a>

            </div>
            <!-- END LOGO -->

            <!-- BEGIN PAGE TOP -->
            <div class="page-top">
                <!-- BEGIN HEADER SEARCH BOX -->
                <!-- DOC: Apply "search-form-expanded" right after the "search-form" class to have half expanded search box -->
                <!-- END HEADER SEARCH BOX -->
                <!-- BEGIN TOP NAVIGATION MENU -->
                <div class="top-menu">
                    <ul class="nav navbar-nav">
                        <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                        <li class="dropdown">
                            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                <span> 目的地</span>
                                <i class="fa fa-angle-down"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-default schedule-places">
                            </ul>
                        </li>
                    </ul>

                    <ul class="nav navbar-nav" >
                        <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                        <li class="dropdown">
                            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                <span> 景点 </span>
                                <i class="fa fa-angle-down"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-default schedule-types">
                                <li data-value="SCENE" class="selected">景点</li>
                                <li data-value="HOTEL">酒店</li>
                                <li data-value="FOOD">美食</li>
                                <li data-value="TRAFFIC">交通</li>
                            </ul>
                        </li>
                    </ul>

                    <form class="search-form search-form-expanded" action="page_general_search_3.html" method="GET">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="景点/酒店/美食/关键字...... " id="keyword" name="keyword">
							<span class="input-group-btn">
								<a href="javascript:;" class="btn submit">
									<i class="icon-magnifier"></i>
								</a>
							</span>
                        </div>
                    </form>
                </div>
                <!-- END TOP NAVIGATION MENU -->
            </div>
            <!-- END PAGE TOP -->
        </div>
        <!-- END HEADER INNER -->
    </div>
    <!-- END HEADER -->

  	<!-- BEGIN HEADER & CONTENT DIVIDER -->
    <div class="clearfix"> </div>
    <!-- END HEADER & CONTENT DIVIDER -->

  	<!-- BEGIN CONTAINER -->
  	<div id="schedule-page-container" class="page-container">
        <input type="hidden" id="routeId" value="${routeId}"/>
        <div class="page-sidebar-wrapper">
            <div class="page-sidebar navbar-collapse collapse">
                <ul  style="height:100%" class="page-sidebar-menu  page-header-fixed page-sidebar-menu-hover-submenu schedule" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
                    <li class="nav-item schedule-add">
                        <a href="javascript:;" class="nav-link nav-toggle">
                            <div class="icon-schedule-add"></div>
                        </a>
                    </li>
                </ul>
            </div>
		</div>

        <div id="Page_ScheduleFormView" class="page-content-wrapper"">
            <div  class="portlet-body">
                <div id="map-container" class="map-container">
                </div>

                <div id="detail-container" class="detail-container">
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
                                        <label for="type" class="col-md-3 control-label">地址</label>
                                        <div class="col-md-9">
                                            <span id="address"></span>
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
            </div>
		</div>
  	</div>

  	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
 	<!--[if lt IE 9]>
 	<!--[if lt IE 9]>
		<script src="${context}/assets/global/plugins/respond.min.js"></script>
		<script src="${context}/assets/global/plugins/excanvas.min.js"></script>
    <![endif]-->
	<!-- BEGIN CORE PLUGINS -->
	<script src="${context}/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
	<script src="${context}/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
	<script src="${context}/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
	<script src="${context}/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="${context}/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
	<script src="${context}/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
	<script src="${context}/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
	<script src="${context}/assets/global/plugins/jquery-jstree/js/jquery.jstree.min.js" type="text/javascript"></script>

	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN THEME GLOBAL SCRIPTS -->
	<script src="${context}/assets/global/plugins/jquery-datatable/js/jquery.dataTable.setting.js" type="text/javascript"></script>
	<script src="${context}/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
  <script src="${context}/assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js" type="text/javascript"></script>

	<script src="${context}/assets/global/scripts/app.min.js" type="text/javascript"></script>



	<!-- END THEME GLOBAL SCRIPTS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<!-- END PAGE LEVEL SCRIPTS -->
	<!-- BEGIN THEME LAYOUT SCRIPTS -->
	<script src="${context}/assets/layouts/layout2/scripts/layout.min.js" type="text/javascript"></script>
	<script src="${context}/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
	<!-- END THEME LAYOUT SCRIPTS -->

    <script language="javascript">
        $(document).ready(function(){
            $.Schedule.init(${routeId});
        });
    </script>
  </body>
</html>	