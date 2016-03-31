<head>
    <title>达人内容审核</title>
</head>

<body>
    <h3 class="page-title"> 达人内容审核 </h3>

    <#include "ExpertContentListView.ftl">
    <#include "ExpertContentFormView.ftl">

    <script language="javascript">
        $(document).ready(function(){
            $.ExpertContentApprove.init();
        });
    </script>

</body>