package com.yt.rest.resource;

import java.util.List;
import java.util.Vector;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.yt.response.ResponseDataVO;
import com.yt.vo.maintain.basedata.DivisionVO;

@Component
@Path("divisions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DivisionRestResource {

	@Path("load/{id}.json")
	@GET
	public ResponseDataVO<List<DivisionVO>> getAllDivisions(
			@PathParam("id") String key) {
		int id = 0;
		List<DivisionVO> list = new Vector<DivisionVO>();
		for (int index = 1; index < 10; index++) {
			DivisionVO item = new DivisionVO();
			item.setCode(key + " " + index);
			item.setFullCode(item.getCode());
			item.setText(key + " text " + index);
			item.setId((long) (id++));
			item.setExpanded(true);
			item.setLeaf(!(index == 1 && "root".equals(key)));
			list.add(item);
		}
		return new ResponseDataVO<List<DivisionVO>>(list);
	}
}
