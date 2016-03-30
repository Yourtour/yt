package com.yt.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.business.bean.ExpertBean;
import com.yt.business.bean.InfoBean;
import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.ResourceBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.pack.PlacePackBean;
import com.yt.business.repository.neo4j.ExpertTuple;
import com.yt.business.repository.neo4j.PlaceBeanRepository;
import com.yt.business.repository.neo4j.RouteTuple;
import com.yt.business.service.IPlaceService;
import com.yt.core.utils.BeanUtils;
import com.yt.core.utils.CollectionUtils;
import com.yt.neo4j.repository.CrudOperate;

@Service
public class PlaceServiceImpl extends ServiceBase implements IPlaceService {
	@Autowired
	private PlaceBeanRepository repository;

	@Autowired
	private CrudOperate<PlaceBean> placeCrudOperate;

	public PlaceServiceImpl() {
		super();
	}

	@Override
	public List<ExpertBean> getExperts(Long placeId, Long nextCursor, int limit)
			throws Exception {
		List<ExpertTuple> tuples = repository.getExperts(placeId, nextCursor,
				limit);

		List<ExpertBean> experts = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(tuples)) {
			for (ExpertTuple tuple : tuples) {
				experts.add(tuple.getExpert());
			}
		}

		return experts;
	}

	@Override
	public List<ResourceBean> getResources(Long placeId, Long nextCursor,
			int limit) throws Exception {
		return this.repository.getResources(placeId, nextCursor, limit);
	}

	@Override
	public List<RouteMainBean> getRoutes(Long placeId, Long nextCursor,
			int limit) throws Exception {
		List<RouteMainBean> routes = new ArrayList<>();

		List<RouteTuple> tuples = this.repository.getRoutes(placeId,
				nextCursor, limit);
		if (tuples != null) {
			for (RouteTuple tuple : tuples) {
				routes.add(tuple.getRoute());
			}
		}

		return routes;
	}

	@Override
	public void savePlace(PlaceBean place, Long userId) throws Exception {
		PlaceBean original = place;
		if (place.getId() != null) {
			original = placeCrudOperate.get(place.getId());
			if (original != null) {
				BeanUtils.merge(original, place);
			}
		}

		super.updateBaseInfo(place, userId);
		placeCrudOperate.save(place);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.business.service.IPlaceService#getAllPlaces()
	 */
	@Override
	public List<PlaceBean> getAllPlaces() {
		List<PlaceBean> places = this.repository.getAllPlaces();

		return places;

		/*if(CollectionUtils.isNotEmpty(places)){
			List<PlaceBean> roots = new ArrayList<>();

			Map<Long, PlaceBean> maps = new HashMap<>();
			for(PlaceBean place :places){
				maps.put(place.getId(), place);
			}

			PlaceBean parent = null;
			for(PlaceBean place : places){
				if(place.getParentId() != null){
					maps.get(place.getParentId()).addSubPlace(place);
				}else{
					roots.add(place);
				}
			}

			return roots;
		}

		return null;*/
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yt.business.service.IPlaceService#getRecommendPlaces()
	 */
	@Override
	public List<PlaceBean> getRecommendPlaces() throws Exception {
		return repository.getRecommendPlaces();
	}

	@Override
	public PlaceBean deletePlace(Long id, Long userId) throws Exception {
		PlaceBean place = this.getPlace(id);
		if (place == null)
			return null;
		place.setDeleted(true);
		super.updateBaseInfo(place, userId);
		placeCrudOperate.save(place);
		return place;
	}

	@Override
	public PlaceBean getPlace(Long id) throws Exception {
		return this.placeCrudOperate.get(id, false);
	}

	@Override
	public PlacePackBean getPlacePack(Long id, Long lastModifiedTime)
			throws Exception {
		PlacePackBean pack = new PlacePackBean();

		PlaceBean place = this.getPlace(id);
		if (place == null)
			return pack;
		pack.setPlace(place);

		// 获取目的地推荐行程
		pack.setRoutes(this.getRoutes(id, 0l, 5));

		// 获取目的地推荐的达人
		pack.setExperts(this.getExperts(id, 0l, 5));

		// 获取目的地咨询信息
		pack.setInfoes(this.getInfoes(id, 0l, 5));

		return pack;
	}

	@Override
	public List<InfoBean> getInfoes(Long placeId, Long nextCursor, int limit)
			throws Exception {
		return null;
	}
}
