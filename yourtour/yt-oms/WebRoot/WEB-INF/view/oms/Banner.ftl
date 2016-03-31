<head>
    <title>广告位管理</title>
</head>

<body>
    <h3 class="page-title"> 广告位管理 </h3>

    <#include "banner/BannerListView.ftl">
    <#include "banner/BannerFormView.ftl">

    <script language="javascript">
        $(document).ready(function(){
            $.Banner.init();
        });
    </script>

</body>