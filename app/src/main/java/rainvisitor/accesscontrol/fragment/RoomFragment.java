package rainvisitor.accesscontrol.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.KeyboardUtils;
import com.google.gson.Gson;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rainvisitor.accesscontrol.MainActivity;
import rainvisitor.accesscontrol.R;
import rainvisitor.accesscontrol.adapter.RoomAdapter;
import rainvisitor.accesscontrol.api.RoomStatus;
import rainvisitor.accesscontrol.models.Room;
import rainvisitor.accesscontrol.models.api.Check_key_response;

import static rainvisitor.accesscontrol.libs.Constants.community;
import static rainvisitor.accesscontrol.libs.Constants.index_building;
import static rainvisitor.accesscontrol.libs.Constants.index_room;

/**
 * Created by Ray on 2017/7/16.
 */

public class RoomFragment extends Fragment implements BlockingStep {

    View view;
    @BindView(R.id.textView_title)
    TextView textViewTitle;
    @BindView(R.id.recyclerView_room)
    RecyclerView recyclerViewRoom;

    RoomAdapter roomAdapter;

    RoomAdapter.OnItemClickListener onItemClickListener;

    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_room, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public VerificationError verifyStep() {
        //return null if the user can go to the next step, create a new VerificationError instance otherwise
        return null;
    }

    @Override
    public void onSelected() {
        //update UI when selected
        KeyboardUtils.hideSoftInput(getActivity());
        textViewTitle.setText(String.format("%s %s", community.getBuildingList().get(index_building).getTitle(), getString(R.string.chose_room)));
        setRoomRecyclerView();
    }

    @Override
    public void onError(@NonNull VerificationError error) {
        //handle error inside of the fragment, e.g. show error on EditText
    }

    @Override
    @UiThread
    public void onNextClicked(final StepperLayout.OnNextClickedCallback callback) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("載入中");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
        RoomStatus.check(
                community.getBuildingList().get(index_building).getTitle(),
                community.getBuildingList().get(index_building).getRoomList().get(index_room).getTitle(),
                new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull final Response response) throws IOException {
                        getActivity().runOnUiThread(new Runnable() {
                            String str = response.body().string();

                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                Check_key_response check_key_response = new Gson().fromJson(str, Check_key_response.class);
                                //Log.i("onResponse", check_key_response.toString());
                                if (check_key_response.getMessage().contains("啟用中")) {
                                    callback.goToNextStep();
                                } else if (check_key_response.getMessage().contains("enable")) {
                                    callback.goToNextStep();
                                } else {
                                    Toast.makeText(getActivity(), check_key_response.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
    }

    @Override
    @UiThread
    public void onCompleteClicked(final StepperLayout.OnCompleteClickedCallback callback) {
        callback.complete();
    }

    @Override
    @UiThread
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setRoomRecyclerView() {
        onItemClickListener = new RoomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Room item) {
                setContextRoom(item);
                ((MainActivity) getActivity()).getStepperLayout().setCurrentStepPosition(2);
            }
        };
        roomAdapter = new RoomAdapter(getActivity(), community.getBuildingList().get(index_building).getRoomList(), onItemClickListener);
        recyclerViewRoom.setAdapter(roomAdapter);
        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewRoom.setLayoutManager(linearLayoutManager);
        recyclerViewRoom.setHasFixedSize(true);
        setContextRoom(community.getBuildingList().get(index_building).getRoomList().get(index_room));
    }

    private void setContextRoom(Room item) {
        int target = -1;
        for (int i = 0; i < community.getBuildingList().get(index_building).getRoomList().size(); i++) {
            community.getBuildingList().get(index_building).getRoomList().get(i).setClicked(false);
            if (community.getBuildingList().get(index_building).getRoomList().get(i).equals(item))
                target = i;
        }
        community.getBuildingList().get(index_building).getRoomList().get(target).setClicked(true);
        index_room = target;
        roomAdapter.notifyDataSetChanged();
    }
}