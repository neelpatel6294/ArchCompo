package com.sample.archcompo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sample.archcompo.R;
import com.sample.archcompo.model.Student;

import java.util.List;

public class StudentAdapter extends PagedListAdapter<Student,StudentAdapter.ViewHolder> {

    private final LayoutInflater mInflater;
    private List<Student> mStudents;
    final private ListItemClickListener mOnClickListener;


    public StudentAdapter(Context context, ListItemClickListener listener) {
        super(DIFF_CALLBACK);
        mInflater = LayoutInflater.from(context);
        mOnClickListener = listener;
    }
//    public StudentAdapter(Context context) {
//        super(DIFF_CALLBACK);
//        mInflater = LayoutInflater.from(context);
//    }


    public interface ListItemClickListener {
        void onListItemClick(Student clickedItemIndex);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycle_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mStudents != null) {
            Student student = mStudents.get(position);
            holder.mName.setText(student.getName());
            holder.mEmail.setText(student.getEmail());
            holder.mCountry.setText(student.getCountry());
        }
    }

    public Student getStudentAtPosition(int position) {
        return mStudents.get(position);
    }

    @Override
    public int getItemCount() {
        if (mStudents != null) {
            return mStudents.size();
        } else
            return 0;
    }

    public void setStudents(List<Student> students) {
        mStudents = students;
        notifyDataSetChanged();
    }

    private static final DiffUtil.ItemCallback<Student> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Student>() {
                @Override
                public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                    return oldItem.getStudentId() == newItem.getStudentId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                    return oldItem.getStudentId() == newItem.getStudentId() && oldItem.getName().equalsIgnoreCase(newItem.getName());
                }
            };


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mName;
        private TextView mEmail;
        private TextView mCountry;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mName = itemView.findViewById(R.id.text_Name);
            this.mEmail = itemView.findViewById(R.id.text_email);
            this.mCountry = itemView.findViewById(R.id.text_country);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Student student = getStudentAtPosition(position);
            mOnClickListener.onListItemClick(student);
        }
    }



}
