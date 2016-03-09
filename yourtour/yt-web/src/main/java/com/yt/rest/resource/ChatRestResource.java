package com.yt.rest.resource;

import com.yt.business.bean.ChatMessageBean;
import com.yt.business.bean.ChatSessionBean;
import com.yt.business.service.IChatService;
import com.yt.business.utils.Neo4jUtils;
import com.yt.core.common.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.vo.chat.ChatMessageVO;
import com.yt.vo.chat.ChatSessionVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Vector;

@Component
@Path("app/chat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChatRestResource extends RestResource {
	private static final Log LOG = LogFactory.getLog(ChatRestResource.class);

	@Autowired
	private IChatService chatService;

	public ChatRestResource() {
		super();
	}

	@GET
	@Path("/chatroom/{type}/{roomCode}/open")
	public ResponseDataVO<ChatSessionVO> openChatRoom(
			@Context HttpServletRequest request,
			@PathParam("type") String type,
			@PathParam("roomCode") String roomCode) throws Exception {
		String operator = super.getCurrentUserId(request);
		ChatSessionBean sessionBean = null;
		switch (type) {
		case "PLACE_SESSION":
			sessionBean = chatService.openPlaceChatRoom(
					Long.valueOf(roomCode), operator);
			break;
		case "ROUTE_SESSION":
			sessionBean = chatService.openRouteChatRoom(
					Long.valueOf(roomCode), operator);
			break;
		case "DYNAMIC_SESSION":
		default:
			sessionBean = chatService.openDynamicChatRoom(roomCode,
					operator);
			break;
		}
		if (sessionBean != null) {
			ChatSessionVO vo = new ChatSessionVO(sessionBean);
			return new ResponseDataVO<ChatSessionVO>(vo);
		} else {
			return new ResponseDataVO<ChatSessionVO>(StaticErrorEnum.DATA_NOT_EXIST);
		}
	}

	// TODO get the chatters

	@GET
	@Path("/chatroom/{type}/top{n}")
	public ResponseDataVO<List<ChatSessionVO>> getTopNChatRooms(
			@PathParam("type") String type, @PathParam("n") int n) throws Exception {
		List<ChatSessionBean> beans = null;
		switch (type) {
		case "ALL":
			beans = chatService.getTopNChatRooms(n);
			break;
		case "PLACE_SESSION":
			beans = chatService.getTopNPlaceChatRooms(n);
			break;
		case "ROUTE_SESSION":
			beans = chatService.getTopNRouteChatRooms(n);
			break;
		case "DYNAMIC_SESSION":
		default:
			beans = chatService.getTopNDynamicChatRooms(n);
			break;
		}
		List<ChatSessionVO> list = new Vector<ChatSessionVO>();
		for (ChatSessionBean bean : beans) {
			if (bean == null) {
				continue;
			}
			list.add(new ChatSessionVO(bean));
		}
		return new ResponseDataVO<List<ChatSessionVO>>(list);
	}

	@GET
	@Path("/chatroom/{roomCode}/unread/count")
	public ResponseDataVO<Long> getUnreadMessageCount(
			@Context HttpServletRequest request,
			@PathParam("roomCode") String roomCode) throws Exception {
		Long userId = super.getCurrentUserId();
		long count = chatService.getUnreadMessageCount(roomCode,userId);
		return new ResponseDataVO<Long>(count);
	}

	@GET
	@Path("/chatroom/{roomId}/unread/messages")
	public ResponseDataVO<List<ChatMessageVO>> getUnreadMessages(
			@Context HttpServletRequest request,
			@PathParam("roomCode") String roomCode) throws Exception{
		Long userId = super.getCurrentUserId();

		List<ChatMessageBean> beans = chatService.getUnreadMessages(roomCode, 0l, userId);
		List<ChatMessageVO> list = new Vector<ChatMessageVO>();
		for (ChatMessageBean bean : beans) {
			if (bean == null) {
				continue;
			}
			list.add(new ChatMessageVO(bean));
		}
		return new ResponseDataVO<List<ChatMessageVO>>(list);
	}

}
