<head>
    <title>美食管理</title>
</head>

<body>
    <h3 class="page-title"> 美食管理 </h3>

    <#include "resource/RestaurantListView.ftl">
    <#include "resource/RestaurantFormView.ftl">

    <script language="javascript">
        $(document).ready(function(){
            $.RestaurantResource.init();
        });
    </script>

</body>