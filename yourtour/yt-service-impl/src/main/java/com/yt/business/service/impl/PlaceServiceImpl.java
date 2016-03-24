package com.yt.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yt.business.bean.ExpertBean;
import com.yt.business.bean.InfoBean;
import com.yt.business.bean.PlaceBean;
import com.yt.business.bean.ResourceBean;
import com.yt.business.bean.RouteMainBean;
import com.yt.business.bean.pack.PlaceBeanPack;
import com.yt.business.repository.neo4j.ExpertTuple;
import com.yt.business.repository.neo4j.PlaceBeanRepository;
import com.yt.business.repository.neo4j.PlaceTuple;
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
	public void savePlace(Long parentId, PlaceBean place, Long userId)
			throws Exception {
		if (place.getId() != null) {
			PlaceBean original = placeCrudOperate.get(place.getId());
			if (original != null) {
				BeanUtils.merge(original, place);
				if (parentId != null && parentId > 0) {
					PlaceBean parent = placeCrudOperate.get(parentId);
					if (parent == null) {
						throw new Exception(String.format(
								"The parent place not exist, placeId(%d).",
								parentId));
					}
					place.setParent(parent);
				}
			}
		}

		// 先保存父节点
		PlaceBean parent = place.getParent();
		if (parent != null && parent.getId() != null) {
			parent = placeCrudOperate.get(parent.getId());
			if (parent.isLeaf()) {
				parent.setLeaf(false);
				super.updateBaseInfo(parent, userId);
				placeCrudOperate.save(parent);
			}
		}

		// 没有父节点，则当前节点就是根节点
		place.setRoot(parent == null);
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
		List<PlaceBean> roots = new ArrayList<PlaceBean>();
		Map<String, PlaceBean> history = new HashMap<String, PlaceBean>();

		// 首先获取具有父级关系的目的地
		List<PlaceTuple> tuples = repository.getPlacesHasParentRelationship();
		for (PlaceTuple pt : tuples) {
			PlaceBean parent = pt.getParentPlace();
			PlaceBean place = pt.getPlace();
			if (!history.containsKey(parent.getShorter())) {
				history.put(parent.getShorter(), parent);
				if (parent.isRoot()) {
					roots.add(parent);
				}
			}
			parent = history.get(parent.getShorter());
			if (!history.containsKey(place.getShorter())) {
				place.setParent(parent);
				parent.getSubPlaces().add(place);
				history.put(place.getShorter(), place);
			}
		}

		// 然后获取没有父级关系的目的地
		List<PlaceBean> places = repository.getPlacesHasnotParentRelationship();
		if (places != null && !places.isEmpty()) {
			roots.addAll(places);
		}

		return roots;
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
	public PlaceBeanPack getPlacePack(Long id, Long lastModifiedTime)
			throws Exception {
		PlaceBeanPack pack = new PlaceBeanPack();

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
