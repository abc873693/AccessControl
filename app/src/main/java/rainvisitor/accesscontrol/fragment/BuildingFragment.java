package rainvisitor.accesscontrol.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rainvisitor.accesscontrol.R;
import rainvisitor.accesscontrol.adapter.BuildingAdapter;
import rainvisitor.accesscontrol.libs.Constants;
import rainvisitor.accesscontrol.models.Building;

/**
 * Created by Ray on 2017/7/15.
 */

public class BuildingFragment extends Fragment implements BlockingStep {

    View view;
    @BindView(R.id.recyclerView_building)
    RecyclerView recyclerViewBuilding;

    BuildingAdapter buildingAdapter;

    BuildingAdapter.OnItemClickListener onItemClickListener;

    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_building, container, false);
        unbinder = ButterKnife.bind(this, view);
        //initialize your UI
        setBuildingRecyclerView();
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

    }

    @Override
    public void onError(@NonNull VerificationError error) {
        //handle error inside of the fragment, e.g. show error on EditText
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        if (Constants.community.getBuildingList().size() != 0) {
            callback.goToNextStep();
        }else {
            Toast.makeText(getActivity(),"無選擇",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {

    }

    private void setBuildingRecyclerView() {
        onItemClickListener = new BuildingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Building item) {
                int target = -1;
                for (int i = 0; i < Constants.community.getBuildingList().size(); i++) {
                    Constants.community.getBuildingList().get(i).setClicked(false);
                    if (Constants.community.getBuildingList().get(i).equals(item)) target = i;
                }
                Constants.community.getBuildingList().get(target).setClicked(true);
                Constants.index_building = target;
                buildingAdapter.notifyDataSetChanged();
            }
        };
        buildingAdapter = new BuildingAdapter(getActivity(), Constants.community.getBuildingList(), onItemClickListener);
        recyclerViewBuilding.setAdapter(buildingAdapter);
        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewBuilding.setLayoutManager(linearLayoutManager);
        recyclerViewBuilding.setHasFixedSize(true);
    }
}
