package br.com.ucsal.devregistration.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.ucsal.devregistration.R;
import br.com.ucsal.devregistration.domain.Resume;

public class ResumeAdapter extends BaseAdapter {

    private List<Resume> data;
    private Context context;

    public ResumeAdapter(Context context, List<Resume> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Resume getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.resume_list_item, viewGroup, false);
        }
        TextView namePersonResume = view.findViewById(R.id.resume_item_person_name);
        TextView goalPersonResume = view.findViewById(R.id.resume_item_person_goal);

        Resume resume = data.get(position);

        namePersonResume.setText(resume.getName());
        goalPersonResume.setText(resume.getGoal());

        return view;
    }

    public void remove(Resume resume){
        data.remove(resume);
        update();
    }

    public void update(){
        notifyDataSetChanged();
    }

}
