
<html lang="en">
  <head>
  	<title>游徒管理平台</title>
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
	<link href="${context}/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.css" rel="stylesheet" type="text/css" />
	<link href="${context}/assets/global/plugins/morris/morris.css" rel="stylesheet" type="text/css" />
	<link href="${context}/assets/global/plugins/jquery-datatable/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
	<link href="${context}/assets/global/plugins/jquery-datatable/css/jquery.dataTables.setting.css" rel="stylesheet" type="text/css" />
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

	<link href="${context}/assets/apps/css/admin.css" rel="stylesheet" type="text/css"/>

  	<link href="${context}/assets/pages/css/login.min.css" rel="stylesheet" type="text/css"/>

	<script src="${context}/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
	<script src="${context}/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
  </head>

  <body class="login">
  	  <input id="context" type="hidden" value="${context}"/>

	  <div class="menu-toggler sidebar-toggler"></div>
	  <!-- END SIDEBAR TOGGLER BUTTON -->
	  <!-- BEGIN LOGO -->
	  <div class="logo">
		  <a href="index.html">
			  <img src="${context}/assets/pages/img/logo-big.png" alt="" /> </a>
	  </div>
	  <!-- END LOGO -->
	  <!-- BEGIN LOGIN -->
	  <div class="content">
		  <!-- BEGIN LOGIN FORM -->
		  <form class="login-form">
			  <h3 class="form-title font-green">Sign In</h3>
			  <div class="alert alert-danger display-hide">
				  <button class="close" data-close="alert"></button>
				  <span> Enter any username and password. </span>
			  </div>
			  <div class="form-group">
				  <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				  <label class="control-label visible-ie8 visible-ie9">Username</label>
				  <input class="form-control" type="text" placeholder="Username" id="username" name="username" /> </div>
			  <div class="form-group">
				  <label class="control-label visible-ie8 visible-ie9">Password</label>
				  <input class="form-control" type="password" placeholder="Password" id="password" name="password" /> </div>
			  <div class="form-actions">
				  <button id="login" type="button" class="btn green uppercase">Login</button>
				  <label class="rememberme check">
					  <input type="checkbox" name="remember" value="1" />Remember </label>
			  </div>
		  </form>
	  </div>

	  <div class="copyright hide"> 2014 © Metronic. Admin Dashboard Template. </div>
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

	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN THEME GLOBAL SCRIPTS -->
	<script src="${context}/assets/global/scripts/app.min.js" type="text/javascript"></script>
	<script src="${context}/assets/apps/js/yt-plugin.js" type="text/javascript"></script>
    <script src="${context}/assets/apps/js/yt-account.js" type="text/javascript"></script>

      <!-- END THEME GLOBAL SCRIPTS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<!-- END PAGE LEVEL SCRIPTS -->
	<!-- BEGIN THEME LAYOUT SCRIPTS -->
	<script src="${context}/assets/layouts/layout2/scripts/layout.min.js" type="text/javascript"></script>
	<script src="${context}/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
	<!-- END THEME LAYOUT SCRIPTS -->

	  <script language="javascript">
		  $(document).ready(function(){
			  $("#login").on('click', function(){
				  $.Account.login();
			  })
		  });
	  </script>
  </body>
</html>