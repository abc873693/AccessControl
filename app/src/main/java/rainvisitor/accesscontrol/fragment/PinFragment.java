package rainvisitor.accesscontrol.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.KeyboardUtils;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rainvisitor.accesscontrol.R;
import rainvisitor.accesscontrol.libs.PinView;
import rainvisitor.accesscontrol.libs.Utils;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pin, container, false);
        //initialize your UI
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
        pinView.clear();
        KeyboardUtils.hideSoftInput(getActivity());
        Utils.showSoftInput(getActivity(),pinView.getEditText(0));
        pinView.setPinViewEventListener(new PinView.PinViewEventListener() {
            @Override
            public void onDataEntered(PinView pinview, boolean fromUser) {
                //Make api calls here or what not
                Toast.makeText(getActivity(), pinview.getValue(), Toast.LENGTH_SHORT).show();
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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "Click onComplete", Toast.LENGTH_SHORT).show();
                callback.complete();
            }
        }, 2000L);
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
}