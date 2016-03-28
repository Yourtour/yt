<head>
    <title>账户管理</title>
</head>

<body>
    <h3 class="page-title"> 账户管理 </h3>

    <#include "account/ListView.ftl">
    <#include "account/FormView.ftl">

    <script language="javascript">
        $(document).ready(function(){
            $.Account.init();
        });
    </script>

</body>