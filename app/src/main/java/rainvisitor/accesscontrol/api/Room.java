package rainvisitor.accesscontrol.api;

import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import rainvisitor.accesscontrol.libs.SocketClient;
import rainvisitor.accesscontrol.models.api.Key;

import static rainvisitor.accesscontrol.libs.Constants.API_GET_ROOM;
import static rainvisitor.accesscontrol.libs.Constants.API_URL;
import static rainvisitor.accesscontrol.libs.Constants.SOCKET_IP;
import static rainvisitor.accesscontrol.libs.Constants.SOCKET_PORT;
import static rainvisitor.accesscontrol.libs.Constants.clearKey;
import static rainvisitor.accesscontrol.libs.Constants.keyList;
import static rainvisitor.accesscontrol.libs.Constants.postOpenDoor;
import static rainvisitor.accesscontrol.libs.Constants.setKey;

/**
 * Created by Ray on 2017/7/24.
 */

public class Room {
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

    public static void clear() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SocketClient client = new SocketClient(SOCKET_IP, SOCKET_PORT);
                    client.println(clearKey);
                    client.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void setKey() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SocketClient client = new SocketClient(SOCKET_IP, SOCKET_PORT);
                    client.println(clearKey);
                    client.close();
                    for (Key key : keyList) {
                        Thread.sleep(500);
                        client = new SocketClient(SOCKET_IP, SOCKET_PORT);
                        client.println(String.format(setKey, key.getKey(), key.getStartTime(), key.getEndTime()));
                        client.close();
                        Log.e("postBody", String.format(setKey, key.getKey(), key.getStartTime(), key.getEndTime()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
