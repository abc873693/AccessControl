package rainvisitor.accesscontrol.libs;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import rainvisitor.accesscontrol.models.Community;
import rainvisitor.accesscontrol.models.api.Key;

/**
 * Created by Ray on 2017/7/16.
 */

public class Constants {
    public static final String buildingFullStr = "{\"data\":[{\"title\":\"杉板灣民宿\",\"roomList\":[{\"title\":\"202\"},{\"title\":\"2205\"},{\"title\":\"2206\"},{\"title\":\"305\"},{\"title\":\"205\"}]},{\"title\":\"圖資大樓\",\"roomList\":[{\"title\":\"資001\"},{\"title\":\"資002\"},{\"title\":\"資301\"},{\"title\":\"資501A\"},{\"title\":\"資701\"}]}]}";

    public static int index_building = 0;
    public static int index_room = 0;
    public static String CURRENT_STEP_POSITION_KEY = "STEP_POSITION";

    public static List<Key> keyList = new ArrayList<>();

    public static Community community;

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");

    public static final String API_URL = "https://holey.cc";
    public static final String API_ROOM_STATUS = "/phpQRCodeDemo/check-key.php";
    public static final String API_QR_CODE = "/phpQRCodeDemo/prd-list.php";
    public static final String API_GET_ROOM = "/phpQRCodeDemo/get-rooms.php";
    public static final String SOCKET_IP = "192.168.100.180";

    public static final int SOCKET_PORT = 7878;

    public static String postOpenDoor = "{\"Command\" : 15,\"Password\" : \"0000\",\"Set\" : 3}";
    public static String clearKey = "{\"Command\" : 10,\"Password\" : \"0000\", \"Key\": \"\"}";
    public static String setKey = "{\"Command\" : 8,\"Password\" : \"0000\", \"KeyType\": 2, \"Key\": \"%s\", \"StartTime\": \"%sT00:00:00\",\"EndTime\" : \"%sT23:59:59\"}";

    public static String postQRcode = "";
}
