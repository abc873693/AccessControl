package rainvisitor.accesscontrol.api;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import static rainvisitor.accesscontrol.libs.Constants.API_ROOM_STATUS;
import static rainvisitor.accesscontrol.libs.Constants.API_URL;
import static rainvisitor.accesscontrol.libs.Constants.community;
import static rainvisitor.accesscontrol.libs.Constants.index_building;
import static rainvisitor.accesscontrol.libs.Constants.index_room;

/**
 * Created by Ray on 2017/7/16.
 */

public class RoomStatus {

    public static void check(String building, String room, Callback callback) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        String url = API_URL + API_ROOM_STATUS;
        final FormBody body = new FormBody.Builder()
                .add("en_hname", building)
                .add("en_room_no", room)
                .add("en_nkey", "")
                .build();
        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public static void check(String pin, Callback callback) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        String url = API_URL + API_ROOM_STATUS;
        final FormBody body = new FormBody.Builder()
                .add("en_hname", community.getBuildingList().get(index_building).getTitle())
                .add("en_room_no", community.getBuildingList().get(index_building).getRoomList().get(index_room).getTitle())
                .add("en_nkey", pin)
                .build();
        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
