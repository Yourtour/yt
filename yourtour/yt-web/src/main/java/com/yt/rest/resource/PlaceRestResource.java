package com.yt.rest.resource;

import com.yt.business.bean.PlaceBean;
import com.yt.business.service.IPlaceService;
import com.yt.core.utils.CollectionUtils;
import com.yt.response.ResponseDataVO;
import com.yt.vo.place.PlaceVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Component
@Path("app/places")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlaceRestResource {
    private static final Log LOG = LogFactory.getLog(PlaceRestResource.class);

    @Autowired
    private IPlaceService placeService;

    /**
     * 获取目的地首页数据
     *
     * @return
     */
    @GET
    public ResponseDataVO<List<PlaceVO>> getPlaces() throws Exception {
        List<PlaceVO> list = new ArrayList<PlaceVO>();
        List<PlaceBean> places = new ArrayList<>(); //placeService.getAllRootPlaces();

        String[] names = new String[]{"重庆", "上海", "北京"};
        String[] images = new String[]{"resources/images/chongqing.jpg", "resources/images/shanghai.jpg", "resources/images/beijing.jpg"};
        String[] memos = new String[]{"重庆，它是一座山城，因建立在重重山峦之上，起伏的山势和依山而建的楼房让地图在这里显得用处不大，因为地图是平面的，而重庆则是立体的。",
                                      "上海，又称“上海滩”，是一座极具现代化而又不失中国传统特色的国际大都市。由于其地处中国漫长海岸线的最正中，是中国四个中央直辖市之一，是中国的历史文化名城。",
                                      "北京，中国首都。每个人心中，都有一个“我爱北京天安门”的北京情结，都曾梦想着生活在传说中的紫禁城；梦想着穿梭在王朔笔下的胡同和大院；也梦想着爬上万里长城，大喊：我是好汉！在每个人心中，都一个人属于自己的北京。 "};
        for (int index = 0; index < 3; index++) {
            PlaceBean place = new PlaceBean();
            place.setId((long) index);
            place.setImageUrl(images[index]);
            place.setName(names[index]);
            place.setMemo(memos[index]);

            places.add(place);
        }

        for (PlaceBean place : places) {
            list.add(PlaceVO.transform(place));
        }

        return new ResponseDataVO<List<PlaceVO>>(list);
    }


    /**
     * 获取和指定目的地相关的目的地
     *
     * @return
     */
    @GET
    @Path("/relative/{placeId}/query")
    public ResponseDataVO<List<PlaceVO>> getRelatedPlaces(@PathParam("placeId") Long placeId) throws Exception {
        List<PlaceVO> list = new ArrayList<PlaceVO>();
        List<PlaceBean> places = null; //
        /* placeService.getRelatedPlaces(placeId);
        for (PlaceBean place : places) {
            list.add(PlaceVO.transform(place));
        }*/

        return new ResponseDataVO<List<PlaceVO>>(list);
    }

    /**
     * 获取目的地信息
     *
     * @param placeId
     * @return
     */
    @GET
    @Path("/{placeId}")
    public ResponseDataVO<PlaceVO> queryMainInfo(@PathParam("placeId") Long placeId) {
        PlaceVO vo = null;

        return new ResponseDataVO<PlaceVO>(vo);
    }
}
