<head>
    <title>账户管理</title>
</head>

<body>
    <h3 class="page-title"> 账户管理 </h3>

    <#include "account/AccountListView.ftl">
    <#include "account/AccountFormView.ftl">

    <script language="javascript">
        $(document).ready(function(){
            $.AccountManagement.init();
        });
    </script>

</body>