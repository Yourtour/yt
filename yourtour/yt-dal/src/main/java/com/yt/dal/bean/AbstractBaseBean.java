package com.yt.dal.bean;

public abstract class AbstractBaseBean {

		public abstract Object getId();
		
		public abstract void setId(Object id);
		
		public abstract Long getCreatedTime();
		
		public abstract void setCreatedTime(Long time);
		
		public abstract Long getUpdatedTime();
		
		public abstract void setUpdatedTime(Long time);
		
		public abstract Object getCreatedUserId();
		
		public abstract void setCreatedUserId(Object id);
		
		public abstract Object getUpdatedUserId();
		
		public abstract void setUpdatedUserId(Object id);

}
