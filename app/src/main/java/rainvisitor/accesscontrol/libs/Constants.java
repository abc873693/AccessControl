package rainvisitor.accesscontrol.libs;

import okhttp3.MediaType;
import rainvisitor.accesscontrol.models.Community;

/**
 * Created by Ray on 2017/7/16.
 */

public class Constants {
    public static final String buildingFullStr = "{\"data\":[{\"title\":\"育賢樓\",\"roomList\":[{\"title\":\"育101\"},{\"title\":\"育102\"},{\"title\":\"育103\"},{\"title\":\"育104\"},{\"title\":\"育105\"}]},{\"title\":\"圖資大樓\",\"roomList\":[{\"title\":\"資001\"},{\"title\":\"資002\"},{\"title\":\"資301\"},{\"title\":\"資501A\"},{\"title\":\"資701\"}]}]}";

    public static int index_building = 0;
    public static int index_room = 0;
    public static String CURRENT_STEP_POSITION_KEY = "STEP_POSITION";

    public static Community community;


    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");

    public static final String API_URL = "";
    public static final String API_RoomStatus = "";
}
