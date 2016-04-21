package com.yt.rest.resource;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yt.business.bean.ChatRoomBean;
import com.yt.business.bean.ChatRoomBean.ChatRoomType;
import com.yt.business.service.IChatService;
import com.yt.response.ResponseDataVO;
import com.yt.response.ResponseVO;
import com.yt.vo.chat.ChatRoomVO;

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

	/**
	 * 获取聊天室列表及相关信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@GET
	public ResponseDataVO<List<ChatRoomVO>> getChatRooms(
			@Context HttpServletRequest request) throws Exception {
		Long userId = super.getCurrentUserId(request);
		if (userId == null) {
			if (LOG.isErrorEnabled()) {
				LOG.error("The user's id can not pass to server.");
			}
			throw new NullPointerException();
		}
		List<ChatRoomBean> chatRoomBeans = chatService.getChatRooms(userId);
		Vector<ChatRoomVO> vos = new Vector<>();
		for (ChatRoomBean bean : chatRoomBeans) {
			if (bean == null) {
				continue;
			}
			if (bean.getChatRoomType() == ChatRoomType.ROUTE) {
				// 如果是行程聊天室，则放在第一个
				vos.add(0, ChatRoomVO.transform(bean));
			} else {
				vos.add(ChatRoomVO.transform(bean));
			}
		}
		return new ResponseDataVO<List<ChatRoomVO>>(vos);
	}

	/**
	 * 创建一个动态聊天室
	 * 
	 * @param request
	 * @param member
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("create")
	public ResponseDataVO<ChatRoomVO> createDynamicChatRoom(
			@Context HttpServletRequest request,
			@QueryParam("member") String member) throws Exception {
		Long userId = super.getCurrentUserId(request);
		if (userId == null) {
			if (LOG.isErrorEnabled()) {
				LOG.error("The user's id can not pass to server.");
			}
			throw new NullPointerException();
		}
		List<Long> memberIdList = new Vector<Long>();
		String[] ids = member.split(",");
		for (String id : ids) {
			memberIdList.add(Long.valueOf(id));
		}
		ChatRoomBean bean = chatService.createDynamicChatRoom(userId,
				memberIdList);
		return new ResponseDataVO<ChatRoomVO>(ChatRoomVO.transform(bean));
	}

	@GET
	@Path("rest/app/chat/{chatRoomNO}/addMember")
	public ResponseVO addDynamicChatters(@Context HttpServletRequest request,
			@PathParam("chatRoomNO") String chatRoomNO,
			@QueryParam("member") String member) throws Exception {
		Long userId = super.getCurrentUserId(request);
		if (userId == null) {
			if (LOG.isErrorEnabled()) {
				LOG.error("The user's id can not pass to server.");
			}
			throw new NullPointerException();
		}
		List<Long> memberIdList = new Vector<Long>();
		String[] ids = member.split(",");
		for (String id : ids) {
			memberIdList.add(Long.valueOf(id));
		}
		chatService.addDynamicChatters(chatRoomNO, memberIdList, userId);
		return null;
	}

	@GET
	@Path("rest/app/chat/{chatRoomNO}/delMember")
	public ResponseVO delDynamicChatters(@Context HttpServletRequest request,
			@PathParam("chatRoomNO") String chatRoomNO,
			@QueryParam("member") String member) throws Exception {
		Long userId = super.getCurrentUserId(request);
		if (userId == null) {
			if (LOG.isErrorEnabled()) {
				LOG.error("The user's id can not pass to server.");
			}
			throw new NullPointerException();
		}
		List<Long> memberIdList = new Vector<Long>();
		String[] ids = member.split(",");
		for (String id : ids) {
			memberIdList.add(Long.valueOf(id));
		}
		chatService.delDynamicChatters(chatRoomNO, memberIdList, userId);
		return null;
	}
}
