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

import com.yt.business.bean.LineBean;
import com.yt.business.repository.LineRepository;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.vo.home.HomeVO;
import com.yt.vo.home.LineVO;

@Component
@Path("home")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HomeRestResource {
	private static final Log LOG = LogFactory.getLog(HomeRestResource.class);

	private @Autowired
	LineRepository lineRepository;

	@Path("place/{placeId}/query")
	@GET
	public ResponseDataVO<HomeVO> getHomeInfo(
			@PathParam("placeId") String placeId) {
		HomeVO homeVO = new HomeVO();

		try {
			List<LineVO> lines = new ArrayList<LineVO>();
			List<LineBean> lineBeans = lineRepository.getLinesByPlace(Long
					.parseLong(placeId));
			if (lineBeans != null) {
				for (LineBean line : lineBeans) {
					lines.add(new LineVO(line));
				}
			}
			homeVO.setLines(lines);
		} catch (Exception exc) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Fetch home infoes failed.", exc);
			}

			return new ResponseDataVO<HomeVO>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}

		return new ResponseDataVO<HomeVO>(homeVO);
	}
}
