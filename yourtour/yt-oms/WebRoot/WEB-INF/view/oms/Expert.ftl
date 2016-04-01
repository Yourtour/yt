<head>
    <title>达人管理</title>
</head>

<body>
    <h3 class="page-title"> 达人管理 </h3>

    <#include "expert/ExpertListView.ftl">

    <script language="javascript">
        $(document).ready(function(){
            $.Expert.init();
        });
    </script>

</body>