<head>
    <title>行程管理</title>
</head>

<body>
    <h3 class="page-title"> 行程管理 </h3>

    <#include "route/ListView.ftl">
    <#include "route/FormView.ftl">

    <script language="javascript">
        $(document).ready(function(){
            $.Route.init();
        });
    </script>
</body>