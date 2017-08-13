package rainvisitor.keywritetool;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rainvisitor.keywritetool.api.QRCode;
import rainvisitor.keywritetool.libs.Constants;
import rainvisitor.keywritetool.models.api.prd_response;

public class SplashActivity extends AppCompatActivity {

    private static int DELAY_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                getQRCode();
            }
        }, DELAY_TIME);
    }

    private void getQRCode(){
        QRCode.get(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull final Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    String str = response.body().string();

                    @Override
                    public void run() {
                        prd_response prd_response = new Gson().fromJson(str, prd_response.class);
                        Constants.keyList = prd_response.getKeyList();
                        Log.i("onResponse", prd_response.toString());
                        openMain();
                    }
                });
            }
        });
    }

    private void openMain() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
