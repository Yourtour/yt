package com.yt.business.common;

public interface Constants {
	// 图节点关系
	/**
	 * 关联
	 */
	public static final String RELATION_TYPE_RELATED = "RELATED";
	/**
	 * 包括
	 */
	public static final String RELATION_TYPE_CONTAIN = "CONTAIN";
	/**
	 * 跟随
	 */
	public static final String RELATION_TYPE_FOLLOW = "FOLLOWING";

	/**
	 * 关注
	 */
	public static final String RELATION_TYPE_WATCH = "WATCH";
	/**
	 * 父
	 */
	public static final String RELATION_TYPE_PARENT = "PARENT";
	/**
	 * 拥有
	 */
	public static final String RELATION_TYPE_HAS = "HAS";
	/**
	 * 拥有
	 */
	public static final String RELATION_TYPE_IS = "IS";
	/**
	 * 在
	 */
	public static final String RELATION_TYPE_AT = "AT";

	/**
	 * 为
	 */
	public static final String RELATION_TYPE_FOR = "FOR";

	/**
	 * 从
	 */
	public static final String RELATION_TYPE_FROM = "FROM";

	/**
	 * 到
	 */
	public static final String RELATION_TYPE_TO = "TO";

	/**
	 * 属于
	 */
	public static final String RELATION_TYPE_BELONG = "BELONG";

	/**
	 * 参与关系
	 */
	public static final String RELATION_TYPE_PARTICIPATE = "PARTICIPATE";

	/**
	 * 推荐关系
	 */
	public static final String RELATION_TYPE_RECOMMEND = "RECOMMEND";

	/**
	 * 服务关系
	 */
	public static final String RELATION_TYPE_SERVICE = "SERVICE";

	/**
	 * 点评关系
	 */
	public static final String RELATION_TYPE_COMMENTED = "COMMENTED";

	/**
	 * 成员关系
	 */
	public static final String RELATION_TYPE_MEMBER = "MEMBER";

	/**
	 * 领队关系
	 */
	public static final String RELATION_TYPE_LEADER = "LEADER";

	/**
	 * 达人关系
	 */
	public static final String RELATION_TYPE_EXPERT = "EXPERT";

	/**
	 * 审批关系
	 */
	public static final String RELATION_TYPE_APPROVED = "APPROVED";

	/**
	 * 审批关系
	 */
	public static final String RELATION_TYPE_DIVIDED = "DIVIDED";

	/**
	 * 审批关系
	 */
	public static final String RELATION_TYPE_FAVORITE = "FAVORITE";

	/**
	 * 旅行车
	 */
	public static final String RELATION_TYPE_TRAVELCART = "FAVORITE";

	public final static String RELATION_TYPE_PUBLISH = "PUBLISH";

	public static enum ThemeType{
		SCENE("SCENE", "景点"), FOOD("FOOD", "美食"), HOTEL("HOTEL", "住宿"), TRAFFIC("TRAFFIC", "交通"), WEATHER("WEATHER", "天气");

		public String code;
		public String name;

		private ThemeType(String code, String name) {
			this.code = code;
			this.name = name;
		}
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

		public String getCode(){
			return code;
		}

		public static GenderType getEnum(String value) {
			for(GenderType v : values())
				if(v.getCode().equalsIgnoreCase(value)) return v;

			throw new IllegalArgumentException();
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

		public static ResType getEnum(String value) {
			for(ResType v : values())
				if(v.code.equalsIgnoreCase(value)) return v;

			throw new IllegalArgumentException();
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

	// 结伴枚举
	public static enum AlongIntentionType {
		TOGETHER_CAR("TOGETHER_CAR", "拼车"), TOGETHER_EAT("TOGETHER_EAT", "拼吃"), TOGETHER_TRAVEL(
				"TOGETHER_TRAVEL", "拼玩");

		public String code;
		public String name;

		private AlongIntentionType(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public static AlongIntentionType get(String name){
			AlongIntentionType[] types = AlongIntentionType.values();
			for(AlongIntentionType type : types){
				if(type.name.equals(name)){
					return type;
				}
			}

			return null;
		}
	}

	// 结伴枚举
	public static enum RouteSectionType {
		ROUTE_PREPARATION("ROUTE_PREPARATION", "准备"), ROUTE_SCHEDULE(
				"ROUTE_SCHEDULE", "日程");

		public String code;
		public String name;

		private RouteSectionType(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	// 资源枚举
	public static enum Currency {
		RMB("RMB", "人民币","¥","元");

		public String code;
		public String name;
		public String symbol;
		public String unit;

		private Currency(String code, String name,String symbol, String unit) {
			this.code = code;
			this.name = name;
			this.symbol = symbol;
			this.unit = unit;
		}

		public static Currency getCurrency(String code){
			return Currency.valueOf(code);
		}
	}

	// 资源枚举
	public static enum Image {
		USER_LOGO("user", "用户头像");

		public String code;
		public String name;

		private Image(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}
}
