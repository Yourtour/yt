<head>
    <title>行程规划</title>
</head>

<body>
    <div id="schedule-page-header" class="page-header navbar navbar-fixed-top">
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
                <div class="top-menu" style="margin-right:10px;">
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

                    <form class="search-form search-form-expanded" method="GET">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="景点/酒店/美食/关键字...... " id="keyword" name="keyword">
                        <span class="input-group-btn">
                            <a href="javascript:;" class="btn submit">
                                <i class="icon-magnifier"></i>
                            </a>
                        </span>
                        </div>
                    </form>

                    <div class="page-actions">
                        <div class="btn-group">
                            <button id="btn-map" type="button" class="btn btn-default">
                                <i class="fa fa-map-o"></i> 行程规划</button>

                            <button id="btn-setting" type="button" class="btn btn-default">
                                <i class="fa fa-cogs"></i> 行程设置

                            <button id="btn-service" type="button" class="btn btn-default"  style="display:none">
                                <i class="fa fa-cogs"></i> 服务设置</button>
                        </div>
                    </div>
                </div>
                <!-- END TOP NAVIGATION MENU -->
            </div>
            <!-- END PAGE TOP -->
        </div>
    </div>
    <!-- END HEADER -->

    <!-- BEGIN HEADER & CONTENT DIVIDER -->
    <div class="clearfix"> </div>
    <!-- END HEADER & CONTENT DIVIDER -->

    <div id="schedule-page-container" class="page-container">
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

        <div class="page-content-wrapper">
            <div id="page-container" class="page-content">
                <div id="Page_RouteMapView" class="map-container" data-role="page">

                </div>
                <#include "route/SettingFormView.ftl">
                <#include "route/ScheduleFormView.ftl">
            </div>
        </div>
    </div>

    <script language="javascript">
        $(document).ready(function(){
            $.Schedule.init(${routeId});
        });
    </script>
</body>