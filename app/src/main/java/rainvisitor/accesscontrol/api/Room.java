package rainvisitor.accesscontrol.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import rainvisitor.accesscontrol.libs.SocketClient;

import static rainvisitor.accesscontrol.libs.Constants.SOCKET_IP;
import static rainvisitor.accesscontrol.libs.Constants.MEDIA_TYPE_JSON;
import static rainvisitor.accesscontrol.libs.Constants.SOCKET_PORT;
import static rainvisitor.accesscontrol.libs.Constants.postBody;

/**
 * Created by Ray on 2017/7/24.
 */

public class Room {


    final static RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, postBody);

    public static void open(WebSocketListener webSocketListener) {
        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url(SOCKET_IP)
                .build();
        WebSocket ws = client.newWebSocket(request, webSocketListener);
    }


    public static void open() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SocketClient client = new SocketClient(SOCKET_IP, SOCKET_PORT);
                    client.println(postBody);
                    client.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
