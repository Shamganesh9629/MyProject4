package com.example.myproject.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.R;
import com.example.myproject.RoomDataBase.Registration;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.Viewholder> {
    List<Registration> registrationList;
    Context context;

    public RecycleAdapter(Context context, List<Registration> registrations) {
        this.context = context;
        this.registrationList = registrations;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adpter, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Registration registration = registrationList.get(position);
        holder.name.setText(registration.getName());
        holder.gender.setText(registration.getGender());
        holder.moblile.setText(registration.getMobileNumber());
        holder.email.setText(registration.geteMail());
    }

    @Override
    public int getItemCount() {
        return registrationList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView name, gender, moblile, email;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameText2);
            gender = itemView.findViewById(R.id.genderText2);
            moblile = itemView.findViewById(R.id.mobileNumberText2);
            email = itemView.findViewById(R.id.emailText2);
        }
    }
}
