/**
 * 
 * @type {{query: Function, saveDictInfo: Function, loadDictInfo: Function}}
 */
jQuery.SceneResource = {
	init : function() {
		var me = this;

		$.Page.show("Page_SceneListView");

		var listview = $("#Page_SceneListView");
		$("#btn_add", listview).on('click', function() {
			$.Page.show("Page_SceneFormView");
		});

		var formview = $("#Page_SceneFormView");
		// 保存按钮事件
		$("#btnSave", formview).on('click', function() {
			me.saveSceneInfo()
		});

		// 取消按钮事件
		$(" #btnCancel", formview).on('click', function() {
			$.Page.back();
		});

		this.query();
	},

	/**
	 * 列表查询
	 */
	query : function() {
		$("#datatable_Scene").dataTable().fnDestroy();
		$("#datatable_Scene")
				.dataTable(
						{
							"aoColumns" : [
									{
										"mData" : "id",
										"sClass" : "center",
										"sWidth" : "5%",
										"mRender" : function(data, type, row) {
											return "<input type='checkbox' class='checkboxes' value='"
													+ data + "'/>";
										}
									}, {
										"mData" : "code",
										"sWidth" : "20%"
									}, {
										"mData" : "name",
										"sWidth" : "30%"
									}, {
										"mData" : "intro",
										"sWidth" : "40%"
									} ]
						});
	},

	/**
	 * 保存字典数据
	 */
	saveSceneInfo : function() {
		var scene = {}, sceneForm = $('#SceneForm'), me = this;
		scene = sceneForm.serialize();
		// TODO 删除不需要的字段
		var fd = new FormData();
		fd.append('scene', JSON.stringify(scene));
		fd.append("imageUrl", $("#imageUrl")[0].files[0]);
		
		console.log(scene);

		$.Request.post("/rest/oms/resources/scenes/save", scene, function(
				result) {
			bootbox.alert("保存成功。", function() {
				$.Page.back(function() {
					me.query();
				});
			});
		})
	},

};
