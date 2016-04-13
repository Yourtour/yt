<!--
    本页面主要为达人提供客户行程定制功能
-->
<head>
    <title>行程定制</title>
</head>

<body>
    <h3 class="page-title"> 行程定制 </h3>

    <#include "route/MakeListView.ftl">

    <script language="javascript">
        $(document).ready(function(){
            $.Route.make.init();
        });
    </script>
</body>