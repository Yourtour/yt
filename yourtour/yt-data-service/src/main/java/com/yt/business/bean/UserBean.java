package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.business.common.Constants.Role;
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

	private @HbaseColumn(name = "name")
	@Indexed
	String userName; // 登录名
	private @HbaseColumn(name = "pwd")
	String pwd; // 登录密码
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
	String imageUrl; // 头像
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
	private @HbaseColumn(name = "role")
	Role role; // 角色
	@Indexed
	private @HbaseColumn(name = "rank")
	int rank; // 等级
	private @HbaseColumn(name = "stat")
	@Indexed
	Status status;

	private @HbaseColumn(name = "slga")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	String slogan; //个人口号

	public UserBean() {
		super();
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
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

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
