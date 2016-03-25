<head>
    <title>活动管理</title>
</head>

<body>
    <h3 class="page-title"> 活动管理 </h3>

    <#include "activity/ActivityListView.ftl">
    <#include "activity/ActivityFormView.ftl">

    <script language="javascript">
        $(document).ready(function(){
            $.ActivityManagement.init();
        });
    </script>
</body>