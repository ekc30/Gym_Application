package com.example.gymapplication;


import static com.example.gymapplication.TrainingActivity.TRAINING_KEY;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.webkit.TracingConfig;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class PlanDetailDialog extends DialogFragment {

    // interface for passing data between PlanDetailDialog and TrainingActivity
    public interface PassPlanInterface {
        void getPlan(Plan plan);
    }

    private PassPlanInterface passPlanInterface;

    private Button btnDismiss, btnAdd;
    private TextView txtName;
    private EditText edtTxtMinutes;
    private Spinner spinnerDay;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        // inflate view and set it as view of the alert dialog
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_plan_details, null);
        initViews(view);

        // create alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Enter Details");

        // passing received data to the fragment
        Bundle bundle = getArguments();
        if(bundle != null) {
            // get the training from the bundle
            Training training = bundle.getParcelable(TRAINING_KEY);
            if(training != null) {
                txtName.setText(training.getName());

                // dismiss button
                btnDismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });

                // create new Plan item and add it to plans list based on data given in the dialog
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // collect the data from the dialog and create plan
                        String day = spinnerDay.getSelectedItem().toString();
                        int minutes = Integer.parseInt((edtTxtMinutes.getText().toString()));
                        Plan plan = new Plan(training, minutes, day, false);

                        // pass new plan to TrainingActivity using callback interface
                        try {
                            passPlanInterface = (PassPlanInterface) getActivity();
                            passPlanInterface.getPlan(plan);
                            dismiss();
                        } catch (ClassCastException e) {
                            e.printStackTrace();
                            dismiss();
                        }
                    }
                });
            }
        }

        return builder.create();
    }

    private void initViews(View view) {
        btnDismiss = view.findViewById(R.id.btnDismiss);
        btnAdd = view.findViewById(R.id.btnAdd);
        txtName = view.findViewById(R.id.txtName);
        edtTxtMinutes = view.findViewById(R.id.edtTxtMinutes);
        spinnerDay = view.findViewById(R.id.spinnerDays);
    }
}
