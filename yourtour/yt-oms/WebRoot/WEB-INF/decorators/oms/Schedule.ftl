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
  </head>

  <body class="page-header-fixed page-sidebar-closed-hide-logo">
  	<input id="context" type="hidden" value="${context}"/>

    <div class="page-header navbar navbar-fixed-top">
  		<!-- BEGIN HEADER INNER -->
		<div class="page-header-inner ">
		<!-- BEGIN LOGO -->
		<div class="page-logo">
			<img src="${context}/assets/layouts/layout2/img/logo-default.png" alt="logo" class="logo-default" />
		</div>
		<!-- END LOGO -->

		<!-- BEGIN PAGE TOP -->
		<div class="page-top">
            <form class="search-form search-form-expanded" action="page_general_search_3.html" method="GET">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search..." name="query">
					<span class="input-group-btn">
						<a href="javascript:;" class="btn submit">
							<i class="icon-magnifier"></i>
						</a>
					</span>
                </div>
            </form>

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
  	<div id="container" class="page-container">
        <div class="page-sidebar-wrapper">
            <div class="page-sidebar navbar-collapse collapse">
                <div class="portlet light portlet-fit">
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
		</div>
        <div class="page-content-wrapper">
			<!-- BEGIN CONTENT -->
			<div id="Page_ScheduleFormView">
				<div class="portlet-body" id="map-container" style="height: 605px; background-color: green">

				</div>
			</div>
			<!-- END CONTENT -->
		</div>
  	</div>

  	<!-- BEGIN FOOTER -->
  	<div class="page-footer-fixed page-footer">
  		<div class="page-footer-inner"> 2015 &copy; Metronic by keenthemes.
		    <a href="http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes" title="Purchase Metronic just for 27$ and get lifetime updates for free" target="_blank">Purchase Metronic!</a>
		</div>
		<div class="scroll-to-top">
		    <i class="icon-arrow-up"></i>
		</div>
  	</div>
  	<!-- END FOOTER -->
  	
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

	<script src="${context}/assets/global/scripts/app.min.js" type="text/javascript"></script>

	<script src="${context}/assets/apps/js/yt-plugin.js" type="text/javascript"></script>
	<script src="${context}/assets/apps/js/yt-dict.js" type="text/javascript"></script>
	<script src="${context}/assets/apps/js/yt-activity.js" type="text/javascript"></script>
	<script src="${context}/assets/apps/js/yt-information.js" type="text/javascript"></script>
	<script src="${context}/assets/apps/js/yt-account.js" type="text/javascript"></script>
	<script src="${context}/assets/apps/js/yt-place.js" type="text/javascript"></script>
	<script src="${context}/assets/apps/js/yt-route.js" type="text/javascript"></script>
	<script src="${context}/assets/apps/js/yt-sceneResource.js" type="text/javascript"></script>
	<script src="${context}/assets/apps/js/yt-hotelResource.js" type="text/javascript"></script>
	<script src="${context}/assets/apps/js/yt-restaurantResource.js" type="text/javascript"></script>
	<script src="${context}/assets/apps/js/yt-banner.js" type="text/javascript"></script>
	<script src="${context}/assets/apps/js/yt-expert.js" type="text/javascript"></script>
	<script src="${context}/assets/apps/js/yt-expertApplicationApprove.js" type="text/javascript"></script>
	<script src="${context}/assets/apps/js/yt-expertContentApprove.js" type="text/javascript"></script>
	<script src="${context}/assets/apps/js/yt-app.js" type="text/javascript"></script>

	<!-- END THEME GLOBAL SCRIPTS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<!-- END PAGE LEVEL SCRIPTS -->
	<!-- BEGIN THEME LAYOUT SCRIPTS -->
	<script src="${context}/assets/layouts/layout2/scripts/layout.min.js" type="text/javascript"></script>
	<script src="${context}/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
	<!-- END THEME LAYOUT SCRIPTS -->
  </body>
</html>	