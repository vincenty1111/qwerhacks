package com.example.qwerhacksapplication;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.qwerhacksapplication.R;

import java.util.ArrayList;




public class PostRecyclerViewAdapter extends RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "PostRecyclerViewAdapter";

    //vars
    private ArrayList<String> mQuestions = new ArrayList<>();
    private ArrayList<String> mAnswers = new ArrayList<>();
    private ArrayList<String> mAnsNum = new ArrayList<>();
    private ArrayList<String> mViews = new ArrayList<>();
    private RecyclerViewClickListener listener;
    private Context mContext;

    public PostRecyclerViewAdapter(Context context, ArrayList<String> questions, ArrayList<String> answers,
                                   ArrayList<String> ansNum, ArrayList<String> views, RecyclerViewClickListener listen) {
        mQuestions = questions;
        mAnswers = answers;
        mContext = context;
        mAnsNum = ansNum;
        mViews = views;
        listener = listen;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.safespace_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.question.setText(mQuestions.get(position));
        holder.answer.setText(mAnswers.get(position));
        holder.numAns.setText(mAnsNum.get(position));
        holder.views.setText(mViews.get(position));
//        holder.id.setText(""+mIds.get(position));

    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView question;
        TextView answer;
        TextView numAns;
        TextView views;
        ConstraintLayout parent;
        TextView id;
        public ViewHolder(View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.post_question);
            answer = itemView.findViewById(R.id.post_answer);
            numAns = itemView.findViewById(R.id.num_answers);
            views = itemView.findViewById(R.id.num_views);
            parent = itemView.findViewById(R.id.parent);
//            id = itemView.findViewById(R.id.explore_bus_id);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }
    public interface RecyclerViewClickListener {
        void onClick(View v, int pos);
    }
}