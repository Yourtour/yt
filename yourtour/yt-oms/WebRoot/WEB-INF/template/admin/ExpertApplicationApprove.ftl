<head>
    <title>达人资格审核</title>
</head>

<body>
    <h3 class="page-title"> 达人资格审核 </h3>

    <#include "approve/ExpertApplicationListView.ftl">
    <#include "approve/ExpertApplicationFormView.ftl">

    <script language="javascript">
        $(document).ready(function(){
            $.ExpertApplicationApprove.init();
        });
    </script>

</body>