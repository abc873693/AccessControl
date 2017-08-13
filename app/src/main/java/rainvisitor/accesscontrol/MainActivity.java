package rainvisitor.accesscontrol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stepstone.stepper.StepperLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import rainvisitor.accesscontrol.adapter.StepperAdapter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.stepperLayout)
    StepperLayout stepperLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        stepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);
        stepperLayout.setAdapter(new StepperAdapter(getSupportFragmentManager(), this));
    }
}
