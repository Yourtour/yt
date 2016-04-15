jQuery.Popups = {
    place:function(callback){
        $.Request.get("/oms/places", null, function(result) {
            var value = result;

            var html = '<form id="PlaceForm" class="form-horizontal form-place" role="form">';
            html +=  '<div class="form-group"><label for="name" class="col-md-3 control-label">地区</label><div class="col-md-9"><select id="district" class="form-control"></select></div></div>';
            html +=  '<div class="form-group"><label for="name" class="col-md-3 control-label">省/市/州</label><div class="col-md-9"><select id="state" class="form-control"></select></div></div>';
            html +=  '<div class="form-group"><label for="name" class="col-md-3 control-label">市</label><div class="col-md-9"><div class="input-group select2-bootstrap-append" style="width:100%"><select id="city" class="form-control select2" multiple></select></div></div></div>';
            html +=  '</form>';

            bootbox.dialog({
                message:html,
                title: "目的地选择",
                buttons:
                {
                    "success" :
                    {
                        "label" : "<i class='icon-ok'></i> 确定",
                        "className" : "btn-sm btn-success",
                        "callback": function() {
                            var placeForm = $(".form-place"),values=""
                            city = $("#city", placeForm);
                            parent = city.parent(),
                                selected = $("ul.select2-selection__rendered>li.select2-selection__choice");
                            if(selected.length > 0){
                                $.each(selected, function(index, item){
                                    if(index > 0) values +='|';

                                    $.each(city.children("option"), function(oindex, oitem){
                                        if($(oitem).text() == $(item).attr("title")){
                                            values = values + $(oitem).attr("value") + "," + $(oitem).text();
                                        }
                                    })
                                })
                            }

                            callback(values);
                        }
                    }
                }
            });

            var placeForm = $(".form-place"),
                district = $("#district", placeForm),
                state = $("#state", placeForm),
                city = $("#city", placeForm);

            $.each(value, function(index, item){
                if(item.parent == "#") {
                    district.append('<option value="' + item.id + '">' + item.text + '</option>');
                }
            });

            state.on("click", function(){
                var me = $(this),
                    parent = me.data("parent"),
                    districtId = district.val();

                if(parent && parent == districtId){
                    return;
                }

                me.empty();
                city.empty();

                $.each(value, function(index, item){
                    if(item.parent == districtId) {
                        me.append('<option value="' + item.id + '">' + item.text + '</option>');
                    }
                });

                me.data("parent", districtId);
            });

            city.select2({width:"100%"});
            state.on("change", function(){
                var me = $(this),
                    stateId = me.val();

                city.empty();
                $.each(value, function(index, item){
                    if(item.parent == stateId) {
                        city.append('<option value="' + item.id + '">' + item.text + '</option>');
                    }
                });
            });
        });
    }
};

