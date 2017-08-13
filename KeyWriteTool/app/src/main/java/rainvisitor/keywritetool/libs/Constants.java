package rainvisitor.keywritetool.libs;

import java.util.ArrayList;
import java.util.List;

import rainvisitor.keywritetool.models.api.Key;

/**
 * Created by Ray on 2017/7/16.
 */

public class Constants {

    public static List<Key> keyList = new ArrayList<>();

    public static final String API_URL = "https://holey.cc";
    public static final String API_QR_CODE = "/phpQRCodeDemo/prd-list.php";
    public static final String SOCKET_IP = "192.168.100.180";

    public static final int SOCKET_PORT = 7878;

    public static String clearKey = "{\"Command\" : 10,\"Password\" : \"0000\", \"Key\": \"\"}";
    public static String setKey = "{\"Command\" : 8,\"Password\" : \"0000\", \"KeyType\": 2, \"Key\": \"%s\", \"StartTime\": \"%sT00:00:00\",\"EndTime\" : \"%sT23:59:59\"}";
}
