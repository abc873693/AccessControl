package rainvisitor.accesscontrol.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import rainvisitor.accesscontrol.fragment.BuildingFragment;
import rainvisitor.accesscontrol.fragment.PinFragment;
import rainvisitor.accesscontrol.fragment.RoomFragment;

import static rainvisitor.accesscontrol.libs.Constants.CURRENT_STEP_POSITION_KEY;

/**
 * Created by Ray on 2017/7/16.
 */

public class StepperAdapter extends AbstractFragmentStepAdapter {

    public StepperAdapter(FragmentManager fm, Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        Bundle b = new Bundle();
        switch (position){
            case 0:
                final BuildingFragment step = new BuildingFragment();
                b.putInt(CURRENT_STEP_POSITION_KEY, position);
                step.setArguments(b);
                return step;
            case 1:
                final RoomFragment buildingFragment = new RoomFragment();
                b.putInt(CURRENT_STEP_POSITION_KEY, position);
                buildingFragment.setArguments(b);
                return buildingFragment;
            default:
                final PinFragment pinFragment = new PinFragment();
                b.putInt(CURRENT_STEP_POSITION_KEY, position);
                pinFragment.setArguments(b);
                return pinFragment;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        return new StepViewModel.Builder(context)
                .setTitle("Test") //can be a CharSequence instead
                .create();
    }
}
