package com.yt.business.repository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.CrudAllInOneOperateImpl;
import com.yt.business.neo4j.repository.SceneResourceBeanRepository;

@Component
public class SceneRepositoryImpl extends CrudAllInOneOperateImpl implements
		SceneRepository {
	private static final Log LOG = LogFactory.getLog(SceneRepositoryImpl.class);

	@Autowired
	private SceneResourceBeanRepository sceneRepo;

}
