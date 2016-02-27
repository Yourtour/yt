package com.yt.business.repository;

import java.util.List;

import com.yt.business.common.AppException;
import com.yt.business.neo4j.repository.UserTuple;
import com.yt.core.utils.Base64Utils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.neo4j.repository.UserBeanRepository;

@Component
public class UserRepositoryImpl extends CrudAllInOneOperateImpl implements
		UserRepository {
	private static final Log LOG = LogFactory.getLog(UserRepositoryImpl.class);

	@Autowired
	private UserBeanRepository repository;

	public UserRepositoryImpl() {
		super();
	}

	@Override
	public UserProfileBean getUser(String userName, String password) throws AppException, Exception {
		UserTuple tuple = repository.getUserByUserName(userName);
		if(tuple == null){
			throw new AppException("账号或密码不存在");
		}

		String rPassword = tuple.getAccount().getPwd();
		if(! rPassword.equals(Base64Utils.MD5(password.trim()))){
			throw new AppException("账号或密码不存在");
		}

		return tuple.getProfile();
	}

	@Override
	public UserProfileBean getUserByUserName(String userName) throws Exception {
		UserTuple tuple = repository.getUserByUserName(userName);
		if(tuple != null){
			return tuple.getProfile();
		}

		return null;
	}

	@Override
	public UserProfileBean getUserByNickName(String nickName) throws Exception {
		UserTuple tuple = repository.getUserByNickName(nickName);
		if(tuple != null){
			return tuple.getProfile();
		}

		return null;
	}

	@Override
	public List<UserProfileBean> getUsers(String searchWords) {
		return repository.getUsers(searchWords);
	}
}
