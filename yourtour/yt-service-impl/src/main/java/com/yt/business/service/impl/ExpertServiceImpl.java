package com.yt.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.business.PagingConditionBean;
import com.yt.business.PagingDataBean;
import com.yt.business.bean.ExpertApplicationBean;
import com.yt.business.bean.ExpertServiceBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.bean.pack.ExpertPackBean;
import com.yt.business.repository.neo4j.ExpertBeanRepository;
import com.yt.business.repository.neo4j.ExpertTuple;
import com.yt.business.repository.neo4j.RouteMainBeanRepository;
import com.yt.business.service.IExpertService;
import com.yt.core.utils.CollectionUtils;
import com.yt.core.utils.StringUtils;
import com.yt.neo4j.repository.CrudOperate;

@Service
public class ExpertServiceImpl extends ServiceBase implements IExpertService {
	private static final Log LOG = LogFactory.getLog(ExpertServiceImpl.class);

	@Autowired
	private CrudOperate<ExpertApplicationBean> applicationCrudOperate;

	@Autowired
	private CrudOperate<ExpertServiceBean> serviceCrudOperate;

	@Autowired
	private CrudOperate<UserProfileBean> profileCrudOperate;

	@Autowired
	private ExpertBeanRepository expertRepository;

	@Autowired
	private RouteMainBeanRepository routeRepository;

	public ExpertServiceImpl() {
		super();
	}

	@Override
	public List<UserProfileBean> getExperts(String placeIds, String pServices)
			throws Exception {
		Long[] places = null, services = null;
		List<ExpertTuple> tuples = null;

		if (StringUtils.isNotNull(placeIds)) {
			String[] ids = placeIds.split(",");
			places = new Long[ids.length];
			for (int index = 0; index < ids.length; index++) {
				places[index] = Long.valueOf(ids[index]);
			}
		}

		if (StringUtils.isNull(pServices)) {
			tuples = expertRepository.getExperts(places);
		} else {
			String[] ids = pServices.split(",");
			services = new Long[ids.length];
			for (int index = 0; index < ids.length; index++) {
				services[index] = Long.valueOf(ids[index]);
			}

			tuples = expertRepository.getExperts(places, services);
		}

		List<UserProfileBean> experts = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(tuples)) {
			for (ExpertTuple tuple : tuples) {
				UserProfileBean expert = tuple.getProfile();

				experts.add(expert);
			}
		}

		return experts;
	}

	@Override
	public ExpertApplicationBean saveApplication(
			ExpertApplicationBean application, Long userId) throws Exception {
		UserProfileBean profile = profileCrudOperate.get(userId);
		application.setExpert(profile);
		super.updateBaseInfo(application, userId);
		this.applicationCrudOperate.save(application);
		return application;
	}

	@Override
	public ExpertApplicationBean getApplication(Long userId) throws Exception {
		return expertRepository.getApplication(userId);
	}

	@Override
	public PagingDataBean<List<ExpertPackBean>> getExperts(
			PagingConditionBean pagingCondition) throws Exception {
		List<ExpertPackBean> experts = new Vector<ExpertPackBean>();
		int total = pagingCondition.getTotal();
		if (total <= 0) {
			total = expertRepository.countValidatedExperts();
		}
		List<UserProfileBean> userProfiles = expertRepository
				.getValidatedExperts(pagingCondition.getNextCursor(),
						pagingCondition.getLimit());
		// TODO 目前没有更好的办法进行外联接，暂时采用低效率的循环方式获取数据，以后需要进行优化
		for (UserProfileBean profile : userProfiles) {
			if (profile == null) {
				continue;
			}
			ExpertPackBean pack = new ExpertPackBean();
			pack.setUserProfile(profile);
			pack.setApplication(expertRepository.getApplication(profile.getId()));
			pack.setContentApplications(expertRepository
					.getContentApplications(profile.getId()));
			pack.setRoutes(expertRepository.getRoutes(profile.getId()));
			pack.setServices(expertRepository.getServices(profile.getId()));
			experts.add(pack);
		}
		return new PagingDataBean<>(total, experts);
	}

	@Override
	public UserProfileBean getExpert(Long userId) throws Exception {
		UserProfileBean expert = this.profileCrudOperate.get(userId);
		return expert;
	}

}
