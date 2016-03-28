package com.yt.rest.resource;

import java.util.Arrays;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yt.core.utils.MessageDigestUtils;

@Path("wx")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WxEcho extends RestResource {
	private static final Log LOG = LogFactory.getLog(WxEcho.class);
	private final String token = "yourterdev";

	@GET
	public String echo(@QueryParam("signature") String signature,
			@QueryParam("timestamp") String timestamp,
			@QueryParam("nonce") String nonce,
			@QueryParam("echostr") String echoStr) throws Exception {
		LOG.debug(String.format(
				"signature[%s], timestamp[%s], nonce[%s], echoStr[%s].",
				signature, timestamp, nonce, echoStr));
		LOG.debug(String.format("src: %s%s%s", token, timestamp, nonce));
		String[] src = { token, timestamp, nonce };
		Arrays.sort(src);
		String tar = String.format("%s%s%s", src[0], src[1], src[2]);
		LOG.debug(String.format("tar: %s", tar));
		
		String sign = MessageDigestUtils.digest("sha1", tar);
		LOG.debug(String
				.format("src sign[%s],\ntar sign[%s].", signature, sign));
		return echoStr;
	}
}
