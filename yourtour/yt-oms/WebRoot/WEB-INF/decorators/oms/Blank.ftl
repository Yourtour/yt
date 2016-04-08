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
	<link href="${context}/assets/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
    <link href="${context}/assets/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
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
	<script src="${context}/assets/apps/js/yt-schedule.js" type="text/javascript"></script>
  </head>

  <body class="page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid page-schedule" style="height:100%; overflow-x:hidden!important;">
  	<input id="context" type="hidden" value="${context}"/>

    <sitemesh:write property='body' />

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
    <script src="${context}/assets/global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
	<script src="${context}/assets/global/scripts/app.min.js" type="text/javascript"></script>



	<!-- END THEME GLOBAL SCRIPTS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<!-- END PAGE LEVEL SCRIPTS -->
	<!-- BEGIN THEME LAYOUT SCRIPTS -->
	<script src="${context}/assets/layouts/layout2/scripts/layout.min.js" type="text/javascript"></script>
	<script src="${context}/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
  </body>
</html>	