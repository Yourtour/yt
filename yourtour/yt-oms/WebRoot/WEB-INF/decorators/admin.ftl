<!--
	页面模板
-->
<html lang="en">
  <head>
  	<title>游徒APP管理平台-<sitemesh:write property='title'/></title>
	<meta name="keywords" content="">
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="../../assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<link href="../../assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
	<link href="../../assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="../../assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />

	<link href="../../assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
	<!-- END GLOBAL MANDATORY STYLES -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<link href="../../assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.css" rel="stylesheet" type="text/css" />

	<link href="../../assets/global/plugins/morris/morris.css" rel="stylesheet" type="text/css" />
	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN THEME GLOBAL STYLES -->
	<link href="../../assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
	<link href="../../assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
	<!-- END THEME GLOBAL STYLES -->
	<!-- BEGIN THEME LAYOUT STYLES -->
	<link href="../../assets/layouts/layout2/css/layout.min.css" rel="stylesheet" type="text/css" />
	<link href="../../assets/layouts/layout2/css/themes/blue.min.css" rel="stylesheet" type="text/css" id="style_color" />
	<link href="../../assets/layouts/layout2/css/custom.min.css" rel="stylesheet" type="text/css" />
	<!-- END THEME LAYOUT STYLES -->
	<link rel="shortcut icon" href="favicon.ico" /> </head>

    <script type="text/javascript">
		var base = '${base}';
	</script>
  </head>
  
  <body class="page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid">
  	<!-- BEGIN HEADER -->
    <div class="page-header navbar navbar-fixed-top">
  		<!-- BEGIN HEADER INNER -->
		<div class="page-header-inner ">
		<!-- BEGIN LOGO -->
		<div class="page-logo">
			<div class="menu-toggler sidebar-toggler">
				<!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
			</div>
		</div>
		<!-- END LOGO -->

		<!-- BEGIN RESPONSIVE MENU TOGGLER -->
		<a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse"> </a>
		<!-- END RESPONSIVE MENU TOGGLER -->

		<!-- BEGIN PAGE TOP -->
		<div class="page-top">

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
  		<!-- BEGIN SIDEBAR -->
        <div class="page-sidebar-wrapper">
			<!-- END SIDEBAR -->
			<div class="page-sidebar navbar-collapse collapse">
				<!-- BEGIN SIDEBAR MENU -->
				<ul class="page-sidebar-menu  page-header-fixed page-sidebar-menu-hover-submenu " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
					<li class="nav-item start active open">
						<a href="javascript:;" class="nav-link nav-toggle">
						    <i class="icon-home"></i>
						    <span class="title">Dashboard</span>
						    <span class="selected"></span>
						    <span class="arrow open"></span>
						</a>
						<ul class="sub-menu">
						    <li class="nav-item start active open">
						        <a href="index.html" class="nav-link ">
						            <i class="icon-bar-chart"></i>
						            <span class="title">Dashboard 1</span>
						            <span class="selected"></span>
						        </a>
						    </li>
						    <li class="nav-item start ">
						        <a href="dashboard_2.html" class="nav-link ">
						            <i class="icon-bulb"></i>
						            <span class="title">Dashboard 2</span>
						            <span class="badge badge-success">1</span>
						        </a>
						    </li>
						    <li class="nav-item start ">
						        <a href="dashboard_3.html" class="nav-link ">
						            <i class="icon-graph"></i>
						            <span class="title">Dashboard 3</span>
						            <span class="badge badge-danger">5</span>
						        </a>
						    </li>
						</ul>
					</li>

					<li class="nav-item  ">
						<a href="javascript:;" class="nav-link nav-toggle">
						    <i class="icon-diamond"></i>
						    <span class="title">UI Features</span>
						    <span class="arrow"></span>
						</a>
						<ul class="sub-menu">
						    <li class="nav-item  ">
						        <a href="ui_colors.html" class="nav-link ">
						            <span class="title">Color Library</span>
						        </a>
						    </li>
						    <li class="nav-item  ">
						        <a href="ui_general.html" class="nav-link ">
						            <span class="title">General Components</span>
						        </a>
						    </li>
						    <li class="nav-item  ">
						        <a href="ui_buttons.html" class="nav-link ">
						            <span class="title">Buttons</span>
						        </a>
						    </li>
						    <li class="nav-item  ">
						        <a href="ui_confirmations.html" class="nav-link ">
						            <span class="title">Popover Confirmations</span>
						        </a>
						    </li>
						    <li class="nav-item  ">
						        <a href="ui_icons.html" class="nav-link ">
						            <span class="title">Font Icons</span>
						        </a>
						    </li>
						    <li class="nav-item  ">
						        <a href="ui_socicons.html" class="nav-link ">
						            <span class="title">Social Icons</span>
						        </a>
						    </li>
						    <li class="nav-item  ">
						        <a href="ui_typography.html" class="nav-link ">
						            <span class="title">Typography</span>
						        </a>
						    </li>
						    <li class="nav-item  ">
						        <a href="ui_tabs_accordions_navs.html" class="nav-link ">
						            <span class="title">Tabs, Accordions & Navs</span>
						        </a>
						    </li>
						    <li class="nav-item  ">
						        <a href="ui_timeline.html" class="nav-link ">
						            <span class="title">Timeline</span>
						        </a>
						    </li>
						    <li class="nav-item  ">
						        <a href="ui_tree.html" class="nav-link ">
						            <span class="title">Tree View</span>
						        </a>
						    </li>
						    <li class="nav-item  ">
						        <a href="javascript:;" class="nav-link nav-toggle">
						            <span class="title">Page Progress Bar</span>
						            <span class="arrow"></span>
						        </a>
						        <ul class="sub-menu">
						            <li class="nav-item ">
						                <a href="ui_page_progress_style_1.html" class="nav-link "> Flash </a>
						            </li>
						            <li class="nav-item ">
						                <a href="ui_page_progress_style_2.html" class="nav-link "> Big Counter </a>
						            </li>
						        </ul>
						    </li>
						    <li class="nav-item  ">
						        <a href="ui_blockui.html" class="nav-link ">
						            <span class="title">Block UI</span>
						        </a>
						    </li>
						    <li class="nav-item  ">
						        <a href="ui_bootstrap_growl.html" class="nav-link ">
						            <span class="title">Bootstrap Growl Notifications</span>
						        </a>
						    </li>
						    <li class="nav-item  ">
						        <a href="ui_notific8.html" class="nav-link ">
						            <span class="title">Notific8 Notifications</span>
						        </a>
						    </li>
						    <li class="nav-item  ">
						        <a href="ui_toastr.html" class="nav-link ">
						            <span class="title">Toastr Notifications</span>
						        </a>
						    </li>
						    <li class="nav-item  ">
						        <a href="ui_bootbox.html" class="nav-link ">
						            <span class="title">Bootbox Dialogs</span>
						        </a>
						    </li>
						    <li class="nav-item  ">
						        <a href="ui_alerts_api.html" class="nav-link ">
						            <span class="title">Metronic Alerts API</span>
						        </a>
						    </li>
						    <li class="nav-item  ">
						        <a href="ui_session_timeout.html" class="nav-link ">
						            <span class="title">Session Timeout</span>
						        </a>
						    </li>
						    <li class="nav-item  ">
						        <a href="ui_idle_timeout.html" class="nav-link ">
						            <span class="title">User Idle Timeout</span>
						        </a>
						    </li>
						    <li class="nav-item  ">
						        <a href="ui_modals.html" class="nav-link ">
						            <span class="title">Modals</span>
						        </a>
						    </li>
						    <li class="nav-item  ">
						        <a href="ui_extended_modals.html" class="nav-link ">
						            <span class="title">Extended Modals</span>
						        </a>
						    </li>
						    <li class="nav-item  ">
						        <a href="ui_tiles.html" class="nav-link ">
						            <span class="title">Tiles</span>
						        </a>
						    </li>
						    <li class="nav-item  ">
						        <a href="ui_datepaginator.html" class="nav-link ">
						            <span class="title">Date Paginator</span>
						        </a>
						    </li>
						    <li class="nav-item  ">
						        <a href="ui_nestable.html" class="nav-link ">
						            <span class="title">Nestable List</span>
						        </a>
						    </li>
						</ul>
					</li>
				</ul>
			<!-- END SIDEBAR MENU -->
			</div>
			<!-- END SIDEBAR -->
		</div>

		<!-- BEGIN CONTENT -->
  		<div class="page-content-wrapper">
  		 	<div class="page-content">
  		 		<sitemesh:write property='body' />
	  		</div>
  		</div>
  		<!-- END CONTENT -->
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
		<script src="../../assets/global/plugins/respond.min.js"></script>
		<script src="../../assets/global/plugins/excanvas.min.js"></script>
    <![endif]-->
	<!-- BEGIN CORE PLUGINS -->
	<script src="../../assets/global/plugins/jquery.min.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="../../assets/global/plugins/moment.min.js" type="text/javascript"></script>

	<script src="../../assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/morris/morris.min.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/morris/raphael-min.js" type="text/javascript"></script>

	<script src="../../assets/global/plugins/counterup/jquery.waypoints.min.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/counterup/jquery.counterup.min.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/amcharts/amcharts/amcharts.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/amcharts/amcharts/serial.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/amcharts/amcharts/pie.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/amcharts/amcharts/radar.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/amcharts/amcharts/themes/light.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/amcharts/amcharts/themes/patterns.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/amcharts/amcharts/themes/chalk.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/amcharts/ammap/ammap.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/amcharts/ammap/maps/js/worldLow.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/amcharts/amstockcharts/amstock.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/flot/jquery.flot.min.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/flot/jquery.flot.resize.min.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/flot/jquery.flot.categories.min.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/jquery-easypiechart/jquery.easypiechart.min.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/jquery.sparkline.min.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/jqvmap/jqvmap/jquery.vmap.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.russia.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.world.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.europe.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.germany.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.usa.js" type="text/javascript"></script>
	<script src="../../assets/global/plugins/jqvmap/jqvmap/data/jquery.vmap.sampledata.js" type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN THEME GLOBAL SCRIPTS -->
	<script src="../../assets/global/scripts/app.min.js" type="text/javascript"></script>
	<!-- END THEME GLOBAL SCRIPTS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="../../assets/pages/scripts/dashboard.min.js" type="text/javascript"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	<!-- BEGIN THEME LAYOUT SCRIPTS -->
	<script src="../../assets/layouts/layout2/scripts/layout.min.js" type="text/javascript"></script>
	<script src="../../assets/layouts/layout2/scripts/demo.min.js" type="text/javascript"></script>
	<script src="../../assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
	<!-- END THEME LAYOUT SCRIPTS -->
  </body>
</html>	