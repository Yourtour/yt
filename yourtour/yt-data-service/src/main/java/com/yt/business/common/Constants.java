package com.yt.business.common;

public interface Constants {
	//资源枚举
	public static enum ScheduleType{
		PLAY("Play","游玩"), 
		FOOD("Food","餐饮"), 
		HOTEL("Hotel","住宿"), 
		TRAFFIC("Traffic","交通"),
		FREE("Free","自行安排"),
		MATTER("Matter","事项");
		
		public String code;
		public String name;
		
		private ScheduleType(String code, String name){
			this.code = code;
			this.name = name;
		}
	}
		
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
	
	//APP角色
	public static enum Role{
			MEMBER("Member","一般成员"), 
			EXPERT("Expert","大人"),
			HOST("Host","地主"), 
			LEADER("Leader","团长");
			
			public String code;
			public String name;
			
			private Role(String code, String name){
				this.code = code;
				this.name = name;
			}
		}
	
	//行程团队角色
	public static enum GroupRole{
			MEMBER("Member","一般成员"), 
			EXPERT("Expert","达人"),
			HOST("Host","地主"), 
			LEADER("Leader","团长");
			
			public String code;
			public String name;
			
			private GroupRole(String code, String name){
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
	public static enum ContentType{
			TEXT("Text","文本"), 
			IMAGE("Image","图片"),
			VIDEO("Video","视频"),
			FILE("File","附件"),
			URL("Url","转发");
				
			public String code;
			public String name;
				
			private ContentType(String code, String name){
					this.code = code;
					this.name = name;
			}
	}
	
	//费用枚举
	public static enum ChargeType{
			PERSONAL_EXPEND("PExpend","个人支出"), 
			GROUP_EXPEND("GExpend","集体支出"),
			GROUP_RERECEIVE("GPreReceive","集体预收"),
			PERSONAL_BUDGET("PBudget","个人预算"),
			GROUP_BUDGET("GBudget","集体预算"),
			GROUP_SPLIT("GSplit","集体分摊");
					
			public String code;
			public String name;
				
			private ChargeType(String code, String name){
					this.code = code;
					this.name = name;
			}
		}
}
