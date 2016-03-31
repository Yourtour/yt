package com.yt.business.bean;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.yt.business.BaseBeanImpl;
import com.yt.business.common.Constants;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import com.yt.neo4j.annotation.Neo4jRelationship;
import com.yt.neo4j.annotation.Neo4jRelationship.Direction;

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
@HbaseTable(name = "T_USER_ACCOUNT_INFO")
@NodeEntity
public class UserAccountBean extends BaseBeanImpl {
	private static final long serialVersionUID = 6231921514683196684L;

	public enum Status {
		/**
		 * 有效的账户
		 */
		VALIDATED,
		/**
		 * 无效的账户
		 */
		INVALIDE,
		/**
		 * 冻结的账户
		 */
		FROOZE
	}

	public enum AccountType {
		/**
		 * 管理账号，一般没有业务功能，仅具有账户管理、安全管理、模块管理、权限管理等功能
		 */
		ADMINISTRATOR,
		/**
		 * 运营帐号，一般只能在游徒运维平台中访问系统
		 */
		OPERATOR,
		/**
		 * 达人帐号，一般只能在游徒达人APP中访问系统
		 */
		EXPERT,
		/**
		 * 游客账号，一般只能在游徒APP中访问系统
		 */
		USER
	}

	@HbaseColumn(name = "uname")
	@Indexed
	private String userName; // 手机

	@HbaseColumn(name = "pwd")
	private String pwd; // 登录密码

	private AccountType type = AccountType.USER;

	@HbaseColumn(name = "stat")
	@Indexed
	private Status status = Status.VALIDATED;

	@Neo4jRelationship(relationship = Constants.RELATION_TYPE_BELONG, type = UserProfileBean.class, direction = Direction.OUTGOING)
	private transient UserProfileBean profile;

	public UserAccountBean() {
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

	public UserProfileBean getProfile() {
		return profile;
	}

	public void setProfile(UserProfileBean profile) {
		this.profile = profile;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}
}
