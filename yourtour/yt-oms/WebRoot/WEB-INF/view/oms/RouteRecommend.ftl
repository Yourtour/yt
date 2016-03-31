<head>
    <title>行程管理</title>
</head>

<body>
    <h3 class="page-title"> 行程管理 </h3>

    <#include "route/RecommendListView.ftl">
    <#include "route/FormView.ftl">
    <#include "route/ScheduleFormView.ftl">

    <script language="javascript">
        $(document).ready(function(){
            $.RouteRecommend.init();
        });
    </script>
</body>