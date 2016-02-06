package com.yt.vo.member;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yt.business.bean.UserProfileBean;
import com.yt.business.common.Constants.GenderType;
import com.yt.business.common.Constants.Role;
import com.yt.business.common.Constants.Status;
import com.yt.vo.BaseVO;

public class UserVO extends BaseVO {
	private static final Log LOG = LogFactory.getLog(UserVO.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy/MM/dd");
	private String userName; // 登录名
	private String password; // 登录密码
	private String realName; // 真实姓名
	private String nickName; // 昵称
	private GenderType gender = GenderType.NA; // 性别 F/M
	private String birthday; // 生日
	private String imageUrl; // 头像
	private String character; // 性格
	private String mobileNo; // 手机号
	private String email; // 邮箱地址
	private String residence; // 居住地
	private String nativePlace; // 籍贯
	private String constellation; // 星座
	private Role role; // 角色
	private int rank; // 等级
	private Status status;
	private String slogan; // 个人口号
	private String  identity;
	private String  age;
	private String memo;
	private String tags;
	private int snsAuthenticate;
	private int mobileAuthenticate;
	private int idAuthenticate;

	private int followingNum = 0;
	private int followedNum = 0;
	private int thumbupNum = 0;
	private int albumNum = 0;

	public static UserVO transform(UserProfileBean bean) {
		if (bean == null) {
			return null;
		}
		UserVO vo = new UserVO();

		transform(vo, bean);

		return vo;
	}

	public static void transform(UserVO vo, UserProfileBean bean){
		vo.fromBean(bean);
		vo.setBirthday(dateFormat.format(new Date(bean.getBirthday())));
		vo.setCharacter(bean.getCharacter());
		vo.setConstellation(bean.getConstellation());
		vo.setEmail(bean.getEmail());
		vo.setImageUrl(bean.getImageUrl());
		vo.setMobileNo(bean.getMobileNo());
		vo.setNativePlace(bean.getNativePlace());
		vo.setNickName(bean.getNickName());
		vo.setRank(bean.getRank());
		vo.setRealName(bean.getName());
		vo.setResidence(bean.getResidence());
		vo.setRole(bean.getRole());
		vo.setGender(bean.getGender());
		vo.setSlogan(bean.getSlogan());
		vo.setStatus(bean.getStatus());
		vo.setUserName(bean.getCode());
		vo.setAge(bean.getAge());
		vo.setIdentity(bean.getIdentity());
		vo.setSnsAuthenticate(bean.getSnsAuthenticate());
		vo.setIdAuthenticate(bean.getIdAuthenticate());
		vo.setMobileAuthenticate(bean.getMobileAuthenticate());
		vo.setFollowedNum(bean.getFollowedNum());
		vo.setFollowingNum(bean.getFollowingNum());
		vo.setThumbupNum(bean.getThumbupNum());
		vo.setAlbumNum(bean.getAlbumNum());
		vo.setTags(bean.getTags());
		vo.setMemo(bean.getMemo());
	}

	public static UserProfileBean transform(UserVO vo) {
		if (vo == null) {
			return null;
		}
		UserProfileBean bean = new UserProfileBean();
		vo.toBean(bean);
		bean.setRowKey(vo.getUserName());
		vo.setUserName(vo.getUserName());
		try {
			bean.setBirthday(dateFormat.parse(vo.getBirthday()).getTime());
		} catch (Exception ex) {
			if (LOG.isWarnEnabled()) {
				LOG.warn(String.format("Parse the Date['%s'] fail.",
						vo.getBirthday()));
			}
		}
		bean.setCharacter(vo.getCharacter());
		bean.setConstellation(vo.getConstellation());
		bean.setEmail(vo.getEmail());
		bean.setImageUrl(vo.getImageUrl());
		bean.setMobileNo(vo.getMobileNo());
		bean.setNativePlace(vo.getNativePlace());
		bean.setNickName(vo.getNickName());
		bean.setName(vo.getRealName());
		bean.setResidence(vo.getResidence());
		bean.setRole(vo.getRole());
		bean.setGender(vo.getGender());
		bean.setSlogan(vo.getSlogan());
		bean.setStatus(vo.getStatus());
		bean.setCode(vo.getUserName());
		return bean;
	}

	public UserVO() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getRank() {
		return "resources/icons/12/icon_rank_" + rank + ".png";
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

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
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
}
