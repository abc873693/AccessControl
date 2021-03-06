package rainvisitor.accesscontrol;

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
import rainvisitor.accesscontrol.api.Door;
import rainvisitor.accesscontrol.libs.Constants;
import rainvisitor.accesscontrol.models.Community;

import static rainvisitor.accesscontrol.libs.Constants.community;
import static rainvisitor.accesscontrol.libs.Constants.index_building;

public class SplashActivity extends AppCompatActivity {

    private static int DELAY_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //community = new Gson().fromJson(Constants.buildingFullStr, Community.class);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                CheckStatus();
            }
        }, DELAY_TIME);
    }

    public void CheckStatus() {
        Door.get(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull final Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    String str = response.body().string();

                    @Override
                    public void run() {
                        Constants.community = new Gson().fromJson(str, Community.class);
                        Log.i("onResponse", community.toString());
                        if(community.getBuildingList().size() !=0){
                            Constants.community.getBuildingList().get(index_building).setClicked(true);
                        }
                        Log.e("Community", community.toString());
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
