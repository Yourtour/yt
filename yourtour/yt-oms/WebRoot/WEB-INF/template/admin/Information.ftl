<head>
    <title>资讯管理</title>
</head>

<body>
    <h3 class="page-title"> 资讯管理 </h3>

    <#include "information/ListView.ftl">
    <#include "information/FormView.ftl">

    <script language="javascript">
        $(document).ready(function(){
            $.Information.init();
        });
    </script>
</body>