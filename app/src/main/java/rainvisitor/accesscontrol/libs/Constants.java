package rainvisitor.accesscontrol.libs;

import okhttp3.MediaType;
import rainvisitor.accesscontrol.models.Community;

/**
 * Created by Ray on 2017/7/16.
 */

public class Constants {

    public static int index_building = 0;
    public static int index_room = 0;
    public static String CURRENT_STEP_POSITION_KEY = "STEP_POSITION";

    public static Community community;

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");

    public static final String API_URL = "https://holey.cc";
    public static final String API_ROOM_STATUS = "/phpQRCodeDemo/check-key.php";
    public static final String API_GET_ROOM = "/phpQRCodeDemo/get-rooms.php";
    public static final String SOCKET_IP = "192.168.100.180";

    public static final int SOCKET_PORT = 7878;

    public static String postOpenDoor = "{\"Command\" : 15,\"Password\" : \"0000\",\"Set\" : 3}";
}
