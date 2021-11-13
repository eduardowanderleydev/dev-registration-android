package br.com.ucsal.devregistration.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.ucsal.devregistration.R;
import br.com.ucsal.devregistration.domain.Resume;

public class ResumeAdapter extends RecyclerView.Adapter<ResumeAdapter.CustomViewHolder> {

    private List<Resume> data;

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resume_list_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Resume resume = data.get(position);
        holder.namePersonResume.setText(resume.getName());
        holder.goalPersonResume.setText(resume.getGoal());
    }

    public void update(List<Resume> resumes) {
        notifyItemRangeRemoved(0,this.data.size());
        this.data.clear();
        this.data.addAll(resumes);
        notifyItemRangeInserted(0,this.data.size());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        private TextView namePersonResume;
        private TextView goalPersonResume;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            namePersonResume = itemView.findViewById(R.id.resume_item_person_name);
            goalPersonResume = itemView.findViewById(R.id.resume_item_person_goal);
        }

        public TextView getNamePersonResume() {
            return namePersonResume;
        }

        public TextView getGoalPersonResume() {
            return goalPersonResume;
        }
    }
}
