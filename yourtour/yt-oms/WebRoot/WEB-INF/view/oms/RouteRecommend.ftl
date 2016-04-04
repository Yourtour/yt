<head>
    <title>行程推荐</title>
</head>

<body>
    <h3 class="page-title"> 行程推荐 </h3>

    <#include "route/RecommendListView.ftl">
    <#include "route/FormView.ftl">
    <#include "route/ScheduleFormView.ftl">

    <script language="javascript">
        $(document).ready(function(){
            $.RouteRecommend.init();
        });
    </script>
</body>