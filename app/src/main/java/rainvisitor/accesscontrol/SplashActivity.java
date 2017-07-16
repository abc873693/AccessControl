package rainvisitor.accesscontrol;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

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
        community = new Gson().fromJson(Constants.buildingFullStr, Community.class);
        Constants.community.getBuildingList().get(index_building).setClicked(true);
        Log.e("Community",community.toString());
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                CheckStatus();
            }
        }, DELAY_TIME);
    }

    public void CheckStatus() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
