<head>
    <title>资讯管理</title>
</head>

<body>
    <h3 class="page-title"> 资讯管理 </h3>

    <#include "information/InformationListView.ftl">
    <#include "information/InformationFormView.ftl">

    <script language="javascript">
        $(document).ready(function(){
            $.InformationManagement.init();
        });
    </script>
</body>