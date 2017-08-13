package rainvisitor.accesscontrol.api;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import rainvisitor.accesscontrol.libs.SocketClient;

import static rainvisitor.accesscontrol.libs.Constants.API_GET_ROOM;
import static rainvisitor.accesscontrol.libs.Constants.API_URL;
import static rainvisitor.accesscontrol.libs.Constants.SOCKET_IP;
import static rainvisitor.accesscontrol.libs.Constants.SOCKET_PORT;
import static rainvisitor.accesscontrol.libs.Constants.postOpenDoor;

/**
 * Created by Ray on 2017/7/24.
 */

public class Door {
    public static void get(Callback callback) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        String url = API_URL + API_GET_ROOM;
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public static void open() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SocketClient client = new SocketClient(SOCKET_IP, SOCKET_PORT);
                    client.println(postOpenDoor);
                    client.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
