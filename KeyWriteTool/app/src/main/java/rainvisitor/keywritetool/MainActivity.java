package rainvisitor.keywritetool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rainvisitor.keywritetool.adapter.KeyAdapter;
import rainvisitor.keywritetool.api.Room;
import rainvisitor.keywritetool.models.api.Key;

import static rainvisitor.keywritetool.libs.Constants.keyList;

public class MainActivity extends AppCompatActivity {

    KeyAdapter keyAdapter;

    KeyAdapter.OnItemClickListener onItemClickListener;

    @BindView(R.id.recyclerView_key)
    RecyclerView recyclerViewKey;
    @BindView(R.id.button_send_data)
    Button buttonSendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setKeyRecyclerView();
    }

    private void setKeyRecyclerView() {
        onItemClickListener = new KeyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Key item) {

            }
        };
        keyAdapter = new KeyAdapter(MainActivity.this, keyList, onItemClickListener);
        recyclerViewKey.setAdapter(keyAdapter);
        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewKey.setLayoutManager(linearLayoutManager);
        recyclerViewKey.setHasFixedSize(true);
    }

    @OnClick(R.id.button_send_data)
    public void onViewClicked() {
        if (keyList.size() == 0) {
            Toast.makeText(this, "尚無資料可送", Toast.LENGTH_SHORT).show();
        } else {
            Room.setKey(this);
        }
    }
}
