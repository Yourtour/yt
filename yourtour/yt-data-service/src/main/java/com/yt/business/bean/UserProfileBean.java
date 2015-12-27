package com.yt.business.bean;

import java.util.List;
import java.util.Vector;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.business.BaseDictBeanImpl;
import com.yt.business.common.Constants;
import com.yt.business.common.Constants.GenderType;
import com.yt.business.common.Constants.Role;
import com.yt.business.common.Constants.Status;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;

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
@HbaseTable(name = "T_USER_PROFILE_INFO")
@NodeEntity
public class UserProfileBean extends BaseDictBeanImpl {
	private static final long serialVersionUID = -6977525800090683657L;
	private static final String INDEX_NAME = "user"; // 定义了本实体中全文检索的索引名称。

	public static final String EXPERT_NOT = "-1";
	public static final String EXPERT_APPLY = "0";
	public static final String EXPERT_APPROVED = "1";

	@HbaseColumn(name = "nname")
	@Indexed
	private String nickName; // 昵称

	@HbaseColumn(name = "sex")
	private GenderType gender = GenderType.NA; // 性别 F/M
	
	@HbaseColumn(name = "token")
	private String token;
	
	@HbaseColumn(name = "birth")
	private long birthday; // 生日

	private String expert = EXPERT_NOT;

	@HbaseColumn(name = "img")
	private String imageUrl; // 头像

	@HbaseColumn(name = "char")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String character; // 性格

	@HbaseColumn(name = "mbno")
	@Indexed
	private String mobileNo; // 手机号

	@HbaseColumn(name = "mail")
	private String email; // 邮箱地址

	@HbaseColumn(name = "rpla")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String residence; // 居住地

	@HbaseColumn(name = "npla")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String nativePlace; // 籍贯

	@HbaseColumn(name = "cstl")
	private String constellation; // 星座

	@HbaseColumn(name = "role")
	private Role role = Role.MEMBER; // 角色

	@HbaseColumn(name = "rank")
	@Indexed
	private int rank; // 等级

	@HbaseColumn(name = "stat")
	@Indexed
	private Status status = Status.VALIDATED;

	@HbaseColumn(name = "slga")
	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String slogan; // 个人口号
	
	@Neo4jRelationship(relationship=Constants.RELATION_TYPE_FOLLOW, type = UserProfileBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<UserProfileBean> followers;
	
	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_WATCH, type = UserProfileBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<UserProfileBean> watchers;
	
	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_WATCH, type = LineBean.class, direction = Direction.OUTGOING, isList = true)
	private transient List<LineBean> watchedLines;

	public UserProfileBean() {
		super();
		this.followers = new Vector<UserProfileBean>();
		this.watchers = new Vector<UserProfileBean>();
		this.watchedLines = new Vector<LineBean>();
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getExpert() {
		return expert;
	}

	public void setExpert(String expert) {
		this.expert = expert;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
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
