package com.example.gymapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class TrainingActivity extends AppCompatActivity implements PlanDetailDialog.PassPlanInterface {
    private static final String TAG = "TrainingActivity";

    public static final String TRAINING_KEY = "training";

    private TextView txtName, txtDescription;
    private Button btnAddToPlan;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        initViews();

        // get the intent containing data to be passed onto this page
        Intent intent = getIntent();
        if(intent != null) {
            Training training = intent.getParcelableExtra(TRAINING_KEY);
            // get the data from the intent and display it
            if(training != null) {
                txtName.setText(training.getName());
                txtDescription.setText(training.getLongDesc());
                Glide.with(this)
                        .asBitmap()
                        .load(training.getImageUrl())
                        .into(image);

                // open dialog for information about training
                btnAddToPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PlanDetailDialog dialog = new PlanDetailDialog();
                        // pass data to the fragment
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(TRAINING_KEY, training);
                        dialog.setArguments(bundle);
                        dialog.show(getSupportFragmentManager(), "plan detail dialog");
                    }
                });
            }
        }
    }

    private void initViews() {
        Log.d(TAG, "initViews: called");
        txtName = findViewById(R.id.txtName);
        txtDescription = findViewById(R.id.txtDescription);
        btnAddToPlan = findViewById(R.id.btnAddToPlan);
        image = findViewById(R.id.trainingImage);
    }

    @Override
    public void getPlan(Plan plan) {
        Log.d(TAG, "getPlan: called");
        if(Utils.addPlan(plan)) {
            Toast.makeText(this, plan.getTraining().getName() + " added to your plan", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PlanActivity.class);
            // clear backstack
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}