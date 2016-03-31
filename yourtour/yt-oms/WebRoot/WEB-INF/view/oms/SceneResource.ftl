<head>
    <title>游玩管理</title>
</head>

<body>
    <h3 class="page-title"> 游玩管理 </h3>

    <#include "resource/SceneListView.ftl">
    <#include "resource/SceneFormView.ftl">

    <script language="javascript">
        $(document).ready(function(){
            $.SceneResource.init();
        });
    </script>

</body>