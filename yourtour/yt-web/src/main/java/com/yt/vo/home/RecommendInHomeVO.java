package com.yt.vo.home;

import java.util.List;
import java.util.Vector;

import com.yt.business.bean.BannerBean;
import com.yt.business.bean.ContentBean;
import com.yt.business.bean.DiscoverBean;
import com.yt.vo.content.ContentVO;
import com.yt.vo.content.DiscoverVO;

/**
 * 首页推荐界面的VO对象
 * 
 * @author John.Peng
 * 
 */
public class RecommendInHomeVO {
	private List<BannerVO> banners;
	private List<ContentVO> yt_recommends;
	private List<DiscoverVO> discovers;

	public RecommendInHomeVO() {
		super();
		this.banners = new Vector<BannerVO>();
		this.yt_recommends = new Vector<ContentVO>();
		this.discovers = new Vector<DiscoverVO>();
	}

	public static RecommendInHomeVO transform(List<BannerBean> bannerBeans,
			List<ContentBean> recommendBeans, List<DiscoverBean> discoverBeans) {
		RecommendInHomeVO vo = new RecommendInHomeVO();
		for (BannerBean banner : bannerBeans) {
			if (banner == null) {
				continue;
			}
			vo.banners.add(BannerVO.transform(banner));
		}
		for (ContentBean content : recommendBeans) {
			if (content == null) {
				continue;
			}
			vo.yt_recommends.add(ContentVO.transform(content));
		}
		for (DiscoverBean discover : discoverBeans) {
			if (discover == null) {
				continue;
			}
			vo.discovers.add(DiscoverVO.transform(discover));
		}
		return vo;
	}

	public List<BannerVO> getBanners() {
		return banners;
	}

	public List<ContentVO> getYtRecommends() {
		return yt_recommends;
	}

	public List<DiscoverVO> getDiscovers() {
		return discovers;
	}

}
