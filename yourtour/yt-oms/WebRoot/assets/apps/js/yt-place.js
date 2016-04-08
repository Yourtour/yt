
/**
 *
 * @type {{query: Function, saveDictInfo: Function, loadDictInfo: Function}}
 */
jQuery.Place = {
    init:function(){
        $.Page.show("Page_PlaceListView");

        var me = this, view = $("#Page_PlaceListView");
        $("#btn_add", view).on('click', function(){
            me.addPlaceInfo();
        });

        $("#btn_save", view).on('click', function(){
            me.savePlaceInfo();
        });

        $("#btn_delete", view).on('click', function(){
            me.deletePlaceInfo();
        });

        this.inittree();
    },

    /**
     * 初始化目的地树
     */
    inittree:function(){
        var me = this;
        $.Request.get("/oms/places",null,function(result){
            $('#placetree').on('changed.jstree', function (e, data) {
                    var i, j, id;
                    for(i = 0, j = data.selected.length; i < j; i++) {
                        id = data.instance.get_node(data.selected[i]).id;
                        break;
                    }

                    me.loadPlaceInfo(id);
                }).jstree({ 'core' : {
                'check_callback': true,
                'data' : result
            } });
        })
    },

    /**
     * 保存字典数据
     */
    addPlaceInfo:function(){
        var me = this, view = $("#Page_PlaceListView");

        $("#PlaceForm").clear();

        var place = this.getSelectedPlace();
        if(place != null){
            $('#parentId', view).val(place.id);
            $('#parentName', view).val(place.text);
        }
    },

    /**
     * 从服务器获取指定目的地信息
     * @param placeId
     */
    loadPlaceInfo:function(placeId){
        $.Request.get("/oms/places/" + placeId,null,function(result){
            $("#PlaceForm").deserialize(result);
        });
    },

    /**
     * 删除指定目的地
     */
    deletePlaceInfo:function(){
        var me = this,
            placeId = $("#Page_PlaceListView #id").val();

        if(placeId && placeId == null){
            bootbox.alert("选择需要删除的目的地。");

            return;
        }

        bootbox.confirm("确定要删除选择的目的地吗?", function(result){
            if (result) {
                $.Request.delete("/oms/places/" + placeId + "/delete", null, function(result){
                    var placetree = $('#placetree').jstree(true),
                        sel = placetree.get_selected();

                    placetree.delete_node(sel);
                })
            }
        })
    },

    /**
     * 保存字典数据
     */
    savePlaceInfo:function(){
        var me = this, view = $("#Page_PlaceListView"), place;

        place = $("#PlaceForm").serialize();
        delete place.parentName;

        var fd = new FormData();
        fd.append('place', JSON.stringify(place));
        fd.append("imageUrl", $("#imageUrl")[0].files[0]);

        $.Request.postFormData("/oms/places/save",fd,function(result){
            bootbox.alert("保存成功。");

            $('#id', view).val(result.id);

            $("#placetree").jstree("create_node", result.parentId == null ?'#':'#' + place.parentId, {id:result.id, text:result.name});
        })
    },

    /**
     * 获取目的地树选中节点
     * @returns {*}
     */
    getSelectedPlace:function(){
        var places = $('#placetree').jstree('get_selected', true);
        if(places.length != 0){
            return places[0];
        }else{
            return null;
        }
    }
};

