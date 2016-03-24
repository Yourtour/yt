package com.yt.business.service.impl;

import java.util.List;

import org.apache.commons.lang.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.business.PagingConditionBean;
import com.yt.business.PagingDataBean;
import com.yt.business.bean.BannerBean;
import com.yt.business.repository.neo4j.BannerBeanRepository;
import com.yt.business.service.IBannerService;
import com.yt.core.utils.BeanUtils;
import com.yt.neo4j.repository.CrudOperate;

@Service
public class BannerService extends ServiceBase implements IBannerService {
	@Autowired
	private CrudOperate<BannerBean> bannerCrudOperate;

	@Autowired
	private BannerBeanRepository repository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.business.service.IBannerService#getBanners(com.yt.business.
	 * PagingConditionBean)
	 */
	@Override
	public PagingDataBean<List<BannerBean>> getBanners(
			PagingConditionBean pagingCondition) throws Exception {
		long totalCount = pagingCondition.getTotal();
		if (totalCount <= 0) {
			totalCount = bannerCrudOperate.count();
		}
		List<BannerBean> banners = repository.getBanners(
				pagingCondition.getNextCursor(), pagingCondition.getLimit());

		return new PagingDataBean<List<BannerBean>>(totalCount, banners);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.business.service.IBannerService#getBanner(java.lang.Long)
	 */
	@Override
	public BannerBean getBanner(Long bannerId) throws Exception {
		if (bannerId == null) {
			throw new NullArgumentException("The banner's id is null.");
		}
		return bannerCrudOperate.get(bannerId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yt.business.service.IBannerService#saveBanner(com.yt.business.bean
	 * .BannerBean, java.lang.Long)
	 */
	@Override
	public BannerBean saveBanner(BannerBean bean, Long userId) throws Exception {
		if (!bean.isNew()) {
			BeanUtils.merge(bannerCrudOperate.get(bean.getId()), bean, true);
		}
		super.updateBaseInfo(bean, userId);
		bannerCrudOperate.save(bean);
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.business.service.IBannerService#deleteBanner(java.lang.Long,
	 * java.lang.Long)
	 */
	@Override
	public BannerBean deleteBanner(Long bannerId, Long userId) throws Exception {
		if (bannerId == null || userId == null) {
			throw new NullArgumentException(
					"The banner's id or user's id is null.");
		}
		BannerBean banner = bannerCrudOperate.get(bannerId);
		banner.setDeleted(true);
		super.updateBaseInfo(banner, userId);
		bannerCrudOperate.save(banner);
		return banner;
	}

}
