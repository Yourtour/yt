package com.yt.vo;

import com.yt.business.bean.CommentBean;
import com.yt.business.bean.NewsBean;
import com.yt.core.utils.DateUtils;
import com.yt.vo.member.UserVO;

public class ConsultVO extends BaseVO{
	public ConsultVO() {
		super();
	}

	public static ConsultVO transform(NewsBean bean){
		ConsultVO vo = new ConsultVO();

		return vo;
	}
}
