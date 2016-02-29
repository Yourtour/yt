package com.yt.rest.resource;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.bean.ChatMessageBean;
import com.yt.business.bean.ChatSessionBean;
import com.yt.business.bean.UserProfileBean;
import com.yt.business.repository.ChatRepository;
import com.yt.error.StaticErrorEnum;
import com.yt.response.ResponseDataVO;
import com.yt.vo.chat.ChatMessageVO;
import com.yt.vo.chat.ChatSessionVO;

@Component
@Path("app/chat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChatRestResource extends BaseRestResource {
	private static final Log LOG = LogFactory.getLog(ChatRestResource.class);

	@Autowired
	private ChatRepository chatRepository;

	public ChatRestResource() {
		super();
	}

	@GET
	@Path("/chatroom/{type}/{roomCode}/open")
	public ResponseDataVO<ChatSessionVO> openChatRoom(
			@Context HttpServletRequest request,
			@PathParam("type") String type,
			@PathParam("roomCode") String roomCode) {
		try {
			String operator = super.getCurrentUserId(request);
			ChatSessionBean sessionBean = null;
			switch (type) {
			case "PLACE_SESSION":
				sessionBean = chatRepository.openPlaceChatRoom(
						Long.valueOf(roomCode), operator);
				break;
			case "ROUTE_SESSION":
				sessionBean = chatRepository.openRouteChatRoom(
						Long.valueOf(roomCode), operator);
				break;
			case "DYNAMIC_SESSION":
			default:
				sessionBean = chatRepository.openDynamicChatRoom(roomCode,
						operator);
				break;
			}
			if (sessionBean != null) {
				ChatSessionVO vo = new ChatSessionVO(sessionBean);
				return new ResponseDataVO<ChatSessionVO>(vo);
			} else {
				return new ResponseDataVO<ChatSessionVO>(
						StaticErrorEnum.THE_DATA_NOT_EXIST);
			}
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						String.format(
								"Open and fetch the chat room session fail, type:%s, room: %s.",
								type, roomCode), ex);
			}
			return new ResponseDataVO<ChatSessionVO>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	// TODO get the chatters

	@GET
	@Path("/chatroom/{type}/top{n}")
	public ResponseDataVO<List<ChatSessionVO>> getTopNChatRooms(
			@PathParam("type") String type, @PathParam("n") int n) {
		try {
			List<ChatSessionBean> beans = null;
			switch (type) {
			case "ALL":
				beans = chatRepository.getTopNChatRooms(n);
				break;
			case "PLACE_SESSION":
				beans = chatRepository.getTopNPlaceChatRooms(n);
				break;
			case "ROUTE_SESSION":
				beans = chatRepository.getTopNRouteChatRooms(n);
				break;
			case "DYNAMIC_SESSION":
			default:
				beans = chatRepository.getTopNDynamicChatRooms(n);
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
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Fetch the top%d chat room session fail, type:%s.", n,
						type), ex);
			}
			return new ResponseDataVO<List<ChatSessionVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@GET
	@Path("/chatroom/{roomCode}/unread/count")
	public ResponseDataVO<Long> getUnreadMessageCount(
			@Context HttpServletRequest request,
			@PathParam("roomCode") String roomCode) {
		try {
			String operator = super.getCurrentUserId(request);
			UserProfileBean userProfile = (UserProfileBean) chatRepository.get(
					UserProfileBean.class, "code", operator);
			if (userProfile != null) {
				long userId = userProfile.getGraphId();
				long count = chatRepository.getUnreadMessageCount(roomCode,
						userId);
				return new ResponseDataVO<Long>(count);
			} else {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String.format("The user profile(%s) not exist.",
							operator));
				}
				return new ResponseDataVO<Long>(StaticErrorEnum.USER_NOT_EXIST);
			}
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Get unread message's count fail, roomCode: %d.",
						roomCode), ex);
			}
			return new ResponseDataVO<Long>(StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

	@GET
	@Path("/chatroom/{roomId}/unread/messages")
	public ResponseDataVO<List<ChatMessageVO>> getUnreadMessages(
			@Context HttpServletRequest request,
			@PathParam("roomCode") String roomCode) {
		try {
			String operator = super.getCurrentUserId(request);
			UserProfileBean userProfile = (UserProfileBean) chatRepository.get(
					UserProfileBean.class, "code", operator);
			if (userProfile != null) {
				long userId = userProfile.getGraphId();
				List<ChatMessageBean> beans = chatRepository.getUnreadMessages(
						roomCode, userId);
				List<ChatMessageVO> list = new Vector<ChatMessageVO>();
				for (ChatMessageBean bean : beans) {
					if (bean == null) {
						continue;
					}
					list.add(new ChatMessageVO(bean));
				}
				return new ResponseDataVO<List<ChatMessageVO>>(list);
			} else {
				if (LOG.isWarnEnabled()) {
					LOG.warn(String.format("The user profile(%s) not exist.",
							operator));
				}
				return new ResponseDataVO<List<ChatMessageVO>>(
						StaticErrorEnum.USER_NOT_EXIST);
			}
		} catch (Exception ex) {
			if (LOG.isErrorEnabled()) {
				LOG.error(String.format(
						"Get unread message's count fail, roomCode: %d.",
						roomCode), ex);
			}
			return new ResponseDataVO<List<ChatMessageVO>>(
					StaticErrorEnum.FETCH_DB_DATA_FAIL);
		}
	}

}
