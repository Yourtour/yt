<head>
    <title>活动管理</title>
</head>

<body>
    <h3 class="page-title"> 活动管理 </h3>

    <#include "activity/ListView.ftl">
    <#include "activity/FormView.ftl">
    <#include "activity/ContentFormView.ftl">
    <#include "route/SearchListView.ftl">

    <script language="javascript">
        $(document).ready(function(){
            $.Activity.init();
        });
    </script>
</body>