<head>
    <title>住宿管理</title>
</head>

<body>
    <h3 class="page-title"> 住宿管理 </h3>

    <#include "resource/HotelListView.ftl">
    <#include "resource/HotelFormView.ftl">

    <script language="javascript">
        $(document).ready(function(){
            $.HotelResource.init();
        });
    </script>

</body>