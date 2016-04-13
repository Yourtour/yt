<head>
    <title>行程推荐</title>
</head>

<body>
    <h3 class="page-title"> 行程推荐 </h3>

    <#include "route/RecommendListView.ftl">

    <script language="javascript">
        $(document).ready(function(){
            $.Route.recommend.init();
        });
    </script>
</body>