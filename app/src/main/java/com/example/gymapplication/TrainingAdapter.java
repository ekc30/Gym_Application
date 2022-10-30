package com.example.gymapplication;

import static com.example.gymapplication.TrainingActivity.TRAINING_KEY;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ViewHolder>{

    private static final String TAG = "TrainingAdapter";

    private ArrayList<Training> trainings = new ArrayList<>();
    private Context context;

    // constructor
    public TrainingAdapter(Context context) {
        this.context = context;
    }

    // create an instance of the ViewHolder and return it
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_item, parent, false);
        return new ViewHolder(view);
    }

    // onBindViewHolder is called when a ViewHolder needs to be assigned data
    // (after it has been created or when it's scrolled onto the screen)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        // set name, description and image for each item in the recycler view
        holder.name.setText(trainings.get(position).getName());
        holder.description.setText(trainings.get(position).getShortDesc());
        Glide.with(context)
                .asBitmap()
                .load(trainings.get(position).getImageUrl())
                .into(holder.image);

        // set onClickListener for items of the recycler view - open TrainingActivity
        // when pressed to view details about the specific training
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TrainingActivity.class);
                // pass the training to be displayed
                intent.putExtra(TRAINING_KEY, trainings.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trainings.size();
    }

    public void setTrainings(ArrayList<Training> trainings) {
        this.trainings = trainings;
        notifyDataSetChanged();
    }

    // define the model for the ViewHolders (each item in the recycler view)
    public class ViewHolder extends RecyclerView.ViewHolder {

        // initialise UI elements
        private MaterialCardView parent;
        private ImageView image;
        private TextView name, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.txtName);
            description = itemView.findViewById(R.id.txtDescription);
        }
    }
}
