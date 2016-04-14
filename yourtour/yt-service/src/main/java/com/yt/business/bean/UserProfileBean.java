package com.yt.business.bean;

import com.yt.business.common.Constants;
import com.yt.neo4j.annotation.Neo4jRelationship;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants.Role;
import com.yt.core.utils.DateUtils;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;

import java.util.List;

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
public class UserProfileBean extends BaseBeanImpl {
	private static final long serialVersionUID = -6977525800090683657L;
	private static final String INDEX_NAME = "user"; // 定义了本实体中全文检索的索引名称。

	public static final String RELATION_TYPE_SERVICE = "SERVICE";

	public enum GenderType {
		/**
		 * 男
		 */
		MALE,
		/**
		 * 女
		 */
		FEMALE,
		/**
		 * 暂未知
		 */
		NA
	}

	@HbaseColumn(name = "nname")
	@Indexed
	private String nickName; // 昵称

	private String realName; // 真实姓名

	private String identity; // 身份

	@HbaseColumn(name = "sex")
	private GenderType gender = GenderType.NA; // 性别 F/M

	@HbaseColumn(name = "token")
	private String token;

	@HbaseColumn(name = "birth")
	private long birthday; // 生日

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

	private String servicePlaces; //服务目的地

	@HbaseColumn(name = "cstl")
	private String constellation; // 星座

	@HbaseColumn(name = "role")
	private Role role = Role.MEMBER; // 角色

	private String tags; // 个人标签

	private String memo; // 个人简介

	@Indexed
	private int rank; // 等级

	@Indexed(indexName = INDEX_NAME, indexType = IndexType.FULLTEXT)
	private String slogan; // 个人口号

	private int snsAuthenticate = 0;
	private int mobileAuthenticate = 0;
	private int idAuthenticate = 0;

	private int followingNum = 0;
	private int followedNum = 0;
	private int thumbupNum = 0;
	private int albumNum = 0;

	@Neo4jRelationship(relationship = RELATION_TYPE_SERVICE, type = PlaceBean.class, direction = Neo4jRelationship.Direction.OUTGOING, isList = true)
	private transient List<PlaceBean> servicePlaceBeans;

	public UserProfileBean(Long id) {
		super(id);
	}

	public UserProfileBean() {
		super();
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

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public int getSnsAuthenticate() {
		return snsAuthenticate;
	}

	public void setSnsAuthenticate(int snsAuthenticate) {
		this.snsAuthenticate = snsAuthenticate;
	}

	public int getMobileAuthenticate() {
		return mobileAuthenticate;
	}

	public void setMobileAuthenticate(int mobileAuthenticate) {
		this.mobileAuthenticate = mobileAuthenticate;
	}

	public int getIdAuthenticate() {
		return idAuthenticate;
	}

	public void setIdAuthenticate(int idAuthenticate) {
		this.idAuthenticate = idAuthenticate;
	}

	public int getFollowingNum() {
		return followingNum;
	}

	public void setFollowingNum(int followingNum) {
		this.followingNum = followingNum;
	}

	public int getFollowedNum() {
		return followedNum;
	}

	public void setFollowedNum(int followedNum) {
		this.followedNum = followedNum;
	}

	public int getThumbupNum() {
		return thumbupNum;
	}

	public void setThumbupNum(int thumbupNum) {
		this.thumbupNum = thumbupNum;
	}

	public int getAlbumNum() {
		return albumNum;
	}

	public void setAlbumNum(int albumNum) {
		this.albumNum = albumNum;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getAge() {
		if (this.getBirthday() != 0) {
			return DateUtils.formatDate(this.birthday, "yy") + "后  "
					+ this.getConstellation();
		}

		return "";
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getServicePlaces() {
		return servicePlaces;
	}

	public void setServicePlaces(String servicePlaces) {
		this.servicePlaces = servicePlaces;
	}

	public List<PlaceBean> getServicePlaceBeans() {
		return servicePlaceBeans;
	}

	public void setServicePlaceBeans(List<PlaceBean> servicePlaceBeans) {
		this.servicePlaceBeans = servicePlaceBeans;
	}
}
