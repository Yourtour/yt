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
        String[] memos = new String[]{"重庆，它是一座山城，因建立在重重山峦之上，起伏的山势和依山而建的楼房让地图在这里显得用处不大，因为地图是平面的，而重庆则是立体的。重庆也被人叫做“雾都”，需要拨开层层迷雾才能见到它的真身，长江和嘉陵江的交汇处，云天与高楼相接，薄雾弥散，宛如仙境一般。它还有一个名字叫“火炉”，和这里的人这里的美食一样，火辣辣的直接抵达人心，直来直去从不绕弯子,端的是豪爽二字。 小吃、美女、火锅、通通都是重庆的标签，但都不能单独拿出来代表它，重庆的魅力是需要自己去慢慢探索的。历史的偶然性和必然性在这里得到重合，让它既充满了江湖味又时时被现代化文明充斥着。 每当暮色降临，万家灯火璀璨，流光溢彩，难怪重庆素有“小香港”之称。它的夜景可是公认比上海和香港的还要漂亮。",
                                      "上海，又称“上海滩”，是一座极具现代化而又不失中国传统特色的国际大都市。由于其地处中国漫长海岸线的最正中，是中国四个中央直辖市之一，是中国的历史文化名城。百余年来，上海一直是中国商业的中心、财富的汇聚地，是中国仅次于香港的著名“购物乐园”，更是和世界联系最紧密的那根纽带。 上海也是一个新兴的旅游目的地，由于它深厚的文化底蕴和众多的历史古迹，如上海的地标——浦西的外滩和新天地。位于浦东的东方明珠广播电视塔与金茂大厦却呈现出另一番繁华景象，它们与上海环球金融中心等建筑共同组成了全球最壮丽的天际线之一，而2014年将建成的上海中心，更会为“东方巴黎”添上灿烂的一笔。此外，上海迪斯尼乐园也将于2015年开张。 昔日的上海烙印着 “十里洋场”的繁华，讲述着旧上海滩的浮华旧梦。今日的上海，则以浦东开发开放为代表，日新月异的都市面貌使上海成为展示中国经济发展和改革开放新成就的窗口，并于2010年成功举办了第41届世界博览会。",
                                      "北京，中国首都。每个人心中，都有一个“我爱北京天安门”的北京情结，都曾梦想着生活在传说中的紫禁城；梦想着穿梭在王朔笔下的胡同和大院；也梦想着爬上万里长城，大喊：我是好汉！在每个人心中，都一个人属于自己的北京。 北京是一座包容万象、海纳百川的城市。三千年的历史，六朝故都，这里荟萃了自元明清以来的中华文化，荟萃了众多名胜古迹和人文景观。这里汇聚了八方来客，宗教、文化、语言在这里融合，兼容并蓄。如果想准确的描绘出北京的模样，无异于痴人说梦，北京在红宫墙外的宁静小路上，在胡同儿的转弯拐角儿，在国贸的匆匆路旁，也在未名湖边的石砖小径。只有用心去感受，去聆听，才能听到北京的内心独白。 在我的心里，北京表面上它是现代大都会，但是内心却有抹不去的古朴和怀旧。闲庭信步在逐渐少去的胡同里，走进那热气腾腾的涮肉店，那才是真正的北京。 有许多著名的学府如清华、北大等也汇聚于此，可以漫步校园中想象朱自清、胡适等昔日大师在校任教时的情景。 还有许多闻名遐迩的自然景观，如红叶迷人的香山公园、竹林遍地的紫竹院、环境幽雅的玉渊潭……"};
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
     * 获取指定目的地下属目的地
     *
     * @param parentCode
     * @return
     */
    @GET
    @Path("/parent/{parentCode}/query")
    public ResponseDataVO<List<PlaceVO>> getPlaces(@PathParam("parentCode") String parentCode) throws Exception {
        List<PlaceVO> list = new ArrayList<PlaceVO>();
        List<PlaceBean> places = placeService.getPlaces(parentCode);
        List<PlaceBean> subPlaces = null;
        for (PlaceBean place : places) {
            list.add(PlaceVO.transform(place));

            subPlaces = place.getSubs();
            if (CollectionUtils.isNotEmpty(subPlaces)) {
                for (PlaceBean subPlace : subPlaces) {
                    list.add(PlaceVO.transform(subPlace));
                }
            }
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
        List<PlaceBean> places = placeService.getRelatedPlaces(placeId);
        for (PlaceBean place : places) {
            list.add(PlaceVO.transform(place));
        }

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
