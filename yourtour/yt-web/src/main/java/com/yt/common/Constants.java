package com.yt.common;

public interface Constants {
	//资源枚举
	public static enum ResType{
		SCENE("Scene","景点资源"), 
		FOOD("Food","美食资源"), 
		HOTEL("Hotel","住宿资源"), 
		TRAFFIC("Traffic","交通资源");
		
		public String code;
		public String name;
		
		private ResType(String code, String name){
			this.code = code;
			this.name = name;
		}
	}
	
	//数据状态
	public static enum Status {
		DRAFT("Draft","草稿"), 
		VALIDATED("Validated","有效"), 
		ACTIVED("Actived","激活"), 
		CLOSED("Closed","关闭"),
		CANCELED("Canceled","取消");
		
		public String code;
		public String name;
	
		private Status(String code, String name){
			this.code = code;
			this.name = name;
		}
	}
	
	//签到枚举
	public static enum CheckinType{
			ARRIVE("Arrive","出发签到"), 
			LEAVE("Leave","离开签到");
			
			public String code;
			public String name;
			
			private CheckinType(String code, String name){
				this.code = code;
				this.name = name;
			}
		}
	
	//成员角色枚举
	public static enum RoleType{
			MEMBER("Member","一般成员"), 
			EXPERT("Expert","大人"),
			HOST("Host","地主"), 
			LEADER("Leader","团长");
			
			public String code;
			public String name;
			
			private RoleType(String code, String name){
				this.code = code;
				this.name = name;
			}
		}
	
	//权益枚举
	public static enum EquityType{
		CASH("Cash","现金"), 
		POINT("Point","积分"),
		COUPON("Coupon","优惠券");
			
		public String code;
		public String name;
			
		private EquityType(String code, String name){
				this.code = code;
				this.name = name;
		}
	}
	
	//权益枚举
	public static enum MessageType{
			TEXT("Text","文本"), 
			IMAGE("Image","图片"),
			VIDEO("Video","视频"),
			FILE("File","附件"),
			URL("Url","转发");
				
			public String code;
			public String name;
				
			private MessageType(String code, String name){
					this.code = code;
					this.name = name;
			}
	}


	public static enum TYPE{CASH, POINT, COUPON} //现金，积分，优惠券
		//费用类型
	public static enum ChargeType{PERSONAL_EXPEND, GROUP_EXPEND, PRERECEIVE, PERSONAL_BUDGET, GROUP_BUDGET, GROUP_SPLIT};
}
