package com.yt.test.neo4j.bean;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;
import com.yt.rsal.neo4j.bean.Neo4JBaseBean;
import com.yt.test.neo4j.bean.Constants.Status;

/**
 * 
 * @author Tony.Zhang 该实体定义了用户的相关信息
 */
@HbaseTable(name = "T_USER_INFO")
@NodeEntity
public class UserBean extends Neo4JBaseBean {
	private static final long serialVersionUID = -6977525800090683657L;

	public static enum RATE {
		GENERAL, EXPERT, HOST
	}

	private @HbaseColumn(name = "name")
	@GraphProperty
	String userName; // 登录名
	private @HbaseColumn(name = "pwd")
	transient String pwd; // 登录密码
	private @HbaseColumn(name = "rname")
	@GraphProperty
	String realName; // 真实姓名
	private @HbaseColumn(name = "nname")
	@GraphProperty
	String nickName; // 昵称
	private @HbaseColumn(name = "sex")
	transient String sex; // 性别 F/M
	private @HbaseColumn(name = "birth")
	transient long birthday; // 生日
	private @HbaseColumn(name = "img")
	transient String imageUrl; // 头像
	private @HbaseColumn(name = "char")
	transient String character; // 性格
	private @HbaseColumn(name = "mbno")
	transient String mobileNo; // 手机号
	private @HbaseColumn(name = "mail")
	transient String email; // 邮箱地址
	private @HbaseColumn(name = "rpla")
	transient String residence; // 居住地
	private @HbaseColumn(name = "npla")
	transient String nativePlace; // 籍贯
	private @HbaseColumn(name = "cstl")
	transient String constellation; // 星座
	private @HbaseColumn(name = "rate")
	transient RATE rate; // 评级

	private @HbaseColumn(name = "cuid")
	transient String createdUserId = "";
	private @HbaseColumn(name = "ct")
	transient long createdTime;
	private @HbaseColumn(name = "uuid")
	transient String updatedUserId = "";
	private @HbaseColumn(name = "ut")
	transient long updatedTime;
	private @HbaseColumn(name = "stat")
	transient Status status;

	private @RelatedTo(type = "playWith", direction = Direction.BOTH)
	Set<UserBean> playmates; // 游伴
	private @RelatedTo(type = "followMe", direction = Direction.OUTGOING)
	Set<UserBean> follows; // 跟随者

	private @RelatedTo(type = "watchRoute", direction = Direction.OUTGOING)
	Set<RouteBean> watchRoutes; // 关注的行程

	public UserBean() {
		super();
	}

	public void playWith(UserBean userBean) {
		if (userBean == null) {
			return;
		}
		if (playmates == null) {
			playmates = new HashSet<UserBean>();
		}
		// 成为游伴时相互的。
		if (this.getRowKey().equals(userBean.getRowKey())) {
			return;
		}
		if (!playmates.contains(userBean)) {
			playmates.add(userBean);
		} else {
			return;
		}
		userBean.playWith(this);
	}

	public Set<UserBean> getPlaymates() {
		return this.playmates;
	}

	public void followMe(UserBean userBean) {
		if (userBean == null) {
			return;
		}
		if (follows == null) {
			follows = new HashSet<UserBean>();
		}
		if (this.getRowKey().equals(userBean.getRowKey())) {
			return;
		}
		if (!follows.contains(userBean)) {
			follows.add(userBean);
		}
	}

	public Set<UserBean> getFollows() {
		return this.follows;
	}

	public void watchRoute(RouteBean routeBean) {
		if (routeBean == null) {
			return;
		}
		if (watchRoutes == null) {
			watchRoutes = new HashSet<RouteBean>();
		}
		if (!watchRoutes.contains(routeBean)) {
			watchRoutes.add(routeBean);
		}
	}
	
	public Set<RouteBean> getWatchRoutes() {
		return this.watchRoutes;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
