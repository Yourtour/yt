package com.yt.rest.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.bean.AlongBean;
import com.yt.business.bean.LineBean;
import com.yt.business.repository.AlongRepository;
import com.yt.business.repository.LineRepository;
import com.yt.core.utils.CollectionUtils;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.vo.AlongVO;
import com.yt.vo.LineVO;
import com.yt.vo.home.HomeVO;

@Component
@Path("home")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HomeRestResource {
	private static final Log LOG = LogFactory.getLog(HomeRestResource.class);
	
	private @Autowired LineRepository lineRepository;
	
	private @Autowired AlongRepository alongRepository;
	
	@SuppressWarnings("unchecked")
	@Path("place/{placeId}/query")
	@GET
	public ResponseDataVO<HomeVO> getHomeInfo(@PathParam("placeId") String placeId) {
		HomeVO homeVO = new HomeVO();
		
		try{
			List<LineVO> lines = new ArrayList();
			List<LineBean> lineBeans = lineRepository.queryLinesByPlace(Long.parseLong(placeId));
			if(lineBeans != null){
				for(LineBean line : lineBeans){
					lines.add(new LineVO(line));
				}
			}
			homeVO.setLines(lines);
			
			List<AlongVO> alongs = new ArrayList();
			List<AlongBean> alongBeans = alongRepository.getAlongsByPlace(Long.parseLong(placeId), 0, 5);
			if(CollectionUtils.isNotNullOrEmpty(alongBeans)){
				for(AlongBean along : alongBeans){
					alongs.add(new AlongVO(along));
				}
			}
			homeVO.setAlongs(alongs);
		}catch(Exception exc){
			if (LOG.isErrorEnabled()) {
				LOG.error("Fetch home infoes failed.", exc);
			}
			
			return new ResponseDataVO<HomeVO>(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
		
		return new ResponseDataVO<HomeVO>(homeVO);
	}
}
