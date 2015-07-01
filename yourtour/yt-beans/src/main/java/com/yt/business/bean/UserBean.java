package com.yt.business.bean;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.business.common.Constants.Status;
import com.yt.dal.hbase.annotation.HbaseColumn;
import com.yt.dal.hbase.annotation.HbaseTable;
import com.yt.rsal.neo4j.bean.Neo4JBaseBean;

/**
 * 该实体定义了用户的相关信息
 * 
 * <p>
 * <b>修改历史：</b>
 * <table border="1">
 * <tr>
 * <th>修改时间</th>
 * <th>修改人</th>
 * <th>备注</th>
 * </tr>
 * <tr>
 * <td>2015年6月2日</td>
 * <td>Tony.Zhang</td>
 * <td>Create</td>
 * </tr>
 * <tr>
 * <td>2015年6月27日</td>
 * <td>John.Peng</td>
 * <td>根据定稿后的hbase和Neo4j操作方式进行修改完善。</td>
 * </tr>
 * </table>
 * 
 * @author Tony.Zhang
 * 
 * @version 1.0
 * @since 1.0
 */
@HbaseTable(name = "T_USER_INFO")
@NodeEntity
public class UserBean extends Neo4JBaseBean {
	private static final long serialVersionUID = -6977525800090683657L;
	private static final String INDEX_NAME = "user"; // 定义了本实体中全文检索的索引名称。

	public static enum RATE {
		/** 成员变量：常规用户 */
		GENERAL("GENERAL", "常规用户"),
		/** 成员变量：达人 */
		EXPERT("EXPERT", "达人"),
		/** 成员变量：地主 */
		HOST("HOST", "地主");

		public String code;
		public String name;

		private RATE(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	private @HbaseColumn(name = "name")
	@Indexed
	String userName; // 登录名
	private @HbaseColumn(name = "pwd")
	transient String pwd; // 登录密码
	private @HbaseColumn(name = "rname")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	String realName; // 真实姓名
	private @HbaseColumn(name = "nname")
	@Indexed
	String nickName; // 昵称
	private @HbaseColumn(name = "sex")
	String sex; // 性别 F/M
	private @HbaseColumn(name = "birth")
	long birthday; // 生日
	private @HbaseColumn(name = "img")
	transient String imageUrl; // 头像
	private @HbaseColumn(name = "char")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	String character; // 性格
	private @HbaseColumn(name = "mbno")
	@Indexed
	String mobileNo; // 手机号
	private @HbaseColumn(name = "mail")
	String email; // 邮箱地址
	private @HbaseColumn(name = "rpla")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	String residence; // 居住地
	private @HbaseColumn(name = "npla")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	String nativePlace; // 籍贯
	private @HbaseColumn(name = "cstl")
	String constellation; // 星座
	@Indexed
	private @HbaseColumn(name = "rate")
	RATE rate; // 评级
	private @HbaseColumn(name = "stat")
	@Indexed
	Status status;

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}