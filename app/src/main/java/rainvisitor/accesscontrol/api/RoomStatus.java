package rainvisitor.accesscontrol.api;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import static rainvisitor.accesscontrol.libs.Constants.API_RoomStatus;
import static rainvisitor.accesscontrol.libs.Constants.API_URL;
import static rainvisitor.accesscontrol.libs.Constants.MEDIA_TYPE_JSON;

/**
 * Created by Ray on 2017/7/16.
 *
 */

public class RoomStatus {

    public static void check(Callback callback){
        OkHttpClient mOkHttpClient = new OkHttpClient();
        String url = API_URL + API_RoomStatus;
        String postBody = "'" + "" + "'";
        final RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, postBody);
        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
