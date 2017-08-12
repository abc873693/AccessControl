package rainvisitor.accesscontrol.api;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import static rainvisitor.accesscontrol.libs.Constants.API_QR_CODE;
import static rainvisitor.accesscontrol.libs.Constants.API_URL;

/**
 * Created by Ray on 2017/7/25.
 */

public class QRCode {

    public static void get(Callback callback) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        String url = API_URL + API_QR_CODE;
        final FormBody body = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);
    }

}
