package com.yt.business.common;

public interface Constants {
	// 图节点关系
	public static enum NodeRelationshipEnum {
		/**
		 * 关联到
		 */
		RELATED, /**
		 * 包含
		 */
		CONTAIN, /**
		 * 跟随
		 */
		FOLLOW, /**
		 * 关注
		 */
		WATCH, /**
		 * 父
		 */
		PARENT, /**
		 * 子
		 */
		CHILDREN, /**
		 * 在...
		 */
		AT;
	}

	// 资源枚举
	public static enum GenderType {
		MALE("MALE", "男性"), FEMALE("FEMALE", "女性"), NA("NA", "暂未知");

		public String code;
		public String name;

		private GenderType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	// 资源枚举
	public static enum ScheduleType {
		PLAY("PLAY", "游玩"), FOOD("FOOD", "餐饮"), HOTEL("HOTEL", "住宿"), TRAFFIC(
				"TRAFFIC", "交通"), FREE("FREE", "自行安排"), MATTER("MATTER", "事项");

		public String code;
		public String name;

		private ScheduleType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	// 资源枚举
	public static enum ResType {
		SCENE("SCENE", "景点资源"), FOOD("FOOD", "美食资源"), HOTEL("HOTEL", "住宿资源"), TRAFFIC(
				"TRAFFIC", "交通资源");

		public String code;
		public String name;

		private ResType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	// 数据状态
	public static enum Status {
		DRAFT("DRAFT", "草稿"), VALIDATED("VALIDATED", "有效"), ACTIVED("ACTIVED",
				"激活"), FROZEN("FRONEN", "冻结"), CLOSED("CLOSED", "关闭"), CANCELED(
				"CANCELED", "取消");

		public String code;
		public String name;

		private Status(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	// 签到枚举
	public static enum CheckinType {
		ARRIVE("ARRIVE", "出发签到"), LEAVE("LEAVE", "离开签到");

		public String code;
		public String name;

		private CheckinType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	// APP角色
	public static enum Role {
		MEMBER("MEMBER", "一般成员"), EXPERT("EXPERT", "达人"), HOST("HOST", "地主");

		public String code;
		public String name;

		private Role(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	// 行程团队角色
	public static enum GroupRole {
		MEMBER("MEMBER", "一般成员"), EXPERT("EXPERT", "达人"), HOST("HOST", "地主"), LEADER(
				"LEADER", "团长");

		public String code;
		public String name;

		private GroupRole(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	// 权益枚举
	public static enum EquityType {
		CASH("CASH", "现金"), POINT("POINT", "积分"), COUPON("COUPON", "优惠券");

		public String code;
		public String name;

		private EquityType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	// 权益枚举
	public static enum ContentType {
		TEXT("TEXT", "文本"), IMAGE("IMAGE", "图片"), VIDEO("VIDEO", "视频"), FILE(
				"FILE", "附件"), URL("URL", "转发");

		public String code;
		public String name;

		private ContentType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	// 费用枚举
	public static enum ChargeType {
		PERSONAL_EXPEND("PERSONAL_EXPEND", "个人支出"), GROUP_EXPEND(
				"GROUP_EXPEND", "集体支出"), GROUP_RERECEIVE("GROUP_RERECEIVE",
				"集体预收"), PERSONAL_BUDGET("PERSONAL_BUDGET", "个人预算"), GROUP_BUDGET(
				"GROUP_BUDGET", "集体预算"), GROUP_SPLIT("GROUP_SPLIT", "集体分摊");

		public String code;
		public String name;

		private ChargeType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}
}
