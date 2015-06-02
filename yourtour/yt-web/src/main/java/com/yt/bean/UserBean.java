package com.yt.bean;

import com.yt.dal.hbase.BaseBean;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;

@HbaseTable(name = "T_USER_INFO")
public class UserBean extends BaseBean {
	private static final long serialVersionUID = -6977525800090683657L;
	
	public static enum RATE{GENERAL, EXPERT, HOST}
	
	private 	@HbaseColumn(name = "uid")		String 	userId; 	//用户ID
	private 	@HbaseColumn(name = "name")	String 	userName; 	//登录名
	private 	@HbaseColumn(name = "pwd")		String 	pwd; 	//登录密码
	private 	@HbaseColumn(name = "rname")	String 	realName; 	//真实姓名
	private 	@HbaseColumn(name = "nname")	String 	nickName; 	//昵称
	private 	@HbaseColumn(name = "sex")		String 	sex; 	//性别 F/M
	private 	@HbaseColumn(name = "birth")		long 	birthday; 	//生日
	private 	@HbaseColumn(name = "img")		String 	imageUrl; 	//头像
	private 	@HbaseColumn(name = "char")		String 	character; 	//性格
	private 	@HbaseColumn(name = "mbno")	String 	mobileNo; 	//手机号
	private 	@HbaseColumn(name = "mail")		String 	email; 	//邮箱地址
	private 	@HbaseColumn(name = "rpla")		String 	residence; 	//居住地
	private 	@HbaseColumn(name = "npla")		String 	nativePlace; 	//籍贯
	private 	@HbaseColumn(name = "cstl")		String 	constellation; 	//星座
	private	@HbaseColumn(name = "rate")		RATE 	rate; 	//评级
	
	private 	@HbaseColumn(name = "cuid")	String createdUserId = "";
	private 	@HbaseColumn(name = "ct")		long createdTime;
	private 	@HbaseColumn(name = "uuid")	String updatedUserId = "";
	private 	@HbaseColumn(name = "ut")		long updatedTime;
	private 	@HbaseColumn(name = "stat")		int	status;

	public UserBean() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public long getBirthday() {
		return birthday;
	}

	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public RATE getRate() {
		return rate;
	}

	public void setRate(RATE rate) {
		this.rate = rate;
	}

	public String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public long getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
