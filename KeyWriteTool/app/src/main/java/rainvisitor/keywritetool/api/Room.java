package rainvisitor.keywritetool.api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import rainvisitor.keywritetool.libs.SocketClient;
import rainvisitor.keywritetool.models.api.Key;

import static rainvisitor.keywritetool.libs.Constants.SOCKET_IP;
import static rainvisitor.keywritetool.libs.Constants.SOCKET_PORT;
import static rainvisitor.keywritetool.libs.Constants.clearKey;
import static rainvisitor.keywritetool.libs.Constants.keyList;
import static rainvisitor.keywritetool.libs.Constants.setKey;

/**
 * Created by Ray on 2017/7/24.
 */

public class Room {

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

    public static void setKey(final Context context) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("載入中");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
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
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "已送出完成", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "送出失敗", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        }).start();
    }
}
