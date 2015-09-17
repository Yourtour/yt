package com.yt.rest.resource;

import java.util.List;
import java.util.Vector;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.yt.response.ResponsePagingDataVO;
import com.yt.vo.member.ExpertVO;

@Component
@Path("experts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExpertRestResource {
	@Path("loadPage.json")
	@GET
	public ResponsePagingDataVO<List<ExpertVO>> loadPage(
			@QueryParam("page") int page, @QueryParam("start") int start,
			@QueryParam("limit") int limit) {
		System.out.println(String.format("Expert restfule page(%d), start(%d), limit(%d).",
				page, start, limit));
		Vector<ExpertVO> result = new Vector<ExpertVO>();
		for (int index = 1; index < 10; index++) {
			ExpertVO vo = new ExpertVO();
			vo.setCode("code " + index);
			vo.setName("name " + index);
			vo.setAge(index + index % 13 * 10);
			result.add(vo);
		}
		return new ResponsePagingDataVO<List<ExpertVO>>(1000, result);
	}
}
