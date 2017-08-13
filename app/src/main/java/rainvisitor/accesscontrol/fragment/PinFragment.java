package rainvisitor.accesscontrol.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rainvisitor.accesscontrol.R;
import rainvisitor.accesscontrol.api.Door;
import rainvisitor.accesscontrol.api.RoomStatus;
import rainvisitor.accesscontrol.libs.PinView;
import rainvisitor.accesscontrol.libs.Utils;
import rainvisitor.accesscontrol.models.api.Check_key_response;

/**
 * Created by Ray on 2017/7/16.
 */

public class PinFragment extends Fragment implements BlockingStep {

    View view;
    @BindView(R.id.textView_title)
    TextView textViewTitle;
    @BindView(R.id.pinView)
    PinView pinView;
    Unbinder unbinder;

    Callback callback_check_pin;
    @BindView(R.id.button_clear)
    Button buttonClear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pin, container, false);
        //initialize your UI
        unbinder = ButterKnife.bind(this, view);
        callback_check_pin = new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull final Response response) throws IOException {
                getActivity().runOnUiThread(new Runnable() {
                    String str = response.body().string();

                    @Override
                    public void run() {
                        Check_key_response check_key_response = new Gson().fromJson(str, Check_key_response.class);
                        //Log.i("onResponse", chat_response.toString());
                        if (check_key_response.getStatus().equals("success")) {
                            openDoor();
                        } else {
                            if (check_key_response.getMessage().equals("Error numerical password")) {
                                Toast.makeText(getActivity(), "密碼錯誤", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), check_key_response.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        };
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
        Toast.makeText(getActivity(), "請輸入密碼", Toast.LENGTH_SHORT).show();
        pinView.clear();
        KeyboardUtils.hideSoftInput(getActivity());
        Utils.showSoftInput(getActivity(), pinView.getEditText(0));
        pinView.setPinViewEventListener(new PinView.PinViewEventListener() {
            @Override
            public void onDataEntered(PinView pinview, boolean fromUser) {
                //Make api calls here or what not
                //Toast.makeText(getActivity(), pinview.getValue(), Toast.LENGTH_SHORT).show();
                RoomStatus.check(pinview.getValue(), callback_check_pin);
            }
        });
    }

    @Override
    public void onError(@NonNull VerificationError error) {
        //handle error inside of the fragment, e.g. show error on EditText
    }

    @Override
    @UiThread
    public void onNextClicked(final StepperLayout.OnNextClickedCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "Click onNext", Toast.LENGTH_SHORT).show();
                callback.goToNextStep();
            }
        }, 2000L);
    }

    @Override
    @UiThread
    public void onCompleteClicked(final StepperLayout.OnCompleteClickedCallback callback) {
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "Click onComplete", Toast.LENGTH_SHORT).show();
                callback.complete();
            }
        }, 2000L);*/
        if (pinView.getValue().length() == 4) {
            RoomStatus.check(pinView.getValue(), callback_check_pin);
        } else {
            Toast.makeText(getActivity(), "長度不足", Toast.LENGTH_SHORT).show();
        }
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

    @OnClick(R.id.button_clear)
    public void onViewClicked() {
        pinView.clear();
    }


    private void openDoor() {
        Toast.makeText(getActivity(), "正在開門", Toast.LENGTH_SHORT).show();
        Door.open();
    }
}