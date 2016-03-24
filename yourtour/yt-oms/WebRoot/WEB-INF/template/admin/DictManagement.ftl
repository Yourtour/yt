<head>
    <title>基础数据管理</title>
</head>

<body>
    <h3 class="page-title"> 基础数据管理 </h3>

    <#include "dict/DictListView.ftl">
    <#include "dict/DictFormView.ftl">

    <script language="javascript">
        $(document).ready(function(){
            $.DictManagement.init();
        });
    </script>

</body>