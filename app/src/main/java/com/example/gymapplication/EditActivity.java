package com.example.gymapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity implements PlanAdapter.RemovePlan {
    private static final String TAG = "EditActivity";

    private TextView txtDay;
    private RecyclerView recyclerView;
    private Button btnAddPlan;

    private PlanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        initViews();

        // set adapter
        adapter = new PlanAdapter(this);
        adapter.setType("edit");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Intent intent = getIntent();
        if(intent != null) {
            String day = intent.getStringExtra("day");
            if(day != null) {
                txtDay.setText(day);
                ArrayList<Plan> plans = Utils.getPlansByDay(day);
                adapter.setPlans(plans);
            }
        }

        btnAddPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addPlanIntent = new Intent(EditActivity.this, AllTrainingsActivity.class);
                startActivity(addPlanIntent);
            }
        });
    }

    @Override
    public void onRemovePlanResult(Plan plan) {
        if(Utils.removePlan(plan)) {
            Toast.makeText(this, "Training removed from your plan", Toast.LENGTH_SHORT).show();
            adapter.setPlans(Utils.getPlansByDay(plan.getDay()));
        }
    }

    // initialise all the components
    private void initViews() {
        Log.d(TAG, "initViews: called");
        txtDay = findViewById(R.id.txtDay);
        recyclerView = findViewById(R.id.recyclerView);
        btnAddPlan = findViewById(R.id.btnAddPlan);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PlanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}