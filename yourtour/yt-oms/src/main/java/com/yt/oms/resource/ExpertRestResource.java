package com.yt.oms.resource;

import java.util.List;
import java.util.Vector;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.PagingConditionBean;
import com.yt.business.PagingDataBean;
import com.yt.business.bean.pack.ExpertPackBean;
import com.yt.business.service.IExpertService;
import com.yt.oms.vo.expert.ExpertVO;
import com.yt.response.ResponsePagingDataVO;
import com.yt.rest.resource.RestResource;

@Component
@Path("oms/experts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExpertRestResource extends RestResource {
	
	@Autowired
	private IExpertService expertService;
	
	@GET
	@Path("/query")
	public ResponsePagingDataVO<List<ExpertVO>> getExperts(
			@DefaultValue("0") @QueryParam("nextCursor") Long nextCursor,
			@DefaultValue("20") @QueryParam("limit") int limit,
			@QueryParam("total") int total) throws Exception {
		PagingDataBean<List<ExpertPackBean>> pagingData = expertService
				.getExperts(new PagingConditionBean(nextCursor, limit, total));
		List<ExpertVO> vos = new Vector<>();
		for (ExpertPackBean bean : pagingData.getData()) {
			if (bean == null) {
				continue;
			}
			vos.add(ExpertVO.transform(bean));
		}

		return new ResponsePagingDataVO<List<ExpertVO>>(pagingData.getTotal(),
				vos.size(), vos);
	}

}
