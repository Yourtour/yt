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

  <body class="page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid">
  	<img id="img_scale" style="z-index:10000; position:fixed; top:20px; right:20px;" src="${context}/assets/apps/icon/icon-scale.png"/>
	<input type = "hidden" id="window-size" value="full" />
  	<!-- BEGIN HEADER -->
  	<input id="context" type="hidden" value="${context}"/>
    <div class="page-header navbar navbar-fixed-top">
  		<!-- BEGIN HEADER INNER -->
		<div class="page-header-inner ">
		<!-- BEGIN LOGO -->
		<div class="page-logo">
			<img src="${context}/assets/layouts/layout2/img/logo-default.png" alt="logo" class="logo-default" />
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
					<li class="nav-item  active start  open">
						<a href="Place" class="nav-link nav-toggle">
							<i class="icon-diamond"></i>
							<span class="title">目的地管理</span>
							<span class="arrow"></span>
						</a>
					</li>

					<li class="nav-item ">
						<a href="javascript:;" class="nav-link nav-toggle">
						    <i class="icon-home"></i>
						    <span class="title">资源管理</span>
						    <span class="selected"></span>
						    <span class="arrow open"></span>
						</a>
						<ul class="sub-menu">
						    <li class="nav-item start active open">
						        <a href="SceneResource" class="nav-link ">
						            <i class="icon-bar-chart"></i>
						            <span class="title">游玩</span>
						            <span class="selected"></span>
						        </a>
						    </li>
						    <li class="nav-item start ">
						        <a href="RestaurantResource" class="nav-link ">
						            <i class="icon-bulb"></i>
						            <span class="title">美食</span>
						            <span class="badge badge-success">1</span>
						        </a>
						    </li>
						    <li class="nav-item start ">
						        <a href="HotelResource" class="nav-link ">
						            <i class="icon-graph"></i>
						            <span class="title">住宿</span>
						            <span class="badge badge-danger">5</span>
						        </a>
						    </li>
						</ul>
					</li>

					<li class="nav-item ">
						<a href="Banner" class="nav-link nav-toggle">
							<i class="icon-home"></i>
							<span class="title">广告位管理</span>
							<span class="selected"></span>
							<span class="arrow open"></span>
						</a>
					</li>

					<li class="nav-item ">
						<a href="Activity" class="nav-link nav-toggle">
							<i class="icon-home"></i>
							<span class="title">活动管理</span>
							<span class="selected"></span>
							<span class="arrow open"></span>
						</a>
					</li>

					<li class="nav-item ">
						<a href="Information" class="nav-link nav-toggle">
							<i class="icon-home"></i>
							<span class="title">资讯管理</span>
							<span class="selected"></span>
							<span class="arrow open"></span>
						</a>
					</li>

					<li class="nav-item  ">
						<a href="javascript:;" class="nav-link nav-toggle">
							<i class="icon-diamond"></i>
							<span class="title">行程管理</span>
							<span class="arrow"></span>
						</a>

						<ul class="sub-menu">
							<li class="nav-item start active open">
								<a href="SceneResource" class="nav-link ">
									<i class="icon-bar-chart"></i>
									<span class="title">定制</span>
									<span class="selected"></span>
								</a>
							</li>
							<li class="nav-item start ">
								<a href="Route" class="nav-link ">
									<i class="icon-bulb"></i>
									<span class="title">推荐</span>
								</a>
							</li>
						</ul>
					</li>

					<li class="nav-item  ">
						<a href="PlaceFormView" class="nav-link nav-toggle">
							<i class="icon-diamond"></i>
							<span class="title">达人管理</span>
							<span class="arrow"></span>
						</a>
					</li>

					<li class="nav-item  ">
						<a href="PlaceFormView" class="nav-link nav-toggle">
							<i class="icon-diamond"></i>
							<span class="title">投诉管理</span>
							<span class="arrow"></span>
						</a>
					</li>

					<li class="nav-item  ">
						<a href="PlaceFormView" class="nav-link nav-toggle">
							<i class="icon-diamond"></i>
							<span class="title">推荐管理</span>
							<span class="arrow"></span>
						</a>
					</li>

					<li class="nav-item  ">
						<a href="Account" class="nav-link nav-toggle">
							<i class="icon-diamond"></i>
							<span class="title">用户管理</span>
							<span class="arrow"></span>
						</a>
					</li>

					<li class="nav-item  ">
						<a href="Dictionary" class="nav-link nav-toggle">
							<i class="icon-diamond"></i>
							<span class="title">基础数据管理</span>
							<span class="arrow"></span>
						</a>
					</li>
				</ul>
			<!-- END SIDEBAR MENU -->
			</div>
			<!-- END SIDEBAR -->
		</div>

		<!-- BEGIN CONTENT -->
  		<div class="page-content-wrapper">
  		 	<div id="page-content" class="page-content">
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